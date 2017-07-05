package id.rojak.election.application.election;

import id.rojak.election.domain.model.election.Election;
import id.rojak.election.domain.model.election.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by inagi on 7/4/17.
 */
@Service
public class ElectionApplicationService {

    @Autowired
    private ElectionRepository electionRepository;

    @Transactional
    public List<Election> allElections() {
//        List<Election> elections = electionRepository().findAll();
//
//        return elections;
        return null;
    }

    public ElectionRepository electionRepository() {
        return this.electionRepository;
    }

    public void setElectionRepository(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }
}
