package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.analytics.resource.dto.chart.ChartDTO;

/**
 * Created by inagi on 7/27/17.
 */
public class MediaStatisticDTO {

    @JsonProperty("positive_stat")
    private ChartDTO positiveChart;

    @JsonProperty("negative_stat")
    private ChartDTO negativeChart;

    @JsonProperty("neutral_stat")
    private ChartDTO neutralChart;

    public MediaStatisticDTO(ChartDTO positiveChart,
                             ChartDTO negativeChart,
                             ChartDTO neutralChart) {

        this.positiveChart = positiveChart;
        this.negativeChart = negativeChart;
        this.neutralChart = neutralChart;
    }
}
