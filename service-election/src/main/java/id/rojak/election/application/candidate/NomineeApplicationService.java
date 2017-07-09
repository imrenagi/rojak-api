package id.rojak.election.application.candidate;

import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.domain.model.candidate.Nominee;
import id.rojak.election.domain.model.candidate.NomineeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by imrenagi on 7/9/17.
 */
@Service
public class NomineeApplicationService {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private NomineeRepository nomineeRepository;

    @Transactional
    public Page<Nominee> allNominees(Pageable pageRequest) {
        Page<Nominee> nominees = this.nomineeRepository.findAll(pageRequest);



        return nominees;
    }

}
