package id.rojak.auth.domain.model.identity;

import id.rojak.auth.domain.model.DomainRegistry;
import id.rojak.auth.domain.model.access.Permission;
import id.rojak.auth.domain.model.access.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by inagi on 8/1/17.
 */
public class UserInfo extends User
        implements UserDetails, ApplicationContextAware {

    private static final long serialVersionUID = 1L;
    private final static Logger log = LoggerFactory.getLogger(UserInfo.class);
    private static ApplicationContext appContext;

    public UserInfo(User user) {
        super();

        this.setUsername(user.username());
        this.setPassword(user.password());
        this.setEnablement(user.enablement());
        this.setPerson(user.person());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("BEFORE LOADING REPO " + username());

        Group group =
                DomainRegistry
                        .groupMemberService()
                        .groupOf(this);

        log.info("Get Group : {} ", group);

        if (group == null) return null;

        Role role = group.role();
        log.info("Get Role : {} ", role);
        log.info("Total permission in role : {}" , role.permissions().size());
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Permission permission : role.permissions()) {
            log.info("Permission {} ", permission);
            authorities.add(new SimpleGrantedAuthority(permission.name()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password();
    }

    @Override
    public String getUsername() {
        return this.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

}
