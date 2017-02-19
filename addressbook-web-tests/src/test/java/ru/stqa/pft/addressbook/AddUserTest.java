package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class AddUserTest extends TestBase {
    

    @Test
    public void testAddUser() {

        newUserAdd();
        fillinTheNewUserForm(new UserData("nameTest", "Name2Test", "addressTest", "1234567", "m@m.com"));
        submitNewUser();
    }


}
