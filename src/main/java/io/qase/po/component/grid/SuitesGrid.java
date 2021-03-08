package io.qase.po.component.grid;

import io.qase.po.component.AbstractComponent;
import io.qase.utils.elements.ElementAction;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class SuitesGrid extends AbstractComponent {

    @FindBy(css = ".suite-header-title")
    private List<WebElement> nameElements;

    public SuitesGrid(SearchContext context) {
        super(context);
    }

    public List<String> getSuiteNames() {
        if (!isLoaded()) {
            return Collections.emptyList();
        }
        return nameElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void openDeletePopup(String suiteName) {
        String suiteNameXpath = format("//a[contains(@class, 'suite-header-title') and text() = '%s']", suiteName);
        WebElement suiteNameElement = searchContext.findElement(By.xpath(suiteNameXpath));
        ElementAction.hover(suiteNameElement);
        By trLocator = By.xpath(suiteNameXpath + "/following-sibling::*[descendant::i[contains(@class, 'fa-trash')]]");
        searchContext.findElement(trLocator).click();
    }
}
