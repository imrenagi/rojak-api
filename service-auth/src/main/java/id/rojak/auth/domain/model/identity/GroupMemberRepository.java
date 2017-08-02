package id.rojak.auth.domain.model.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by inagi on 8/1/17.
 */
@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

}
