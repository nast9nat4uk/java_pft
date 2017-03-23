package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validUsersFromXml() throws IOException {
       BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.xml")));
       String line = reader.readLine();
       String xml = "";
       while (line!=null) {
           xml+=line;
           line = reader.readLine();
       }
        XStream xstream = new XStream();
        xstream.processAnnotations(UserData.class);
        List<UserData> users =(List<UserData>) xstream.fromXML(xml);
        return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validUsersFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")));
        String json = "";
        String line = reader.readLine();
        while (line!=null) {
            json+=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<UserData> users =  gson.fromJson(json, new TypeToken<List<UserData>>(){}.getType());
        return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }


    @Test (dataProvider = "validUsersFromJson")
    public void testAddUser(UserData user) {
        app.goTo().userPage();
        Users before = app.user().all();
        File photo = new File("src/test/resources/1.jpg");
        app.user().create(user);
        assertThat(app.user().count(), equalTo(before.size()+1));
        Users after = app.user().all();
        assertThat(after, equalTo(
                before.withAdded(user.withId(after.stream().mapToInt((u)->u.getId()).max().getAsInt()))));
    }
}
