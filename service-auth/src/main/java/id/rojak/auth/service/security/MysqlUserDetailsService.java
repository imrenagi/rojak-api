package id.rojak.auth.service.security;

import id.rojak.auth.common.error.ResourceNotFoundException;
import id.rojak.auth.domain.model.access.Permission;
import id.rojak.auth.domain.model.access.Role;
import id.rojak.auth.domain.model.identity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by imrenagi on 5/8/17.
 */
@Service
public class MysqlUserDetailsService implements UserDetailsService {

    private final static Logger log = LoggerFactory.getLogger(MysqlUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + user + " is not found");
        }

        return new UserInfo(user);

    }

}
