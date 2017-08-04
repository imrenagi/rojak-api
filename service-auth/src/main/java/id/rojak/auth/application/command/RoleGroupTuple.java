package id.rojak.auth.application.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by inagi on 8/4/17.
 */
public class RoleGroupTuple {

    @JsonProperty("role")
    private String role;

    @JsonProperty("group")
    private String group;
}
