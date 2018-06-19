package com.academy.automation.practice.ddt.manager.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id="email")
    private WebElement loginField;

    @FindBy(id="passwd")
    private WebElement passwordField;

    @FindBy(id="SubmitLogin")
    private WebElement signInLink;

    @FindBy(css="#center_column > div.alert.alert-danger > ol > li")
    private WebElement authMessageBlock;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage inputLogin(String login) {
        inputTextField(loginField, login);
        return this;
    }

    public LoginPage inputPassword(String password) {
        inputTextField(passwordField, password);
        return this;
    }

    public BasePage clickSignIn(boolean success) {
        signInLink.click();
        if (success)
            return new MyAccountPage(driver);
        else
            return this;
    }

    public String getErrorMessage() {
        return authMessageBlock.getText().trim();
    }
}
