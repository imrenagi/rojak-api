package id.rojak.analytics.resource;

import id.rojak.analytics.application.statistic.CandidateStatisticApplicationService;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.news.NewsSentimentRepository;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.domain.model.sentiments.MediaNewsCount;
import id.rojak.analytics.domain.model.sentiments.MediaSentimentGroup;
import id.rojak.analytics.resource.dto.CandidateMediaDTO;
import id.rojak.analytics.resource.dto.CandidateStatSummaryDTO;
import id.rojak.analytics.resource.dto.MediaDTO;
import id.rojak.analytics.resource.dto.MediaNewsCountDTO;
import id.rojak.analytics.resource.dto.chart.ChartDTO;
import id.rojak.analytics.resource.dto.chart.Series;
import id.rojak.analytics.resource.dto.chart.XAxis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class CandidateStatisticController {

    private final Logger log = LoggerFactory.getLogger(CandidateStatisticController.class);

    @Autowired
    private CandidateStatisticApplicationService candidateStatisticApplicationService;

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/statistics",
            method = RequestMethod.GET)
    public ResponseEntity<ChartDTO> candidateNewsPerDayStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        Date startDate = DateHelper.nMonthAgoOf(1, new Date());
        Date endDate = new Date();

        List<Date> dateSeries = DateHelper.daysBetween(startDate, endDate);

        Series<Long> positiveSeries = new Series<>(
                "# Positive News",
                this.candidateStatisticApplicationService
                        .sentimentsOverTime(anElectionId, aCandidateId,
                                SentimentType.POSITIVE, startDate, endDate, dateSeries));

        Series<Long> negativeSeries = new Series<>(
                "# Negative News",
                this.candidateStatisticApplicationService
                        .sentimentsOverTime(anElectionId, aCandidateId,
                                SentimentType.NEGATIVE, startDate, endDate, dateSeries));

        Series<Long> neutralSeries = new Series<>(
                "# Neutral News",
                this.candidateStatisticApplicationService
                        .sentimentsOverTime(anElectionId, aCandidateId,
                                SentimentType.NEUTRAL, startDate, endDate, dateSeries));

        ChartDTO chartDTO = new ChartDTO(
                new XAxis<>(dateSeries),
                new ArrayList<Series>() {{
                    add(positiveSeries);
                    add(negativeSeries);
                    add(neutralSeries);
                }});

        return new ResponseEntity<>(chartDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/medias",
            method = RequestMethod.GET)
    public ResponseEntity<CandidateMediaDTO> candidateMediaList(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        MediaSentimentGroup sentimentGroup =
                this.candidateStatisticApplicationService.mediaAbout(anElectionId, aCandidateId);

        List<MediaDTO> positives = sentimentGroup.getPositiveMedias().stream()
                .map(this.newsCountToMediaDTO()).collect(Collectors.toList());

        List<MediaDTO> negatives = sentimentGroup.getNegativeMedias().stream()
                .map(this.newsCountToMediaDTO()).collect(Collectors.toList());

        List<MediaDTO> neutrals = sentimentGroup.getNeutralMedias().stream()
                .map(this.newsCountToMediaDTO()).collect(Collectors.toList());

        CandidateMediaDTO dto = new CandidateMediaDTO(positives, negatives, neutrals);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private Function<MediaNewsCount, MediaDTO> newsCountToMediaDTO() {
        return new Function<MediaNewsCount, MediaDTO>() {
            @Override
            public MediaDTO apply(MediaNewsCount newsCount) {
                Media media = newsCount.media();
                return new MediaDTO(media.mediaId().id(),
                        media.name(),
                        media.websiteUrl(),
                        media.websiteUrl(), //todo change with logo url
                        new MediaNewsCountDTO(newsCount.totalPositiveNews(),
                                newsCount.totalNegativeNews(),
                                newsCount.totalNeutralNews()));
            }
        };
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/stat_summary",
            method = RequestMethod.GET)
    public ResponseEntity<CandidateStatSummaryDTO> candidateMediaSummaryStatistic(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        MediaSentimentGroup sentimentGroup =
                this.candidateStatisticApplicationService.mediaSentimentGroupOf(anElectionId, aCandidateId);

        CandidateStatSummaryDTO statSummary = new CandidateStatSummaryDTO(
                sentimentGroup.getPositiveMedias().size(),
                sentimentGroup.getNegativeMedias().size(),
                sentimentGroup.getNeutralMedias().size());

        return new ResponseEntity<>(statSummary, HttpStatus.OK);
    }

}
