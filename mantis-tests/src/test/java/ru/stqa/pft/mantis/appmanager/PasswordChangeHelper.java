package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Настя on 19.02.2017.
 */
public class PasswordChangeHelper extends HelperBase{

    private String user;
    private String password;
    private String username;

    public PasswordChangeHelper(ApplicationManager app) {
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
        this.username = user;
        click(By.linkText("Manage Users"));
        click(By.linkText(username));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void finish(String confirmationLink, String newpassword) {///ИЗМЕНИТЬ!
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click((By.cssSelector("input[value='Update User']")));
    }
}
