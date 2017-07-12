package id.rojak.election.domain.model.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by imrenagi on 7/10/17.
 */
@Service
public class ElectionCandidateService implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private NomineeRepository nomineeRepository;

    public boolean isOk() {
        return false;
    }

}
