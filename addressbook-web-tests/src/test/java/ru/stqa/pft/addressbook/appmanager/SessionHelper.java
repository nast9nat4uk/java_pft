package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Настя on 19.02.2017.
 */
public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {

        super(wd);
    }

    public void login(String username, String userpassword) {

        type(By.name("pass"),userpassword);
        type(By.name("user"),username);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
