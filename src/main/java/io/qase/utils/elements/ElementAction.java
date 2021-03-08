package io.qase.utils.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;
import java.util.function.Supplier;

import static io.qase.utils.wait.WaitUtils.waitFor;

public class ElementAction {

    public static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void setText(WebElement element, String text) {
        if (Objects.nonNull(text)) {
            element.clear();
            element.sendKeys(text);
        }
    }

    public static void click(WebElement element) {
        click(() -> element);
    }

    public static void click(Supplier<WebElement> elementSupplier) {
        try {
            elementSupplier.get().click();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            waitFor(() -> ElementAction.isDisplayed(elementSupplier.get()), "Wait for element to be displayed");
            elementSupplier.get().click();
        }
    }

    public static void hover(WebElement element) {
        new Actions(getDriver(element)).moveToElement(element).perform();
    }

    public static WebDriver getDriver(SearchContext element) {
        if (element instanceof WrapsDriver) {
            return ((WrapsDriver) element).getWrappedDriver();
        } else if (element instanceof WrapsElement) {
            return getDriver(unwrapElement(((WrapsElement) element)));
        } else {
            throw new IllegalArgumentException("Unable to unwrap element: " + element);
        }
    }

    private static WebElement unwrapElement(WrapsElement elementWrapper) {
        WebElement element = null;
        while (Objects.isNull(element) || element instanceof WrapsElement) {
            element = elementWrapper.getWrappedElement();
        }
        return element;
    }

}
