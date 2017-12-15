package id.rojak.auth.domain.model.identity;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import id.rojak.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DatabaseSetup("classpath:datasets/user-items.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:datasets/user-items.xml"})
    public void shouldReturnCorrectUser() {
        User user = this.userRepository.findByUsername("imrenagi");

        Assert.assertEquals("imrenagi", user.username());
        Assert.assertEquals("Imre", user.person().name().firstName());

        Assert.assertNotNull(user);
    }

    @Test
    @DatabaseSetup("classpath:datasets/user-items.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { "classpath:datasets/user-items.xml"})
    public void shouldReturnCorrectNumberOfUser() {
        List<User> users = this.userRepository.findAll();

        Assert.assertEquals(2, users.size());

    }

}