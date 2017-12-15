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
public class NomineeTest {



    private Nominee getFixture() {
        return new Nominee(
                new NomineeId("newNominee"),
                new FullName(
                        "Jokowi",
                        "Susilo",
                        "Subiyanto"),
                "jokowi",
                new SocialMediaInformation(
                        "http://facebook.com/jokowi",
                        "jokowi",
                        "jokowi",
                        "https://facebook.com/jokowi"));
    }

    @Test
    public void shouldBeAbleToCreateNewInstance() {
        Nominee nominee = this.getFixture();

        Assert.assertNotNull(nominee);
    }

    @Test
    public void shouldReturnTrueIfTwoNomineeHaveTheSameNomineeId() {
        Nominee n1 = this.getFixture();
        Nominee n2 = this.getFixture();

        Assert.assertTrue(n1.equals(n2));
    }

    @Test
    public void shouldReturnFalseIfTwoNomineeHaveDifferentNomineeId() {
        Nominee n1 = this.getFixture();
        Nominee n2 = this.getFixture();
        n2.setNomineeId(new NomineeId("jokowi"));

        Assert.assertFalse(n1.equals(n2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNomineeIdIsNull() {
        Nominee n = this.getFixture();
        n.setNomineeId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNomineeIdIsEmpty() {
        Nominee n = this.getFixture();
        n.setNomineeId(new NomineeId(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFullNameIsNull() {
        Nominee n = this.getFixture();
        n.setFullName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfSocialMediaInfoIsNull() {
        Nominee n = this.getFixture();
        n.setSocialMediaInformation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNickNameIsNull() {
        Nominee n = this.getFixture();
        n.setNickName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNickNameIsEmpty() {
        Nominee n = this.getFixture();
        n.setNickName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfNickNameIsLenghtIsGreaterThan10() {
        Nominee n = this.getFixture();
        n.setNickName("this is a nick name larger than 10");
    }

}