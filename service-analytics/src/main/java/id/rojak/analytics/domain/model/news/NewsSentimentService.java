package id.rojak.analytics.domain.model.news;

import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.sentiments.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public SentimentType sentimentTypeOf(NewsSentimentCount sentimentCount) {

        return this.sentimentClassifier.calculate(
                sentimentCount.totalPositiveNews(),
                sentimentCount.totalNegativeNews(),
                sentimentCount.totalNeutralNews());

    }

}
