package id.rojak.auth.config;

import id.rojak.auth.domain.model.identity.BasicPasswordService;
import id.rojak.auth.domain.model.identity.GroupMemberService;
import id.rojak.auth.domain.model.identity.PasswordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by inagi on 8/2/17.
 */
@Configuration
@Import({EncryptionConfig.class})
public class AppConfig {

    @Bean(name = "passwordService")
    public PasswordService passwordService() {
        return new BasicPasswordService();
    }

    @Bean(name = "groupMemberService")
    public GroupMemberService groupMemberService() {
        return new GroupMemberService();
    }

}
