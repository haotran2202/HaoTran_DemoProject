package pageObjects.DemoProject;

import commons.BasePage;
import commons.BasePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to 'Create An Account' button")
    public CreateAnAccountPageObject clickToCreateAnAccountButton() {
        waitElementVisible(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        return PageGeneratorManager.getCreateNewAccountPage(driver);
    }

    @Step("Click to 'Login' button")
    public AccountDashboardPageObject clickToLoginButton() {
        waitElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAccountDashboardPage(driver);
    }

}
