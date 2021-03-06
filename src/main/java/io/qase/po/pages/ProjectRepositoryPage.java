package io.qase.po.pages;

import org.openqa.selenium.WebDriver;

public class ProjectRepositoryPage extends AuthorizedUserPage<ProjectRepositoryPage> {

    public ProjectRepositoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getPagePath() {
        return "/project/" + ID_PLACEHOLDER;
    }

    public ProjectRepositoryPage get(String projectCode) {
        return super.get(projectCode);
    }

}
