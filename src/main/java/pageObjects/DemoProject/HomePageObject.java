package pageObjects.DemoProject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver=driver;
    }

    public LoginPageObject clickToMyAccountLink() {
        waitElementVisible(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.FOOTER_MY_ACCOUNT_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }
}
