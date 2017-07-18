package id.rojak.analytics.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class MediaStatisticController {

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/statistics", method = RequestMethod.GET)
    public ResponseEntity<String> mediaStatisticInElection(@PathVariable("media_id") String aMediaId,
                                              @PathVariable("election_id") String anElectionId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/candidates", method = RequestMethod.GET)
    public ResponseEntity<String> candidateSupported(@PathVariable("media_id") String aMediaId,
                                                     @PathVariable("election_id") String anElectionId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }
}
