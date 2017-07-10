package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by imrenagi on 7/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticSummaryDTO {

    @JsonProperty("positive_news_count")
    private int positiveCount;
    @JsonProperty("negative_news_count")
    private int negativeCount;
    @JsonProperty("neutral_news_count")
    private int neutralCount;

    public StatisticSummaryDTO() {
        this(0, 0, 0);
    }

    public StatisticSummaryDTO(int positiveCount,
                               int negativeCount,
                               int neutralCount) {
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
        this.neutralCount = neutralCount;
    }


}
