package pageObjects.DemoProject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.CreateAnAccountPageUI;

public class CreateAnAccountPageObject extends BasePage {
    WebDriver driver;
    public CreateAnAccountPageObject(WebDriver driver){
        this.driver=driver;
    }

    public void inputToFirstNameTextBox(String firstname) {
        waitAllElementVisible(driver, CreateAnAccountPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, CreateAnAccountPageUI.FIRST_NAME_TEXTBOX, firstname);
    }

    public void inputToMiddleNameTextBox(String middlename) {
        waitAllElementVisible(driver, CreateAnAccountPageUI.MIDDLE_NAME_TEXTBOX);
        sendKeyToElement(driver, CreateAnAccountPageUI.MIDDLE_NAME_TEXTBOX, middlename);
    }

    public void inputToLastNameTextBox(String lastname) {
        waitAllElementVisible(driver, CreateAnAccountPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, CreateAnAccountPageUI.LAST_NAME_TEXTBOX, lastname);
    }

    public void inputToEmailTextBox(String email) {
        waitAllElementVisible(driver, CreateAnAccountPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, CreateAnAccountPageUI.EMAIL_TEXTBOX, email);
    }

    public void inputToPasswordTextBox(String password) {
        waitAllElementVisible(driver, CreateAnAccountPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, CreateAnAccountPageUI.PASSWORD_TEXTBOX, password);
    }

    public void inputToConfirmPasswordTextBox(String confirmpassword) {
        waitAllElementVisible(driver, CreateAnAccountPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, CreateAnAccountPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmpassword);
    }

    public void clickToRegisterButton() {
        waitElementClickable(driver, CreateAnAccountPageUI.REGISTER_BUTTON);
        clickToElement(driver, CreateAnAccountPageUI.REGISTER_BUTTON);
    }

    public boolean isSuccessMessageDisplayed() {
        waitAllElementVisible(driver, CreateAnAccountPageUI.SUCCESS_MESSAGE);
        return isElementDisplayed(driver,CreateAnAccountPageUI.SUCCESS_MESSAGE);
    }
}
