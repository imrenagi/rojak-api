package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/4/17.
 */
public class AssignPermissionToRoleCommand {

    @JsonProperty("permissions")
    private List<String> permissions;

    @JsonProperty("role")
    private String role;

    protected AssignPermissionToRoleCommand() {

    }

    public AssignPermissionToRoleCommand(List<String> permissions, String role) {
        this.permissions = permissions;
        this.role = role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public String getRole() {
        return role;
    }
}
