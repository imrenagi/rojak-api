package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class AddUserToGroupCommand {

    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("users")
    private List<String> usernames;

    public AddUserToGroupCommand(String groupId, List<String> usernames) {
        this.groupId = groupId;
        this.usernames = usernames;
    }

    public AddUserToGroupCommand() {
    }

    public String getGroupId() {
        return groupId;
    }

    public List<String> getUsernames() {
        return usernames;
    }
}
