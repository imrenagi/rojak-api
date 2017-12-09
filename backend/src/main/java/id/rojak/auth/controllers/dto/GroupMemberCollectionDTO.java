package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/7/17.
 */
public class GroupMemberCollectionDTO extends BaseCollectionDTO {

    @JsonProperty("members")
    private List<GroupMemberDTO> members;

    public GroupMemberCollectionDTO(List<GroupMemberDTO> members, MetaDTO meta) {
        super(meta);
        this.members = members;
    }
}
