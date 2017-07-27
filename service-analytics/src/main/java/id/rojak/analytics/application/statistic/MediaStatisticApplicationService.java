package id.rojak.analytics.application.statistic;

import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentService;
import id.rojak.analytics.domain.model.news.SentimentCount;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.resource.dto.chart.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by inagi on 7/26/17.
 */
@Service
public class MediaStatisticApplicationService extends StatisticApplicationService {

    private final Logger log = LoggerFactory.getLogger(MediaStatisticApplicationService.class);

    @Autowired
    private NewsSentimentService newsSentimentService;

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public List<Series> positiveSentimentSeriesFor(
            String electionId,
            String mediaId,
            Date fromDate,
            Date toDate) {

        return this.sentimentSeriesFor(electionId,
                mediaId,
                SentimentType.POSITIVE,
                fromDate,
                toDate);
    }

    public List<Series> negativeSentimentSeriesFor(
            String electionId,
            String mediaId,
            Date fromDate,
            Date toDate) {

        return this.sentimentSeriesFor(electionId,
                mediaId,
                SentimentType.NEGATIVE,
                fromDate,
                toDate);
    }

    public List<Series> neutralSentimentSeriesFor(
            String electionId,
            String mediaId,
            Date fromDate,
            Date toDate) {

        return this.sentimentSeriesFor(electionId,
                mediaId,
                SentimentType.NEUTRAL,
                fromDate,
                toDate);
    }

    @Transactional
    private List<Series> sentimentSeriesFor(
            String electionId,
            String mediaId,
            SentimentType sentimentType,
            Date fromDate,
            Date toDate) {

        Map<CandidateId, List<Long>> candidateMap =
                this.candidatesSentimentSeriesFor(
                        new ElectionId(electionId),
                        new MediaId(mediaId),
                        sentimentType,
                        fromDate,
                        toDate);

        List<Series> listOfSeries = new ArrayList<>();

        for (Map.Entry<CandidateId, List<Long>> entry : candidateMap.entrySet()) {
            CandidateId candidateId = entry.getKey();
            List<Long> values = entry.getValue();

            listOfSeries.add(
                    new Series(
                            String.format("%s", candidateId.id()),
                            values));
        }

        return listOfSeries;
    }

    @Transactional
    private Map<CandidateId, List<Long>> candidatesSentimentSeriesFor(
            ElectionId electionId,
            MediaId mediaId,
            SentimentType sentimentType,
            Date fromDate,
            Date toDate) {

        List<SentimentCount> sentiments =
                this.newsSentimentRepository
                        .sentimentsIn(electionId,
                                mediaId,
                                sentimentType,
                                fromDate,
                                toDate);

        List<Date> dateSeries =
                DateHelper.daysBetween(fromDate, toDate);

        Map<CandidateId, List<SentimentCount>> candidateMap =
                this.newsSentimentService
                        .groupSentimentsByCandidateId(sentiments);

        Map<CandidateId, List<Long>> candidateCounts = new HashMap<>();

        for (Map.Entry<CandidateId, List<SentimentCount>> entry : candidateMap.entrySet()) {

            List<Long> counts =
                    this.fillEmptyGapFor(dateSeries, entry.getValue());

            candidateCounts.put(entry.getKey(), counts);
        }

        return candidateCounts;
    }

}
