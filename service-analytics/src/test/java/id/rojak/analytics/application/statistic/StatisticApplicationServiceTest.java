package id.rojak.analytics.application.statistic;

import id.rojak.analytics.AnalyticsApplication;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.sentiments.AggregatedSentiment;
import id.rojak.analytics.domain.model.news.SentimentType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by inagi on 7/21/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AnalyticsApplication.class)
public class StatisticApplicationServiceTest {

    @Autowired
    private StatisticApplicationService service;

    @Test
    public void shouldReturnEmptyList() {
        List<Date> date = new ArrayList<>();

        List<Long> output = service.fillEmptyGapFor(new ArrayList<>(), date);

        Assert.assertEquals(0, output.size());
    }

    @Test
    public void shouldReturnTheSameLengthAsDateSeries() {

        List<Date> dates = this.dateFixtures();

        List<Long> output = service.fillEmptyGapFor(new ArrayList<>(), dates);

        Assert.assertEquals(dates.size(), output.size());
    }

    @Test
    public void shouldReturnTheZeroIntegers() {

        List<Date> dates = this.dateFixtures();

        List<Long> output = service.fillEmptyGapFor(new ArrayList<>(), dates);

        Assert.assertEquals(dates.size(), output.size());
        Assert.assertEquals(output.get(0), new Long(0));
        Assert.assertEquals(output.get(1), new Long(0));
        Assert.assertEquals(output.get(2), new Long(0));
    }

    @Test
    public void shouldReplaceZeroBasedOnFixtures() {

        List<Date> dates = this.dateFixtures();

        List<Long> output = service.fillEmptyGapFor(this.sentimentCountsFixtures(), dates
        );

        Assert.assertEquals(dates.size(), output.size());
        Assert.assertEquals(new Long(0), output.get(0));
        Assert.assertEquals(new Long(4), output.get(1));
        Assert.assertEquals(new Long(0), output.get(2));
        Assert.assertEquals(new Long(6), output.get(3));
        Assert.assertEquals(new Long(0), output.get(4));
    }

    @Test
    public void shouldReplaceAllZeroIfEachDayHasValue() {

        List<Date> dates = this.dateFixtures(5);

        List<AggregatedSentiment> aggregatedSentiments = new ArrayList<>();

        aggregatedSentiments.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 1).getTime(), 1L));
        aggregatedSentiments.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 2).getTime(), 2L));
        aggregatedSentiments.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 3).getTime(), 3L));
        aggregatedSentiments.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 4).getTime(), 4L));

        List<Long> output = service.fillEmptyGapFor(aggregatedSentiments, dates);

        Assert.assertEquals(dates.size(), output.size());
        Assert.assertEquals(new Long(1), output.get(0));
        Assert.assertEquals(new Long(2), output.get(1));
        Assert.assertEquals(new Long(3), output.get(2));
        Assert.assertEquals(new Long(4), output.get(3));
    }

    private List<Date> dateFixtures() {
        return this.dateFixtures(10);
    }

    private List<Date> dateFixtures(int days) {
        return DateHelper.daysBetween(
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, days).getTime());
    }

    private List<AggregatedSentiment> sentimentCountsFixtures() {

        List<AggregatedSentiment> aggregatedSentiments = new ArrayList<>();

        aggregatedSentiments.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 2).getTime(), 4L));
        aggregatedSentiments.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 4).getTime(), 6L));

        return aggregatedSentiments;
    }

    private AggregatedSentiment sentimentCountWithDate(Date date, Long count) {
        return new AggregatedSentiment(new ElectionId("dkijakarta"),
                new CandidateId("ahok"),
                date,
                SentimentType.POSITIVE,
                count);
    }

}