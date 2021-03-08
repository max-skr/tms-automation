package io.qase.po.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMenuComponent extends AbstractComponent {

    @FindBy(css = ".user-menu-item > [href $= '/logout']")
    private WebElement userMenuSignOutButton;

    public UserMenuComponent(WebElement element) {
        super(element);
    }

    public void signOut() {
        userMenuSignOutButton.click();
    }
}
