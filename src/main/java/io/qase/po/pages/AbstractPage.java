package io.qase.po.pages;

import io.qase.utils.wait.WaitUtils;
import org.apache.commons.lang3.mutable.MutableObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.stream.Stream;

public abstract class AbstractPage<T extends AbstractPage<T>> implements Loadable {

    protected static final String ID_PLACEHOLDER = "{id}";

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        if (!isLoaded()) {
            String pageUrl = getBaseUrl() + getPagePath();
            driver.get(pageUrl);
            return (T) WaitUtils.waitLoaded(this);
        } else {
            return (T) this;
        }
    }

    @SuppressWarnings("unchecked")
    protected T get(String... ids) {
        driver.get(getBaseUrl() + getFormattedPath(ids));
        return (T) WaitUtils.waitLoaded(this);
    }

    @Override
    public boolean isLoaded() {
        String pagePath = getPagePath();
        if (pagePath.contains(ID_PLACEHOLDER)) {
            return driver.getCurrentUrl().matches(getBaseUrl() + pagePath.replaceAll("\\{id}", ".+"));
        } else {
            return driver.getCurrentUrl().startsWith(getBaseUrl() + getPagePath());
        }
    }

    private boolean isCurrentUrlStartsWith(String startUrl) {
        return driver.getCurrentUrl().startsWith(startUrl);
    }

    protected abstract String getPagePath();

    private String getFormattedPath(String... ids) {
        MutableObject<String> result = new MutableObject<>(getPagePath());
        Stream.of(ids).forEach(id -> result.setValue(result.getValue().replace(ID_PLACEHOLDER, id)));
        return result.getValue();
    }

    protected final String getBaseUrl() {
        return "https://app.qase.io";
    }

}
