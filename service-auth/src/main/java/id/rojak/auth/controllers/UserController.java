package id.rojak.auth.controllers;

import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.domain.model.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
//
//    @PreAuthorize("#oauth2.hasScope('server')")
//    @RequestMapping(method = RequestMethod.POST)
//    public void createUser(@Valid @RequestBody User user) {
//        userService.create(user);
//    }

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public ResponseEntity<String> testing(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        User user = identityApplicationService.testing(username, password);

        log.info("{} has been created", user.username());

        return new ResponseEntity<String>("test", HttpStatus.OK);
    }

}
