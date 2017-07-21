package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 7/20/17.
 */
public class CandidateStatSummaryDTO {

    @JsonProperty("positive_media")
    private int positiveCount;
    @JsonProperty("negative_media")
    private int negativeCount;
    @JsonProperty("neutral_media")
    private int neutralCount;

    public CandidateStatSummaryDTO() {
        this(0, 0, 0);
    }

    public CandidateStatSummaryDTO(int positiveCount,
                               int negativeCount,
                               int neutralCount) {
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
        this.neutralCount = neutralCount;
    }
}
