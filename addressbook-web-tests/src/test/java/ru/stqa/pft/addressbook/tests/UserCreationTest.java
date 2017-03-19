package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsers(){
        List<Object[]> list =  new ArrayList<Object[]>();
        list.add(new Object[] {new UserData().withName("name1").withLastName("lastname1")});
        list.add(new Object[] {new UserData().withName("name2").withLastName("lastname2")});
        list.add(new Object[] {new UserData().withName("name3").withLastName("lastname3")});
        return list.iterator();
    }


    @Test (dataProvider = "validUsers")
    public void testAddUser(UserData user) {
        Users before = app.user().all();
        File photo = new File("src/test/resources/1.jpg");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size()+1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((u)->u.getId()).max().getAsInt()))));
    }
}
