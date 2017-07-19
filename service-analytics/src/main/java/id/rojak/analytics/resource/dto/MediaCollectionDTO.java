package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by imrenagi on 7/18/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaCollectionDTO {

    @JsonProperty("medias")
    private List<MediaDTO> medias;
    @JsonProperty("meta")
    private MetaDTO meta;

    public MediaCollectionDTO(List<MediaDTO> medias, MetaDTO meta) {
        this.medias = medias;
        this.meta = meta;
    }
}
