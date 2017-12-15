package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/20/17.
 */
public class CandidateMediaDTO {

    @JsonProperty("positive_medias")
    private List<MediaDTO> positiveMedia;

    @JsonProperty("negative_medias")
    private List<MediaDTO> negativeMedia;

    @JsonProperty("neutral_medias")
    private List<MediaDTO> neutralMedia;

    public CandidateMediaDTO(List<MediaDTO> positiveMedia,
                             List<MediaDTO> negativeMedia,
                             List<MediaDTO> neutralMedia) {
        this.positiveMedia = positiveMedia;
        this.negativeMedia = negativeMedia;
        this.neutralMedia = neutralMedia;
    }
}
