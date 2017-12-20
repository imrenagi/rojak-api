package id.rojak.election.application.candidate;

/**
 * Created by imrenagi on 12/19/17.
 */
public class UpdateNomineeCommand extends NomineeCommand {

    public UpdateNomineeCommand(String firstName,
                                String middleName,
                                String lastName,
                                String imageUrl,
                                String nickName,
                                String webUrl,
                                String twitterId,
                                String instagramId,
                                String facebookUrl) {
        super(firstName, middleName, lastName, imageUrl, nickName, webUrl, twitterId, instagramId, facebookUrl);
    }

    protected UpdateNomineeCommand() {
        super();
    }
}
