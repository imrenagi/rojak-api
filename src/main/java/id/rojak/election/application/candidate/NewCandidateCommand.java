package id.rojak.election.application.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/8/17.
 */

public class NewCandidateCommand extends CandidateCommand {

    @NotNull
    private String id;

    protected NewCandidateCommand() {
    }

    public NewCandidateCommand(
            String id,
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
        this.id = id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }
}
