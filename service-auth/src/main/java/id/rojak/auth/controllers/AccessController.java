package id.rojak.auth.controllers;

import id.rojak.auth.application.command.AssignRoleToGroupCommand;
import id.rojak.auth.application.command.CreateGroupCommand;
import id.rojak.auth.application.command.CreatePermissionCommand;
import id.rojak.auth.application.command.CreateRoleCommand;
import id.rojak.auth.application.representation.AccessApplicationService;
import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.controllers.dto.GroupDTO;
import id.rojak.auth.controllers.dto.PermissionDTO;
import id.rojak.auth.controllers.dto.RoleDTO;
import id.rojak.auth.domain.model.access.Permission;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.Response;

/**
 * Created by inagi on 8/3/17.
 */
@RestController
public class AccessController {

    private final static Logger log = LoggerFactory.getLogger(AccessController.class);

    @Autowired
    private AccessApplicationService accessApplicationService;

    @Autowired
    private IdentityApplicationService identityApplicationService;

    @RequestMapping(value = "/roles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleDTO> createNewRole(
            @Valid @RequestBody CreateRoleCommand aCommand) {

        Role role = this.accessApplicationService
                .newRole(aCommand);

        return new ResponseEntity<>(new RoleDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/permissions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PermissionDTO> createNewPermission(
            @Valid @RequestBody CreatePermissionCommand aCommand) {

        Permission permission = this.accessApplicationService
                .newPermission(aCommand);

        return new ResponseEntity<>(new PermissionDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupDTO> createNewGroup(
            @Valid @RequestBody CreateGroupCommand aCommand) {

        Group group = this.identityApplicationService
                .newGroup(aCommand);

        return new ResponseEntity<GroupDTO>(new GroupDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/groups/assign_role", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupDTO> assignRoleToGroup(
            @Valid @RequestBody AssignRoleToGroupCommand command) {

        this.accessApplicationService
                .assignRoleToGroup(command);

        return new ResponseEntity<GroupDTO>(new GroupDTO(), HttpStatus.OK);

    }

}