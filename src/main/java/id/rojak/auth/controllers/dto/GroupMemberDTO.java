package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/7/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupMemberDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("type")
    private String type;

    public GroupMemberDTO() {
    }

    public GroupMemberDTO(String username,
                          String type) {
        this.username = username;
        this.type = type;
    }

}
