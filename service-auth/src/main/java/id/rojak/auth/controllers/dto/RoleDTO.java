package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by inagi on 8/3/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDTO extends BasicDTO {

    public RoleDTO() {
    }

    public RoleDTO(String name, String description) {
        super(name, description);
    }
}
