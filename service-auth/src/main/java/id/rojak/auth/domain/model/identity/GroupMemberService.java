package id.rojak.auth.domain.model.identity;

/**
 * Created by inagi on 8/1/17.
 */
public class GroupMemberService {

    private GroupRepository groupRepository;
    private UserRepository userInfoRepository;

    public GroupMemberService(
            UserRepository aUserInfoRepository,
            GroupRepository aGroupRepository) {

        super();

        this.groupRepository = aGroupRepository;
        this.userInfoRepository = aUserInfoRepository;
    }

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

    private GroupRepository groupRepository() {
        return this.groupRepository;
    }

    private UserRepository userRepository() {
        return this.userInfoRepository;
    }
}
