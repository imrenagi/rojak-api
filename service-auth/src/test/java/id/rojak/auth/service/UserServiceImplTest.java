package id.rojak.auth.service;

import id.rojak.auth.AuthApplication;
import id.rojak.auth.domain.User;
import id.rojak.auth.domain.UserRole;
import id.rojak.auth.repository.UserRepository;
import id.rojak.auth.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by imrenagi on 5/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthApplication.class)
@WebAppConfiguration
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldFailWhenUserIsNotFound() throws Exception {
        doReturn(null).when(userRepository).findByUsername(anyString());
        try {
            final User user = new User("imrenagi", "imrenagi", "imre");
            userService.create(user);
            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void shouldSaveNewUserWithUserRole() {
        final User user = new User("imrenagi", "imrenagi", "imre", "nagi");
        user.setId(1L);

        doReturn(null).when(userRepository).findByUsername(user.getUsername());
        doReturn(user).when(userRepository).save(any(User.class));

        userService.create(user);

        verify(userRepository, times(1)).save(any(User.class));
        verify(userRoleRepository, times(1)).save(any(UserRole.class));
    }

}