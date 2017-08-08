package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/6/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseCollectionDTO {

    @JsonProperty("meta")
    private MetaDTO meta;

    public BaseCollectionDTO(MetaDTO meta) {
        this.meta = meta;
    }

    public BaseCollectionDTO() {

    }
}
