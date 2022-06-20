package pageObjects.DemoProject;

import commons.BasePage;
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
}
