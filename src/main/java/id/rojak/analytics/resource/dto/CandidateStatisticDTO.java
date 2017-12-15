package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by imrenagi on 9/29/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateStatisticDTO {

    @JsonProperty("detail")
    private CandidateDTO candidateDetail;

    @JsonProperty("statistic")
    private StatisticDTO statistic;

    protected CandidateStatisticDTO() {}

    public CandidateStatisticDTO(CandidateDTO candidate,
                                 StatisticDTO statistic) {
        this.candidateDetail = candidate;
        this.statistic = statistic;
    }


}
