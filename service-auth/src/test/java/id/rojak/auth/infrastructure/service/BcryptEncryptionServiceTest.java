package id.rojak.auth.infrastructure.service;

import static org.junit.Assert.*;

import id.rojak.auth.AuthApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthApplication.class)
@WebAppConfiguration
public class BcryptEncryptionServiceTest {

    private EncryptionService encryptionService = new BcryptEncryptionService();

    @Test
    public void shouldReturnTrueWhenPasswordIsMatched() {

        String hash = encryptionService.encryptedValue("imrenagi");

        Assert.assertTrue(encryptionService.matches("imrenagi", hash));

    }

}