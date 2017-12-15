package id.rojak.auth.domain.model.identity;

import id.rojak.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class PostalAddressTest {

    @Test
    public void shouldBeTheSame() {
        PostalAddress add1 = stub();
        PostalAddress add2 = stub();

        Assert.assertTrue(add1.equals(add2));
        Assert.assertEquals(add1.hashCode(), add2.hashCode());
    }

    PostalAddress stub() {
        return new PostalAddress(
                "Jl. ABS",
                "Jakarta Selatan",
                "DKI Jakarta",
                "25134",
                "Indonesia"
        );
    }

}