package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Настя on 19.02.2017.
 */
public class ApplicationManager {

    private final Properties properties;
    WebDriver wd;
    private  NavigationHelper navigationHelper;
    private  UserHelper userHelper;
    private  GroupHelper groupHelper;
    private  SessionHelper sessionHelper;
    private String browser;
    private DBHelper dbHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

        dbHelper = new DBHelper();
        if (browser.equals(BrowserType.FIREFOX)){
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)){
            wd = new ChromeDriver();
        } else if (browser.equals( BrowserType.IE)){
            wd = new InternetExplorerDriver();

        }

        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));


        groupHelper = new GroupHelper(wd);
        userHelper = new UserHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));

    }



    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public UserHelper user() {
        return userHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public DBHelper db() {
        return  dbHelper;
    }


}
