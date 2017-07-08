package id.rojak.election.application.election;

import com.netflix.discovery.converters.Auto;
import id.rojak.election.domain.model.candidate.*;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.domain.model.election.ElectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by inagi on 7/4/17.
 */
@Service
public class ElectionApplicationService {

    private static Logger log = LoggerFactory.getLogger(ElectionApplicationService.class);

    @Autowired
    private ElectionRepository electionRepository;

    @Autowired
    private NomineeRepository nomineeRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Transactional
    public List<Election> allElections() {
//        Nominee nom = nomineeRepository.findByNomineeId(new NomineeId("imrenagi"));
        Nominee nom = nomineeRepository.findOne(1L);
        log.info("Receive Nominee info => {} {} ", nom.fullName().asFormattedName(), nom.nomineeId());

        nom = nomineeRepository.findByNomineeId(new NomineeId("imrenagi123"));
        log.info("Receive Nominee info xxx => {} {} ", nom.fullName().asFormattedName(), nom.nomineeId());


        Candidate can = candidateRepository.findOne(1L);//candidateRepository.findByCandidateId(new CandidateId("okeoce"));
        if (can != null)
            log.info("Candidate info => {} {} {} {}", can.candidateId().id(),
                can.mainCandidate().nomineeId().id(),
                can.viceCandidate().nomineeId().id(),
                can.socialMediaInformation().facebookUrl());

        List<Election> elections = electionRepository().findAll();

        return elections;
    }

    public ElectionRepository electionRepository() {
        return this.electionRepository;
    }
//
//    public void setElectionRepository(ElectionRepository electionRepository) {
//        this.electionRepository = electionRepository;
//    }
}
