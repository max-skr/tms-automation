package io.qase.po.pages;

import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectsPage extends AuthorizedUserPage<ProjectsPage> {

    @FindBy(css = "[id = 'createButton'][href ^= '/project/create']")
    private WebElement createProjectButton;

    @FindBy(css = ".project-row .defect-title")
    private List<WebElement> projectNamesElements;

    @FindBy(css = ".dropdown-menu")
    private WebElement dropdownMenu;

    @FindBy(css = ".dropdown-menu .dropdown-item a[href $= 'delete']")
    private WebElement dropdownDeleteButton;

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public ProjectCreatePage openCreateProjectPage() {
        ElementAction.click(createProjectButton);
        return WaitUtils.waitLoaded(new ProjectCreatePage(driver));
    }

    public List<String> getProjectNames() {
        return projectNamesElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public ProjectDeleteConfirmationPage openDeleteProjectConfirmationPage(String projectName) {
        openOptionsDropdownForProject(projectName);
        ElementAction.click(dropdownDeleteButton);
        return WaitUtils.waitLoaded(new ProjectDeleteConfirmationPage(driver));
    }

    private void openOptionsDropdownForProject(String projectName) {
        By dropdownLocator = By.xpath(
                String.format("//tr[contains(@class, 'project-row')][descendant::a[text() = '%s']]", projectName) +
                        "//a[contains(@class, 'btn-dropdown')]");
        ElementAction.click(() -> driver.findElement(dropdownLocator));
        WaitUtils.waitFor(() -> ElementAction.isDisplayed(dropdownMenu), "Wait for dropdown menu appear");
    }

    @Override
    protected String getPagePath() {
        return "/projects";
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded() && ElementAction.isDisplayed(createProjectButton);
    }
}
