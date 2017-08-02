package id.rojak.auth.service.security;

import id.rojak.auth.AuthApplication;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by imrenagi on 5/14/17.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AuthApplication.class)
//@WebAppConfiguration
public class MysqlUserDetailsServiceTest {

//    @InjectMocks
//    private MysqlUserDetailsService userDetailsService;

//    @Mock
//    private UserRepository repository;
//
//    @Before
//    public void setup() {
//        initMocks(this);
//    }
//
//    @Test
//    public void shouldReturnUserDetailWhenAUserIsFound() throws Exception {
//        final User user = new User("imrenagi", "1234", "imre", "nagi");
//
//        doReturn(user).when(repository).findByUsername(user.getUsername());
//        UserDetails found = userDetailsService.loadUserByUsername(user.getUsername());
//
//        assertEquals(user.getUsername(), found.getUsername());
//        assertEquals(user.getPassword(), found.getPassword());
//
//        verify(repository, times(1)).findByUsername(user.getUsername());
//    }
//
//    @Test
//    public void shouldFailWhenUserIsNotFound() throws Exception {
//        doReturn(null).when(repository).findByUsername(anyString());
//        try {
//            userDetailsService.loadUserByUsername(anyString());
//            fail();
//        } catch (Exception e) {
//
//        }
//    }
}