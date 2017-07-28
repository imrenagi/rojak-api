package id.rojak.analytics.resource;

import id.rojak.analytics.application.statistic.MediaStatisticApplicationService;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.resource.dto.MediaStatisticDTO;
import id.rojak.analytics.resource.dto.chart.ChartDTO;
import id.rojak.analytics.resource.dto.chart.Series;
import id.rojak.analytics.resource.dto.chart.XAxis;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class MediaStatisticController {

    @Resource(name = "mediaStatisticApplicationService")
    private MediaStatisticApplicationService mediaStatisticApplicationService;

    @RequestMapping(path = "/elections/{election_id}/medias/{media_id}/statistics", method = RequestMethod.GET)
    public ResponseEntity<MediaStatisticDTO> mediaStatisticInElection(
            @PathVariable("media_id") String aMediaId,
            @PathVariable("election_id") String anElectionId) {

        Date startDate = DateHelper.nMonthAgoOf(1, new Date());
        Date endDate = new Date();

        List<Date> date = DateHelper.daysBetween(startDate, endDate);

        List<Series> positiveSeries = this.mediaStatisticApplicationService
                .positiveSentimentSeriesFor(anElectionId,
                        aMediaId,
                        startDate, endDate);

        List<Series> negativeSeries = this.mediaStatisticApplicationService
                .negativeSentimentSeriesFor(anElectionId,
                        aMediaId,
                        startDate, endDate);

        List<Series> neutralSeries = this.mediaStatisticApplicationService
                .neutralSentimentSeriesFor(anElectionId,
                        aMediaId,
                        startDate, endDate);

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
    public ResponseEntity<String> mediaOfAnElection(
            @PathVariable("election_id") String anElectionId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer size) {

        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(path = "/elections/{election_id}/medias/{media_id}/candidates", method = RequestMethod.GET)
    public ResponseEntity<String> candidateSupportedBy(@PathVariable("media_id") String aMediaId,
                                                       @PathVariable("election_id") String anElectionId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }

}
