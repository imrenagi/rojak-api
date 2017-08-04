package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by inagi on 8/4/17.
 */
public class CreateGroupCommand {

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("description")
    private String description;

    @JsonProperty("support_nesting")
    private Boolean supportNesting;

    @JsonProperty("role")
    private String role;

    protected CreateGroupCommand() {

    }

    public CreateGroupCommand(String name,
                              String description,
                              Boolean supportNesting,
                              String role) {
        this.name = name;
        this.description = description;
        this.supportNesting = supportNesting;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isSupportNesting() {
        return supportNesting;
    }

    public String getRole() {
        return role;
    }
}
