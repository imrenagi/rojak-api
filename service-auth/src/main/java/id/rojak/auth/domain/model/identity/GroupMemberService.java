package id.rojak.auth.domain.model.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    public GroupMemberService() {

    }

//    @Autowired
//    public GroupMemberService(GroupMemberRepository groupMemberRepository,
//                              GroupRepository groupRepository,
//                              UserRepository userRepository) {
//        this.groupMemberRepository = groupMemberRepository;
//        this.groupRepository = groupRepository;
//        this.userInfoRepository = userRepository;
//    }

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
    public Group groupOf(User aUser) {

        GroupMember groupMember =
                this.groupMemberRepository
                        .findByName(aUser.username());

        if (groupMember == null) return null;

        return groupMember.group();
    }


    private GroupRepository groupRepository() {
        return this.groupRepository;
    }

    private UserRepository userRepository() {
        return this.userInfoRepository;
    }
}
