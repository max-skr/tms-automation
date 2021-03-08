package io.qase.po.pages.project;

import io.qase.data.dto.ProjectCreateData;
import io.qase.data.dto.ProjectCreateData.AccessOption;
import io.qase.po.pages.AuthorizedUserPage;
import io.qase.po.pages.repo.RepositoryPage;
import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static io.qase.utils.elements.ElementAction.setText;

public class ProjectCreatePage extends AuthorizedUserPage<ProjectCreatePage> {

    @FindBy(css = ".form-create-project")
    private WebElement createProjectForm;

    @FindBy(id = "inputTitle")
    private WebElement projectNameField;

    @FindBy(id = "inputCode")
    private WebElement projectCodeField;

    @FindBy(id = "inputDescription")
    private WebElement projectDescriptionField;

    @FindBy(id = "private-access-type")
    private WebElement privateAccessTypeCheckbox;

    @FindBy(id = "public-access-type")
    private WebElement publicAccessTypeCheckbox;

    @FindBy(id = "accessAll")
    private WebElement allMemberAccessCheckbox;

    @FindBy(id = "accessGroup")
    private WebElement groupAccessCheckbox;

    @FindBy(id = "accessNone")
    private WebElement noAccessCheckbox;

    @FindBy(css = "button[type = 'submit']")
    private WebElement createProjectButton;

    public ProjectCreatePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded() && ElementAction.isDisplayed(createProjectForm);
    }

    @Override
    protected String getPagePath() {
        return "/project/create";
    }

    public RepositoryPage createProject(ProjectCreateData data) {
        setText(projectNameField, data.getName());
        setText(projectCodeField, data.getCode());
        setText(projectDescriptionField, data.getDescription());
        if (data.isPrivate()) {
            privateAccessTypeCheckbox.click();
        } else {
            publicAccessTypeCheckbox.click();
        }
        selectAccessOption(data.getMembersAccessOption());
        createProjectButton.click();
        return WaitUtils.waitLoaded(new RepositoryPage(driver));
    }

    private void selectAccessOption(AccessOption accessOption) {
        if (AccessOption.ALL_MEMBERS.equals(accessOption)) {
            allMemberAccessCheckbox.click();
        } else if (AccessOption.NO_MEMBERS.equals(accessOption)) {
            noAccessCheckbox.click();
        } else if (AccessOption.SELECT_MEMBERS.equals(accessOption)) {
            groupAccessCheckbox.click();
        }
    }
}
