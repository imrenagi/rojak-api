package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.ValueObject;
import id.rojak.analytics.domain.model.media.MediaId;

/**
 * Created by inagi on 7/20/17.
 */
public class MediaSentiment extends ValueObject {

    private MediaId mediaId;

    private Long positive;
    private Long negative;
    private Long neutral;

    public MediaSentiment(MediaId mediaId,
                          Long positive,
                          Long negative,
                          Long neutral) {
        super();

        this.mediaId = mediaId;
        this.positive = positive;
        this.negative = negative;
        this.neutral = neutral;
    }

    public MediaId getMediaId() {
        return mediaId;
    }

    public Long getPositive() {
        return positive;
    }

    public Long getNegative() {
        return negative;
    }

    public Long getNeutral() {
        return neutral;
    }
}
