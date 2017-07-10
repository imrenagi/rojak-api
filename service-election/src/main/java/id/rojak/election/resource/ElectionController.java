package id.rojak.election.resource;

import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.application.election.ElectionDateUpdateCommand;
import id.rojak.election.application.election.ElectionUpdateCommand;
import id.rojak.election.application.election.NewElectionCommand;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.resource.dto.ElectionCollectionDTO;
import id.rojak.election.resource.dto.ElectionDTO;
import id.rojak.election.resource.dto.MetaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by inagi on 7/4/17.
 */
@RestController
public class ElectionController {

    private static final Logger log = LoggerFactory.getLogger(ElectionController.class);

    @Autowired
    private ElectionApplicationService electionApplicationService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ElectionCollectionDTO> getAllElection() {

        List<Election> elections = this.electionApplicationService.allElections();

        return new ResponseEntity<>(new ElectionCollectionDTO(
                elections.stream().map(election -> new ElectionDTO(election)).collect(Collectors.toSet()),
                new MetaDTO(1,1,1)), HttpStatus.OK);
    }

    @RequestMapping(path = "/{election_id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getElection(@PathVariable("election_id") String anElectionId) {
        return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<String> newElection(@Valid @RequestBody NewElectionCommand aCommand) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/{election_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateElection(@PathVariable("election_id") String anElectionId,
                                                 @Valid @RequestBody ElectionUpdateCommand aCommand) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/{election_id}/date", method = RequestMethod.PUT)
    public ResponseEntity<String> updateElectionDate(@PathVariable("election_id") String anElectionId,
                                                     @Valid @RequestBody ElectionDateUpdateCommand aCommand) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }


}
