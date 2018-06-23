package com.academy.automation.practice.ddt.test;

import com.academy.automation.practice.ddt.manager.TestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

import  com.academy.util.MatcherAssertExt;

public class BaseTest {
    protected static final Logger LOG = LogManager.getLogger(BaseTest.class);
    protected final TestManager manager = new TestManager();


    @BeforeSuite
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws Exception {
        manager.init(browser);
        MatcherAssertExt.log = LOG;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        manager.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] params) {
        LOG.info("Start test {} with parameters {}",
                method.getName(), Arrays.asList(params));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        LOG.info("Stop test {}", method.getName());
    }
}
