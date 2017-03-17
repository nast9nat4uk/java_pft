package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

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
        assertThat(user.getMobile(), equalTo(cleaned(userInfoFromEditForm.getMobile())));
        assertThat(user.getWork(), equalTo(cleaned(userInfoFromEditForm.getWork())));
        assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
        assertThat(user.getEmail(), equalTo(userInfoFromEditForm.getEmail()));
        assertThat(user.getEmail2(), equalTo(userInfoFromEditForm.getEmail2()));
        assertThat(user.getEmail3(), equalTo(userInfoFromEditForm.getEmail3()));
    }
    public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");

    }

}
