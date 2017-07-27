package id.rojak.analytics.resource;

import id.rojak.analytics.application.statistic.MediaStatisticApplicationService;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.resource.dto.MediaStatisticDTO;
import id.rojak.analytics.resource.dto.chart.ChartDTO;
import id.rojak.analytics.resource.dto.chart.Series;
import id.rojak.analytics.resource.dto.chart.XAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by imrenagi on 7/18/17.
 */
@RestController
public class MediaStatisticController {

    @Autowired
    private MediaStatisticApplicationService mediaStatisticApplicationService;

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/statistics", method = RequestMethod.GET)
    public ResponseEntity<MediaStatisticDTO> mediaStatisticInElection(@PathVariable("media_id") String aMediaId,
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

    @RequestMapping(path = "/medias/{media_id}/elections/{election_id}/candidates", method = RequestMethod.GET)
    public ResponseEntity<String> candidateSupportedBy(@PathVariable("media_id") String aMediaId,
                                                     @PathVariable("election_id") String anElectionId) {
        return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
    }
}
