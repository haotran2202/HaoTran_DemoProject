package pageObjects.DemoProject;

import commons.BasePage;
import commons.BasePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.CreateAnAccountPageUI;

public class CreateAnAccountPageObject extends BasePage {
    WebDriver driver;
    public CreateAnAccountPageObject(WebDriver driver){
        this.driver=driver;
    }

    @Step("Input to {0} textbox with value: {1}")
    public void inputToDynamicTextbox(String id, String value){
        waitElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID,value,id);
    }

    @Step("Click to 'Register' button")
    public void clickToRegisterButton() {
        waitElementClickable(driver, CreateAnAccountPageUI.REGISTER_BUTTON);
        clickToElement(driver, CreateAnAccountPageUI.REGISTER_BUTTON);
    }

    @Step("Verify Success message is displayed")
    public boolean isSuccessMessageDisplayed() {
        waitAllElementVisible(driver, CreateAnAccountPageUI.SUCCESS_MESSAGE);
        return isElementDisplayed(driver,CreateAnAccountPageUI.SUCCESS_MESSAGE);
    }

}
