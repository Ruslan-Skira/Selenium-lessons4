package com.academy.automation.practice.ddt.manager.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void inputTextField(WebElement element, String value) {
        if (value != null) {
            element.click();
            String currentValue = element.getAttribute("value");
            if (!value.equals(currentValue)) {
                element.clear();
                element.sendKeys(value);
            }
        }
    }

    protected void selectByText(WebElement sortBySelect, String text) {
        new Select(sortBySelect).selectByVisibleText(text);
    }

    protected  void alertAccept(boolean accept) {
        try {
            if (accept)
                driver.switchTo().alert().accept();
            else
                driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30, 300);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    protected List<String> extractTextFromElements(List<WebElement> elements) {
        List<String> textList = new ArrayList<>(elements.size());
        for(WebElement el : elements)
            textList.add(el.getText().trim());

        return textList;
    }
}
