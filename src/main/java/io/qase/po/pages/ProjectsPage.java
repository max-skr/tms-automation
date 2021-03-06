package io.qase.po.pages;

import io.qase.utils.elements.ElementAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends AuthorizedUserPage<ProjectsPage> {

    @FindBy(css = "[id = 'createButton'][href ^= '/project/create']")
    private WebElement createProjectButton;

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPagePath() {
        return "/projects";
    }

    @Override
    public boolean isLoaded() {
        return ElementAction.isDisplayed(createProjectButton);
    }
}
