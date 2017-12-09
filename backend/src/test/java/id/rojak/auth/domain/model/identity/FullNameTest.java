package id.rojak.auth.domain.model.identity;


import id.rojak.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class FullNameTest {

    @Test
    public void shouldBeAbleToCreateNewObject() {
        FullName name = this.getFixture();
        Assert.assertNotNull(name);
    }

    @Test
    public void shouldBeAbleToCreateNewObjectFromAnotherInstance() {
        FullName name = this.getFixture();
        FullName anotherName = new FullName(name);

        Assert.assertNotNull(anotherName);
        Assert.assertEquals(true, name.equals(anotherName));
    }

    @Test
    public void shouldReturnCorrectFormattedName() {
        FullName name = new FullName("Joko", "Widodo");
        Assert.assertEquals("Joko Widodo", name.asFormattedName());
    }

    @Test
    public void shouldOkIfFirstNameIsChanged() {
        FullName name = this.getFixture();
        FullName newName = name.withChangedFirstName("Ahmad");

        Assert.assertNotEquals(name, newName);
        Assert.assertEquals("Ahmad", newName.firstName());
        Assert.assertTrue(name.lastName().equals(newName.lastName()));
    }

    @Test
    public void shouldOkIfLastNameIsChanged() {
        FullName name = this.getFixture();
        FullName newName = name.withChangedLastName("Prabowo");

        Assert.assertNotEquals(name, newName);
        Assert.assertEquals("Prabowo", newName.lastName());
        Assert.assertTrue(name.firstName().equals(newName.firstName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstNameIsEmpty() {
        FullName name = this.getFixture();
        name.setFirstName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstCharInFirstNameIsNotCapital() {
        FullName name = this.getFixture();
        name.setFirstName("joko");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfFirstCharInFirstNameLengthIsLargerThan50() {
        FullName name = this.getFixture();
        name.setFirstName("If we create our database queries by using Querydsl, our repository interface must extend " +
                "the QueryDslPredicateExecutor<T> interface. This interface declares one method that we can use when " +
                "we want to paginate the query results of database queries that use Querydsl:");
    }

    private FullName getFixture() {
        return new FullName("Joko", "Widodo");
    }

}