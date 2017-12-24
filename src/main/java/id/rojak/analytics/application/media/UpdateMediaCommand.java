package id.rojak.analytics.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateMediaCommand extends MediaCommand {

    public UpdateMediaCommand() {
    }


}
