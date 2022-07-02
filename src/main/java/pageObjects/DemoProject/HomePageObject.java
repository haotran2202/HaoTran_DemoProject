package pageObjects.DemoProject;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver=driver;
    }

    @Step("Click to 'My Account' link")
    public LoginPageObject clickToMyAccountLink() {
        waitElementVisible(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }
}
