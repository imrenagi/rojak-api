package id.rojak.analytics.common.chart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/21/17.
 */
public class XAxis<T> {

    @JsonProperty("categories")
    private List<T> categories;

    public XAxis(List<T> categories) {
        this.categories = categories;
    }


}
