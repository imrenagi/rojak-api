package id.rojak.auth.domain.model.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by inagi on 8/1/17.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findByName(String name);
    Group findByGroupId(GroupId groupId);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
