package id.rojak.analytics.resource;

import id.rojak.analytics.application.media.NewMediaCommand;
import id.rojak.analytics.application.media.UpdateMediaCommand;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.News;
import id.rojak.analytics.domain.model.news.NewsRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentRepository;
import id.rojak.analytics.domain.model.news.SentimentAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by imrenagi on 7/14/17.
 */
@RestController
public class MediaController {

    private static final Logger log = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsSentimentRepository sentimentRepository;

    @RequestMapping(path = "/medias", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getAllMedias() {

        List<SentimentAggregate> res = sentimentRepository.sentimentsGroupBySentimentAndMedia(
                new ElectionId("dkijakarta"),
                new CandidateId("ahok"),
                new MediaId("kompas"));

        log.info(String.format("The number of aggregate %d", res.size()));
        if (res != null) {
            for (SentimentAggregate agg : res) {
                log.info(String.format("Sentiment %s = %d",
                        agg.getSentimentType().name(),
                        agg.getCount()));
            }
        }

//        Media media = mediaRepository.findByMediaId(new MediaId("kompascom"));
//
//        if (media != null) {
//            log.info(String.format("Get the media Id %s ", media.name()));
//            log.info(String.format("Get the media social media %s", media.socialMedia().facebookUrl()));
//        }
//
//        List<News> allNews = newsRepository.findByMediaId(new MediaId("kompascom"));
//
//        if (allNews != null) {
//            for (News news : allNews) {
//                log.info(String.format("Get the news %s from media %s", news.title(), news.media().name()));
//            }
//        }

        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/medias", method = RequestMethod.POST)
    public ResponseEntity<String> createNewMedia(@Valid @RequestBody NewMediaCommand command) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/medias/{media_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateMedia(@Valid @RequestBody UpdateMediaCommand command) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/medias/{media_id}", method = RequestMethod.GET)
    public ResponseEntity<String> mediaDetail(@PathVariable("media_id") String aMediaId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }



}
