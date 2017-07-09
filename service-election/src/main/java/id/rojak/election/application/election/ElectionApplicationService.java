package id.rojak.election.application.election;

import com.netflix.discovery.converters.Auto;
import id.rojak.election.domain.model.candidate.*;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.domain.model.election.ElectionId;
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

    @Transactional
    public List<Election> allElections() {
        List<Election> elections = electionRepository().findAll();

        return elections;
    }

    public ElectionRepository electionRepository() {
        return this.electionRepository;
    }

}
