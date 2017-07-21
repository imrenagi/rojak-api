package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * Created by inagi on 7/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElectionCollectionDTO {

    @JsonProperty("elections")
    private List<ElectionDTO> elections;

    @JsonProperty("meta")
    private MetaDTO meta;

    public ElectionCollectionDTO(List<ElectionDTO> elections, MetaDTO meta) {
        this.elections = elections;
        this.meta = meta;
    }

}
