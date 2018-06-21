package com.academy.tmp;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DemoTmp {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        //driver.manage().timeouts().
    }

    @Test
    public void testFluent() throws InterruptedException {
        final WebElement ulDynamic = driver.findElement(By.id("homeslider"));
        final String[] attr = {"", "", ""};

        ExpectedCondition<Boolean> differAttributes = driver -> {
            try {
                System.out.println(ulDynamic.getAttribute("style"));
                for (int i = 0; i < attr.length; i++) {
                    if (attr[i].isEmpty()) {
                        attr[i] = ulDynamic.getAttribute("style");
                        return false;
                    }
                }

                for (int i = 0; i < attr.length; i++) {
                    if (attr[i].equals(ulDynamic.getAttribute("style"))) {
                        return true;
                    }
                }
                return false;
            }
            catch (Exception e) {
                // no jQuery present
                return false;
            }
        };

        Boolean wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .until(differAttributes);
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }


}
