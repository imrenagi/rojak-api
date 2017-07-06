package id.rojak.election.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by inagi on 7/4/17.
 */
@RestController
public class ElectionController {

    private static final Logger log = LoggerFactory.getLogger(ElectionController.class);

//    @Autowired
//    private ElectionApplicationService electionApplicationService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    private ResponseEntity<String> getAllElection() {
        return new ResponseEntity<>("This is result", HttpStatus.OK);
    }
//    public ResponseEntity<ElectionCollectionDTO> getAllElection(@PathVariable String electionId) {
//
//        List<Election> elections = this.electionApplicationService().allElections();
//
//        return new ResponseEntity<>(new ElectionCollectionDTO(
//                elections.stream().map(election -> new ElectionDTO(election)).collect(Collectors.toSet()),
//                new MetaDTO(1,1,1)), HttpStatus.OK);
//    }

//    protected ElectionApplicationService electionApplicationService() {
//        return this.electionApplicationService;
//    }


}
