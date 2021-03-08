package io.qase.po.component;

import io.qase.utils.elements.ElementAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DropdownSearchSelect extends AbstractComponent {

    @FindBy(css = "[id ^= 'react-select']")
    private WebElement inputField;

    public DropdownSearchSelect(SearchContext context) {
        super(context);
    }

    public void searchSelect(String itemText) {
        ElementAction.setText(inputField, itemText);
        inputField.sendKeys(Keys.RETURN);
    }
}
