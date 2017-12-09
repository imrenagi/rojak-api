package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class RemoveUserFromGroupCommand {

    @JsonProperty("users")
    private List<String> users;

    @JsonProperty("group_id")
    private String groupId;

    public RemoveUserFromGroupCommand(List<String> users, String groupId) {
        this.users = users;
        this.groupId = groupId;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getGroupId() {
        return groupId;
    }
}
