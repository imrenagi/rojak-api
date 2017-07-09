package id.rojak.election.resource;

import id.rojak.election.application.election.ElectionApplicationService;
import id.rojak.election.domain.model.election.Election;
import id.rojak.election.resource.dto.ElectionCollectionDTO;
import id.rojak.election.resource.dto.ElectionDTO;
import id.rojak.election.resource.dto.MetaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
