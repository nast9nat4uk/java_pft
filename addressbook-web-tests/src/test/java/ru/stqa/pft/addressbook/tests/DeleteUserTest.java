package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Настя on 20.02.2017.
 */
public class DeleteUserTest extends  TestBase{
    @Test

    public  void  testDeleteUser(){
        app.getNavigationHelper().gotoUserPage();
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.getGroupHelper().confirmAlert();

    }
}
