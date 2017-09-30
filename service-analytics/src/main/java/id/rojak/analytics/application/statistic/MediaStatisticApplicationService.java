package id.rojak.analytics.application.statistic;

import id.rojak.analytics.application.election.ElectionApplicationService;
import id.rojak.analytics.application.media.MediaApplicationService;
import id.rojak.analytics.clients.ElectionServiceClient;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.common.error.ResourceNotFoundException;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaId;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentRepository;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.domain.model.sentiments.AggregatedSentiment;
import id.rojak.analytics.domain.model.sentiments.CandidateNewsCounter;
import id.rojak.analytics.resource.dto.CandidateDTO;
import id.rojak.analytics.resource.dto.CandidateStatisticDTO;
import id.rojak.analytics.resource.dto.MediaDTO;
import id.rojak.analytics.resource.dto.StatisticDTO;
import id.rojak.analytics.resource.dto.chart.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by inagi on 7/26/17.
 */
@Service("mediaStatisticApplicationService")
public class MediaStatisticApplicationService extends StatisticApplicationService {

    private final Logger log = LoggerFactory.getLogger(MediaStatisticApplicationService.class);

    @Autowired
    private MediaApplicationService mediaApplicationService;

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @Autowired
    private ElectionApplicationService electionApplicationService;

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

        List<AggregatedSentiment> sentiments =
                this.newsSentimentRepository
                        .sentimentsIn(electionId,
                                mediaId,
                                sentimentType,
                                fromDate,
                                toDate);

        List<Date> dateSeries =
                DateHelper.daysBetween(fromDate, toDate);

        Map<CandidateId, List<AggregatedSentiment>> candidateMap =
                this.groupSentimentsByCandidateId(sentiments);

        Map<CandidateId, List<Long>> candidateCounts = new HashMap<>();

        for (Map.Entry<CandidateId, List<AggregatedSentiment>> entry : candidateMap.entrySet()) {

            List<Long> counts =
                    this.fillEmptyGapFor(entry.getValue(), dateSeries);

            candidateCounts.put(entry.getKey(), counts);
        }

        return candidateCounts;
    }


//    public Candidate candidate(String electionId, String candidateId) {
//
//        //TODO add cache checking later
//        return this.electionServiceClient
//                .candidate(electionId, candidateId);
//    }

    public Map<CandidateId, List<AggregatedSentiment>> groupSentimentsByCandidateId(List<AggregatedSentiment> sentiments) {

        Map<CandidateId, List<AggregatedSentiment>> candidateMap = new HashMap<>();

        for (AggregatedSentiment sentiment : sentiments) {

            if (candidateMap.containsKey(sentiment.getCandidateId())) {

                List<AggregatedSentiment> candidateSentiments = candidateMap.get(
                        sentiment.getCandidateId());
                candidateSentiments.add(sentiment);

            } else {

                List<AggregatedSentiment> candidateSentiments = new ArrayList<>();
                candidateSentiments.add(sentiment);
                candidateMap.put(sentiment.getCandidateId(), candidateSentiments);
            }
        }

        return candidateMap;
    }

    public List<MediaDTO> groupedCandidateSentiments(String electionId) {

        List<MediaDTO> result = new ArrayList<>();

        Map<MediaId, List<CandidateNewsCounter>> mediaMap = new HashMap<>();

        List<CandidateNewsCounter> counters =
                this.newsSentimentRepository
                        .getCandidateSentiments(new ElectionId(electionId));

        for (CandidateNewsCounter aCounter : counters) {
            List<CandidateNewsCounter> candidates;
            if (mediaMap.containsKey(aCounter.mediaId())) {
                candidates = mediaMap.get(aCounter.mediaId());
            } else {
                candidates = new ArrayList<>();
            }
            candidates.add(aCounter);
            mediaMap.put(aCounter.mediaId(), candidates);
        }

        for (Map.Entry<MediaId, List<CandidateNewsCounter>> entry : mediaMap.entrySet()) {

            Media media = null;
            try {
                media = this.mediaApplicationService.media(entry.getKey().id());
            } catch (ResourceNotFoundException e) {
                continue;
            }

            List<CandidateStatisticDTO> candidateStatisticDTO =
                    entry.getValue()
                            .stream()
                            .map(counter -> {
                                CandidateDTO candidateDTO =
                                        this.electionApplicationService
                                                .candidate(electionId,
                                                        counter.candidateId().id());

                                return new CandidateStatisticDTO(
                                        candidateDTO,
                                        new StatisticDTO(
                                                counter.totalSentiment(),
                                                counter.numOfPositiveSentiment(),
                                                counter.numOfNegativeSentiment(),
                                                counter.numOfNeutralSentiment()));
                            })
                            .collect(Collectors.toList());

            MediaDTO dto = new MediaDTO(
                    media.mediaId().id(),
                    media.name(),
                    media.websiteUrl(),
                    media.logo(),
                    candidateStatisticDTO);

            result.add(dto);

        }
        return result;
    }


}
