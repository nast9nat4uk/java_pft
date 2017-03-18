package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Настя on 18.03.2017.
 */
public class UserInfoTest extends TestBase {

    @Test
    public  void  testFullUserInfo(){
        UserData user = app.user().all().iterator().next();
        String userInfoFromDetail = app.user().infoFromDetailsForm(user);
        System.out.println(cleaned(userInfoFromDetail));
        assertThat (cleaned(userInfoFromDetail),equalTo(mergeUserInfoFromMainPage(user)));
    }
    private String mergeUserInfoFromMainPage(UserData user) {
        return Arrays.asList(user.getName(), user.getLastName(), user.getAddress(), user.getAllPhones(), user.getAllEmails())
                .stream().filter((s)->!s.equals("")).map(UserPhoneTest::cleaned).collect(Collectors.joining(""));
    }

    public static  String cleaned(String userInfoFromDetail){
        return userInfoFromDetail.replaceAll("M: ","").replaceAll("W: ","").replaceAll("\\s","")
        .replaceAll("[-()]","");

    }

}
