package io.qase.po.components;

import io.qase.po.pages.Loadable;
import io.qase.utils.elements.ElementAction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractComponent implements Loadable {

    private WebElement baseElement;

    public AbstractComponent(WebElement element) {
        this.baseElement = element;
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    @Override
    public boolean isLoaded() {
        return ElementAction.isDisplayed(baseElement);
    }
}
