package id.rojak.auth.controllers;

import id.rojak.auth.application.command.CreateRoleCommand;
import id.rojak.auth.application.representation.AccessApplicationService;
import id.rojak.auth.controllers.dto.RoleDTO;
import id.rojak.auth.domain.model.access.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by inagi on 8/3/17.
 */
@RestController
public class AccessController {

    @Autowired
    private AccessApplicationService accessApplicationService;

    @RequestMapping(value = "/roles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleDTO> registerNewUser(
            @Valid @RequestBody CreateRoleCommand aCommand) {

        Role role = this.accessApplicationService
                .newRole(aCommand);

        return new ResponseEntity<>(new RoleDTO(), HttpStatus.CREATED);
    }
}
