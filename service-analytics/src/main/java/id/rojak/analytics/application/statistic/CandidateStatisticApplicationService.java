package id.rojak.analytics.application.statistic;

import id.rojak.analytics.resource.dto.chart.ChartDTO;
import id.rojak.analytics.resource.dto.chart.Series;
import id.rojak.analytics.resource.dto.chart.XAxis;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.media.Media;
import id.rojak.analytics.domain.model.media.MediaRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentRepository;
import id.rojak.analytics.domain.model.news.NewsSentimentService;
import id.rojak.analytics.domain.model.news.SentimentCount;
import id.rojak.analytics.domain.model.news.SentimentType;
import id.rojak.analytics.domain.model.sentiments.MediaNewsCount;
import id.rojak.analytics.domain.model.sentiments.MediaSentimentGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by inagi on 7/20/17.
 */
@Service
public class CandidateStatisticApplicationService {

    private final Logger log = LoggerFactory.getLogger(CandidateStatisticApplicationService.class);

    @Autowired
    private NewsSentimentService newsSentimentService;

    @Autowired
    private NewsSentimentRepository newsSentimentRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Transactional
    public MediaSentimentGroup mediaSentimentGroupOf(String electionId, String candidateId) {

        MediaSentimentGroup sentimentGroup = this.newsSentimentService
                .sentimentGroupsFor(new ElectionId(electionId),
                        new CandidateId(candidateId));

        return sentimentGroup;

    }

    @Transactional
    public MediaSentimentGroup mediaAbout(String electionId, String candidateId) {

        MediaSentimentGroup sentimentGroup =
                this.mediaSentimentGroupOf(electionId, candidateId);

        this.fetchMediaFor(sentimentGroup.getPositiveMedias());
        this.fetchMediaFor(sentimentGroup.getNegativeMedias());
        this.fetchMediaFor(sentimentGroup.getNeutralMedias());

        return sentimentGroup;

    }

    private void fetchMediaFor(List<MediaNewsCount> newsCount) {

        for (MediaNewsCount mediaNewsCount : newsCount) {

            Media media = this.mediaRepository.findByMediaId(mediaNewsCount.mediaId());
            if (media == null) {
                throw new IllegalStateException(String.format(
                        "Media %s doesn't exist", mediaNewsCount.media()));
            }
            mediaNewsCount.setMedia(media);
        }
    }

    public List<Long> sentimentsOverTime(String electionId, String candidateId,
                                         SentimentType sentimentType,
                                         Date startDate,
                                         Date endDate,
                                         List<Date> dateSeries) {

        List<SentimentCount> sentiments = this.newsSentimentRepository
                .sentimentsGroupedByDateAndSentiment(new ElectionId(electionId),
                        new CandidateId(candidateId),
                        sentimentType, startDate, endDate);

        return this.fillGapBetweenEmpty(dateSeries, sentiments);
    }

    public List<Long> fillGapBetweenEmpty(List<Date> dateSeries, List<SentimentCount> sentimentCounts) {

        List<Long> values = new ArrayList<>(Collections.nCopies(dateSeries.size(), 0L));

        int countIdx = 0;

        for (int i = 0; i < values.size() && countIdx < sentimentCounts.size(); i++) {
            SentimentCount sentiment = sentimentCounts.get(countIdx);

            if (DateHelper.isSimilar(dateSeries.get(i), sentiment.getDate())) {
                values.set(i, sentiment.getCount());
                countIdx++;
            }
        }

        return values;
    }

//        List<Integer> values = new ArrayList<>();
//        List<Date> dates = DateHelper.daysBetween(new GregorianCalendar(2017, 6, 15).getTime(),
//                new Date());

//        for (SentimentCount sentiment : sentiments) {
//            log.info("Sentiment for {} on {} is {} = {}", sentiment.getCandidateId(),
//                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sentiment.getDate()),
//                    sentiment.getSentimentType().toString(),
//                    sentiment.getCount());
//        }
//
//        log.info("Now {} ", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//        log.info("Now JodaTime {}", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss ZZ").print(new DateTime()));
//
//        log.info("Now JodaTime PDT {}", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss ZZ")
//                .print(new LocalDateTime(
//                        new DateTime(DateTimeZone.forID("US/Pacific")))));
//
//        log.info("Now JodaTime WIB {}", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss ZZ")
//                .print(new LocalDateTime(
//                        new DateTime(DateTimeZone.forID("Asia/Jakarta")))));

}
