package io.qase.po.pages;

import io.qase.po.components.UserMenuComponent;
import io.qase.utils.elements.ElementAction;
import io.qase.utils.wait.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AuthorizedUserPage<T extends AuthorizedUserPage<T>> extends AbstractPage<T> {

    @FindBy(id = "user-menu")
    private WebElement userMenuButton;

    @FindBy(css = ".user-menu-block")
    private WebElement userMenuElement;

    public AuthorizedUserPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage logout() {
        openUserMenu().signOut();
        return WaitUtils.waitLoaded(new LoginPage(driver));
    }

    private UserMenuComponent openUserMenu() {
        if (!isUserMenuOpened()) {
            userMenuButton.click();
        }
        return new UserMenuComponent(userMenuElement);
    }

    private boolean isUserMenuOpened() {
        return ElementAction.isDisplayed(userMenuElement);
    }
}
