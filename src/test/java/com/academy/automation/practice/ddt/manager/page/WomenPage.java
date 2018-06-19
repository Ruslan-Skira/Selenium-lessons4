package com.academy.automation.practice.ddt.manager.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenPage extends BasePage {

    @FindBy(css="#center_column > div.content_scene_cat > div > div > span")
    private WebElement womenCaptureElement;

    private WebDriver driver;

    public WomenPage(WebDriver driver) {
        super(driver);
    }
    public String getWomenCapture() {
        return womenCaptureElement.getText().trim();
    }
}
