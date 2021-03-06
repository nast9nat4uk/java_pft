package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Настя on 19.02.2017.
 */
public class NavigationHelper extends HelperBase{
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {

        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                &&isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }
    public void userPage() {

        if (isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void targetGroup(String targetGroup) {
        click(By.linkText(String.format("group page \"%s\"", targetGroup)));
    }
}
