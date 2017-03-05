package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

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
        List<UserData> before = app.getUserHelper().getUserList();
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.getGroupHelper().confirmAlert();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size()-1);

    }
}
