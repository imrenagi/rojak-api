package id.rojak.auth.domain.model;

import id.rojak.auth.infrastructure.service.EncryptionService;
import id.rojak.auth.infrastructure.service.BcryptEncryptionService;
import id.rojak.auth.domain.model.identity.PasswordService;

/**
 * Created by inagi on 8/1/17.
 */
public class DomainRegistry {

    public static PasswordService passwordService() {
        //TODO change this later with DI
        return new PasswordService();
    }

    public static EncryptionService encryptionService() {
        return new BcryptEncryptionService();
    }
}
