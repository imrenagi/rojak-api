package id.rojak.auth.domain.model.identity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by inagi on 8/1/17.
 */
public class UserInfo extends User implements UserDetails {

    public UserInfo(User user) {
        super();

        this.setUsername(user.username());
        this.setPassword(user.password());
        this.setEnablement(user.enablement());
        this.setPerson(user.person());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        System.out.println(this.password());
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
}
