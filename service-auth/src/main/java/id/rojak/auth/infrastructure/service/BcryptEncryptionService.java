package id.rojak.auth.infrastructure.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by inagi on 8/1/17.
 */
@Component
public class BcryptEncryptionService implements EncryptionService {

    private final static Logger log = LoggerFactory.getLogger(BcryptEncryptionService.class);

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encryptedValue(String plainText) {
        return encoder.encode(plainText);
    }

    @Override
    public boolean matches(String plainText, String encryptedText) {
        return encoder.matches(plainText, encryptedText);
    }

}
