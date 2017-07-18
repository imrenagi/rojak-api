package id.rojak.analytics.resource;

import id.rojak.analytics.application.news.NewNewsCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class NewsController {

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news" , method = RequestMethod.GET)
    public ResponseEntity<String> newsFromMedia(
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="limit", defaultValue="10") Integer size) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news", method = RequestMethod.POST)
    public ResponseEntity<String> newNewsAndSentiment(
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId,
            @Valid @RequestBody NewNewsCommand command) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }
}
