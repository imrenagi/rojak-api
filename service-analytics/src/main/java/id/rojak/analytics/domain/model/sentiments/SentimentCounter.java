package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;

/**
 * Created by imrenagi on 9/24/17.
 */
public class SentimentCounter extends ValueObject {

    private long positive;
    private long negative;
    private long neutral;

    protected SentimentCounter() {
        this(0,0,0);
    }

    public SentimentCounter(long positive, long negative, long neutral) {
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
    }

    public long numOfPositiveSentiment() {
        return positive;
    }

    public long numOfNegativeSentiment() {
        return negative;
    }

    public long numOfNeutralSentiment() {
        return neutral;
    }

    public long totalSentiment() {
        return this.positive + this.negative + this.neutral;
    }
}
