package id.rojak.auth.config;

import id.rojak.auth.infrastructure.service.BcryptEncryptionService;
import id.rojak.auth.infrastructure.service.EncryptionService;
import id.rojak.auth.infrastructure.service.MD5EncryptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by inagi on 8/2/17.
 */
@Configuration
public class EncryptionConfig {

    @Bean(name = "bcryptEncryptionService")
    @Primary
    public EncryptionService bcryptEncryptionService() {
        return new BcryptEncryptionService();
    }

    @Bean(name = "md5EncryptionService")
    public EncryptionService md5EncryptionService() {
        return new MD5EncryptionService();
    }
}
