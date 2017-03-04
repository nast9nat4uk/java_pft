package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserDeletionTest extends TestBase {
    @Test

    public void testDeleteUser() {
        app.getNavigationHelper().gotoUserPage();
        if (!app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("nameTest", "Name2Test",
                    "addressTest", "1234567", "m@m.com", "test1"));
        }
        int before =  app.getUserHelper().getUserCount();
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.getGroupHelper().confirmAlert();
        int after =  app.getUserHelper().getUserCount();
        Assert.assertEquals(after, before-1);

    }
}
