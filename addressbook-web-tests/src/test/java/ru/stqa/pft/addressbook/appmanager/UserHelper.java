package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.UserData;

/**
 * Created by Настя on 19.02.2017.
 */
public class UserHelper  extends  HelperBase{


    public UserHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitNewUser() {
       click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillinTheNewUserForm(UserData userData) {
        type(By.name("firstname"),userData.getName());
        type(By.name("middlename"),"\\9");
        type(By.name("lastname"),userData.getLastName());
        type(By.name("nickname"),"\\9");
        type(By.name("title"),"\\9");
        type(By.name("company"),"\\9");
        type(By.name("address"),userData.getAddress());
        type(By.name("home"),userData.getPhone());
        type(By.name("email"),userData.getEmail());
    }

    public void newUserAdd() {
        click(By.linkText("add new"));
    }
}
