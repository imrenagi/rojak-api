package id.rojak.election.application.candidate;

import id.rojak.election.ElectionApplication;
import id.rojak.election.domain.model.candidate.*;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by inagi on 8/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ElectionApplication.class)
@WebAppConfiguration
public class NomineeApplicationServiceTest {

    @InjectMocks
    private INomineeApplicationService nomineeApplicationService = new NomineeApplicationService();

    @Mock
    private NomineeRepository nomineeRepository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldBeAbleToCreateNewNominee() {

        NewNomineeCommand command = new NewNomineeCommand("ahok",
                "Basuki",
                "",
                "Tjahaja",
                "http://facebook.com/image.jpg",
                "ahok",
                "http://facebook.com",
                "ahok",
                "ahok",
                "http://facebook.com");

        Nominee nominee1 = new Nominee(new NomineeId("ahok"),
                new FullName("Basuki", "", "Tjahaja"),
                "ahok",
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        when(this.nomineeRepository.findByNomineeId(new NomineeId(command.getId()))).thenReturn(null);
        when(this.nomineeRepository.save(any(Nominee.class))).thenReturn(nominee1);

        Nominee newNominee = this.nomineeApplicationService
                .newNominee(command);

        Assert.assertEquals(command.getId(), newNominee.nomineeId().id());
        Assert.assertEquals(command.getFirstName(), newNominee.fullName().firstName());
        Assert.assertEquals(command.getLastName(), newNominee.fullName().lastName());
        Assert.assertEquals(command.getNickName(), newNominee.nickName());

        verify(this.nomineeRepository, times(1)).findByNomineeId(any(NomineeId.class));
        verify(this.nomineeRepository, timeout(1)).save(any(Nominee.class));
    }

    @Test
    public void shouldThrowExceptionIfnomineeExist() {
        try {

            NewNomineeCommand command = new NewNomineeCommand("ahok",
                    "Basuki",
                    "",
                    "Tjahaja",
                    "http://facebook.com/image.jpg",
                    "ahok",
                    "http://facebook.com",
                    "ahok",
                    "ahok",
                    "http://facebook.com");

            Nominee nominee1 = new Nominee(new NomineeId("ahok"),
                    new FullName("Basuki", "", "Tjahaja"),
                    "ahok",
                    new SocialMediaInformation("https://facebook.com",
                            "ahok",
                            "ahok",
                            "https://facebook.com/ahok"));

            when(this.nomineeRepository.findByNomineeId(any(NomineeId.class))).thenReturn(nominee1);

            Nominee newNominee = this.nomineeApplicationService
                    .newNominee(command);

            fail();
        } catch (Exception e) {

        }
    }



}