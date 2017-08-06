package id.rojak.auth.controllers;

import id.rojak.auth.application.command.*;
import id.rojak.auth.application.representation.AccessApplicationService;
import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.controllers.dto.*;
import id.rojak.auth.domain.model.access.Permission;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/roles/{role_id}/permissions",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleDTO> assignPermissionsToGrop(
            @PathVariable("role_id") String aRoleId,
            @Valid @RequestBody AssignPermissionToRoleCommand aCommand) {

        this.accessApplicationService
                .assignPermissionToRole(aCommand);

        return new ResponseEntity<>(new RoleDTO(), HttpStatus.OK);
    }

    @RequestMapping(value = "/roles/{role_id}/permissions/{permission_id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoleDTO> removePermissionsFromRole(
            @PathVariable("role_id") String aRoleId,
            @PathVariable("permission_id") String permissionIds) {

        String[] pArr = permissionIds.split(",");

        this.accessApplicationService
                .revokePermissionFromRole(
                        new RevokePermissionFromRoleCommand(
                                Arrays.asList(pArr),
                                aRoleId));

        return new ResponseEntity<>(new RoleDTO(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('BASIC_WRITE')")
    @RequestMapping(value = "/permissions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PermissionDTO> createNewPermission(
            @Valid @RequestBody CreatePermissionCommand aCommand) {

        Permission permission = this.accessApplicationService
                .newPermission(aCommand);

        return new ResponseEntity<>(new PermissionDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupCollectionDTO> allGroups(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer size) {

        Page<Group> groups = this.accessApplicationService
                .allGroups(new PageRequest(page, size));

        List<GroupDTO> groupDTO = groups.getContent()
                .stream()
                .map(group -> {
                    return new GroupDTO(
                            group.groupId().id(),
                            group.name(),
                            group.description(),
                            group.isSupportNesting(),
                            group.role().name());
                })
                .collect(Collectors.toList());

        return new ResponseEntity<GroupCollectionDTO>(
                new GroupCollectionDTO(groupDTO,
                        new MetaDTO(page,
                                groups.getTotalPages(),
                                groups.getTotalElements())),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupDTO> createNewGroup(
            @Valid @RequestBody CreateGroupCommand aCommand) {

        Group group = this.identityApplicationService
                .newGroup(aCommand);

        return new ResponseEntity<GroupDTO>(new GroupDTO(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/groups/{group_id}/roles", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupDTO> assignRoleToGroup(
            @PathVariable("group_id") String aGroupId,
            @Valid @RequestBody AssignRoleToGroupCommand command) {

        this.accessApplicationService
                .assignRoleToGroup(command);

        return new ResponseEntity<GroupDTO>(new GroupDTO(), HttpStatus.OK);

    }

    @RequestMapping(value = "/groups/{group_id}/users", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupDTO> assignUsersToGroup(
            @PathVariable("group_id") String aGroupId,
            @Valid @RequestBody AddUserToGroupCommand command) {

        this.identityApplicationService
                .addUserToGroup(command);

        return new ResponseEntity<GroupDTO>(new GroupDTO(), HttpStatus.OK);
    }

    @RequestMapping(value = "/groups/{group_id}/users/{user_id}", method = RequestMethod.DELETE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GroupDTO> removeUsersFromGroup(
            @PathVariable("group_id") String aGroupId,
            @PathVariable("user_id") String aUserIds) {

        String[] usernames = aUserIds.split(",");

        this.identityApplicationService
                .removeUserFromGroup(
                        new RemoveUserFromGroupCommand(
                                Arrays.asList(usernames),
                                aGroupId));

        return new ResponseEntity<GroupDTO>(new GroupDTO(), HttpStatus.OK);
    }

}
