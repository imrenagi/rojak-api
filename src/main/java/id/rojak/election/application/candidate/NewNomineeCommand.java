package id.rojak.election.application.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.analytics.application.news.NewNewsCommand;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/9/17.
 */
public class NewNomineeCommand extends NomineeCommand {

    @NotNull
    private String id;

    public NewNomineeCommand(
            String id,
            String firstName,
            String middleName,
            String lastName,
            String imageUrl,
            String nickName,
            String webUrl,
            String twitterId,
            String instagramId,
            String facebookUrl) {
        super(firstName, middleName, lastName, imageUrl, nickName, webUrl, twitterId, instagramId, facebookUrl);
        this.id = id;
    }

    protected NewNomineeCommand() {
        super();
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }


}
