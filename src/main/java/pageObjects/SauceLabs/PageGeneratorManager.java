package pageObjects.SauceLabs;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    public static ProductPageObject getProductPage(WebDriver driver) {
        return new ProductPageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

}
