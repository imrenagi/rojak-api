package id.rojak.auth.application.representation;

import id.rojak.auth.AuthApplication;
import id.rojak.auth.application.command.AddUserToGroupCommand;
import id.rojak.auth.application.command.CreateGroupCommand;
import id.rojak.auth.application.command.RegisterUserCommand;
import id.rojak.auth.application.command.RemoveUserFromGroupCommand;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.access.RoleRepository;
import id.rojak.auth.domain.model.identity.*;
import id.rojak.auth.infrastructure.service.BcryptEncryptionService;
import id.rojak.auth.infrastructure.service.EncryptionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthApplication.class)
@WebAppConfiguration
public class IdentityApplicationServiceTest {

    @InjectMocks
    private IdentityApplicationService identityApplicationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private GroupMemberRepository groupMemberRepository;

    private EncryptionService encryptionService = new BcryptEncryptionService();

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldBeAbleToCreateNewUser() {
        RegisterUserCommand command = new RegisterUserCommand(
                "imrenagi",
                "Password01",
                "Imre",
                "Nagi",
                true,
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
                "imre.nagi2812@gmail.com",
                "021-123-1234",
                "021-123-1234",
                "Jl. ABS",
                "Jakarta selatan",
                "DKI Jakarta",
                "25134",
                "Indonesia");

        User userStub = this.userStub();

        doReturn(null).when(this.userRepository).findByUsername("imrenagi");
        doReturn(userStub).when(this.userRepository).save(userStub);

        User user = this.identityApplicationService
                .newUser(command);

        Assert.assertEquals(command.getUsername(), user.username());
//        Assert.assertTrue(encryptionService.matches(command.getPassword(), user.password()));
        Assert.assertEquals(command.isEnabled(), user.isEnabled());
        Assert.assertEquals(command.getFirstName() + " " + command.getLastName(),
                user.person().name().asFormattedName());

        verify(userRepository, times(1)).findByUsername("imrenagi");
        verify(userRepository, times(1)).save(userStub);
    }

    @Test
    public void shouldBeAbleToCreateNewGroup() {

        CreateGroupCommand command =
                new CreateGroupCommand("Administrator",
                        "Group for administrator",
                        false,
                        "ROLE_ADMIN");

        Role role = new Role("Admin", "Role for admin");

        doReturn(UUID.randomUUID().toString()).when(this.groupRepository).nextId();

        Group group = new Group(
                new GroupId(this.groupRepository.nextId()),
                command.getName(),
                command.getDescription(),
                command.isSupportNesting(),
                role);

        doReturn(role).when(this.roleRepository).findByName("ROLE_ADMIN");
        doReturn(null).when(this.groupRepository).findByName(command.getName());
        doReturn(group).when(this.groupRepository).save(group);

        Group savedGroup = this.identityApplicationService
                .newGroup(command);

        Assert.assertEquals(command.getName(), savedGroup.name());
        Assert.assertEquals(command.getDescription(), savedGroup.description());
        Assert.assertEquals(command.isSupportNesting(), savedGroup.isSupportNesting());
        Assert.assertEquals(command.getRole(), savedGroup.role().name());

        verify(this.roleRepository, times(1)).findByName("ROLE_ADMIN");
        verify(this.groupRepository, times(1)).findByName("Administrator");
        verify(this.groupRepository, times(1)).save(any(Group.class));


    }

    @Test
    public void shouldThrowExceptionIfGroupDoesExist() {
        try {
            CreateGroupCommand command =
                    new CreateGroupCommand("Administrator",
                            "Group for administrator",
                            false,
                            "ROLE_ADMIN");

            Role role = new Role("Admin", "Role for admin");

            doReturn(UUID.randomUUID().toString()).when(this.groupRepository).nextId();

            Group group = new Group(
                    new GroupId(this.groupRepository.nextId()),
                    command.getName(),
                    command.getDescription(),
                    command.isSupportNesting(),
                    role);

            doReturn(role).when(this.roleRepository).findByName("ROLE_ADMIN");
            doReturn(any(Group.class)).when(this.groupRepository).findByName(command.getName());
            doReturn(group).when(this.groupRepository).save(group);

            Group savedGroup = this.identityApplicationService
                    .newGroup(command);

            fail();
        } catch (Exception e) {}
    }

    @Test
    public void shouldBeAbleToAddUserToGroup() {

        Role role = new Role("Admin", "Role for admin");

        String groupId = UUID.randomUUID().toString();

        User user1 = this.userStub("imrenagi");
        User user2 = this.userStub("imrenagi2");
        String[] users = {user1.username(), user2.username()};

        Group group = new Group(
                new GroupId(groupId),
                "Administrator",
                "Group for administrator",
                false,
                role);

        Assert.assertEquals(0, group.groupMembers().size());

        AddUserToGroupCommand command =
                new AddUserToGroupCommand(groupId,
                        Arrays.asList(users));

        doReturn(group).when(this.groupRepository).findByGroupId(new GroupId(groupId));
        doReturn(user1).when(this.userRepository).findByUsername("imrenagi");
        doReturn(user2).when(this.userRepository).findByUsername("imrenagi2");

        this.identityApplicationService
                .addUserToGroup(command);

//        Assert.assertEquals(2, group.groupMembers().size());

        verify(this.groupRepository, times(1)).findByGroupId(new GroupId(groupId));
        verify(this.userRepository, times(2)).findByUsername(anyString());

    }

    @Test
    public void shouldThrowErrorIfGroupDoesntExistWhenAddUser() {

        try {
            Role role = new Role("Admin", "Role for admin");

            String groupId = UUID.randomUUID().toString();

            User user1 = this.userStub("imrenagi");
            User user2 = this.userStub("imrenagi2");
            String[] users = {user1.username(), user2.username()};

            Group group = new Group(
                    new GroupId(groupId),
                    "Administrator",
                    "Group for administrator",
                    false,
                    role);

            Assert.assertEquals(0, group.groupMembers().size());

            AddUserToGroupCommand command =
                    new AddUserToGroupCommand(groupId,
                            Arrays.asList(users));

            doReturn(null).when(this.groupRepository).findByGroupId(new GroupId(groupId));
            doReturn(user1).when(this.userRepository).findByUsername("imrenagi");
            doReturn(user2).when(this.userRepository).findByUsername("imrenagi2");

            this.identityApplicationService
                    .addUserToGroup(command);

//        Assert.assertEquals(2, group.groupMembers().size());

            verify(this.groupRepository, times(1)).findByGroupId(new GroupId(groupId));
            verify(this.userRepository, times(0)).findByUsername(anyString());

            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void shouldBeAbleToRemoveUser() {

        Role role = new Role("Admin", "Role for admin");

        String groupId = UUID.randomUUID().toString();

        User user1 = this.userStub("imrenagi");
        User user2 = this.userStub("imrenagi2");
        String[] users = {user1.username(), user2.username()};

        Group group = new Group(
                new GroupId(groupId),
                "Administrator",
                "Group for administrator",
                false,
                role);

        Assert.assertEquals(0, group.groupMembers().size());

        RemoveUserFromGroupCommand command =
                new RemoveUserFromGroupCommand(Arrays.asList(users),
                        groupId);

        doReturn(group).when(this.groupRepository).findByGroupId(new GroupId(groupId));

        this.identityApplicationService
                .removeUserFromGroup(command);

        verify(this.groupRepository, times(1)).findByGroupId(new GroupId(groupId));
        verify(this.userRepository, times(2)).findByUsername(anyString());

    }

    @Test
    public void shouldThrowExceptionIfGroupDoesntExistWhenRemoveUser() {
        try {
            Role role = new Role("Admin", "Role for admin");

            String groupId = UUID.randomUUID().toString();

            User user1 = this.userStub("imrenagi");
            User user2 = this.userStub("imrenagi2");
            String[] users = {user1.username(), user2.username()};

            Group group = new Group(
                    new GroupId(groupId),
                    "Administrator",
                    "Group for administrator",
                    false,
                    role);

            Assert.assertEquals(0, group.groupMembers().size());

            RemoveUserFromGroupCommand command =
                    new RemoveUserFromGroupCommand(Arrays.asList(users),
                            groupId);

            doReturn(null).when(this.groupRepository).findByGroupId(new GroupId(groupId));

            this.identityApplicationService
                    .removeUserFromGroup(command);

            verify(this.groupRepository, times(1)).findByGroupId(new GroupId(groupId));
            verify(this.userRepository, times(0)).findByUsername(anyString());

            fail();
        } catch (Exception e) {}
    }

    private User userStub(String username) {
        return new User(
                username,
                encryptionService.encryptedValue("Password01"),
                new Enablement(true,
                        new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                        new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime()),
                new Person(
                        new FullName("Imre", "Nagi"),
                        new ContactInformation(
                                new EmailAddress("imre.nagi2812@gmail.com"),
                                new PostalAddress(
                                        "Jl. ABS",
                                        "Jakarta Selatan",
                                        "DKI Jakarta",
                                        "25134",
                                        "Indonesia"
                                ),
                                new Telephone("021-123-1234"),
                                new Telephone("021-123-1234"))));
    }

    private User userStub() {
        return this.userStub("imrenagi");
    }

}