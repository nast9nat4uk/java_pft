package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsers() throws IOException {
        List<Object[]> list =  new ArrayList<Object[]>();
       BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.csv")));
       String line = reader.readLine();
       while (line!=null) {
           String[] split = line.split(";");
           list.add(new Object[]{new UserData().withName(split[0]).withLastName(split[1])});
           line = reader.readLine();
       }
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
