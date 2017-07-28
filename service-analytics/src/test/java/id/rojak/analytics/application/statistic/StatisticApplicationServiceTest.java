package id.rojak.analytics.application.statistic;

import id.rojak.analytics.AnalyticsApplication;
import id.rojak.analytics.common.date.DateHelper;
import id.rojak.analytics.domain.model.candidate.CandidateId;
import id.rojak.analytics.domain.model.election.ElectionId;
import id.rojak.analytics.domain.model.news.SentimentCount;
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

        List<Long> output = service.fillEmptyGapFor(date, new ArrayList<>());

        Assert.assertEquals(0, output.size());
    }

    @Test
    public void shouldReturnTheSameLengthAsDateSeries() {

        List<Date> dates = this.dateFixtures();

        List<Long> output = service.fillEmptyGapFor(dates, new ArrayList<>());

        Assert.assertEquals(dates.size(), output.size());
    }

    @Test
    public void shouldReturnTheZeroIntegers() {

        List<Date> dates = this.dateFixtures();

        List<Long> output = service.fillEmptyGapFor(dates, new ArrayList<>());

        Assert.assertEquals(dates.size(), output.size());
        Assert.assertEquals(output.get(0), new Long(0));
        Assert.assertEquals(output.get(1), new Long(0));
        Assert.assertEquals(output.get(2), new Long(0));
    }

    @Test
    public void shouldReplaceZeroBasedOnFixtures() {

        List<Date> dates = this.dateFixtures();

        List<Long> output = service.fillEmptyGapFor(dates,
                this.sentimentCountsFixtures());

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

        List<SentimentCount> sentimentCounts = new ArrayList<>();

        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 1).getTime(), 1L));
        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 2).getTime(), 2L));
        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 3).getTime(), 3L));
        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 4).getTime(), 4L));
        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 5).getTime(), 5L));

        List<Long> output = service.fillEmptyGapFor(dates, sentimentCounts);

        Assert.assertEquals(dates.size(), output.size());
        Assert.assertEquals(new Long(1), output.get(0));
        Assert.assertEquals(new Long(2), output.get(1));
        Assert.assertEquals(new Long(3), output.get(2));
        Assert.assertEquals(new Long(4), output.get(3));
        Assert.assertEquals(new Long(5), output.get(4));
    }

    private List<Date> dateFixtures() {
        return this.dateFixtures(10);
    }

    private List<Date> dateFixtures(int days) {
        return DateHelper.daysBetween(
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, days).getTime());
    }

    private List<SentimentCount> sentimentCountsFixtures() {

        List<SentimentCount> sentimentCounts = new ArrayList<>();

        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 2).getTime(), 4L));
        sentimentCounts.add(this.sentimentCountWithDate(
                new GregorianCalendar(2017, Calendar.JANUARY , 4).getTime(), 6L));

        return sentimentCounts;
    }

    private SentimentCount sentimentCountWithDate(Date date, Long count) {
        return new SentimentCount(new ElectionId("dkijakarta"),
                new CandidateId("ahok"),
                date,
                SentimentType.POSITIVE,
                count);
    }

}