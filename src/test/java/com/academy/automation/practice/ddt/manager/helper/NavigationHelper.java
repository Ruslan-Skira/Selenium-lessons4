package com.academy.automation.practice.ddt.manager.helper;

import com.academy.automation.practice.ddt.manager.page.MyAccountPage;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver driver;
    private String baseUrl;

    public NavigationHelper(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    public void home() {
        driver.get(baseUrl);
    }

    public void address() {
        new MyAccountPage(driver)
                .clickAddress();
    }
}
