package pageObjects.SauceLabs;


import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.SauceLabs.ProductPageUI;

public class ProductPageObject extends BasePage {
    WebDriver driver;

    public ProductPageObject(WebDriver driver) {
        this.driver = driver;

    }

    public void selectItemInProductSortDropdown(String itemValue) {
        waitElementClickable(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN);
        selectItemInDropdown(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN, itemValue);
    }

    public boolean areProductNameSortedAscending() {
        waitAllElementVisible(driver, ProductPageUI.ALL_PRODUCT_NAME);
        sleepInSecond(5);
        return isDataStringSortedAscending(driver, ProductPageUI.ALL_PRODUCT_NAME);
    }

    public boolean areProductNameSortedDescending() {
        waitAllElementVisible(driver, ProductPageUI.ALL_PRODUCT_NAME);
        sleepInSecond(5);
        return isDataStringSortedDescending(driver, ProductPageUI.ALL_PRODUCT_NAME);
    }

    public boolean areProductPriceSortedAscending() {
        waitAllElementVisible(driver, ProductPageUI.ALL_PRODUCT_PRICE);
        sleepInSecond(5);
        return isDataFloatSortedAscending(driver, ProductPageUI.ALL_PRODUCT_PRICE);
    }

    public boolean areProductPriceSortedDescending() {
        waitAllElementVisible(driver, ProductPageUI.ALL_PRODUCT_PRICE);
        sleepInSecond(5);
        return isDataFloatSortedDescending(driver, ProductPageUI.ALL_PRODUCT_PRICE);
    }
}
