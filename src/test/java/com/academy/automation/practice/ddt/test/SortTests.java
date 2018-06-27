package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.page.HomePage;
import com.academy.automation.practice.ddt.manager.page.DressPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SortTests extends BaseTest {

    @Test
    public void testSortDress()  {
        manager.goTo().home();
        DressPage dressPage = new HomePage(manager.getDriver())
                .clickDressLink()
                .selectByText("Price: Lowest first");

        //System.out.println(dressPage.getFirstPrice());
        dressPage.waitForJSandJQueryToLoad();
        Assert.assertEquals(dressPage.getFirstPrice(), "$16.40");
    }
}
