package id.rojak.auth.controllers;

import id.rojak.auth.application.command.RegisterUserCommand;
import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.controllers.dto.UserDTO;
import id.rojak.auth.domain.model.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by imrenagi on 5/9/17.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IdentityApplicationService identityApplicationService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

//    @PreAuthorize("#oauth2.hasScope('server')")
//    @RequestMapping(method = RequestMethod.POST)
//    public void createUser(@Valid @RequestBody User user) {
//        userService.create(user);
//    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> registerNewUser(
            @Valid @RequestBody RegisterUserCommand aCommand) {

        User user = this.identityApplicationService.newUser(aCommand);

        if (user == null) {
            System.out.println("user null");
        }

//        log.info("{} has been created", user.username());

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

}
