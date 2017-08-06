package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by inagi on 8/6/17.
 */
public class AddUserToGroupCommand {

    @JsonProperty("group")
    private String group;

    @JsonProperty("users")
    private List<String> usernames;

    public AddUserToGroupCommand(String group, List<String> usernames) {
        this.group = group;
        this.usernames = usernames;
    }

    public AddUserToGroupCommand() {
    }

    public String getGroup() {
        return group;
    }

    public List<String> getUsernames() {
        return usernames;
    }
}
