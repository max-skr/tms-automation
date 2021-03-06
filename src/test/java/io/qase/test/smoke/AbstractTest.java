package io.qase.test.smoke;

import io.qase.utils.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void startDriver() {
        driver = DriverFactory.getInstance().createDriver();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
    }

}
