package id.rojak.election.application.election;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/10/17.
 */
public class ElectionDetailChangeCommand extends ElectionCommand {

    protected ElectionDetailChangeCommand() {
        super();
    }

    public ElectionDetailChangeCommand(
            String name,
            Long electionDate,
            Long campaignStartDate,
            Long campaignEndDate,
            Long provinceId,
            Long cityId,
            String electionType) {
        super(name, electionDate, campaignStartDate, campaignEndDate, provinceId, cityId, electionType);
    }
}
