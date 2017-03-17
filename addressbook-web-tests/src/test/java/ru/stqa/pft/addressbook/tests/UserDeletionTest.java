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
public class UserDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.user().all().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test")
                    .withAddress("addressTest").withHomePhone( "1234567").withEmail( "m@m.com").withGroup( "test1"));
        }
    }
    @Test

    public void testDeleteUser() {
        Users before = app.user().all();
        UserData deletedUser = before.iterator().next();
        app.user().deleteUser(deletedUser);
        app.group().confirmAlert();
        assertThat(app.user().count(), equalTo(before.size()-1));
        Users after = app.user().all();
        assertThat(after, equalTo(before.without(deletedUser)));

    }


}
