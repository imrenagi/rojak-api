package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/3/17.
 */
public class CreatePermissionCommand {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    protected CreatePermissionCommand() {

    }

    public CreatePermissionCommand(String name, String description) {
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
