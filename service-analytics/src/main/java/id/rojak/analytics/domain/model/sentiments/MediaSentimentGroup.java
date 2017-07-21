package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.news.SentimentType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inagi on 7/20/17.
 */
public class MediaSentimentGroup extends ValueObject {

    private CandidateId candidateId;

    private List<MediaNewsCount> positiveMedias;
    private List<MediaNewsCount> negativeMedias;
    private List<MediaNewsCount> neutralMedias;

    public MediaSentimentGroup(CandidateId candidateId) {
        this.candidateId = candidateId;

        this.positiveMedias = new ArrayList<>();
        this.negativeMedias = new ArrayList<>();
        this.neutralMedias = new ArrayList<>();
    }

    public CandidateId candidateId() {
        return candidateId;
    }

    public List<MediaNewsCount> getPositiveMedias() {
        return positiveMedias;
    }

    public List<MediaNewsCount> getNegativeMedias() {
        return negativeMedias;
    }

    public List<MediaNewsCount> getNeutralMedias() {
        return neutralMedias;
    }

    public void insert(SentimentType sentimentType,
                       MediaNewsCount mediaNewsCount) {

        if (sentimentType.isPositive()) {
            this.getPositiveMedias()
                    .add(mediaNewsCount);

        } else if (sentimentType.isNegative()) {
            this.getNegativeMedias()
                    .add(mediaNewsCount);

        } else if (sentimentType.isNeutral()) {
            this.getNeutralMedias()
                    .add(mediaNewsCount);
        }

    }
}
