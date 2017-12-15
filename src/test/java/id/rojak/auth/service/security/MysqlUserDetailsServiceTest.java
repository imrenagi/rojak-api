package id.rojak.auth.service.security;

import id.rojak.Application;
import id.rojak.auth.domain.model.identity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by imrenagi on 5/14/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MysqlUserDetailsServiceTest {

    @InjectMocks
    private MysqlUserDetailsService userDetailsService;

    @Mock
    private UserRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldReturnUserDetailWhenAUserIsFound() throws Exception {
        User user = this.userStub();

        doReturn(user).when(repository).findByUsername(user.username());

        UserDetails found = userDetailsService.loadUserByUsername(user.username());

        assertEquals(user.username(), found.getUsername());
        assertEquals(user.password(), found.getPassword());
        assertEquals(user.isEnabled(), found.isEnabled());

        verify(repository, times(1)).findByUsername(user.username());
    }

    @Test
    public void shouldFailWhenUserIsNotFound() throws Exception {
        doReturn(null).when(repository).findByUsername(anyString());
        try {
            userDetailsService.loadUserByUsername(anyString());
            fail();
        } catch (Exception e) {

        }
    }

    private User userStub() {
        return new User("imrenagi",
                "Password01",
                Enablement.indefiniteEnablement(),
                new Person(
                        new FullName("Imre", "Nagi"),
                        new ContactInformation(
                                new EmailAddress("imre.nagi2812@gmail.com"),
                                new PostalAddress(
                                        "Jl. ABS",
                                        "Jakarta Selatan",
                                        "DKI Jakarta",
                                        "25134",
                                        "Indonesia"
                                ),
                                new Telephone("021-123-1234"),
                                new Telephone("021-123-1234"))));
    }
}