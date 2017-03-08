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

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

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

    public void selectUser(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteUser() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void editButton(int index) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
        //click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
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
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData(id, name, lastname,null,null,null,null);
            users.add(user);

        }

        return users;


    }

    public void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(wd, 15/*seconds*/);
        WebElement element = wait.until(presenceOfElementLocated(locator));
    }
}
