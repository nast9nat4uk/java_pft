package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserCreationTest extends TestBase {


    @Test
    public void testAddUser() {

        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().createUser(new UserData("nameTest", "Name2Test",
                "addressTest", "1234567", "m@m.com", "test1"));
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size()+1);
    }


}
