package pageObjects.DemoProject;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.AccountDashboardPageUI;
import pageUIs.DemoProject.MobilePageUI;

public class MobilePageObject extends BasePage {
    WebDriver driver;

    public MobilePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click to 'Add To Cart' button of Iphone")
    public ShoppingCartPageObject clickToAddToCartButtonOfIPhone() {
        waitAllElementVisible(driver, MobilePageUI.IPHONE_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.IPHONE_ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getShoppingCartPage(driver);
    }

    @Step("Click to 'Add To Cart' button of Samsung Galaxy")
    public ShoppingCartPageObject clickToAddToCartButtonOfSamsungGalaxy() {
        waitAllElementVisible(driver, MobilePageUI.SAMSUNG_GALAXY_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.SAMSUNG_GALAXY_ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getShoppingCartPage(driver);
    }

    @Step("Click to 'Add To Cart' button of Sony Xperia")
    public ShoppingCartPageObject clickToAddToCartButtonOfSonyXperia() {
        waitAllElementVisible(driver, MobilePageUI.SONY_XPERIA_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.SONY_XPERIA_ADD_TO_CART_BUTTON);
        return PageGeneratorManager.getShoppingCartPage(driver);
    }
}
