package io.qase.po.pages;

import io.qase.data.dto.UserData;
import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage<LoginPage> {

    @FindBy(id = "inputEmail")
    private WebElement loginField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPagePath() {
        return "/login";
    }

    @Override
    public boolean isLoaded() {
        return super.isLoaded() && ElementAction.isDisplayed(loginButton);
    }

    public ProjectsPage login(UserData user) {
        loginField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
        return WaitUtils.waitLoaded(new ProjectsPage(this.driver));
    }
}
