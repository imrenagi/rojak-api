package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/4/17.
 */
public class AssignRoleToGroupCommand {

    @JsonProperty("role")
    private String role;

    @JsonProperty("group_id")
    private String groupId;

    protected AssignRoleToGroupCommand() {

    }

    public AssignRoleToGroupCommand(String role, String groupId) {
        this.role = role;
        this.groupId = groupId;
    }

    public String getRole() {
        return role;
    }

    public String getGroupId() {
        return groupId;
    }
}
