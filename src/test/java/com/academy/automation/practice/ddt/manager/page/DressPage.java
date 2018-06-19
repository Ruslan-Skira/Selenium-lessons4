package com.academy.automation.practice.ddt.manager.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DressPage extends  BasePage{

    @FindBy(id="selectProductSort")
    private WebElement sortBySelect;

    @FindBy(css="#center_column > ul > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.right-block > div.content_price > span.price.product-price")
    private WebElement firstPrice;

    public DressPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstPrice() {
        return firstPrice.getText().trim();
    }

    public DressPage selectByText(String text) {
        selectByText(sortBySelect, text);
        return this;
    }
}
