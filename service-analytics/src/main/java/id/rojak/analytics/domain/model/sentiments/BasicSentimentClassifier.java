package id.rojak.analytics.domain.model.sentiments;

import id.rojak.analytics.domain.model.news.SentimentType;
import org.springframework.stereotype.Service;

/**
 * Created by inagi on 7/20/17.
 */
@Service
public class BasicSentimentClassifier implements SentimentClassifier {

    @Override
    public SentimentType calculate(Long nPositive, Long nNegative, Long nNeutral) {
        long delta = nPositive - nNegative;

        SentimentType type;

        if (delta == 0) {
            type = SentimentType.NEUTRAL;
        } else if (delta > 0) {
            type = SentimentType.POSITIVE;
        } else {
            type = SentimentType.NEGATIVE;
        }

        return type;

    }
}
