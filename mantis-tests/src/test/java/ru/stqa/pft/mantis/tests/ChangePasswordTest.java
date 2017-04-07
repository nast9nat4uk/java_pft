package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Настя on 07.04.2017.
 */
public class ChangePasswordTest extends  TestBaseForHometask {


    @Test
    public  void changePasswordTest(){
    app.getDriver();
    app.perform().login("administrator", "root");
    app.perform().resetPassword("user1");

    }

}
