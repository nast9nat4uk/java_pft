package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if (creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void newUserAdd() {
        click(By.linkText("add new"));
    }


    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteSelectedUser() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }


    public void deleteUser(UserData user) {
        selectUserById(user.getId());
        deleteSelectedUser();
        userCache = null;
    }


    public void editButtonById(int id) {
        WebElement td = wd.findElement(By.cssSelector("input[value='"+id+"']"));
        WebElement raw = td.findElement(By.xpath(" ./../.."));//Подняться от текущего на 2 элемента вверх
        raw.findElement(By.xpath(".//img[@title='Edit']")).click();
    }


    public void update() {
        click(By.name("update"));
    }

    public void create(UserData user) {
        newUserAdd();
        fillinNewUserForm(user, true);
        submitNewUser();
        userCache = null;
        returnToUserPage();
    }

    public void modify(UserData user) {
       editButtonById(user.getId());
       fillinNewUserForm(user, false);
       update();
       userCache = null;
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

    private  Users userCache = null;
    public Users all() {
        if(userCache!=null){
            return new Users(userCache);
        }
        userCache =  new Users();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            userCache.add(new UserData().withId(id).withName(name).withLastName(lastname));
        }
        return new Users(userCache);
    }

    public void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(wd, 15/*seconds*/);
        WebElement element = wait.until(presenceOfElementLocated(locator));
    }

}
