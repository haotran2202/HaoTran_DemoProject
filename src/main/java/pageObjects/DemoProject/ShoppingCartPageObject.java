package pageObjects.DemoProject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.MobilePageUI;
import pageUIs.DemoProject.ShoppingCartPageUI;

public class ShoppingCartPageObject extends BasePage {
    WebDriver driver;
    public ShoppingCartPageObject(WebDriver driver){
        this.driver=driver;
    }


    public void clickToAddToCartButtonOfIPhone() {
        waitAllElementVisible(driver, MobilePageUI.IPHONE_ADD_TO_CART_BUTTON);
        clickToElement(driver, MobilePageUI.IPHONE_ADD_TO_CART_BUTTON);
    }


    public MobilePageObject clickToContinueShoppingLink() {
        waitAllElementVisible(driver, ShoppingCartPageUI.CONTINUE_SHOPPING_LINK);
        clickToElement(driver, ShoppingCartPageUI.CONTINUE_SHOPPING_LINK);
        return PageGeneratorManager.getMobilePage(driver);
    }

    public boolean isIPhoneProductDisplayed() {
        return isElementDisplayed(driver, ShoppingCartPageUI.IPHONE_PRODUCT);
    }

    public boolean isSamsungProductDisplayed() {
        return isElementDisplayed(driver, ShoppingCartPageUI.SAMSUNG_PRODUCT);
    }

    public boolean isSonyProductDisplayed() {
        return isElementDisplayed(driver, ShoppingCartPageUI.SONY_PRODUCT);
    }

    public void clickToEmptyCartLink() {
        waitAllElementVisible(driver, ShoppingCartPageUI.EMPTY_CART_LINK);
        clickToElement(driver, ShoppingCartPageUI.EMPTY_CART_LINK);
    }

    public String getTextNoItemMessage(){
        waitAllElementVisible(driver, ShoppingCartPageUI.NO_ITEM_MESSAGE);
        return getElementText(driver, ShoppingCartPageUI.NO_ITEM_MESSAGE);
    }
}
