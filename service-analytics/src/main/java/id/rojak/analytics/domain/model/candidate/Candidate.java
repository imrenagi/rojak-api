package id.rojak.analytics.domain.model.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.analytics.domain.model.ValueObject;

/**
 * Created by imrenagi on 7/16/17.
 */
public class Candidate extends ValueObject {

    @JsonProperty("id")
    private String id;

    @JsonProperty("candidate_number")
    private int candidateNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("election_id")
    private String electionId;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("twitter_id")
    private String twitterId;

    @JsonProperty("facebook_url")
    private String facebookUrl;

    @JsonProperty("instagram_id")
    private String instagramId;

    public Candidate() {
    }

    public Candidate(String id,
                     String electionId,
                     String webUrl,
                     String twitterId,
                     String facebookUrl,
                     String instagramId,
                     int candidateNumber,
                     String name,
                     String imageUrl) {
        this.id = id;
        this.electionId = electionId;
        this.webUrl = webUrl;
        this.twitterId = twitterId;
        this.facebookUrl = facebookUrl;
        this.instagramId = instagramId;
        this.candidateNumber = candidateNumber;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("candidate_number")
    public int getCandidateNumber() {
        return candidateNumber;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public String getElectionId() {
        return electionId;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getInstagramId() {
        return instagramId;
    }
}
