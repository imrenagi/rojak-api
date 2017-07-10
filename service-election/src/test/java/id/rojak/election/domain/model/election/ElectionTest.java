package id.rojak.election.domain.model.election;

import id.rojak.election.ElectionApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by imrenagi on 7/9/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ElectionApplication.class)
public class ElectionTest {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void shouldBeAbleToCreateElection() throws Exception {
        Election election = this.getFixture();
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionWhenElectionIdIsNull() throws Exception {
        Election e = this.getFixture();
        e.setElectionId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionWhenNameIsEmpty() throws Exception {
        Election e = this.getFixture();
        e.setName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionWhenElectionType() throws Exception {
        Election e = this.getFixture();
        e.setType(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void willThrowExceptionWhenElectionDateIsNull() throws Exception {
        Election e = this.getFixture();
        e.setElectionDate(null);
    }

    @Test
    public void twoElectionWithTheSameElectionIdShouldReturnTrue() throws Exception {
        Election e = this.getFixture();
        Election e1 = this.getFixture();

        Assert.assertTrue(e.equals(e1));
    }

    @Test
    public void twoElectionWithDifferentElectionIdShouldBeDifferent() throws Exception {
        Election e = this.getFixture();
        e.setElectionId(new ElectionId("pilkadadki"));
        Election e1 = this.getFixture();

        Assert.assertFalse(e.equals(e1));
    }

    private Election getFixture() throws ParseException {
        return new Election(new ElectionId("dkijakarta"),
                "Pemilu Gubernur DKI Jakarta",
                sdf.parse("15/01/2017"),
                sdf.parse("01/01/2017"),
                sdf.parse("14/01/2017"),
                new City("Padang",
                        new Location(10.0, 10.0),
                        new Province("Sumatera Barat", "sumbar",
                                new Country("Indonesia", "ID"))),
                ElectionType.GOVERNOR);
    }

}