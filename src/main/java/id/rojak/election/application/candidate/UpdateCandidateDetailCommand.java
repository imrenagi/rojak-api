package id.rojak.election.application.candidate;

/**
 * Created by imrenagi on 7/10/17.
 */
public class UpdateCandidateDetailCommand extends CandidateCommand {

    public UpdateCandidateDetailCommand(
            String electionId,
            Integer candidateNumber,
            String mainCandidateId,
            String viceCandidateId,
            String imageUrl,
            String webUrl,
            String twitterId,
            String facebookUrl,
            String instagramId) {
        super(electionId, candidateNumber, mainCandidateId, viceCandidateId, imageUrl, webUrl, twitterId, facebookUrl, instagramId);
    }

    protected UpdateCandidateDetailCommand() {}

}
