package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 7/20/17.
 */
public class MediaNewsCountDTO {

    @JsonProperty("positive_news")
    private long positiveCount;
    @JsonProperty("negative_news")
    private long negativeCount;
    @JsonProperty("neutral_media")
    private long neutralCount;

    public MediaNewsCountDTO() {
        this(0, 0, 0);
    }

    public MediaNewsCountDTO(long positiveCount,
                             long negativeCount,
                             long neutralCount) {
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
        this.neutralCount = neutralCount;
    }
}
