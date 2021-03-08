package io.qase.test.smoke;

import io.qase.data.AccountProvider;
import io.qase.data.DataProvider;
import io.qase.data.dto.ProjectCreateData;
import io.qase.data.dto.UserData;
import io.qase.po.pages.LoginPage;
import io.qase.po.pages.project.ProjectCreatePage;
import io.qase.po.pages.project.ProjectsPage;
import io.qase.utils.wait.WaitUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectsCreateTest extends AbstractTest {

    private final ProjectCreateData projectData = DataProvider.getProjectCreateData();

    @BeforeClass
    public void login() {
        UserData user = AccountProvider.getInstance().getAdmin();
        driver.manage().deleteAllCookies();
        new LoginPage(driver).get().login(user);
    }

    @Test
    public void testRedirectFromProjectsList() {
        new ProjectsPage(driver).get()
                .openCreateProjectPage();
    }

    @Test
    public void testCreateProject() {
        new ProjectCreatePage(driver).get()
                .createProject(projectData);
        ProjectsPage projectsPage = new ProjectsPage(driver).get();
        WaitUtils.waitAsserted(() -> assertThat(projectsPage.getProjectNames())
                .as("Created project not displayed")
                .contains(projectData.getName()));
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testDeleteProject() {
        ProjectsPage projectsPage = new ProjectsPage(driver).get()
                .openDeleteProjectConfirmationPage(projectData.getName())
                .deleteProject();
        assertThat(projectsPage.getProjectNames())
                .as("Created project not displayed")
                .doesNotContain(projectData.getName());
    }

}
