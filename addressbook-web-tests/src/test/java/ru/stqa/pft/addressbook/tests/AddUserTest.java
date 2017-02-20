package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class AddUserTest extends TestBase {


    @Test
    public void testAddUser() {

        app.getUserHelper().newUserAdd();
        app.getUserHelper().fillinTheNewUserForm(new UserData("nameTest", "Name2Test", "addressTest", "1234567", "m@m.com"));
        app.getUserHelper().submitNewUser();
    }


}