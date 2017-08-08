package id.rojak.auth.domain.model.identity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by inagi on 8/1/17.
 */
@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long>, Serializable {

//    GroupMember findByName(String name);
    List<GroupMember> findByName(String name);

    List<GroupMember> findByGroup(Group group);
    Page<GroupMember> findByGroup(Group group, Pageable pageable);

}
