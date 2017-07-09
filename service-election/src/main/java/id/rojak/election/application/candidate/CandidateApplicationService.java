package id.rojak.election.application.candidate;

import id.rojak.election.domain.model.candidate.Candidate;
import id.rojak.election.domain.model.candidate.CandidateRepository;
import id.rojak.election.domain.model.election.ElectionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by imrenagi on 7/8/17.
 */
@Service
public class CandidateApplicationService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Transactional
    public List<Candidate> allCandidates(String anElectionId) {
        List<Candidate> candidates = this.candidateRepository
                .findByElectionId(
                        new ElectionId(anElectionId));
        return candidates;
    }

    @Transactional
    public void newCandidate(NewCandidateCommand aCommand) {
        //TODO implement!
    }

    @Transactional
    public void removeCandidate(RemoveCandidateCommand aCommand) {
        //TODO implement!
    }

}
