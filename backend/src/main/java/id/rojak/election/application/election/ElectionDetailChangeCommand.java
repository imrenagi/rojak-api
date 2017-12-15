package id.rojak.election.application.election;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/10/17.
 */
public class ElectionDetailChangeCommand extends NewElectionCommand {

    @NotNull
    private String electionId;

    protected ElectionDetailChangeCommand() {
        super();
    }

    public ElectionDetailChangeCommand(
            String electionId,
            String name,
            Long electionDate,
            Long campaignStartDate,
            Long campaignEndDate,
            Long cityId,
            String electionType) {
        super(name, electionDate, campaignStartDate, campaignEndDate, cityId, electionType);
    }

    public String getElectionId() {
        return electionId;
    }

    @JsonProperty("election_id")
    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }
}
