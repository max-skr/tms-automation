package io.qase.po.component.popup;

import io.qase.po.component.AbstractComponent;
import io.qase.utils.elements.ElementAction;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteSuitePopup extends AbstractComponent {

    @FindBy(css = "button.btn-danger")
    private WebElement deleteButton;

    public DeleteSuitePopup(SearchContext context) {
        super(context);
    }

    public void deleteSuite() {
        ElementAction.click(deleteButton);
    }
}
