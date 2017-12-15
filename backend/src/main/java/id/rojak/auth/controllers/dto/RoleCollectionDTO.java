package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class RoleCollectionDTO extends BaseCollectionDTO {

    @JsonProperty("roles")
    private List<RoleDTO> roles;

    public RoleCollectionDTO(List<RoleDTO> roles, MetaDTO meta) {
        super(meta);

        this.roles = roles;
    }
}
