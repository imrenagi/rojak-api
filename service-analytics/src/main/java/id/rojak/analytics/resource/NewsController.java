package id.rojak.analytics.resource;

import id.rojak.analytics.application.news.InsertSentimentCommand;
import id.rojak.analytics.application.news.NewNewsCommand;
import id.rojak.analytics.application.news.NewsApplicationService;
import id.rojak.analytics.domain.model.news.News;
import id.rojak.analytics.resource.dto.MetaDTO;
import id.rojak.analytics.resource.dto.NewsCollectionDTO;
import id.rojak.analytics.resource.dto.NewsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsApplicationService newsApplicationService;

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news", method = RequestMethod.GET)
    public ResponseEntity<NewsCollectionDTO> newsFromMedia(
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer size) {

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

    @PreAuthorize("#oauth2.hasScope('spider')")
    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/news/{news_id}",
            method = RequestMethod.POST)
    public ResponseEntity<NewsDTO> insertSentimentFor(
            @PathVariable("election_id") String electionId,
            @PathVariable("media_id") String mediaId,
            @PathVariable("news_id") String newsId,
            @Valid @RequestBody InsertSentimentCommand command) {

        News news = this.newsApplicationService
                .insertSentiments(electionId,
                        mediaId,
                        newsId,
                        command);

        NewsDTO newsDTO = new NewsDTO(news.newsId().id(),
                news.title(),
                news.url(),
                news.content(),
                news.timestamp().getTime(),
                news.sentiments()
                        .stream()
                        .map(newsSentiment -> newsSentiment.shortText())
                        .collect(Collectors.toList()));

        return new ResponseEntity<>(newsDTO, HttpStatus.OK);

    }

    @PreAuthorize("#oauth2.hasScope('spider')")
    @RequestMapping(path = "/elections/{election_id}/news",
            method = RequestMethod.GET)
    public ResponseEntity<NewsCollectionDTO> getNewsWithDateRange(
            @PathVariable("election_id") String electionId,
            @RequestParam(value = "start_date")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "end_date")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "9999") Integer size) {

        Page<News> news = this.newsApplicationService
                .allNewsBy(electionId,
                        Date.from(startDate.atStartOfDay(
                                ZoneId.systemDefault()).toInstant()),
                        Date.from(endDate.atStartOfDay(
                                ZoneId.systemDefault()).toInstant()),
                        new PageRequest(page, size));

        List<NewsDTO> newsDTO = news.getContent().stream()
                .map((News aNews) -> {
                    return new NewsDTO(
                            aNews.newsId().id(),
                            aNews.title(),
                            aNews.url(),
                            aNews.content(),
                            aNews.timestamp().getTime(),
                            null);
                }).collect(Collectors.toList());

        return new ResponseEntity<>(new NewsCollectionDTO(
                newsDTO,
                new MetaDTO(page,
                        news.getTotalPages(),
                        news.getTotalElements())
        ), HttpStatus.OK);
    }
}
