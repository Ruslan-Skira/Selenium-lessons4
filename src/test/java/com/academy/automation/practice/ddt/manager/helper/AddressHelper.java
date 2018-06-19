package com.academy.automation.practice.ddt.manager.helper;

import com.academy.automation.practice.ddt.manager.page.AddressPage;
import org.openqa.selenium.WebDriver;

public class AddressHelper {

    private WebDriver driver;

    public AddressHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPresentAlias(String alias) {
        return new AddressPage(driver)
                .getAliases()
                .contains(alias.toUpperCase());
    }

    public void removeAddress(String alias) {
        new AddressPage(driver)
                .clickDeleteButton(alias)
                .acceptDeletion();
    }
}
