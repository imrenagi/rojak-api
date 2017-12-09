package id.rojak.auth.domain.model.identity;

import id.rojak.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.fail;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EnablementTest {

    @Test
    public void shouldBeAbleToCreateNewInstance() {
        Enablement enablement = new Enablement(true,
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime());

        Assert.assertTrue(enablement.isEnabled());
    }

    @Test
    public void shouldThrowErrorBecauseStartIsAfterEnd() {
        try {
            Enablement enablement = new Enablement(true,
                    new GregorianCalendar(2017, Calendar.DECEMBER, 1).getTime(),
                    new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime());
            fail();
        } catch (Exception e) {}
    }

    @Test
    public void shouldBeExpired() {
        Enablement enablement = new Enablement(true,
                new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2010, Calendar.DECEMBER, 1).getTime());

        Assert.assertTrue(enablement.isTimeExpired());
        Assert.assertTrue(enablement.isEnabled());
        Assert.assertFalse(enablement.isEnablementEnabled());
    }

    @Test
    public void indefiniteEnablementShouldHaveNoStartEndDate() {
        Enablement e = Enablement.indefiniteEnablement();

        Assert.assertTrue(e.isEnabled());
        Assert.assertNull(e.startDate());
        Assert.assertNull(e.endDate());

    }

    @Test
    public void shouldBeTheSame() {
        Enablement enablement = new Enablement(true,
                new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2010, Calendar.DECEMBER, 1).getTime());

        Enablement enablement2 = new Enablement(true,
                new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2010, Calendar.DECEMBER, 1).getTime());

        Assert.assertTrue(enablement.equals(enablement2));
    }

}