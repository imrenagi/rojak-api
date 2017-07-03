package id.rojak.auth.repository;

import id.rojak.auth.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by imrenagi on 5/12/17.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
