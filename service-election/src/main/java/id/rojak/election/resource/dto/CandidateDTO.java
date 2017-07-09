package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.election.domain.model.candidate.Candidate;

/**
 * Created by imrenagi on 7/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateDTO {

    private String id;
    private String electionId;
    private int candidateNumber;
    private String name;
    private NomineeSmallDTO mainCandidate;
    private NomineeSmallDTO viceCandidate;
    private String imageUrl;
    private String twitterId;
    private String facebookUrl;
    private String instagramId;
    private StatisticSummaryDTO statisticSummary;

    public CandidateDTO(Candidate candidate) {
        this.id = candidate.candidateId().id();
        this.electionId = candidate.electionId().id();
        this.candidateNumber = candidate.candidateNumber();
        this.name = candidate.mainCandidate().fullName().asFormattedName() + " & " +
                candidate.viceCandidate().fullName().asFormattedName();
        this.mainCandidate = new NomineeSmallDTO(candidate.mainCandidate());
        this.viceCandidate = new NomineeSmallDTO(candidate.viceCandidate());
        this.imageUrl = candidate.imageuRL();
        this.twitterId = candidate.socialMediaInformation().twitterId();
        this.facebookUrl = candidate.socialMediaInformation().facebookUrl();
        this.instagramId = candidate.socialMediaInformation().instagramId();
        //TODO change this
        this.statisticSummary = new StatisticSummaryDTO();
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("election_id")
    public String getElectionId() {
        return electionId;
    }

    @JsonProperty("candidate_number")
    public int getCandidateNumber() {
        return candidateNumber;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("main_candidate")
    public NomineeSmallDTO getMainCandidate() {
        return mainCandidate;
    }

    @JsonProperty("vice_candidate")
    public NomineeSmallDTO getViceCandidate() {
        return viceCandidate;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("twitter_id")
    public String getTwitterId() {
        return twitterId;
    }

    @JsonProperty("facebook_url")
    public String getFacebookUrl() {
        return facebookUrl;
    }

    @JsonProperty("instagram_id")
    public String getInstagramId() {
        return instagramId;
    }

    @JsonProperty("statistic_summary")
    public StatisticSummaryDTO getStatisticSummary() {
        return statisticSummary;
    }
}
