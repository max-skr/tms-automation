package io.qase.test.smoke;

import io.qase.data.AccountProvider;
import io.qase.data.StringGenerator;
import io.qase.data.dto.ProjectCreateData;
import io.qase.data.dto.UserData;
import io.qase.po.pages.LoginPage;
import io.qase.po.pages.ProjectCreatePage;
import io.qase.po.pages.ProjectsPage;
import io.qase.utils.wait.WaitUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectsCreateTest extends AbstractTest {

    private final String projectName = "TEST-" + UUID.randomUUID().toString();
    private final String projectCode = StringGenerator.getRandomString(6).toUpperCase();

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
        ProjectCreateData data = getCreationData();

        new ProjectCreatePage(driver).get()
                .createProject(data);
        ProjectsPage projectsPage = new ProjectsPage(driver).get();
        WaitUtils.waitAsserted(() -> assertThat(projectsPage.getProjectNames())
                .as("Created project not displayed")
                .contains(projectName));
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testDeleteProject() {
        ProjectsPage projectsPage = new ProjectsPage(driver).get()
                .openDeleteProjectConfirmationPage(projectName)
                .deleteProject();
        assertThat(projectsPage.getProjectNames())
                .as("Created project not displayed")
                .doesNotContain(projectName);
    }

    private ProjectCreateData getCreationData() {
        ProjectCreateData data = new ProjectCreateData();
        data.setName(projectName);
        data.setCode(projectCode);
        data.setPrivate(true);
        data.setMembersAccessOption(ProjectCreateData.AccessOption.NO_MEMBERS);
        data.setDescription("Test desc");
        return data;
    }
}
