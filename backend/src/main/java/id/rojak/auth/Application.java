package id.rojak.auth;

import id.rojak.auth.application.command.*;
import id.rojak.auth.application.representation.AccessApplicationService;
import id.rojak.auth.application.representation.IdentityApplicationService;
import id.rojak.auth.config.AppConfig;
import id.rojak.auth.domain.model.DomainRegistry;
import id.rojak.auth.domain.model.identity.Group;
import id.rojak.auth.domain.model.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by imrenagi on 12/7/17.
 */
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({AppConfig.class})
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private IdentityApplicationService identityApplicationService;

    @Autowired
    private AccessApplicationService accessApplicationService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        setupAuthTable();
    }

    public static void setupAuthTable() {
        try {
            log.info("STARTING SETUP!");

            DomainRegistry
                    .accessApplicationService()
                    .newRole(new CreateRoleCommand("Admin", "Admin full access"));
            DomainRegistry
                    .accessApplicationService()
                    .newRole(new CreateRoleCommand("Default", "Default role"));

            DomainRegistry
                    .accessApplicationService()
                    .newPermission(new CreatePermissionCommand("BASIC_WRITE", "Lowest write capabilities"));
            DomainRegistry
                    .accessApplicationService()
                    .newPermission(new CreatePermissionCommand("BASIC_READ", "Lowest read capabilities"));

            String[] defPermissionArr = {"BASIC_READ"};
            String[] defAdminPermissionArr = {"BASIC_WRITE", "BASIC_READ"};

            List<String> defaultPermission = Arrays.asList(defPermissionArr);
            List<String> adminPermission = Arrays.asList(defAdminPermissionArr);

            DomainRegistry.accessApplicationService()
                    .assignPermissionToRole(
                            new AssignPermissionToRoleCommand(defaultPermission, "ROLE_DEFAULT"));
            DomainRegistry.accessApplicationService()
                    .assignPermissionToRole(
                            new AssignPermissionToRoleCommand(adminPermission, "ROLE_ADMIN"));

            Group adminGroup = DomainRegistry
                    .identityApplicationService()
                    .newGroup(
                            new CreateGroupCommand("Administrator",
                                    "Administrator with full privileges",
                                    false,
                                    "ROLE_ADMIN"));

            Group defaultGroup = DomainRegistry
                    .identityApplicationService()
                    .newGroup(
                            new CreateGroupCommand("Default",
                                    "Default group for first time created user",
                                    false,
                                    "ROLE_DEFAULT"));

            User admin = DomainRegistry.identityApplicationService()
                    .newUser(
                            new RegisterUserCommand(
                                    "admin",
                                    "Password01",
                                    "Admin",
                                    "Admin",
                                    true, null, null,
                                    "rojak.admin@gmail.com",
                                    "021-856-9876",
                                    "021-856-9876",
                                    "Jl. Pegangsaan Timur",
                                    "Jakarta Selatan",
                                    "DKI Jakarta",
                                    "25134",
                                    "Indonesia"));

            List<String> adminUserNames = new ArrayList<>();
            adminUserNames.add(admin.username());

            DomainRegistry.identityApplicationService()
                    .addUserToGroup(
                            new AddUserToGroupCommand(
                                    adminGroup.groupId().id(),
                                    adminUserNames));
            log.info("SETUP COMPLETE!");

        } catch (Exception e) {
            log.info("New setup is canceled due to : {}!", e.getMessage());
        }
    }

}
