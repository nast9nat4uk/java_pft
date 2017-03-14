package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;
import java.util.Set;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.user().all().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test")
                    .withAddress("addressTest").withPhone( "1234567").withEmail( "m@m.com").withGroup( "test1"));
        }
    }
    @Test

    public void testDeleteUser() {
        Set<UserData> before = app.user().all();
        UserData deletedUser = before.iterator().next();
        app.user().deleteUser(deletedUser);
        app.group().confirmAlert();
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(deletedUser);
        Assert.assertEquals(before, after);

    }


}
