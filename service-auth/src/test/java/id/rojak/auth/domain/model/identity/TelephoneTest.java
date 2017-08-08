package id.rojak.auth.domain.model.identity;

import id.rojak.auth.AuthApplication;
import org.junit.Assert;
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
@SpringApplicationConfiguration(classes = AuthApplication.class)
@WebAppConfiguration
public class TelephoneTest {

    @Test
    public void shouldBeAbleToSetPhoneNumber() {
        Telephone telephone = new Telephone("021-123-1234");
        Assert.assertEquals("021-123-1234", telephone.number());
    }

    @Test
    public void shouldThrowExceptionIfPhoneNumberIsEmpty() {
        try {
            Telephone telephone = new Telephone("");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldThrowExceptionIfPhoneNumberLengthIsSmallerThan5() {
        try {
            Telephone telephone = new Telephone("021");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldThrowExceptionIfPhoneNumberLengthIsLongerThan20() {
        try {
            Telephone telephone = new Telephone("021-123-1231234123124123");
            fail();
        } catch (Exception e) {
        }
    }

}