package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by inagi on 8/6/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasicDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private Date createdDate;

    @JsonProperty("updated_at")
    private Date updatedDate;

    public BasicDTO() {
    }

    public BasicDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public BasicDTO(String name, String description, Date createdDate, Date updatedDate) {
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
