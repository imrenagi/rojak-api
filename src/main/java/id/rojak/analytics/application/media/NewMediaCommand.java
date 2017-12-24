package id.rojak.analytics.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewMediaCommand extends MediaCommand {

    @NotNull
    private String id;

    public NewMediaCommand() {
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
