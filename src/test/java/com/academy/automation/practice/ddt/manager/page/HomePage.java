package com.academy.automation.practice.ddt.manager.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(css="#block_top_menu > ul > li:nth-child(1) > a")
    private WebElement womenMenuLink;

    @FindBy(css="#header > div.nav > div > div > nav > div.header_user_info > a")
    private WebElement signInLink;

    @FindBy(css="#block_top_menu > ul > li:nth-child(2) > a")
    private WebElement dressLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WomenPage clickWomenLink() {
        womenMenuLink.click();
        return new WomenPage(driver);
    }

    public LoginPage clickSignInLink() {
        signInLink.click();
        return new LoginPage(driver);
    }

    public DressPage clickDressLink() {
        dressLink.click();
        return new DressPage(driver);
    }
}
