package id.rojak.analytics.application.statistic;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentService;
import id.rojak.analytics.domain.model.sentiments.MediaNewsCount;
import id.rojak.analytics.domain.model.sentiments.MediaSentimentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by inagi on 7/20/17.
 */
@Service
public class CandidateStatisticApplicationService {

    @Autowired
    private NewsSentimentService newsSentimentService;

    @Autowired
    private MediaRepository mediaRepository;

    @Transactional
    public MediaSentimentGroup mediaSentimentGroupOf(String electionId, String candidateId) {

        MediaSentimentGroup sentimentGroup = this.newsSentimentService
                .sentimentGroupsFor(new ElectionId(electionId),
                        new CandidateId(candidateId));

        return sentimentGroup;

    }

    @Transactional
    public MediaSentimentGroup mediaAbout(String electionId, String candidateId) {

        MediaSentimentGroup sentimentGroup =
                this.mediaSentimentGroupOf(electionId, candidateId);

        this.fetchMediaFor(sentimentGroup.getPositiveMedias());
        this.fetchMediaFor(sentimentGroup.getNegativeMedias());
        this.fetchMediaFor(sentimentGroup.getNeutralMedias());

        return sentimentGroup;

    }

    private void fetchMediaFor(List<MediaNewsCount> newsCount) {

        for (MediaNewsCount mediaNewsCount : newsCount) {

            Media media = this.mediaRepository.findByMediaId(mediaNewsCount.mediaId());
            if (media == null) {
                throw new IllegalStateException(String.format(
                        "Media %s doesn't exist", mediaNewsCount.media()));
            }
            mediaNewsCount.setMedia(media);
        }
    }

}
