package id.rojak.election.application.candidate;

import id.rojak.election.domain.model.candidate.Candidate;
import id.rojak.election.domain.model.candidate.CandidateRepository;
import id.rojak.election.domain.model.election.ElectionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Candidate> allCandidates(String anElectionId, Pageable pageRequest) {

        Page<Candidate> result = this.candidateRepository
                .findByElectionId(new ElectionId(anElectionId),
                        pageRequest);

        return result;
    }

    private Pageable candidatePageRequest(int page, int size) {
        return new PageRequest(page, size);
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
