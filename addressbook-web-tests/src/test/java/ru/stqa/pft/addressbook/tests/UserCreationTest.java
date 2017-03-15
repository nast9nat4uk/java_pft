package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTest extends TestBase {


    @Test
    public void testAddUser() {

        Users before = app.user().all();
        UserData user = new UserData().withName("nameTest333").withLastName("Name2Test").withGroup("test1");
        app.user().create(user);
        Users after = app.user().all();
        assertThat(after.size(), equalTo(before.size()+1));


        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((u)->u.getId()).max().getAsInt()))));

    }

}
