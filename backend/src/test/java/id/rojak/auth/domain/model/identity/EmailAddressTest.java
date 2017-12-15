package id.rojak.auth.domain.model.identity;

import id.rojak.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.fail;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmailAddressTest {

    @Test
    public void shouldBeAbleToCreateNewInstance() {
        EmailAddress email = new EmailAddress("imre.nagi2812@gmail.com");
        email = new EmailAddress("imrenagi@gmail.com");
        email = new EmailAddress("imrenagi@yahoo.co.id");
        email = new EmailAddress("imre_nagi@yahoo.co.id");
    }

    @Test
    public void shouldTrownExceptionWhenEmailIsEmpty() {
        try {
            EmailAddress email = new EmailAddress("");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldTrownExceptionWhenEmailIsTooLong() {
        try {
            EmailAddress email = new EmailAddress("iasdajlshdajshdkjashfjahsfkjashfjkahsfkjahskjfakjshfjkashfkjahsfkjahsfkjahsfjkahsfkjhaskjfhajkshfkasfhajhsfkjahsfjkashkfjhasfjhaskfhaksjhfakjshfkajhsfa@gmail.com");
            fail();
        } catch (Exception e) {
        }
    }

}