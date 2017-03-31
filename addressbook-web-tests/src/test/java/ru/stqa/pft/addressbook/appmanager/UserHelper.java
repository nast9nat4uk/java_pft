package ru.stqa.pft.addressbook.appmanager;

import com.sun.jna.platform.win32.Netapi32Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

/**
 * Created by Настя on 19.02.2017.
 */
public class UserHelper extends HelperBase {


    private int i;

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
        type(By.name("home"), userData.getHome());
        type(By.name("email"), userData.getEmail());
        attach(By.name("photo"), userData.getPhoto());



        /*if (creation) {
          if (userData.getGroup() == null) {
                new Select(wd.findElement
                        (By.name("new_group"))).selectByValue("[none]");//группа по умолчанию
            } else {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }*/

    }

    public void newUserAdd() {
        click(By.linkText("add new"));
    }


    public void selectUserById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    private void detailsButtonById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
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


    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Users userCache = null;

    public Users all() {
        if (userCache != null) {
            return new Users(userCache);
        }
        userCache = new Users();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();

            userCache.add(new UserData().withId(id).withName(name).withLastName(lastname).withAllPhones(allPhones)
                    .withAddress(address).withAllEmails(allEmails));
        }
        return new Users(userCache);
    }

    public UserData infoFromEditForm(UserData user) {
        editButtonById(user.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return  new UserData().withId(user.getId()).withName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    public String infoFromDetailsForm(UserData user) {
        detailsButtonById(user.getId());
        String allInfo = wd.findElement(By.id("content")).getText();
        return  allInfo;
    }

    public void addUserToGroup(UserData user) {
        selectUserById(user.getId());
        click(By.name("add"));
    }

    public String getTargetGroup() {
        String targetGroup = wd.findElement(By.xpath("//select[@name='to_group']/option[1]")).getText();
        return targetGroup;
    }

    public Boolean assertUserIsInGroup(UserData user, String targetGroup) {
        Select select = new Select(wd.findElement(By.name("group")));
        select.selectByVisibleText(targetGroup);
        return  isElementPresent(By.id(String.format("%s", user.getId())));
    }

    public void removeUserFromTargetGroup() {
        click(By.name("remove"));
    }

    public void assertUserIsRemovedFromGroup(UserData selectedUser) {
        assertTrue(!isElementPresent(By.id(String.format("%s",selectedUser.getId()))));
    }

    public void isUserInGroup(UserData selectedUser, String targetGroup) {
        Set<GroupData> groups =new HashSet<GroupData>(selectedUser.getGroups());
        i=0;
        for (GroupData group:groups){
            if (group.getName().equals(targetGroup)){
               i = 1;
                break;
            }
        }
        assertTrue(i==1);
    }

    public void isUserNotInGroup(UserData selectedUser, String targetGroup) {
        Set<GroupData> groups =new HashSet<GroupData>(selectedUser.getGroups());
        i=0;
        for (GroupData group:groups){
            if (group.getName().equals(targetGroup)) {
                i = 1;
                break;
            }
        }
        assertTrue(i==0);
    }
}
