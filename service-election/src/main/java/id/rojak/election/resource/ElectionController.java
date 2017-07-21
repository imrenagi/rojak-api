package id.rojak.election.resource;

import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.application.election.ElectionDateUpdateCommand;
import id.rojak.election.application.election.ElectionDetailChangeCommand;
import id.rojak.election.application.election.NewElectionCommand;
import id.rojak.election.common.error.ResourceNotFoundException;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.resource.dto.ElectionCollectionDTO;
import id.rojak.election.resource.dto.ElectionDTO;
import id.rojak.election.resource.dto.MetaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<ElectionCollectionDTO> getAllElection(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer size) {

        Pageable pageRequest = new PageRequest(page, size);

        Page<Election> electionPage = this.electionApplicationService
                .allElections(pageRequest);

        List<ElectionDTO> elections = electionPage.getContent()
                .stream().map(election -> new ElectionDTO(election))
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new ElectionCollectionDTO(
                        elections,
                        new MetaDTO(
                                page,
                                electionPage.getTotalPages(),
                                electionPage.getTotalElements())), HttpStatus.OK);
    }

    @RequestMapping(path = "/{election_id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ElectionDTO> getElection(@PathVariable("election_id") String anElectionId)
            throws ResourceNotFoundException {

        Election election = this.electionApplicationService.election(anElectionId);

        if (election == null) {
            throw new ResourceNotFoundException(
                    String.format("Election %s is not found", anElectionId));
        }

        return new ResponseEntity<ElectionDTO>(new ElectionDTO(election), HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<ElectionDTO> newElection(@Valid @RequestBody NewElectionCommand aCommand) {

        Election election = this.electionApplicationService
                .newElection(aCommand);

        return new ResponseEntity<>(new ElectionDTO(election),
                HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{election_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateElection(@PathVariable("election_id") String anElectionId,
                                                 @Valid @RequestBody ElectionDetailChangeCommand aCommand) {

        aCommand.setElectionId(anElectionId);

        this.electionApplicationService.changeElectionDetail(aCommand);

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(path = "/{election_id}/date", method = RequestMethod.PUT)
    public ResponseEntity<String> updateElectionDate(@PathVariable("election_id") String anElectionId,
                                                     @Valid @RequestBody ElectionDateUpdateCommand aCommand) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }


}
