package id.rojak.analytics.application.news;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by imrenagi on 7/19/17.
 */
@Service
public class NewsApplicationService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Transactional
    public Page<News> allNewsBy(String mediaId,
                                String electionId,
                                Pageable pageRequest) {

        Page<News> news = this.newsRepository.findByMediaIdAndElectionId(
                new MediaId(mediaId),
                new ElectionId(electionId),
                pageRequest);

        return news;

    }

    @Transactional
    public News insertNews(String electionId,
                           String mediaId,
                           NewNewsCommand aCommand) {

        Media media = this.mediaRepository.findByMediaId(new MediaId(mediaId));

        if (media == null) {
            throw new IllegalArgumentException(String.format(
                    "Media %s is not exist",
                    mediaId));
        }

        News news = new News(
                new NewsId(this.newsRepository.nextId()),
                aCommand.getTitle(),
                aCommand.getUrl(),
                aCommand.getContent(),
                new MediaId(mediaId),
                new ElectionId(electionId),
                new Date(aCommand.getTimestamp()));

        news.insertTo(media);

        for (CandidateSentimentCommand sentiment : aCommand.getSentiments()) {

            NewsSentiment newsSentiment = new NewsSentiment(
                    new MediaId(mediaId),
                    new ElectionId((electionId)),
                    new CandidateId(sentiment.getCandidateId()),
                    new Date(aCommand.getTimestamp()),
                    SentimentType.valueOf(sentiment.getSentiment()));

            newsSentiment.insertTo(news);
        }

        return news;
    }
}
