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

    public static AccountDashboardPageObject getAccountDashboardPage(WebDriver driver){
        return new AccountDashboardPageObject(driver);
    }

    public static MobilePageObject getMobilePage(WebDriver driver){
        return new MobilePageObject(driver);
    }

    public static ShoppingCartPageObject getShoppingCartPage(WebDriver driver){
        return new ShoppingCartPageObject(driver);
    }

    public static TVPageObject getTVPage(WebDriver driver){
        return new TVPageObject(driver);
    }
}
