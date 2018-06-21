package com.academy.automation.practice.ddt.manager;

import com.academy.automation.practice.ddt.manager.helper.AccountHelper;
import com.academy.automation.practice.ddt.manager.helper.AddressHelper;
import com.academy.automation.practice.ddt.manager.helper.NavigationHelper;
import com.academy.automation.practice.ddt.manager.helper.SessionHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestManager {
    private static int DEFAULT_WAIT = 30;
    protected final Properties prop = new Properties();
    private String baseUrl;

    private WebDriver driver;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private AccountHelper accountHelper;
    private AddressHelper addressHelper;

    public WebDriver getDriver() {
        return driver;
    }

    public void init(String browser) throws IOException {
        String path = System.getProperty("cfg");
        prop.load(new FileInputStream(path));
        baseUrl = prop.getProperty("url");

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.driver"));
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", prop.getProperty("firefox.driver"));
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
        // driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
        //        driver.manage().window().maximize();

        navigationHelper = new NavigationHelper(driver, baseUrl);
        sessionHelper = new SessionHelper(driver, prop.getProperty("login"), prop.getProperty("password"));
        accountHelper = new AccountHelper(driver);
        addressHelper = new AddressHelper(driver);
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper session() {
        return sessionHelper;
    }

    public AccountHelper account() {
        return accountHelper;
    }

    public AddressHelper address() {
        return addressHelper;
    }

    public void stop() {
        driver.quit();
    }

    public String root() {
        return System.getProperty("basedir");
    }
}
