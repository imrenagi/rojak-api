package id.rojak.auth.service.security;

import id.rojak.auth.domain.model.identity.GroupMemberRepository;
import id.rojak.auth.domain.model.identity.User;
import id.rojak.auth.domain.model.identity.UserInfo;
import id.rojak.auth.domain.model.identity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
