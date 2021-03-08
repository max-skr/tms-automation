package io.qase.test.smoke;

import io.qase.data.AccountProvider;
import io.qase.data.dto.UserData;
import io.qase.po.pages.LoginPage;
import io.qase.po.pages.project.ProjectsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginLogoutTest extends AbstractTest {

    private UserData user;

    @BeforeClass
    public void initUser() {
        user = AccountProvider.getInstance().getAdmin();
    }

    @Test
    public void testLogin() {
        new LoginPage(driver).get().login(user);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testLogout() {
        new ProjectsPage(driver).get().logout();
    }
}
