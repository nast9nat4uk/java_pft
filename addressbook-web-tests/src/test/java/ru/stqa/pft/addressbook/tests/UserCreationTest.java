package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Set;

public class UserCreationTest extends TestBase {


    @Test
    public void testAddUser() {

        Set<UserData> before = app.user().all();
        UserData user = new UserData().withName("nameTest333").withLastName("Name2Test").withGroup("test1");
        app.user().create(user);
        Set<UserData> after = app.user().all();
        Assert.assertEquals(after.size(), before.size()+1);

        user.withId(after.stream().mapToInt((u)->u.getId()).max().getAsInt());
        before.add(user);
        Assert.assertEquals(before,after);

    }

}
