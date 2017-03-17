package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Настя on 16.03.2017.
 */
public class UserPhoneTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().userPage();
        if (app.user().all().size() ==0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test"));
        }
    }
    @Test
public void testUserPhones(){
      //  app.goTo().userPage();
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getHome(), equalTo(cleaned(userInfoFromEditForm.getHome())));

        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));


        assertThat(user.getEmail(), equalTo(userInfoFromEditForm.getEmail()));
        assertThat(user.getEmail2(), equalTo(userInfoFromEditForm.getEmail2()));
        assertThat(user.getEmail3(), equalTo(userInfoFromEditForm.getEmail3()));
    }

    private String mergePhones(UserData user) {
        return Arrays.asList(user.getHome(),user.getMobile(),user.getWork())
                .stream().filter((s) -> ! s.equals("")).map(UserPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }
    

    public static  String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");

    }

}
