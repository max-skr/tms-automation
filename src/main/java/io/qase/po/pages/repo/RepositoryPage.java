package io.qase.po.pages.repo;

import io.qase.data.dto.SuiteCreateData;
import io.qase.po.component.grid.SuitesGrid;
import io.qase.po.component.popup.CreateSuitePopup;
import io.qase.po.component.popup.DeleteSuitePopup;
import io.qase.po.pages.AuthorizedUserPage;
import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RepositoryPage extends AuthorizedUserPage<RepositoryPage> {

    @FindBy(css = "img[alt='Empty project']")
    private WebElement emptyProjectImg;

    @FindBy(id = "suitecases-container")
    private WebElement suitesContainer;

    @FindBy(xpath = "//*[text() = 'Create new suite' or @id = 'create-suite-button']")
    private WebElement createSuiteButton;

    @FindBy(css = ".modal-case")
    private WebElement modalContainer;

    public RepositoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPagePath() {
        return "/project/" + ID_PLACEHOLDER;
    }

    public RepositoryPage get(String projectCode) {
        return super.get(projectCode);
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded() &&
                (ElementAction.isDisplayed(emptyProjectImg) || ElementAction.isDisplayed(suitesContainer));
    }

    public List<String> getSuiteNames() {
        return getSuitesGrid().getSuiteNames();
    }

    public RepositoryPage createSuite(SuiteCreateData suiteData) {
        openCreateSuitePopup().createSuite(suiteData);
        return WaitUtils.waitLoaded(this);
    }

    public RepositoryPage deleteSuite(String name) {
        getSuitesGrid().openDeletePopup(name);
        WaitUtils.waitLoaded(new DeleteSuitePopup(modalContainer)).deleteSuite();
        return WaitUtils.waitLoaded(this);
    }

    public CreateSuitePopup openCreateSuitePopup() {
        ElementAction.click(createSuiteButton);
        return WaitUtils.waitLoaded(new CreateSuitePopup(modalContainer));
    }

    private SuitesGrid getSuitesGrid() {
        return new SuitesGrid(suitesContainer);
    }
}
