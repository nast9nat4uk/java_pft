package ru.stqa.pft.addressbook.appmanager;

import com.sun.jna.platform.win32.Netapi32Util;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Настя on 19.02.2017.
 */
public class UserHelper extends HelperBase {


    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void submitNewUser() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillinNewUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getName());
        type(By.name("middlename"), "\\9");
        type(By.name("lastname"), userData.getLastName());
        type(By.name("nickname"), "\\9");
        type(By.name("title"), "\\9");
        type(By.name("company"), "\\9");
        type(By.name("address"), userData.getAddress());
        type(By.name("home"), userData.getPhone());
        type(By.name("email"), userData.getEmail());

        if (creation ){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void newUserAdd() {
        click(By.linkText("add new"));
    }

    public void selectUser() {
        click(By.name("selected[]"));
    }

    public void deleteUser() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void editButton() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateUser() {
        click(By.name("update"));
    }

    public void createUser(UserData user) {
        newUserAdd();
        fillinNewUserForm(user, true);
        submitNewUser();
        returnToUserPage();


    }

    private void returnToUserPage() {
        click(By.linkText("home"));
    }


    public boolean isThereAUser() {
        return isElementPresent(By.name("selected[]"));
    }


    public int getUserCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getUserList() {
        List<UserData> users =  new ArrayList<UserData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//td[3]"));
        for (WebElement element: elements) {
            String name = element.getText();
            UserData user = new UserData(name, null,null,null,null,null);
            users.add(user);
        }
        return users;

    }
}
