package id.rojak.auth.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.rojak.auth.domain.model.identity.User;

import javax.validation.constraints.NotNull;

/**
 * Created by inagi on 8/3/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("lst_name")
    private String lastName;

    @JsonProperty("email_address")
    private String emailAddress;

    public UserDTO(){}

    public UserDTO(String username,
                   String firstName,
                   String lastName,
                   String emailAddress) {

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public UserDTO(User user) {
        this(user.username(),
                user.person().name().firstName(),
                user.person().name().lastName(),
                user.person().emailAddress().address());
    }
}
