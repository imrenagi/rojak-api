package id.rojak.auth.infrastructure.service;

/**
 * Created by inagi on 8/1/17.
 */
public interface EncryptionService {

    String encryptedValue(String plainText);
    boolean matches(String plainText, String encryptedText);
}
