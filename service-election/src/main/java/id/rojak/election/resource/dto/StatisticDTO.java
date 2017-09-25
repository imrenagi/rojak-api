package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by imrenagi on 9/24/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticDTO {

    @JsonProperty("total_news")
    private long total;
    @JsonProperty("total_pos_news")
    private long nPositive;
    @JsonProperty("total_neg_news")
    private long nNegative;
    @JsonProperty("total_neutral_news")
    private long nNeutral;

    public StatisticDTO() {
        this(0, 0, 0, 0);
    }

    public StatisticDTO(long total, long nPositive, long nNegative, long nNeutral) {
        this.total = total;
        this.nPositive = nPositive;
        this.nNegative = nNegative;
        this.nNeutral = nNeutral;
    }
}
