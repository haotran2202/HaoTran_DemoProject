package pageObjects.DemoProject;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }

    public static CreateAnAccountPageObject getCreateNewAccountPage(WebDriver driver){
        return new CreateAnAccountPageObject(driver);
    }
}
