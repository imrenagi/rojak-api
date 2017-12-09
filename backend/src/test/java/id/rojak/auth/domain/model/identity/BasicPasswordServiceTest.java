package id.rojak.auth.domain.model.identity;

import id.rojak.Application;
import org.junit.Assert;
import org.junit.Before;
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
public class BasicPasswordServiceTest {

    private PasswordService passwordService;

    @Before
    public void setup() {
        this.passwordService = new BasicPasswordService();
    }

    @Test
    public void shouldGenerateStrongPassword() {
        String password = passwordService.generateStrongPassword();

        Assert.assertTrue(password.length() >= 7);
        Assert.assertEquals(true, passwordService.isStrong(password));
    }

    @Test
    public void shouldBeWeakPassword() {

        Assert.assertTrue(passwordService.isWeak("123456789"));
        Assert.assertTrue(passwordService.isWeak("abcdef"));

    }

    @Test
    public void shouldBeStrongButNotVeryStrong() {

        Assert.assertTrue(passwordService.isStrong("abcdefghik"));
        Assert.assertFalse(passwordService.isVeryStrong("abcdefghik"));

        Assert.assertTrue(passwordService.isStrong("abc12345"));
        Assert.assertFalse(passwordService.isVeryStrong("abc12345"));

    }

    @Test
    public void shouldBeVeryStrong() {

        Assert.assertTrue(passwordService.isVeryStrong("T0nOcooL_123Asd"));
        Assert.assertTrue(passwordService.isVeryStrong("QwertY_898!@as23"));
        Assert.assertTrue(passwordService.isVeryStrong("I am falling from the sky and I will die!"));
    }

    @Test
    public void shouldBeNotVeryStrong() {
        Assert.assertFalse(passwordService.isVeryStrong("QwertY_898"));
        Assert.assertFalse(passwordService.isVeryStrong("KuaLa_Kumal"));
    }


}