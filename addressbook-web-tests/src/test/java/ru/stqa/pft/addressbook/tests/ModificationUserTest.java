package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.concurrent.TimeUnit;

/**
 * Created by Настя on 20.02.2017.
 */
public class ModificationUserTest extends TestBase{
    WebDriver wd;
    @Test

    public void testUserModify(){
        app.getNavigationHelper().gotoUserPage();
        if (!app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("nameTest", "Name2Test",
                    "addressTest", "1234567", "m@m.com", "test1"));
        }

       // wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        app.getUserHelper().editButton();
        app.getUserHelper().fillinNewUserForm(new UserData("nameTest", "Name2Test",
                "addressTest", "1234567", "m@m.com", null), false);
        app.getUserHelper().updateUser();
    }
}
