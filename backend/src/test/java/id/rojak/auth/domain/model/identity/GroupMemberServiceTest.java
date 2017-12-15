package id.rojak.auth.domain.model.identity;

import id.rojak.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class GroupMemberServiceTest {

    @InjectMocks
    private GroupMemberService groupMemberService;

    @Mock
    private GroupMemberRepository groupMemberRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldConfirmUser() {
        Group group = new Group("admin", "for admin");
        User user1 = this.userStub("imrenagi");
        group.addUser(user1);

        doReturn(user1).when(userRepository).findByUsername("imrenagi");

        Assert.assertTrue(groupMemberService.confirmUser(group, user1));

        verify(userRepository, times(1)).findByUsername("imrenagi");
    }

    @Test
    public void shouldConfirmInvalidUserIfUserIsntEnabled() {
        Group group = new Group("admin", "for admin");
        User user1 = this.userStub("imrenagi");
        group.addUser(user1);
        user1.disable();
        doReturn(user1).when(userRepository).findByUsername("imrenagi");

        Assert.assertFalse(groupMemberService.confirmUser(group, user1));

        verify(userRepository, times(1)).findByUsername("imrenagi");
    }

    @Test
    public void shouldReturnUserGroup() {
        List<GroupMember> gmMock = new ArrayList<>();

        User user = userStub("imrenagi");

        Group adminGroup = new Group("admin", "admin group");
        Group betaTesterGroup = new Group("beta tester", "beta tester");

        GroupMember asAdmin = user.toGroupMember();
        asAdmin.setGroup(adminGroup);

        GroupMember asBetaTester = user.toGroupMember();
        asBetaTester.setGroup(betaTesterGroup);

        gmMock.add(asAdmin);
        gmMock.add(asBetaTester);

        doReturn(gmMock).when(this.groupMemberRepository).findByName("imrenagi");

        List<Group> result = this.groupMemberService.groupOf(user);

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(adminGroup, result.get(0));
        Assert.assertEquals(betaTesterGroup, result.get(1));

        verify(this.groupMemberRepository, times(1)).findByName("imrenagi");
    }

    @Test
    public void shouldReturnEmptyUserGroup() {
        List<GroupMember> gmMock = new ArrayList<>();

        User user = userStub("imrenagi");

        doReturn(gmMock).when(this.groupMemberRepository).findByName("imrenagi");

        List<Group> result = this.groupMemberService.groupOf(user);

        Assert.assertEquals(0, result.size());

        verify(this.groupMemberRepository, times(1)).findByName("imrenagi");
    }

    @Test
    public void shouldReturnNullUserGroup() {
        User user = userStub("imrenagi");

        doReturn(null).when(this.groupMemberRepository).findByName("imrenagi");

        List<Group> result = this.groupMemberService.groupOf(user);

        Assert.assertNull(result);

        verify(this.groupMemberRepository, times(1)).findByName("imrenagi");
    }

    private User userStub(String username) {
        return new User(
                username,
                "Password01",
                Enablement.indefiniteEnablement(),
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

    private User nonactiveUser(String username) {
        return new User(
                username,
                "Password01",
                new Enablement(false, null, null),
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


}