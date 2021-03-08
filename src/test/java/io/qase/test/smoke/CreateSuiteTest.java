package io.qase.test.smoke;

import io.qase.data.AccountProvider;
import io.qase.data.DataProvider;
import io.qase.data.dto.ProjectCreateData;
import io.qase.data.dto.SuiteCreateData;
import io.qase.data.dto.UserData;
import io.qase.po.pages.LoginPage;
import io.qase.po.pages.project.ProjectCreatePage;
import io.qase.po.pages.project.ProjectDeleteConfirmationPage;
import io.qase.po.pages.repo.RepositoryPage;
import io.qase.utils.wait.WaitUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateSuiteTest extends AbstractTest {

    private final SuiteCreateData suiteData = DataProvider.getSuiteCreateData();
    private final ProjectCreateData projectData = DataProvider.getProjectCreateData();

    @BeforeClass
    public void createProject() {
        UserData user = AccountProvider.getInstance().getAdmin();
        driver.manage().deleteAllCookies();
        new LoginPage(driver).get().login(user);
        new ProjectCreatePage(driver).get().createProject(projectData);
    }

    @AfterClass
    public void deleteProject() {
        new ProjectDeleteConfirmationPage(driver).get(projectData.getCode()).deleteProject();
    }

    @Test
    public void testCreateSuite() {
        RepositoryPage repositoryPage = new RepositoryPage(driver).get(projectData.getCode())
                .createSuite(suiteData);

        WaitUtils.waitAsserted(() -> assertThat(repositoryPage.getSuiteNames())
                .as("Incorrect suite names displayed")
                .isEqualTo(Collections.singletonList(suiteData.getName())));
    }

    @Test(dependsOnMethods = "testCreateSuite")
    public void testDeleteSuite() {
        RepositoryPage repositoryPage = new RepositoryPage(driver).get(projectData.getCode())
                .deleteSuite(suiteData.getName());

        WaitUtils.waitAsserted(() -> assertThat(repositoryPage.getSuiteNames())
                .as("Incorrect suite names displayed")
                .isEmpty());
    }

}
