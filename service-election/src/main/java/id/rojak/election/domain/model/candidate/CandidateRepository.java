package id.rojak.election.domain.model.candidate;

import id.rojak.election.domain.model.election.ElectionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by imrenagi on 7/7/17.
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Page<Candidate> findByElectionId(ElectionId electionId, Pageable pageRequest);

    Candidate findByElectionIdAndCandidateId(ElectionId electionId,
                                             CandidateId candidateId);

    void deleteByElectionIdAndCandidateId(ElectionId electionId,
                                           CandidateId candidateId);

    default String nextId() {
        return UUID.randomUUID().toString();
    }
}
