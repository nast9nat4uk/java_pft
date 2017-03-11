package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.user().list().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test")
                    .withAddress("addressTest").withPhone( "1234567").withEmail( "m@m.com").withGroup( "test1"));
        }
    }
    @Test //(enabled = false)

    public void testDeleteUser() {
        List<UserData> before = app.user().list();
        int index = before.size() - 1;
        app.user().selectUser(index);
        app.user().delete();
        app.group().confirmAlert();
        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(index);
        Assert.assertEquals(before, after);

    }
}
