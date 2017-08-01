package id.rojak.analytics.resource;

import id.rojak.analytics.application.media.MediaApplicationService;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class CandidateStatisticController {

    @Resource(name = "candidateStatisticApplicationService")
    private CandidateStatisticApplicationService candidateStatisticApplicationService;

    @Autowired
    private MediaApplicationService mediaApplicationService;

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/statistics",
            method = RequestMethod.GET)
    public ResponseEntity<ChartDTO> candidateNewsPerDayStatistic(
            @RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        Date fromDate = Date
                .from(startDate.atStartOfDay(ZoneId.systemDefault())
                        .toInstant());

        Date toDate = Date
                .from(endDate.atStartOfDay(ZoneId.systemDefault())
                        .toInstant());

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(fromDate) + "-->" + df.format(toDate));


        Series<Long> positiveSeries = new Series<>(
                "# Positive News",
                this.candidateStatisticApplicationService
                        .sentimentsOverTime(anElectionId, aCandidateId,
                                SentimentType.POSITIVE, fromDate, toDate));

        Series<Long> negativeSeries = new Series<>(
                "# Negative News",
                this.candidateStatisticApplicationService
                        .sentimentsOverTime(anElectionId, aCandidateId,
                                SentimentType.NEGATIVE, fromDate, toDate));

        Series<Long> neutralSeries = new Series<>(
                "# Neutral News",
                this.candidateStatisticApplicationService
                        .sentimentsOverTime(anElectionId, aCandidateId,
                                SentimentType.NEUTRAL, fromDate, toDate));

        ChartDTO chartDTO = new ChartDTO(
                new XAxis<>(DateHelper.daysBetween(fromDate, toDate)),
                new ArrayList<Series>() {{
                    add(positiveSeries);
                    add(negativeSeries);
                    add(neutralSeries);
                }});

        return new ResponseEntity<>(chartDTO, HttpStatus.OK);
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/medias",
            method = RequestMethod.GET)
    public ResponseEntity<CandidateMediaDTO> groupedMediaBySentimentFor(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        MediaSentimentGroup sentimentGroup =
                this.candidateStatisticApplicationService
                        .mediaAbout(anElectionId, aCandidateId);

        List<MediaDTO> positives =
                this.convertFrom(sentimentGroup.getPositiveMedias());

        List<MediaDTO> negatives =
                this.convertFrom(sentimentGroup.getNegativeMedias());

        List<MediaDTO> neutrals =
                this.convertFrom(sentimentGroup.getNeutralMedias());

        CandidateMediaDTO dto =
                new CandidateMediaDTO(positives, negatives, neutrals);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    private List<MediaDTO> convertFrom(List<MediaNewsCount> mediaNewsCounts) {
        return mediaNewsCounts.stream()
                .map(newsCount -> {
                    Media media =
                            this.mediaApplicationService
                                    .media(newsCount.mediaId().id());

                    return new MediaDTO(media.mediaId().id(),
                            media.name(),
                            media.websiteUrl(),
                            media.logo(),
                            new MediaNewsCountDTO(newsCount.totalPositiveNews(),
                                    newsCount.totalNegativeNews(),
                                    newsCount.totalNeutralNews()));
                }).collect(Collectors.toList());
    }

    @RequestMapping(path = "/elections/{election_id}/candidates/{candidate_id}/stat_summary",
            method = RequestMethod.GET)
    public ResponseEntity<CandidateStatSummaryDTO> numberOfMediaGroupedBySentiments(
            @PathVariable("election_id") String anElectionId,
            @PathVariable("candidate_id") String aCandidateId) {

        MediaSentimentGroup sentimentGroup =
                this.candidateStatisticApplicationService.mediaAbout(anElectionId, aCandidateId);

        CandidateStatSummaryDTO statSummary = new CandidateStatSummaryDTO(
                sentimentGroup.getPositiveMedias().size(),
                sentimentGroup.getNegativeMedias().size(),
                sentimentGroup.getNeutralMedias().size());

        return new ResponseEntity<>(statSummary, HttpStatus.OK);
    }

}
