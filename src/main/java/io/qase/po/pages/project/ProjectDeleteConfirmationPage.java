package io.qase.po.pages.project;

import io.qase.po.pages.AuthorizedUserPage;
import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectDeleteConfirmationPage extends AuthorizedUserPage<ProjectDeleteConfirmationPage> {

    @FindBy(xpath = "//button[contains(text(), 'Delete project')]")
    private WebElement deleteProjectButton;

    public ProjectDeleteConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded() && ElementAction.isDisplayed(deleteProjectButton);
    }

    public ProjectDeleteConfirmationPage get(String projectCode) {
        return super.get(projectCode);
    }

    public ProjectsPage deleteProject() {
        ElementAction.click(deleteProjectButton);
        return WaitUtils.waitLoaded(new ProjectsPage(driver));
    }

    @Override
    protected String getPagePath() {
        return "/project/" + ID_PLACEHOLDER + "/delete";
    }
}
