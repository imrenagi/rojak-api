package id.rojak.election.domain.model.candidate;

import id.rojak.election.domain.model.election.ElectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by imrenagi on 7/7/17.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    public Candidate findByCandidateId(CandidateId candidateId);

    public List<Candidate> findByElectionId(ElectionId electionId);

}
