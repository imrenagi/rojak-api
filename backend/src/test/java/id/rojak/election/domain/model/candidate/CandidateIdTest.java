package id.rojak.election.domain.model.candidate;

import id.rojak.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by imrenagi on 7/9/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CandidateIdTest {

    @Test
    public void shouldReturnTrueIfIdIsTheSame() {
        CandidateId id1 = new CandidateId("first");
        CandidateId id2 = new CandidateId("first");

        Assert.assertTrue(id1.equals(id2));
    }

    @Test
    public void shouldReturnFalseIfIdIsDifferent() {
        CandidateId id1 = new CandidateId("first");
        CandidateId id2 = new CandidateId("second");

        Assert.assertFalse(id1.equals(id2));
    }

    @Test
    public void shouldReturnFalseIfTheClassIsDifferent() {
        CandidateId id = new CandidateId("first");
        String id2 = "fist";

        Assert.assertFalse(id.equals(id2));
    }
}