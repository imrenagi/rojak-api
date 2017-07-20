package id.rojak.analytics.resource;

import id.rojak.analytics.application.news.NewNewsCommand;
import id.rojak.analytics.application.news.NewsApplicationService;
import id.rojak.analytics.domain.model.news.News;
import id.rojak.analytics.resource.dto.MetaDTO;
import id.rojak.analytics.resource.dto.NewsCollectionDTO;
import id.rojak.analytics.resource.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class NewsController {

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

        List<String> dummy = new ArrayList<>();

        List<NewsDTO> newsDTO = news.getContent().stream()
                .map(aNews -> new NewsDTO(
                        aNews.title(),
                        aNews.url(),
                        aNews.content(),
                        aNews.timestamp().getTime(),
                        dummy //TODO change this
                )).collect(Collectors.toList());

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
                aNews.title(),
                aNews.url(),
                aNews.content(),
                aNews.timestamp().getTime(),
                null
                ), HttpStatus.OK);
    }
}
