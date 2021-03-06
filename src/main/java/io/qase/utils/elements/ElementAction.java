package io.qase.utils.elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

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

}
