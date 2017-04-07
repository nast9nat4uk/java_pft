package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Настя on 19.02.2017.
 */
public class NavigationHelper extends HelperBase{

    String user;
    String password;

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String user, String password) {
        this.user = user;
        this.password = password;
        type(By.name("username"), user);
        type(By.name("password"), password);
        click(By.className("button"));
    }

    public void resetPassword(String user) {
        this.user = user;
        click(By.linkText("Manage Users"));
        click(By.linkText(user));
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
