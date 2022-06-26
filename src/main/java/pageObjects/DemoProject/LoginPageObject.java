package pageObjects.DemoProject;

import commons.BasePage;
import commons.BasePageUI;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject (WebDriver driver){
        this.driver = driver;
    }

    public CreateAnAccountPageObject clickToCreateAnAccountButton() {
        waitElementVisible(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
        return PageGeneratorManager.getCreateNewAccountPage(driver);
    }

    public void inputToDynamicTextbox(String id, String value){
        waitElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, id);
        sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX,value,id);
    }

    public AccountDashboardPageObject clickToLoginButton() {
        waitElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAccountDashboardPage(driver);
    }

}
