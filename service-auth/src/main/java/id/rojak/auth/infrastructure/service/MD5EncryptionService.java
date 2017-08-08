package id.rojak.auth.infrastructure.service;

import id.rojak.auth.common.AssertionConcern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by inagi on 8/2/17.
 */
@Component
public class MD5EncryptionService extends AssertionConcern implements EncryptionService {


    @Override
    public String encryptedValue(String plainText) {
        this.assertArgumentNotEmpty(
                plainText,
                "Plain text value to encrypt must be provided.");

        String encryptedValue = null;

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(plainText.getBytes("UTF-8"));

            BigInteger bigInt = new BigInteger(1, messageDigest.digest());

            encryptedValue = bigInt.toString(16);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return encryptedValue;
    }

    @Override
    public boolean matches(String plainText, String encryptedText) {
        return this.encryptedValue(plainText).equals(encryptedText);
    }

}
