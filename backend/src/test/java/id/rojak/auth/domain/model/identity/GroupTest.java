package id.rojak.auth.domain.model.identity;

import id.rojak.Application;
import id.rojak.auth.domain.model.access.Role;
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
public class GroupTest {

    @Test
    public void shouldBeAbleToCreateNewInstance() {
        Group group = stub();

        Assert.assertEquals("Administrator", group.name());
        Assert.assertEquals(false, group.isSupportNesting());
        Assert.assertEquals("ROLE_ADMIN", group.role().name());
        Assert.assertEquals(0, group.groupMembers().size());
    }

    @Test
    public void shouldIncreaseGroupMemberAfterAddNewUser() {
        Group group = stub();

        Assert.assertEquals(0, group.groupMembers().size());
        group.addUser(userStub("imrenagi"));
        Assert.assertEquals(1, group.groupMembers().size());

    }

    @Test
    public void shouldNotIncreaseGroupMemberAfterAddExistingUser() {
        Group group = stub();

        Assert.assertEquals(0, group.groupMembers().size());
        group.addUser(userStub("imrenagi"));
        Assert.assertEquals(1, group.groupMembers().size());
        group.addUser(userStub("imrenagi"));
        Assert.assertEquals(1, group.groupMembers().size());

    }

    @Test
    public void shouldReturnTrueWhenUserIsMember() {
        Group group = new Group();

        User user = userStub("imrenagi");
        Assert.assertFalse(group.isMember(user));

        group.addUser(user);
        Assert.assertTrue(group.isMember(user));

    }

    @Test
    public void shouldRemoveUserFromMembers() {
        Group group = stub();

        Assert.assertEquals(0, group.groupMembers().size());

        group.addUser(userStub("imrenagi"));
        Assert.assertEquals(1, group.groupMembers().size());

        group.removeUser(userStub("imrenagi"));
        Assert.assertEquals(0, group.groupMembers().size());
        Assert.assertFalse(group.isMember(userStub("imrenagi")));
    }

    @Test
    public void nothingHappenWhenRemovingNonMember() {

        Group group = stub();

        Assert.assertEquals(0, group.groupMembers().size());

        group.addUser(userStub("imrenagi"));
        Assert.assertEquals(1, group.groupMembers().size());

        group.removeUser(userStub("imre"));
        Assert.assertEquals(1, group.groupMembers().size());

    }

    private Group stub() {
        return new Group("Administrator",
                "Group For All Administrators",
                false,
                new Role("Admin", "Admin role"));
    }


    private User userStub(String username) {
        return new User(
                username,
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