package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.user().all().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test"));
        }
    }


    @Test

    public void testUserModify() {
        Users before = app.user().all();
        UserData modifiedUser = before.iterator().next();//выбираем юзера из множества случайным образом
        UserData user = new UserData().withId(modifiedUser.getId()).withName("nameTest")
                .withLastName("Name2Test").withAddress("addressTest").withPhone("1234567").withEmail( "m@m.com");
        app.user().modify(user);
        Users after = app.user().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedUser);
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    }


}
