package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/4/17.
 */
public class AssignRoleToGroupCommand {

    @JsonProperty("role_name")
    private String role;

    @JsonProperty("group_name")
    private String group;

    protected AssignRoleToGroupCommand() {

    }

    public AssignRoleToGroupCommand(String role, String group) {
        this.role = role;
        this.group = group;
    }

    public String getRole() {
        return role;
    }

    public String getGroup() {
        return group;
    }
}
