package pageObjects.DemoProject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.AccountDashboardPageUI;
import pageUIs.DemoProject.CreateAnAccountPageUI;

public class AccountDashboardPageObject extends BasePage {
    WebDriver driver;
    public AccountDashboardPageObject(WebDriver driver){
        this.driver=driver;
    }

    public boolean isDashboardTitleDisplayed() {
        waitAllElementVisible(driver, AccountDashboardPageUI.DASHBOARD_TITLE);
        return isElementDisplayed(driver,AccountDashboardPageUI.DASHBOARD_TITLE);
    }

    public MobilePageObject clickToMobileTab() {
        waitAllElementVisible(driver, AccountDashboardPageUI.MOBILE_TAB);
        clickToElement(driver, AccountDashboardPageUI.MOBILE_TAB);
        return PageGeneratorManager.getMobilePage(driver);
    }
}
