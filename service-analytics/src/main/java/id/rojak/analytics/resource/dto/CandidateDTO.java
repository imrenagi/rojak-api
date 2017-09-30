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

    @JsonProperty("short_name")
    private String shortName;

    protected CandidateDTO() {}

    public CandidateDTO(int number,
                        String name,
                        String shortName) {
        this();

        this.number = number;
        this.name = name;
        this.shortName = shortName;
    }
}
