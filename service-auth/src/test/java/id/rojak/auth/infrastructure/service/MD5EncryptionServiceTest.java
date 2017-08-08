package id.rojak.auth.infrastructure.service;

import id.rojak.auth.AuthApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthApplication.class)
@WebAppConfiguration
public class MD5EncryptionServiceTest {

    EncryptionService encryptionService = new MD5EncryptionService();

    @Test
    public void shouldMatch() {
        String hash = encryptionService.encryptedValue("imrenagi");

        Assert.assertTrue(encryptionService.matches("imrenagi",
                hash));
    }

    @Test
    public void shouldNotMatch() {
        String hash = encryptionService.encryptedValue("imrenagi");

        Assert.assertFalse(encryptionService.matches("imrnagi",
                hash));
    }

}