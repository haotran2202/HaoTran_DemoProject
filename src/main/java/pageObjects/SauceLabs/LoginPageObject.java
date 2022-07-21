package pageObjects.SauceLabs;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.SauceLabs.LoginPageUI;


public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendkeyToUserNameTextbox(String userName) {
        sendKeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, userName);
    }

    public void sendkeyToPasswordTextbox(String password) {
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
