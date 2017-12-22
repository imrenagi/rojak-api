package id.rojak.election.application.candidate;

import id.rojak.Application;

import id.rojak.election.client.AnalyticsServiceClientImpl;
import id.rojak.election.domain.model.candidate.*;
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
public class CandidateApplicationServiceTest {

    @InjectMocks
    private CandidateApplicationService candidateApplicationService = new CandidateApplicationService();

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private NomineeRepository nomineeRepository;

    @Mock
    private ElectionRepository electionRepository;

    @Mock
    private ElectionCandidateService electionCandidateService;

    @Mock
    private AnalyticsServiceClientImpl analyticsService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldThrowExceptionIfThereIsNoCandidate() {
        try {
            when(this.candidateRepository.findByElectionIdAndCandidateId(
                    new ElectionId("dkijakarta"),
                    new CandidateId("okeoce"))).thenReturn(null);

            Candidate candidate = this.candidateApplicationService
                    .candidate("dkijakarta", "okeoce");
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldReturnCandidateWithSameElectionIdAndCandidateID() {

        doReturn(mock(Candidate.class)).when(this.candidateRepository).findByElectionIdAndCandidateId(
                new ElectionId("dkijakarta"),
                new CandidateId("okeoce"));

        Candidate candidate = this.candidateApplicationService
                .candidate("dkijakarta", "okeoce");

        verify(this.candidateRepository, times(1))
                .findByElectionIdAndCandidateId(new ElectionId("dkijakarta"),
                        new CandidateId("okeoce"));
    }

    @Test
    public void shouldCreateNewCandidateSuccessfully() {
        NewCandidateCommand command =
                new NewCandidateCommand("okeoce",
                        "dkijakarta",
                        1,
                        "ahok", "aniesbaswedan",
                        "https://hello-pet.com/assets/uploads/2016/07/Ridwan-Kamil-KalamPosCom-700x400.jpg",
                        "http://facebook.com",
                        "okeoce",
                        "https://facebook.com",
                        "okeoce");

        Election election = new Election(
                new ElectionId("dkijakarta"),
                "Pemilu DKI Jakarta 2017",
                new GregorianCalendar(2017, Calendar.MARCH, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.FEBRUARY, 1).getTime(),
                mock(City.class),
                ElectionType.GOVERNOR);

        Nominee nominee1 = new Nominee(new NomineeId("ahok"),
                mock(FullName.class),
                "ahok",
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        Nominee nominee2 = new Nominee(new NomineeId("aniesbaswedan"),
                mock(FullName.class),
                "anies",
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        when(this.electionRepository.findByElectionId(any(ElectionId.class)))
                .thenReturn(election);
        when(this.nomineeRepository.findByNomineeId(new NomineeId(command.getMainCandidateId()))).thenReturn(nominee1);
        when(this.nomineeRepository.findByNomineeId(new NomineeId(command.getViceCandidateId()))).thenReturn(nominee2);
        when(this.candidateRepository.findByCandidateId(new CandidateId(command.getId()))).thenReturn(null);

        Candidate candidate = this.candidateApplicationService
                .newCandidate(command);

        Assert.assertEquals("okeoce", candidate.candidateId().id());
        Assert.assertEquals(1, candidate.candidateNumber());
        Assert.assertEquals(nominee1, candidate.mainCandidate());
        Assert.assertEquals(nominee2, candidate.viceCandidate());
        Assert.assertEquals("dkijakarta", candidate.electionId().id());
        Assert.assertEquals(1, candidate.election().candidates().size());

        verify(this.electionRepository, times(1)).findByElectionId(any(ElectionId.class));
        verify(this.nomineeRepository, times(2)).findByNomineeId(any(NomineeId.class));
        verify(this.candidateRepository, times(1)).findByCandidateId(any(CandidateId.class));
    }

    @Test
    public void shouldThrowExceptionIfElectionIsNotFoundWhenCreateNewCandidate() {
        try {
            NewCandidateCommand command =
                    new NewCandidateCommand("okeoce",
                            "dkijakarta2",
                            1,
                            "ahok", "aniesbaswedan",
                            null,
                            "http://facebook.com",
                            "okeoce",
                            "https://facebook.com",
                            "okeoce");

            when(this.electionRepository.findByElectionId(any(ElectionId.class)))
                    .thenReturn(null);

            Candidate candidate = this.candidateApplicationService
                    .newCandidate(command);

            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldThrowExceptionIfNomineeIsNotFoundWhenCreateNewCandidate() {
        try {
            NewCandidateCommand command =
                    new NewCandidateCommand("okeoce",
                            "dkijakarta",
                            1,
                            "ahok", "aniesbaswedan",
                            null,
                            "http://facebook.com",
                            "okeoce",
                            "https://facebook.com",
                            "okeoce");

            when(this.electionRepository.findByElectionId(any(ElectionId.class)))
                    .thenReturn(any(Election.class));
            when(this.nomineeRepository.findByNomineeId(new NomineeId(command.getMainCandidateId()))).thenReturn(null);

            Candidate candidate = this.candidateApplicationService
                    .newCandidate(command);

            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void shouldThrowExceptionIfCandidateWithTheSamIdDoesExistWhenCreateNewCandidate() {
        try {
            NewCandidateCommand command =
                    new NewCandidateCommand("okeoce",
                            "dkijakarta",
                            1,
                            "ahok", "aniesbaswedan",
                            null,
                            "http://facebook.com",
                            "okeoce",
                            "https://facebook.com",
                            "okeoce");

            Election election = new Election(
                    new ElectionId("dkijakarta"),
                    "Pemilu DKI Jakarta 2017",
                    new GregorianCalendar(2017, Calendar.MARCH, 1).getTime(),
                    new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                    new GregorianCalendar(2017, Calendar.FEBRUARY, 1).getTime(),
                    mock(City.class),
                    ElectionType.GOVERNOR);

            Nominee nominee1 = new Nominee(new NomineeId("ahok"),
                    mock(FullName.class),
                    "ahok",
                    new SocialMediaInformation("https://facebook.com",
                            "ahok",
                            "ahok",
                            "https://facebook.com/ahok"));

            Nominee nominee2 = new Nominee(new NomineeId("aniesbaswedan"),
                    mock(FullName.class),
                    "anies",
                    new SocialMediaInformation("https://facebook.com",
                            "ahok",
                            "ahok",
                            "https://facebook.com/ahok"));

            Candidate existingCandidate = new Candidate(
                    new CandidateId("okeoce"),
                    new ElectionId("dkijakarta"),
                    1,
                    "http://2.bp.blogspot.com/-0lGfBSjI754/VMkoenAX6MI/AAAAAAAANR4/KsbVvD9T5yg/s1600/Tri-Rismaharini.jpg",
                    nominee1, nominee2,
                    new SocialMediaInformation("https://facebook.com",
                            "ahok",
                            "ahok",
                            "https://facebook.com/ahok"));

            when(this.electionRepository.findByElectionId(any(ElectionId.class)))
                    .thenReturn(election);
            when(this.nomineeRepository.findByNomineeId(new NomineeId(command.getMainCandidateId()))).thenReturn(nominee1);
            when(this.nomineeRepository.findByNomineeId(new NomineeId(command.getViceCandidateId()))).thenReturn(nominee2);
            when(this.candidateRepository.findByCandidateId(new CandidateId(command.getId()))).thenReturn(existingCandidate);

            Candidate candidate = this.candidateApplicationService
                    .newCandidate(command);

            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void shouldBeAbleToRemoveCandidate() {
        RemoveCandidateCommand command =
                new RemoveCandidateCommand("dkijakarta", "okeoce");

        Election election = new Election(
                new ElectionId("dkijakarta"),
                "Pemilu DKI Jakarta 2017",
                new GregorianCalendar(2017, Calendar.MARCH, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.FEBRUARY, 1).getTime(),
                mock(City.class),
                ElectionType.GOVERNOR);

        Nominee nominee1 = new Nominee(new NomineeId("ahok"),
                mock(FullName.class),
                "ahok",
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        Nominee nominee2 = new Nominee(new NomineeId("aniesbaswedan"),
                mock(FullName.class),
                "anies",
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        Candidate existingCandidate = new Candidate(
                new CandidateId("okeoce"),
                new ElectionId("dkijakarta"),
                1,
                "http://2.bp.blogspot.com/-0lGfBSjI754/VMkoenAX6MI/AAAAAAAANR4/KsbVvD9T5yg/s1600/Tri-Rismaharini.jpg",
                nominee1, nominee2,
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        Assert.assertEquals(0, election.candidates().size());

        election.addCandidate(existingCandidate);

        Assert.assertEquals(1, election.candidates().size());

        when(this.electionRepository.findByElectionId(new ElectionId(command.getElectionId()))).thenReturn(election);
        when(this.candidateRepository.findByCandidateId(any(CandidateId.class))).thenReturn(existingCandidate);

        this.candidateApplicationService.removeCandidate(existingCandidate.candidateId().id());

        Assert.assertEquals(0, election.candidates().size());

        verify(this.electionRepository, times(1)).findByElectionId(any());
        verify(this.candidateRepository, times(1)).findByCandidateId(any());

    }

    @Test
    public void shouldBeAbleToUpdateCandidate() {
        String candidateId = "okeoce";

        UpdateCandidateDetailCommand command =
                new UpdateCandidateDetailCommand(
                        "dkijakarta2",
                        2,
                        "ahok", "aniesbaswedan",
                        "http://facebook.com",
                        "http://facebook.com",
                        "okeoce",
                        "https://facebook.com",
                        "okeoce");

        Election election = new Election(
                new ElectionId("dkijakarta"),
                "Pemilu DKI Jakarta 2017",
                new GregorianCalendar(2017, Calendar.MARCH, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.FEBRUARY, 1).getTime(),
                mock(Province.class),
                mock(City.class),
                ElectionType.GOVERNOR);

        Election election2 = new Election(
                new ElectionId("dkijakarta2"),
                "Pemilu DKI Jakarta 2018",
                new GregorianCalendar(2017, Calendar.MARCH, 1).getTime(),
                new GregorianCalendar(2017, Calendar.JANUARY, 1).getTime(),
                new GregorianCalendar(2017, Calendar.FEBRUARY, 1).getTime(),
                mock(Province.class),
                mock(City.class),
                ElectionType.GOVERNOR);

        Nominee nominee1 = new Nominee(new NomineeId("ahok"),
                mock(FullName.class),
                "ahok",
                mock(SocialMediaInformation.class));

        Nominee nominee2 = new Nominee(new NomineeId("aniesbaswedan"),
                mock(FullName.class),
                "anies",
                mock(SocialMediaInformation.class));

        Candidate originalCandidate = new Candidate(
                new CandidateId("okeoce"),
                new ElectionId("dkijakarta"),
                1,
                "http://2.bp.blogspot.com/-0lGfBSjI754/VMkoenAX6MI/AAAAAAAANR4/KsbVvD9T5yg/s1600/Tri-Rismaharini.jpg",
                nominee1, nominee2,
                new SocialMediaInformation("https://facebook.com",
                        "ahok",
                        "ahok",
                        "https://facebook.com/ahok"));

        Assert.assertEquals(0, election.candidates().size());
        Assert.assertEquals(0, election2.candidates().size());

        election.addCandidate(originalCandidate);

        Assert.assertEquals(1, election.candidates().size());
        Assert.assertEquals(0, election2.candidates().size());

        when(this.electionRepository.findByElectionId(new ElectionId("dkijakarta"))).thenReturn(election);
        when(this.electionRepository.findByElectionId(new ElectionId("dkijakarta2"))).thenReturn(election2);
        when(this.nomineeRepository.findByNomineeId(any(NomineeId.class))).thenReturn(nominee1);
        when(this.candidateRepository.findByCandidateId(any(CandidateId.class))).thenReturn(originalCandidate);

        Candidate updatedCandidate = this.candidateApplicationService.updateCandidate(candidateId,command);

        Assert.assertEquals(0, election.candidates().size());
        Assert.assertEquals(1, election2.candidates().size());

        Assert.assertEquals(command.getElectionId(), updatedCandidate.electionId().id());
        Assert.assertEquals(command.getCandidateNumber().intValue(), updatedCandidate.candidateNumber());
    }

}

