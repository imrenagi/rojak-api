package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by inagi on 8/4/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDTO extends BasicDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("support_nesting")
    private boolean supportNesting;

    @JsonProperty("role")
    private String role;

    public GroupDTO() {
        super();
    }

    public GroupDTO(String id,
                    String name,
                    String description,
                    boolean supportNesting,
                    String roleName) {
        super(name, description);

        this.id = id;
        this.supportNesting = supportNesting;
        this.role = roleName;
    }

}
