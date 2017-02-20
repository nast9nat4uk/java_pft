package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

/**
 * Created by Настя on 20.02.2017.
 */
public class ModificationUserTest extends TestBase{
    @Test
    public void testUserModify(){
        app.getNavigationHelper().gotoUserPage();
        app.getUserHelper().editButton();
        app.getUserHelper().fillinTheNewUserForm(new UserData("nameTest", "Name2Test", "addressTest", "1234567", "m@m.com"));
        app.getUserHelper().updateUser();
    }
}
