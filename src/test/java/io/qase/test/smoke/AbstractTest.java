package io.qase.test.smoke;

import io.qase.utils.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AbstractTest {

    protected WebDriver driver;

    @BeforeClass
    public void startDriver() {
        driver = DriverFactory.getInstance().createDriver();
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}
