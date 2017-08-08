package id.rojak.auth.domain.model.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by inagi on 8/1/17.
 */
@Service("groupMemberRepository")
public class GroupMemberService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userInfoRepository;

    @Transactional(readOnly = true)
    public boolean confirmUser(Group aGroup, User aUser) {
        boolean userConfirmed = true;

        User confirmedUser =
                this.userRepository()
                        .findByUsername(aUser.username());

        if (confirmedUser == null || !confirmedUser.isEnabled()) {
            userConfirmed = false;
        }

        return userConfirmed;
    }

    @Transactional(readOnly = true)
    public List<Group> groupOf(User aUser) {

        List<GroupMember> groupMembers =
                this.groupMemberRepository
                        .findByName(aUser.username());

        if (groupMembers == null) return null;

        return groupMembers.stream()
                .map(groupMember -> groupMember.group())
                .collect(Collectors.toList());
    }


    private GroupRepository groupRepository() {
        return this.groupRepository;
    }

    private UserRepository userRepository() {
        return this.userInfoRepository;
    }
}
