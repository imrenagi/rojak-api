package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/3/17.
 */
public class CreateRoleCommand {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    public CreateRoleCommand() {
    }

    public CreateRoleCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
