package id.rojak.analytics.application.statistic;

import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentService;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.domain.model.sentiments.AggregatedSentiment;
import id.rojak.analytics.domain.model.sentiments.MediaNewsCount;
import id.rojak.analytics.domain.model.sentiments.MediaSentimentGroup;
import id.rojak.analytics.domain.model.sentiments.SentimentClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by inagi on 7/20/17.
 */
@Primary
@Service("candidateStatisticApplicationService")
public class CandidateStatisticApplicationService extends StatisticApplicationService {

    private final Logger log = LoggerFactory.getLogger(CandidateStatisticApplicationService.class);

    @Autowired
    private NewsSentimentService newsSentimentService;

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private SentimentClassifier sentimentClassifier;

    @Transactional
    public MediaSentimentGroup mediaAbout(String anElectionId, String aCandidateId) {

        ElectionId electionId = new ElectionId(anElectionId);
        CandidateId candidateId = new CandidateId(aCandidateId);

        List<AggregatedSentiment> sentiments = this.newsSentimentRepository
                .sentimentsGroupedByMediaAndSentiment(
                        electionId,
                        candidateId);

        Map<MediaId, MediaNewsCount> mediaMap =
                this.groupSentimentsByMedia(sentiments);

        MediaSentimentGroup sentimentGroup =
                new MediaSentimentGroup(candidateId);

        for (MediaNewsCount newsCount : mediaMap.values()) {

            SentimentType mediaSentiment =
                    this.newsSentimentService
                            .sentimentTypeOf(newsCount);

            sentimentGroup.insert(mediaSentiment, newsCount);
        }

        return sentimentGroup;
    }


    private Map<MediaId, MediaNewsCount> groupSentimentsByMedia(List<AggregatedSentiment> sentiments) {

        Map<MediaId, MediaNewsCount> mediaMap = new HashMap<>();

        for (AggregatedSentiment sentiment : sentiments) {
            MediaNewsCount mediaNewsCount;

            if (mediaMap.containsKey(sentiment.getMediaId())) {
                mediaNewsCount =
                        mediaMap.get(sentiment.getMediaId());

            } else {
                mediaNewsCount =
                        new MediaNewsCount(sentiment.getMediaId());

            }

            mediaNewsCount.insert(sentiment.getSentimentType(),
                    sentiment.getCount());

            mediaMap.put(mediaNewsCount.mediaId(), mediaNewsCount);
        }

        return mediaMap;
    }

    public List<Long> sentimentsOverTime(String electionId, String candidateId,
                                         SentimentType sentimentType,
                                         Date startDate,
                                         Date endDate) {

        List<AggregatedSentiment> sentiments = this.newsSentimentRepository
                .sentimentsGroupedByDateAndSentiment(
                        new ElectionId(electionId),
                        new CandidateId(candidateId),
                        sentimentType,
                        startDate, endDate);

        List<Long> filledSeries =
                this.fillEmptyGapFor(
                        sentiments,
                        DateHelper.daysBetween(startDate, endDate));

        return filledSeries;
    }

}
