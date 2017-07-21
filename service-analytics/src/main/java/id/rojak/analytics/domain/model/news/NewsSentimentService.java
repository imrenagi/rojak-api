package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.sentiments.MediaSentimentGroup;
import id.rojak.analytics.domain.model.sentiments.MediaNewsCount;
import id.rojak.analytics.domain.model.sentiments.SentimentClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by inagi on 7/20/17.
 */
@Service
public class NewsSentimentService {

    private static final Logger log = LoggerFactory.getLogger(NewsSentimentService.class);

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @Autowired
    private SentimentClassifier sentimentClassifier;

    private SentimentType sentimentOfMedia(MediaNewsCount sentiment) {

        return sentimentClassifier.calculate(sentiment.totalPositiveNews(),
                sentiment.totalNegativeNews(),
                sentiment.totalNeutralNews());

    }

    public MediaSentimentGroup sentimentGroupsFor(ElectionId electionId, CandidateId candidateId) {

        List<SentimentCount> sentiments = this.newsSentimentRepository
                .sentimentsGroupedByMediaAndSentiment(electionId, candidateId);

        Map<MediaId, MediaNewsCount> mediaMap =
                this.groupSentimentsByMedia(sentiments);

        return this.groupBySentimentType(candidateId, mediaMap);
    }

    private Map<MediaId, MediaNewsCount> groupSentimentsByMedia(List<SentimentCount> sentiments) {

        Map<MediaId, MediaNewsCount> mediaMap = new HashMap<>();

        for (SentimentCount sentiment : sentiments) {

            MediaNewsCount mediaNewsCount;

            if (mediaMap.containsKey(sentiment.getMediaId())) {
                mediaNewsCount = mediaMap.get(sentiment.getMediaId());
            } else {
                mediaNewsCount = new MediaNewsCount(sentiment.getMediaId());
            }

            mediaNewsCount.insert(sentiment.getSentimentType(),
                    sentiment.getCount());

            mediaMap.put(mediaNewsCount.mediaId(), mediaNewsCount);
        }

        return mediaMap;
    }

    private MediaSentimentGroup groupBySentimentType(CandidateId candidateId,
                                                     Map<MediaId, MediaNewsCount> mediaMap) {

        MediaSentimentGroup candidate = new MediaSentimentGroup(candidateId);

        for (MediaNewsCount mediaNewsCount : mediaMap.values()) {
            SentimentType sentimentOfAMedia =
                    this.sentimentOfMedia(mediaNewsCount);

            candidate.insert(sentimentOfAMedia, mediaNewsCount);
        }
        return candidate;
    }


}
