package pageObjects.DemoProject;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.MobilePageUI;
import pageUIs.DemoProject.ShoppingCartPageUI;

public class ShoppingCartPageObject extends BasePage {
    WebDriver driver;
    public ShoppingCartPageObject(WebDriver driver){
        this.driver=driver;
    }

    @Step("Click to 'Continue Shopping' link")
    public MobilePageObject clickToContinueShoppingLink() {
        waitAllElementVisible(driver, ShoppingCartPageUI.CONTINUE_SHOPPING_LINK);
        clickToElement(driver, ShoppingCartPageUI.CONTINUE_SHOPPING_LINK);
        return PageGeneratorManager.getMobilePage(driver);
    }

    @Step("Verify Iphone product is displayed")
    public boolean isIPhoneProductDisplayed() {
        return isElementDisplayed(driver, ShoppingCartPageUI.IPHONE_PRODUCT);
    }

    @Step("Verify Samsung product is displayed")
    public boolean isSamsungProductDisplayed() {
        return isElementDisplayed(driver, ShoppingCartPageUI.SAMSUNG_PRODUCT);
    }

    @Step("Verify Sony product is displayed")
    public boolean isSonyProductDisplayed() {
        return isElementDisplayed(driver, ShoppingCartPageUI.SONY_PRODUCT);
    }

    @Step("Click to 'Empty Cart' link")
    public void clickToEmptyCartLink() {
        waitAllElementVisible(driver, ShoppingCartPageUI.EMPTY_CART_LINK);
        clickToElement(driver, ShoppingCartPageUI.EMPTY_CART_LINK);
    }

    @Step("Get text of No item message")
    public String getTextNoItemMessage(){
        waitAllElementVisible(driver, ShoppingCartPageUI.NO_ITEM_MESSAGE);
        return getElementText(driver, ShoppingCartPageUI.NO_ITEM_MESSAGE);
    }
}
