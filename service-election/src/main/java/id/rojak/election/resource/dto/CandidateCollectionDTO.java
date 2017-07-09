package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.election.domain.model.candidate.Candidate;

import java.util.Set;

/**
 * Created by imrenagi on 7/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateCollectionDTO {

    @JsonProperty("candidates")
    private Set<CandidateDTO> candidates;

    @JsonProperty("meta")
    private MetaDTO meta;

    public CandidateCollectionDTO(Set<CandidateDTO> candidates, MetaDTO meta) {
        this.candidates = candidates;
        this.meta = meta;
    }
}

