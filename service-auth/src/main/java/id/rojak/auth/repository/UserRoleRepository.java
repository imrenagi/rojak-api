package id.rojak.auth.repository;

import id.rojak.auth.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by imrenagi on 5/13/17.
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
