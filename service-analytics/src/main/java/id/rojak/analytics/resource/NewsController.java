package id.rojak.analytics.resource;

import id.rojak.analytics.application.news.InsertSentimentCommand;
import id.rojak.analytics.application.news.NewNewsCommand;
import id.rojak.analytics.application.news.NewsApplicationService;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.news.*;
import id.rojak.analytics.resource.dto.MetaDTO;
import id.rojak.analytics.resource.dto.NewsCollectionDTO;
import id.rojak.analytics.resource.dto.NewsDTO;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsApplicationService newsApplicationService;

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news" , method = RequestMethod.GET)
    public ResponseEntity<NewsCollectionDTO> newsFromMedia(
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId,
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="limit", defaultValue="10") Integer size) {

        Page<News> news = this.newsApplicationService.allNewsBy(aMediaId, anElectionId,
                new PageRequest(page, size));

        List<NewsDTO> newsDTO = news.getContent().stream()
                .map((News aNews) -> {
                    return new NewsDTO(
                            aNews.newsId().id(),
                            aNews.title(),
                            aNews.url(),
                            aNews.content(),
                            aNews.timestamp().getTime(),
                            aNews.sentiments().stream()
                                    .map(newsSentiment -> newsSentiment.shortText())
                                    .collect(Collectors.toList()));
                }).collect(Collectors.toList());

        return new ResponseEntity<>(new NewsCollectionDTO(
                newsDTO,
                new MetaDTO(page,
                        news.getTotalPages(),
                        news.getTotalElements())
        ), HttpStatus.OK);
    }

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news", method = RequestMethod.POST)
    public ResponseEntity<NewsDTO> newNewsAndSentiment(
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId,
            @Valid @RequestBody NewNewsCommand command) {

        News aNews = this.newsApplicationService.insertNews(anElectionId, aMediaId, command);

        return new ResponseEntity<>(new NewsDTO(
                aNews.newsId().id(),
                aNews.title(),
                aNews.url(),
                aNews.content(),
                aNews.timestamp().getTime(),
                aNews.sentiments().stream()
                        .map(newsSentiment -> newsSentiment.shortText())
                        .collect(Collectors.toList())
                ), HttpStatus.OK);
    }

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news/{news_id}",
            method = RequestMethod.POST)
    public ResponseEntity<String> insertSentimentFor(
                    @PathVariable("election_id") String electionId,
            @PathVariable("media_id") String mediaId,
            @PathVariable("news_id") String newsId,
            @Valid @RequestBody InsertSentimentCommand command) {

        return new ResponseEntity<String>("",HttpStatus.NOT_IMPLEMENTED);

    }

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news/raw",
        method = RequestMethod.GET)
    public ResponseEntity<String> getNewsWithDateRange(
            @PathVariable("election_id") String electionId,
            @PathVariable("media_id") String mediaId,
            @RequestParam(value="start_date") Date startDate,
            @RequestParam(value="end_date") Date endDate) {
        return new ResponseEntity<String>("",HttpStatus.NOT_IMPLEMENTED);
    }
}
