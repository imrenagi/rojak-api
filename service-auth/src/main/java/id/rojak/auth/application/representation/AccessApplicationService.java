package id.rojak.auth.application.representation;

import id.rojak.auth.application.command.CreateRoleCommand;
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

    @Transactional
    public Role newRole(CreateRoleCommand command) {

        Role role = this.roleRepository.findByName(command.getName());

        if (role != null) {
            throw new IllegalArgumentException("Role " + command.getName() + " is already exist");
        }

        role = new Role(command.getName(),
                command.getDescription());

        role = this.roleRepository.save(role);

        return role;
    }

}
