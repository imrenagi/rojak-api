package id.rojak.analytics.resource.dto.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/21/17.
 */
public class XAxis<T> {

    @JsonProperty("name")
    private String name;

    @JsonProperty("categories")
    private List<T> categories;

    public XAxis(List<T> categories) {
        this.categories = categories;
    }

    public XAxis(String name, List<T> categories) {
        this.name = name;
        this.categories = categories;
    }
}
