package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class GroupCollectionDTO extends BaseCollectionDTO {

    @JsonProperty("groups")
    private List<GroupDTO> groups;

    public GroupCollectionDTO(List<GroupDTO> groups, MetaDTO meta) {
        super(meta);

        this.groups = groups;
    }
}
