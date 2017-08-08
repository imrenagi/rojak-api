package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class RevokePermissionFromRoleCommand {

    @JsonProperty("permissions")
    private List<String> permissions;

    @JsonProperty("role")
    private String role;

    public RevokePermissionFromRoleCommand(List<String> permissions, String role) {
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
