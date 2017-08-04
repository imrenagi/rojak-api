package id.rojak.auth.application.representation;

import id.rojak.auth.application.command.CreatePermissionCommand;
import id.rojak.auth.application.command.CreateRoleCommand;
import id.rojak.auth.domain.model.access.Permission;
import id.rojak.auth.domain.model.access.PermissionRepository;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.access.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by inagi on 8/3/17.
 */
@Service
public class AccessApplicationService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

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


}
