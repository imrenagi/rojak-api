package id.rojak.auth.infrastructure.service;

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
public class BcryptEncryptionServiceTest {

    private EncryptionService encryptionService = new BcryptEncryptionService();

    @Test
    public void shouldReturnTrueWhenPasswordIsMatched() {

        String hash = encryptionService.encryptedValue("imrenagi");

        Assert.assertTrue(encryptionService.matches("imrenagi", hash));

    }

}