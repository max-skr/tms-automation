package io.qase.test.smoke;

import io.qase.data.AccountProvider;
import io.qase.data.dto.UserData;
import io.qase.po.pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SmokeTest extends AbstractTest {

    private UserData user;

    @BeforeTest
    public void initUser() {
        user = AccountProvider.getInstance().getAdmin();
    }

    @Test
    public void testLoginLogout() {
        new LoginPage(driver).get()
                .login(user)
                .logout();
    }

}
