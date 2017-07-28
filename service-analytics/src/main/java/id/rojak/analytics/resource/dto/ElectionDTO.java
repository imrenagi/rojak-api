package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 7/28/17.
 */
public class ElectionDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    public ElectionDTO() {
    }

    public ElectionDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
