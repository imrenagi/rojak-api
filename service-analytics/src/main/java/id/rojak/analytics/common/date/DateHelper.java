package id.rojak.analytics.common.date;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by inagi on 7/21/17.
 */
public class DateHelper {

    private static Logger log = LoggerFactory.getLogger(DateHelper.class);

    public static List<DateTime> daysBetween(DateTime startDate, DateTime endDate) {
        int days = Days.daysBetween(startDate, endDate).getDays();
        List<DateTime> dates = new ArrayList<>(days);
        for (int i = 0; i < days; i++) {
            DateTime d = startDate.withFieldAdded(DurationFieldType.days(), i);
            dates.add(d);
        }
        return dates;
    }

    /**
     * Inclusive dates from start to end
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> daysBetween(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        while (isSimilar(calendar.getTime(), endDate) ||
                calendar.getTime().before(endDate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public static boolean isSimilar(Date dateA, Date dateB) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.setTime(dateA);
        cal2.setTime(dateB);

        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

        return sameDay;
    }

    public static Date nMonthAgoOf(int n, Date now) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(now);

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        cal.add(Calendar.MONTH, -1*n);

        return cal.getTime();
    }


}
