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
        if (app.user().all().size() == 0) {
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test"));//Добавить поля ввода!!!
        }
    }

    @Test
    public void testUserPhones() {
        UserData user = app.user().all().iterator().next();
        UserData userInfoFromEditForm = app.user().infoFromEditForm(user);
        assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
        assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
        assertThat(user.getAddress(), equalTo(userInfoFromEditForm.getAddress()));
    }

    private String mergeEmails(UserData user) {
        return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    private String mergePhones(UserData user) {
        return Arrays.asList(user.getHome(), user.getMobile(), user.getWork())
                .stream().filter((s) -> !s.equals("")).map(UserPhoneTest::cleaned)
                .collect(Collectors.joining("\n"));
    }


    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

    }
}
