package pageObjects.DemoProject;

import commons.BasePage;
import commons.BasePageUI;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.CreateAnAccountPageUI;

public class CreateAnAccountPageObject extends BasePage {
    WebDriver driver;
    public CreateAnAccountPageObject(WebDriver driver){
        this.driver=driver;
    }

    public void inputToDynamicTextbox(String id, String value){
        waitElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX, id);
        sendKeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX,value,id);
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
