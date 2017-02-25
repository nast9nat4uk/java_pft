package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by Настя on 19.02.2017.
 */
public class ApplicationManager {

    WebDriver wd;
    private  NavigationHelper navigationHelper;
    private  UserHelper userHelper;
    private  GroupHelper groupHelper;
    private  SessionHelper sessionHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {

        if (browser== org.openqa.selenium.remote.BrowserType.FIREFOX){
            wd = new FirefoxDriver();
        } else if (browser== org.openqa.selenium.remote.BrowserType.CHROME){
            wd = new ChromeDriver();
        } else if (browser == org.openqa.selenium.remote.BrowserType.IE){
            wd = new InternetExplorerDriver();
        }
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        groupHelper = new GroupHelper(wd);
        userHelper = new UserHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
