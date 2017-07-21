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

import static org.junit.Assert.*;

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

        Assert.assertEquals(10, dates.size());

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

        Assert.assertEquals(1, dates.size());
    }

    @Test
    public void shouldReturnCorrectDateInNMonthAgo() {

        Date now = new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime();

        Date nMonthAgo = DateHelper.nMonthAgoOf(2, now);

        Assert.assertEquals(new GregorianCalendar(2016, Calendar.NOVEMBER, 1).getTime(), nMonthAgo);

    }

}