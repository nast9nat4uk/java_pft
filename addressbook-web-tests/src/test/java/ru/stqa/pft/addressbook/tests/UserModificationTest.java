package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.user().list().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test"));
        }
    }


    @Test(enabled = false)

    public void testUserModify() {
        List<UserData> before = app.user().list();
        int index = before.size() - 1;
        UserData user = new UserData().withId(before.get(index).getId()).withName("nameTest").withLastName("Name2Test").withAddress("addressTest").withPhone("1234567").withEmail( "m@m.com");
        app.user().modify(index, user);
        List<UserData> after = app.user().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
