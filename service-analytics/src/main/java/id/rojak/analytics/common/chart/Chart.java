package id.rojak.analytics.common.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/21/17.
 */
public class Chart {

    @JsonProperty("xAxis")
    private XAxis xAxis;

    @JsonProperty("series")
    private List<Series> series;

    public Chart(XAxis xAxis, List<Series> series) {
        this.xAxis = xAxis;
        this.series = series;
    }
}
