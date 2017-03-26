package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Настя on 20.02.2017.
 */
public class UserModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.db().users().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test").withPhoto(new File("src/test/resources/1.jpg")));
        }
    }


    @Test

    public void testUserModify() {
        Users before = app.db().users();
        UserData modifiedUser = before.iterator().next();//выбираем юзера из множества случайным образом
        UserData user = new UserData().withId(modifiedUser.getId()).withName("nameTest")
                .withLastName("Name2Test").withAddress("addressTest").withHomePhone("1234567").withEmail( "m@m.com")
                .withPhoto(new File("src/test/resources/1.jpg"));
        app.user().modify(user);
        assertThat(app.user().count(), equalTo(before.size()));
        Users after = app.db().users();
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    }


}
