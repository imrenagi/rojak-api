package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.election.domain.model.election.Election;

/**
 * Created by inagi on 7/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElectionDTO {

    private String id;
    private String name;
    private Long electionDate;
    private Long campaignStartDate;
    private Long campaignEndDate;
    private ElectionLocationDTO location;
//    private int provinceId;
//    private int cityId;
    private String electionType;

    public ElectionDTO(String id,
                       String name,
                       Long electionDate,
                       Long campaignStartDate,
                       Long campaignEndDate,
                       String city,
                       String province,
                       String country,
                       String electionType) {
        this.id = id;
        this.name = name;
        this.electionDate = electionDate;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndDate = campaignEndDate;
        this.location = new ElectionLocationDTO(city, province, country);
        this.electionType = electionType;
    }

    public ElectionDTO(Election anElection) {
        this(anElection.electionId().id(),
                anElection.name(),
                anElection.electionDates().electionDate().getTime(),
                anElection.electionDates().campaignStart().getTime(),
                anElection.electionDates().campaignEnd().getTime(),
                anElection.city().name(),
                anElection.city().province().name(),
                anElection.city().province().country().name(),
                anElection.type().name());
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("election_date")
    public Long getElectionDate() {
        return electionDate;
    }

    @JsonProperty("election_campaign_start_date")
    public Long getCampaignStartDate() {
        return campaignStartDate;
    }

    @JsonProperty("election_campaign_end_date")
    public Long getCampaignEndDate() {
        return campaignEndDate;
    }

    @JsonProperty("location")
    public ElectionLocationDTO getLocation() {
        return this.location;
    }

    @JsonProperty("election_type")
    public String getElectionType() {
        return electionType;
    }
}
