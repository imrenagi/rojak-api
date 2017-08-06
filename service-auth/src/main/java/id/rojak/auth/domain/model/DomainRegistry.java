package id.rojak.auth.domain.model;

import id.rojak.auth.application.representation.AccessApplicationService;
import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.common.domain.model.Identity;
import id.rojak.auth.domain.model.identity.GroupMemberService;
import id.rojak.auth.domain.model.identity.PasswordService;
import id.rojak.auth.infrastructure.service.EncryptionService;
import id.rojak.auth.domain.model.identity.BasicPasswordService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by inagi on 8/1/17.
 */
@Component
public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static PasswordService passwordService() {
        return (PasswordService) applicationContext.getBean("passwordService");
    }

    public static EncryptionService encryptionService() {
        return (EncryptionService) applicationContext.getBean("bcryptEncryptionService");
    }

    public static GroupMemberService groupMemberService() {
        return (GroupMemberService) applicationContext.getBean("groupMemberService");
    }

    public static IdentityApplicationService identityApplicationService() {
        return (IdentityApplicationService) applicationContext.getBean("identityApplicationService");
    }

    public static AccessApplicationService accessApplicationService() {
        return (AccessApplicationService) applicationContext.getBean("accessApplicationService");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = applicationContext;
        }
    }
}
