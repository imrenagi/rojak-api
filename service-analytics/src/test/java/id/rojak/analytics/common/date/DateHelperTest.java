package id.rojak.analytics.common.date;

import id.rojak.analytics.AnalyticsApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by inagi on 7/21/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AnalyticsApplication.class)
public class DateHelperTest {

    @Test
    public void shouldReturnCorrectNumberOfDay() {

        List<Date> dates = DateHelper.daysBetween(
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 10).getTime());

        Assert.assertEquals(9, dates.size());

    }

    @Test
    public void shouldGenerateCorrectDates() {
        List<Date> dates = DateHelper.daysBetween(
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 10).getTime());

        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                dates.get(0));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 2).getTime(),
                dates.get(1));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 3).getTime(),
                dates.get(2));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 4).getTime(),
                dates.get(3));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 5).getTime(),
                dates.get(4));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 6).getTime(),
                dates.get(5));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 7).getTime(),
                dates.get(6));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 8).getTime(),
                dates.get(7));
        Assert.assertEquals(
                new GregorianCalendar(2017, Calendar.JANUARY, 9).getTime(),
                dates.get(8));
    }

    @Test
    public void shouldReturnZeroIfEndDateIsBeforeStartDate() {
        List<Date> dates = DateHelper.daysBetween(
                new GregorianCalendar(2017, Calendar.JANUARY, 10).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime());

        Assert.assertEquals(0, dates.size());
    }

    @Test
    public void shouldReturnADateIfStartAndEndDateAreTheSame() {
        List<Date> dates = DateHelper.daysBetween(
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime());

        Assert.assertEquals(0, dates.size());
    }


}