package id.rojak.auth.domain.model.identity;

import id.rojak.auth.AuthApplication;
import id.rojak.auth.domain.model.access.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.fail;

/**
 * Created by inagi on 8/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthApplication.class)
@WebAppConfiguration
public class GroupMemberTest {

    @Test
    public void shouldBeAbleToCreateGroupMember() {

        GroupMember gm = new GroupMember("imrenagi", GroupMemberType.User);

        Assert.assertEquals("imrenagi", gm.name());
        Assert.assertTrue(gm.isUser());
        Assert.assertNull(gm.group());

        gm.setGroup(this.groupStub());
        Assert.assertEquals("Administrator", gm.group().name());
    }

    @Test
    public void shouldThrowExceptionIfNameIsEmpty() {
        try {
            GroupMember gm = new GroupMember("", GroupMemberType.User);
        } catch (Exception e) {}
    }

    @Test
    public void shouldThrowExceptionIfTypeIsNull() {
        try {
            GroupMember gm = new GroupMember("imrenagi", null);
        } catch (Exception e) {}
    }

    private Group groupStub() {
        return new Group("Administrator",
                "Group For All Administrators",
                false,
                new Role("Admin", "Admin role"));
    }



}