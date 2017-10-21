package id.rojak.analytics;

import id.rojak.analytics.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by inagi on 7/20/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AnalyticsApplicationTest.class)
@WebAppConfiguration
@TestPropertySource(locations="classpath:application.properties")
@Import(RedisConfig.class)
public class AnalyticsApplicationTest {

    @Test
    public void contextLoads() {

    }

}