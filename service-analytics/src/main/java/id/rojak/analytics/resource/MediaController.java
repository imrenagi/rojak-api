package id.rojak.analytics.resource;

import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.News;
import id.rojak.analytics.domain.model.news.NewsRepository;
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

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getAllMedias() {

        Media media = mediaRepository.findByMediaId(new MediaId("kompascom"));

        if (media != null) {
            log.info(String.format("Get the media Id %s ", media.name()));
            log.info(String.format("Get the media social media %s", media.socialMedia().facebookUrl()));
        }

        List<News> allNews = newsRepository.findByMediaId(new MediaId("kompascom"));

        if (allNews != null) {
            for (News news : allNews) {
                log.info(String.format("Get the news %s from media %s", news.title(), news.media().name()));
            }
        }

        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
