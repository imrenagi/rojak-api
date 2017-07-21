package id.rojak.election.application.election;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/10/17.
 */
public class NewElectionCommand {

    @NotNull
    private String name;
    @NotNull
    private Long electionDate;
    @NotNull
    private Long campaignStartDate;
    @NotNull
    private Long campaignEndDate;
    @NotNull
    private Long cityId;
    @NotNull
    private String electionType;

    protected NewElectionCommand() {

    }

    public NewElectionCommand(String name,
                              Long electionDate,
                              Long campaignStartDate,
                              Long campaignEndDate,
                              Long cityId,
                              String electionType) {
        this();
        this.name = name;
        this.electionDate = electionDate;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndDate = campaignEndDate;
        this.cityId = cityId;
        this.electionType = electionType;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Long getElectionDate() {
        return electionDate;
    }

    @JsonProperty("election_date")
    public void setElectionDate(Long electionDate) {
        this.electionDate = electionDate;
    }

    public Long getCampaignStartDate() {
        return campaignStartDate;
    }

    @JsonProperty("campaign_start_date")
    public void setCampaignStartDate(Long campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public Long getCampaignEndDate() {
        return campaignEndDate;
    }

    @JsonProperty("campaign_end_date")
    public void setCampaignEndDate(Long campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public Long getCityId() {
        return cityId;
    }

    @JsonProperty("city_id")
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getElectionType() {
        return electionType;
    }

    @JsonProperty("election_type")
    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }
}
