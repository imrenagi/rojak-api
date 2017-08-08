package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class PermissionCollectionDTO extends BaseCollectionDTO {

    @JsonProperty("permissions")
    private List<PermissionDTO> permissions;

    public PermissionCollectionDTO(List<PermissionDTO> permissions, MetaDTO meta) {
        super(meta);

        this.permissions = permissions;
    }

    public PermissionCollectionDTO(List<PermissionDTO> permissions) {
        super();
        this.permissions = permissions;
    }
}
