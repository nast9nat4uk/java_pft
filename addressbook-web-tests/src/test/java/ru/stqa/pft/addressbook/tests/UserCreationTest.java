package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTest extends TestBase {


    @Test
    public void testAddUser() {

        Users before = app.user().all();
        File photo = new File("src/test/resources/1.jpg");
        UserData user = new UserData().withName("nameTest333").withLastName("Name2Test").withPhoto(photo);
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size()+1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((u)->u.getId()).max().getAsInt()))));
    }
}
