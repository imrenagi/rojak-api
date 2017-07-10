package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by imrenagi on 7/9/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NomineesCollectionDTO {

    @JsonProperty("nominees")
    private List<NomineeDTO> nominees;
    @JsonProperty("meta")
    private MetaDTO meta;

    public NomineesCollectionDTO(List<NomineeDTO> nominees, MetaDTO meta) {
        this.nominees = nominees;
        this.meta = meta;
    }
}
