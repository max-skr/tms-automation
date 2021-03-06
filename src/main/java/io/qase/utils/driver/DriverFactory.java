package io.qase.utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Paths;

public enum DriverFactory {

    INSTANCE;

    public static DriverFactory getInstance() {
        return INSTANCE;
    }

    public WebDriver createDriver() {
        return createChrome();
    }

    private WebDriver createChrome() {
        String driverPath = Paths.get("src", "main", "resources", "driver", "chromedriver.exe").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }

}
