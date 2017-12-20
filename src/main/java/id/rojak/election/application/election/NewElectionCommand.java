package id.rojak.election.application.election;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/10/17.
 */
public class NewElectionCommand extends ElectionCommand {

    @NotNull
    private String id;

    protected NewElectionCommand() {
    }

    public NewElectionCommand(String id,
                              String name,
                              Long electionDate,
                              Long campaignStartDate,
                              Long campaignEndDate,
                              Long provinceId,
                              Long cityId,
                              String electionType) {
        super(name, electionDate, campaignStartDate, campaignEndDate, provinceId, cityId, electionType);
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

}
