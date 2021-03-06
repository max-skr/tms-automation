package io.qase.po.pages;

import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage<T extends AbstractPage<T>> implements Loadable {

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        driver.get(getBaseUrl() + getPagePath());
        return (T) WaitUtils.waitLoaded(this);
    }

    protected abstract String getPagePath();

    private String getBaseUrl() {
        return "https://app.qase.io";
    }

}
