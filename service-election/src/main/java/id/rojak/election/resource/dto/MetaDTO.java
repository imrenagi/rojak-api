package id.rojak.election.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 7/4/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaDTO {

    private int currentPage;
    private int totalPage;
    private long totalItems;

    public MetaDTO() {}

    public MetaDTO(int currentPage, int totalPage, long totalItems) {
        this();
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalItems = totalItems;
    }

    @JsonProperty("page")
    public int getCurrentPage() {
        return this.currentPage;
    }

    @JsonProperty("total_pages")
    public int getTotalPage() {
        return totalPage;
    }

    @JsonProperty("total_items")
    public long getTotalItems() {
        return totalItems;
    }
}
