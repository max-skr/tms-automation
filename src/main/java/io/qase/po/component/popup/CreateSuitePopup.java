package io.qase.po.component.popup;

import io.qase.data.dto.SuiteCreateData;
import io.qase.po.component.AbstractComponent;
import io.qase.po.component.DropdownSearchSelect;
import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class CreateSuitePopup extends AbstractComponent {

    @FindBy(id = "name")
    private WebElement suiteNameField;

    @FindBy(id = "parent_idGroup")
    private WebElement parentSuiteDropdownContainer;

    @FindBy(css = "#descriptionGroup input")
    private WebElement descriptionField;

    @FindBy(css = "#preconditionsGroup input")
    private WebElement preconditionsField;

    @FindBy(id = "save-suite-button")
    private WebElement createButton;

    @FindBy(xpath = "//div[contains(@class, 'modal-footer')]/*[text() = 'Cancel']")
    private WebElement cancelButton;

    public CreateSuitePopup(SearchContext context) {
        super(context);
    }

    @Override
    public boolean isLoaded() {
        return ElementAction.isDisplayed(createButton);
    }

    public void createSuite(SuiteCreateData suiteData) {
        ElementAction.setText(suiteNameField, suiteData.getName());
        Optional.ofNullable(suiteData.getParentSuite()).ifPresent(parentSuiteName ->
                new DropdownSearchSelect(parentSuiteDropdownContainer).searchSelect(parentSuiteName));
        Optional.ofNullable(suiteData.getDescription()).ifPresent(description ->
                ElementAction.setText(descriptionField, description));
        Optional.ofNullable(suiteData.getPreconditions()).ifPresent(preconditions ->
                ElementAction.setText(preconditionsField, preconditions));
        ElementAction.click(createButton);
        WaitUtils.waitFor(() -> !this.isLoaded(), "Wait for Create suite popup disappear");
    }
}
