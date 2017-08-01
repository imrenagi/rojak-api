package id.rojak.analytics.resource;

import id.rojak.analytics.application.media.MediaApplicationService;
import id.rojak.analytics.application.statistic.MediaStatisticApplicationService;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.Candidate;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.resource.dto.*;
import id.rojak.analytics.resource.dto.chart.ChartDTO;
import id.rojak.analytics.resource.dto.chart.Series;
import id.rojak.analytics.resource.dto.chart.XAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class MediaStatisticController {

    @Autowired
    private MediaApplicationService mediaApplicationService;

    @Resource(name = "mediaStatisticApplicationService")
    private MediaStatisticApplicationService mediaStatisticApplicationService;

    @RequestMapping(path = "/elections/{election_id}/medias/{media_id}/statistics", method = RequestMethod.GET)
    public ResponseEntity<MediaStatisticDTO> mediaStatisticInElection(
            @RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId) {

        Date fromDate = Date
                .from(startDate.atStartOfDay(ZoneId.systemDefault())
                        .toInstant());

        Date toDate = Date
                .from(endDate.atStartOfDay(ZoneId.systemDefault())
                        .toInstant());

        List<Date> date = DateHelper.daysBetween(fromDate, toDate);

        List<Series> positiveSeries = this.mediaStatisticApplicationService
                .positiveSentimentSeriesFor(anElectionId,
                        aMediaId,
                        fromDate, toDate);

        List<Series> negativeSeries = this.mediaStatisticApplicationService
                .negativeSentimentSeriesFor(anElectionId,
                        aMediaId,
                        fromDate, toDate);

        List<Series> neutralSeries = this.mediaStatisticApplicationService
                .neutralSentimentSeriesFor(anElectionId,
                        aMediaId,
                        fromDate, toDate);

        ChartDTO positiveSentimentsChart = new ChartDTO(
                new XAxis(date),
                positiveSeries);

        ChartDTO negativeSentimentsChart = new ChartDTO(
                new XAxis(date),
                negativeSeries);

        ChartDTO neutralSentimentsChart = new ChartDTO(
                new XAxis(date),
                neutralSeries);

        MediaStatisticDTO dto =
                new MediaStatisticDTO(
                        positiveSentimentsChart,
                        negativeSentimentsChart,
                        neutralSentimentsChart);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(path = "/elections/{election_id}/medias", method = RequestMethod.GET)
    public ResponseEntity<MediaCollectionDTO> mediaOfAnElection(
            @PathVariable("election_id") String anElectionId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer size) {

        Page<Media> medias = this.mediaApplicationService
                .allMedias(new PageRequest(page, size));

        List<MediaDTO> mediaDTOs = medias.getContent().stream()
                .map(media -> {
                            CandidateId candidateId =
                                    this.mediaStatisticApplicationService
                                            .topCandidateFor(anElectionId, media.mediaId().id());

                            Candidate candidate =
                                    this.mediaStatisticApplicationService
                                            .candidate(anElectionId, candidateId.id());

                            //TODO refactor this
                            if (candidate != null) {
                                return new MediaDTO(
                                        media.mediaId().id(),
                                        media.name(),
                                        media.websiteUrl(),
                                        media.logo(),
                                        new CandidateDTO(
                                                candidate.getCandidateNumber(),
                                                candidate.getName(),
                                                candidate.getImageUrl()
                                        ));
                            } else {
                                return new MediaDTO(
                                        media.mediaId().id(),
                                        media.name(),
                                        media.websiteUrl(),
                                        media.logo());
                            }
                        }
                )
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                new MediaCollectionDTO(mediaDTOs,
                        new MetaDTO(page,
                                medias.getTotalPages(),
                                medias.getTotalElements())), HttpStatus.OK);
    }

    @RequestMapping(path = "/elections/{election_id}/medias/{media_id}/candidates", method = RequestMethod.GET)
    public ResponseEntity<String> candidateSupportedBy(@PathVariable("media_id") String aMediaId,
                                                       @PathVariable("election_id") String anElectionId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

}
