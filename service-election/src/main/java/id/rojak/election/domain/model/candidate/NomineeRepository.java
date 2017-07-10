package id.rojak.election.domain.model.candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by imrenagi on 7/7/17.
 */
@Repository
public interface NomineeRepository extends JpaRepository<Nominee, Long> {

    public Nominee findByNomineeId(NomineeId nomineeId);

    public default String nextId() {
        return UUID.randomUUID().toString();
    }
}
