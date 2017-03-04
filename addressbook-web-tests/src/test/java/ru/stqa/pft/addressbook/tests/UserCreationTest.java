package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase {


    @Test
    public void testAddUser() {
        int before =  app.getUserHelper().getUserCount();
        app.getUserHelper().createUser(new UserData("nameTest", "Name2Test",
                "addressTest", "1234567", "m@m.com", "test1"));
        int after =  app.getUserHelper().getUserCount();
        Assert.assertEquals(after, before+1);
    }


}
