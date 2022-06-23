package pageObjects.DemoProject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.AccountDashboardPageUI;
import pageUIs.DemoProject.MobilePageUI;

public class MobilePageObject extends BasePage {
    WebDriver driver;
    public MobilePageObject(WebDriver driver){
        this.driver=driver;
    }


    public ShoppingCartPageObject clickToAddToCartButtonOfIPhone() {
        waitAllElementVisible(driver, MobilePageUI.IPHONE_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.IPHONE_ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getShoppingCartPage(driver);
    }

    public ShoppingCartPageObject clickToAddToCartButtonOfSamsungGalaxy() {
        waitAllElementVisible(driver, MobilePageUI.SAMSUNG_GALAXY_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.SAMSUNG_GALAXY_ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getShoppingCartPage(driver);
    }

    public ShoppingCartPageObject clickToAddToCartButtonOfSonyXperia() {
        waitAllElementVisible(driver, MobilePageUI.SONY_XPERIA_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.SONY_XPERIA_ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getShoppingCartPage(driver);
    }
}
