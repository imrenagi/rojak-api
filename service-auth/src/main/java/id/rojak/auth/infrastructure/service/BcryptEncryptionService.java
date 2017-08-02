package id.rojak.auth.infrastructure.service;

import id.rojak.auth.infrastructure.service.EncryptionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by inagi on 8/1/17.
 */
public class BcryptEncryptionService implements EncryptionService {

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
