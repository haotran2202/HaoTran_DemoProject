package pageObjects.DemoProject;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.AccountDashboardPageUI;

public class AccountDashboardPageObject extends BasePage {
    WebDriver driver;

    public AccountDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Verify the Dashboard Title is displayed")
    public boolean isDashboardTitleDisplayed() {
        waitAllElementVisible(driver, AccountDashboardPageUI.DASHBOARD_TITLE);
        return isElementDisplayed(driver, AccountDashboardPageUI.DASHBOARD_TITLE);
    }

}
