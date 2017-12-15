package id.rojak.auth.application.representation;

import id.rojak.auth.application.command.AddUserToGroupCommand;
import id.rojak.auth.application.command.CreateGroupCommand;
import id.rojak.auth.application.command.RegisterUserCommand;
import id.rojak.auth.application.command.RemoveUserFromGroupCommand;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.access.RoleRepository;
import id.rojak.auth.domain.model.identity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by inagi on 8/1/17.
 */
@Service("identityApplicationService")
//@Service
public class IdentityApplicationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Transactional
    public User newUser(RegisterUserCommand aCommand) {

        User user = this.userRepository.findByUsername(aCommand.getUsername());

        if (user != null) {
            throw new IllegalArgumentException("User " +
                aCommand.getUsername() +
                    " is already exist");
        }

        user = new User(
                aCommand.getUsername(),
                aCommand.getPassword(),
                new Enablement(
                        aCommand.isEnabled(),
                        aCommand.getStartDate(),
                        aCommand.getEndDate()),
                new Person(
                        new FullName(aCommand.getFirstName(),
                                aCommand.getLastName()),
                        new ContactInformation(
                                new EmailAddress(aCommand.getEmailAddress()),
                                new PostalAddress(aCommand.getAddressStreetAddress(),
                                        aCommand.getAddressCity(), aCommand.getAddressStateProvince(),
                                        aCommand.getAddressPostalCode(),
                                        aCommand.getAddressCountry()),
                                new Telephone(aCommand.getPrimaryTelephone()),
                                new Telephone(aCommand.getSecondaryTelephone()))));

        user = this.userRepository.save(user);

        return user;
    }

    @Transactional
    public Group newGroup(CreateGroupCommand aCommand) {

        Group group = this.groupRepository
                .findByName(aCommand.getName());

        if (group != null) {
            throw new IllegalArgumentException("Group " +
                    aCommand.getName() +
                    " is already exist");
        }

        Role role = this.roleRepository
                .findByName(aCommand.getRole());

        if (role == null) {
            throw new IllegalArgumentException("Role doesn't exist");
        }

        group = new Group(
                new GroupId(this.groupRepository.nextId()),
                aCommand.getName(),
                aCommand.getDescription(),
                aCommand.isSupportNesting(),
                role);

        group = this.groupRepository.save(group);

        return group;
    }

    @Transactional
    public void addUserToGroup(AddUserToGroupCommand aCommand) {

        Group group = this.existingGroup(new GroupId(aCommand.getGroupId()));

        for (String username : aCommand.getUsernames()) {

            User user = this.userRepository
                    .findByUsername(username);

            if (user == null) continue;

            group.addUser(user);
        }

    }

    @Transactional
    public void removeUserFromGroup(RemoveUserFromGroupCommand command) {

        Group group = this.existingGroup(
                new GroupId(command.getGroupId()));

        for (String username : command.getUsers()) {
            User user = this.userRepository
                    .findByUsername(username);

            if (user == null) continue;

            group.removeUser(user);
        }
    }

    @Transactional
    public Page<GroupMember> allGroupMember(String groupId, Pageable pageable) {

        Group group = this.existingGroup(new GroupId(groupId));

        return this.groupMemberRepository
                .findByGroup(group, pageable);
    }

    private Group existingGroup(GroupId groupId) {

        Group group = this.groupRepository
                .findByGroupId(groupId);

        if (group == null) {
            throw new IllegalArgumentException(
                    "Group "
                            + groupId +
                            " doesn't exist");
        }

        return group;
    }

    private User existingUser(String username) {

        User user = this.userRepository
                .findByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User " +
                    user + " doesn't exist");
        }

        return user;
    }


}
