package id.rojak.election.application.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/8/17.
 */

public class NewCandidateCommand {

    @NotNull
    private String id;
    @NotNull
    private String electionId;
    @NotNull
    private Integer candidateNumber;
    @NotNull
    private String mainCandidateId;
    @NotNull
    private String viceCandidateId;
    private String imageUrl;
    private String webUrl;
    private String twitterId;
    private String facebookUrl;
    private String instagramId;

    protected NewCandidateCommand() {
    }

    public NewCandidateCommand(
            String id,
            String electionId,
            Integer candidateNumber,
            String mainCandidateId,
            String viceCandidateId,
            String imageUrl,
            String twitterId,
            String facebookUrl,
            String instagramId) {
        this();

        this.id = id;
        this.electionId = electionId;
        this.candidateNumber = candidateNumber;
        this.mainCandidateId = mainCandidateId;
        this.viceCandidateId = viceCandidateId;
        this.imageUrl = imageUrl;
        this.twitterId = twitterId;
        this.facebookUrl = facebookUrl;
        this.instagramId = instagramId;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("election_id")
    public String getElectionId() {
        return electionId;
    }

    @JsonProperty("election_id")
    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    @JsonProperty("candidate_number")
    public Integer getCandidateNumber() {
        return candidateNumber;
    }

    @JsonProperty("candidate_number")
    public void setCandidateNumber(Integer candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    @JsonProperty("main_candidate_id")
    public String getMainCandidateId() {
        return mainCandidateId;
    }

    @JsonProperty("main_candidate_id")
    public void setMainCandidateId(String mainCandidateId) {
        this.mainCandidateId = mainCandidateId;
    }

    @JsonProperty("vice_candidate_id")
    public String getViceCandidateId() {
        return viceCandidateId;
    }

    @JsonProperty("vice_candidate_id")
    public void setViceCandidateId(String viceCandidateId) {
        this.viceCandidateId = viceCandidateId;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("twitter_id")
    public String getTwitterId() {
        return twitterId;
    }

    @JsonProperty("twitter_id")
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @JsonProperty("facebook_url")
    public String getFacebookUrl() {
        return facebookUrl;
    }

    @JsonProperty("facebook_url")
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    @JsonProperty("instagram_id")
    public String getInstagramId() {
        return instagramId;
    }

    @JsonProperty("instagram_id")
    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    @JsonProperty("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonProperty("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
