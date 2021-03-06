package io.qase.po.components;

import io.qase.po.pages.Loadable;
import io.qase.utils.elements.ElementAction;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class AbstractComponent implements Loadable {

    private final SearchContext searchContext;

    public AbstractComponent(SearchContext context) {
        this.searchContext = context;
        PageFactory.initElements(new DefaultElementLocatorFactory(searchContext), this);
    }

    @Override
    public boolean isLoaded() {
        if (searchContext instanceof WebElement) {
            return ElementAction.isDisplayed(((WebElement) searchContext));
        } else {
            throw new IllegalStateException("Default implementation of isLoaded() does not support: " +
                    searchContext.getClass().getSimpleName());
        }
    }
}
