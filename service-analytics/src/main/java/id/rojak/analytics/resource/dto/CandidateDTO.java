package id.rojak.analytics.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 7/31/17.
 */
public class CandidateDTO {

    @JsonProperty("candidate_number")
    private int number;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    public CandidateDTO() {}

    public CandidateDTO(int number,
                        String name,
                        String imageUrl) {
        this();

        this.number = number;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
