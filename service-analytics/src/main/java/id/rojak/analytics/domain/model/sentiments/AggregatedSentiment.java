package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.domain.model.sentiments.ChartPoint;

import java.util.Date;

/**
 * Created by inagi on 7/20/17.
 */
public class AggregatedSentiment extends ValueObject implements ChartPoint<Date, Long> {

    private ElectionId electionId;
    private CandidateId candidateId;
    private MediaId mediaId;
    private Date date;
    private SentimentType sentimentType;
    private Long count;

    public AggregatedSentiment(ElectionId electionId,
                               CandidateId candidateId,
                               MediaId mediaId,
                               Date date,
                               SentimentType sentimentType,
                               Long count) {
        super();

        this.electionId = electionId;
        this.candidateId = candidateId;
        this.mediaId = mediaId;
        this.sentimentType = sentimentType;
        this.count = count;
        this.date = date;
    }

    public AggregatedSentiment(ElectionId electionId,
                               CandidateId candidateId,
                               Date date,
                               SentimentType sentimentType,
                               Long count) {
        this(electionId, candidateId, null, date, sentimentType, count);
    }

    public AggregatedSentiment(ElectionId electionId,
                               CandidateId candidateId,
                               MediaId mediaId,
                               SentimentType sentimentType,
                               Long count) {
        this(electionId, candidateId, mediaId, null, sentimentType, count);
    }

    public ElectionId getElectionId() {
        return electionId;
    }

    public CandidateId getCandidateId() {
        return candidateId;
    }

    public MediaId getMediaId() {
        return mediaId;
    }

    public Date getDate() {
        return date;
    }

    public SentimentType getSentimentType() {
        return sentimentType;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public Date xValue() {
        return getDate();
    }

    @Override
    public Long yValue() {
        return getCount();
    }
}
