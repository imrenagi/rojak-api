package id.rojak.auth.application.representation;

import id.rojak.auth.application.command.*;
import id.rojak.auth.domain.model.access.Permission;
import id.rojak.auth.domain.model.access.PermissionRepository;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.access.RoleRepository;
import id.rojak.auth.domain.model.identity.Group;
import id.rojak.auth.domain.model.identity.GroupId;
import id.rojak.auth.domain.model.identity.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by inagi on 8/3/17.
 */
@Service("accessApplicationService")
public class AccessApplicationService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public Role newRole(CreateRoleCommand command) {

        Role newRole = new Role(command.getName(),
                command.getDescription());

        Role existingRole =
                this.roleRepository
                        .findByName(newRole.name());

        if (existingRole != null) {
            throw new IllegalArgumentException(
                    "Role " + command.getName() +
                            " is already exist");
        }

        newRole = this.roleRepository.save(newRole);

        return newRole;
    }

    @Transactional
    public Permission newPermission(CreatePermissionCommand command) {

        Permission newPermission =
                new Permission(command.getName(),
                        command.getDescription());

        Permission oldPermission =
                this.permissionRepository
                        .findByName(newPermission.name());

        if (oldPermission != null) {
            throw new IllegalArgumentException(
                    "Permission "
                            + command.getName() +
                            " is already exist");
        }

        newPermission = this.permissionRepository.save(newPermission);

        return newPermission;
    }

    @Transactional
    public void assignRoleToGroup(AssignRoleToGroupCommand command) {

        Group group = this.groupRepository
                .findByGroupId(new GroupId(command.getGroupId()));

        if (group == null) {
            throw new IllegalArgumentException(
                    "Group "
                            + command.getGroupId() +
                            " doesn't exist");
        }

        Role role = this.existingRole(command.getRole());

        role.assignTo(group);

        this.groupRepository.save(group);
    }

    @Transactional
    public void assignPermissionToRole(AssignPermissionToRoleCommand command) {

        Role role = this.existingRole(command.getRole());

        for (String permissionName : command.getPermissions()) {

            Permission permission = this.permissionRepository
                    .findByName(permissionName);

            if (permission == null) {
                continue;
            }

            role.addPermission(permission);
        }

    }

    @Transactional
    public void revokePermissionFromRole(RevokePermissionFromRoleCommand command) {

        Role role = this.existingRole(command.getRole());

        for (String p : command.getPermissions()) {

            Permission permission = this.permissionRepository
                    .findByName(p);

            if (permission == null) continue;

            role.removePermission(permission);
        }
    }

    private Role existingRole(String roleName) {
        Role role = this.roleRepository
                .findByName(roleName);

        if (role == null) {
            throw new IllegalArgumentException(
                    "Role "
                            + roleName +
                            " doesn't exist");
        }

        return role;
    }


}
