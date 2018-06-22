package com.academy.automation.practice.ddt.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeTests {

    @Test
    public void testRollingImage() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        WebElement slider = driver.findElement(By.id("homeslider"));
        String[] styles = {"", "", ""};

//        ExpectedCondition<Boolean> rollingComplete =
//                new ExpectedCondition<Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver driver) {
//                        // логика условия
//                        System.out.println(slider.getAttribute("style"));
//                        for (int i = 0; i < styles.length; i++) {
//                            if (styles[i].isEmpty()) {
//                                styles[i] = slider.getAttribute("style");
//                                return false;
//                            }
//                        }
//
//                        for (int i = 0; i < styles.length; i++) {
//                            if (styles[i].equals(slider.getAttribute("style"))) {
//                                return true;
//                            }
//                        }
//                        return false;
//                };
//    };

        ExpectedCondition<Boolean> rollingComplete =
                driver1 -> {
                    // логика условия
                    System.out.println(slider.getAttribute("style"));
                    for (int i = 0; i < styles.length; i++) {
                        if (styles[i].isEmpty()) {
                            styles[i] = slider.getAttribute("style");
                            return false;
                        }
                    }

                    for (int i = 0; i < styles.length; i++) {
                        if (styles[i].equals(slider.getAttribute("style"))) {
                            return true;
                        }
                    }
                    return false;
            };

        new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(15))
            .pollingEvery(Duration.ofSeconds(3))
            .until(rollingComplete);
}
}
