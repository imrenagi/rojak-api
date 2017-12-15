package id.rojak.analytics.resource.dto.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inagi on 7/21/17.
 */
public class ChartDTO {

    @JsonProperty("xAxis")
    private XAxis xAxis;

    @JsonProperty("series")
    private List<Series> series;

    public ChartDTO() {
        this.series = new ArrayList<>();
    }

    public ChartDTO(XAxis xAxis, List<Series> series) {
        this.xAxis = xAxis;
        this.series = series;
    }
}
