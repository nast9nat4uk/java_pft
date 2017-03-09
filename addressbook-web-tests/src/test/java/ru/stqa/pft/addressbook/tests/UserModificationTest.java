package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserModificationTest extends TestBase{




    @Test

    public void testUserModify(){


        app.getNavigationHelper().gotoUserPage();
        if (!app.getUserHelper().isThereAUser()) {
            app.getUserHelper().createUser(new UserData("nameTest", "Name2Test",
                    "addressTest", "1234567", "m@m.com", "test1"));
        }
        List<UserData> before = app.getUserHelper().getUserList();
        //ожидание
        app.getUserHelper().waitForElement(By.xpath("//img[@title='Edit']"));
        app.getUserHelper().editButton(before.size()-1);
        UserData user = new UserData(before.get(before.size()-1).getId(),  "nameTest", "Name2Test",
                "addressTest", "1234567", "m@m.com", null);
        app.getUserHelper().fillinNewUserForm(user, false);
        app.getUserHelper().updateUser();
        app.getNavigationHelper().gotoUserPage();
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
