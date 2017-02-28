package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

/**
 * Created by Настя on 20.02.2017.
 */
public class DeleteUserTest extends TestBase {
    @Test

    public void testDeleteUser() {
        app.getNavigationHelper().gotoUserPage();
        if (!app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("nameTest", "Name2Test",
                    "addressTest", "1234567", "m@m.com", "test1"));
        }
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.getGroupHelper().confirmAlert();

    }
}
