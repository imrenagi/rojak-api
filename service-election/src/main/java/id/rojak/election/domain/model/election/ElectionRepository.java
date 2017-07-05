package id.rojak.election.domain.model.election;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by inagi on 7/4/17.
 */
//@Repository
public interface ElectionRepository { // extends JpaRepository<Election, Long> {
    Election findById(Long id);
    Election findByElectionId(ElectionId anElectionId);
}
