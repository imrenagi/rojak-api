package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.sentiments.MediaSentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by inagi on 7/20/17.
 */
@Service
public class NewsSentimentService {

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    public SentimentType sentimentOfMedia(MediaSentiment sentiment) {

        //TODO update this with real computation
        return SentimentType.UNKNOWN;
    }





}
