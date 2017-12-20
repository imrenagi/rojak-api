package id.rojak.election.application.election;

import id.rojak.Application;
import id.rojak.election.domain.model.election.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by inagi on 8/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ElectionApplicationServiceTest {

    @InjectMocks
    private ElectionApplicationService electionApplicationService = new ElectionApplicationService();

    @Mock
    private ElectionRepository electionRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ProvinceRepository provinceRepository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldReturnAnElection() {
        Election election = new Election(
                new ElectionId("dkijakarta"),
                "Pilkada DKI Jakarta",
                new GregorianCalendar(2017, Calendar.JULY,1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY,1).getTime(),
                new GregorianCalendar(2017, Calendar.FEBRUARY,1).getTime(),
                mock(City.class),
                ElectionType.GOVERNOR);

        when(this.electionRepository.findByElectionId(any(ElectionId.class))).thenReturn(election);

        Election exisitingElection = this.electionApplicationService
                .election("dkijakarta");

        Assert.assertEquals(election.electionId(), exisitingElection.electionId());
        Assert.assertEquals(election.type(), exisitingElection.type());

        verify(this.electionRepository, times(1)).findByElectionId(new ElectionId(("dkijakarta")));
    }

    @Test
    public void shouldThrowExceptionIfElectionDoesntExist() {

        try {
            when(this.electionRepository.findByElectionId(any(ElectionId.class))).thenReturn(null);

            Election exisitingElection = this.electionApplicationService
                    .election("dkijakarta");

            verify(this.electionRepository, times(1)).findByElectionId(any(ElectionId.class));

            fail("Exception isn't thrown..");
        } catch (Exception e) {}
    }

    @Test
    public void shouldBeAbleToCreateNewElection() {

        NewElectionCommand command =
                new NewElectionCommand(
                        "pilkada_dki_jakarta_2017",
                        "Pilkada DKI Jakarta 2017",
                        new GregorianCalendar(2017, Calendar.JULY,1).getTime().getTime(),
                        new GregorianCalendar(2017, Calendar.JANUARY,1).getTime().getTime(),
                        new GregorianCalendar(2017, Calendar.FEBRUARY,1).getTime().getTime(),
                        1L,
                        1L,
                        "GOVERNOR");

        String uuid = UUID.randomUUID().toString();

        Election election = new Election(
                new ElectionId("pilkada_dki_jakarta_2017"),
                "Pilkada DKI Jakarta 2017",
                new GregorianCalendar(2017, Calendar.JULY,1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY,1).getTime(),
                new GregorianCalendar(2017, Calendar.FEBRUARY,1).getTime(),
                mock(City.class),
                ElectionType.GOVERNOR);


        City city = new City("Jakarta Selatan", mock(Location.class), mock(Province.class));
        Province province = new Province("DKI Jakarta", "JKT", mock(Country.class));

        when(this.cityRepository.findOne(anyLong())).thenReturn(city);
        when(this.provinceRepository.findOne(anyLong())).thenReturn(province);
        when(this.electionRepository.nextId()).thenReturn(uuid);
        when(this.electionRepository.save(any(Election.class))).thenReturn(election);

        Election newElection = this.electionApplicationService
                .newElection(command);

        Assert.assertEquals("pilkada_dki_jakarta_2017", election.electionId().id());
        Assert.assertEquals(ElectionType.GOVERNOR, election.type());

        verify(this.cityRepository, times(1)).findOne(anyLong());
        verify(this.electionRepository, times(1)).save(any(Election.class));




    }



}