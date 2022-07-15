package DemoProject.AddProducts;

import DemoProject.common.Common_01_CreateNewAccount;
import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.DemoProject.*;
import pageUIs.DemoProject.ShoppingCartPageUI;
import reportConfig.AllureTestListener;

import java.util.Set;

@Listeners({AllureTestListener.class})
@Epic("Regression")
@Feature("Adding")
public class TS_03_AddProductToCart extends BaseTest {
    WebDriver driver;
    String email, password;
    HomePageObject homePage;
    LoginPageObject loginPage;
    AccountDashboardPageObject accountDashboardPage;
    MobilePageObject mobilePage;
    ShoppingCartPageObject shoppingCartPage;

    @Parameters({"browser" , "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        log.info("Pre-Condition - STEP 01: Open browser " + browserName + " and navigate to " + appUrl);
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);

        log.info("Pre-Condition - STEP 2: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();

        log.info("Pre-Condition - STEP 3: Input to Email textbox with value: "+ Common_01_CreateNewAccount.email);
        loginPage.inputToTextboxByIdAttribute(driver,"email", Common_01_CreateNewAccount.email);

        log.info("Pre-Condition - STEP 4: Input to Password textbox with value: "+ Common_01_CreateNewAccount.password);
        loginPage.inputToTextboxByIdAttribute(driver, "pass",Common_01_CreateNewAccount.password);

        log.info("Pre-Condition - STEP 5: Click to 'Login' button");
        loginPage.clickToButtonByTitle(driver, "Login");
        accountDashboardPage = new AccountDashboardPageObject(driver);

                log.info("Pre-Condition - STEP 6: Verify Dashboard title is displayed");
        verifyTrue(accountDashboardPage.isDashboardTitleDisplayed());
    }

    @Description("TC 01: Add product to Cart")
    @Story("Add product to Cart")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Add_Product_To_Cart() {
        log.info("TC_01 - STEP 1: Click to 'Mobile' tab");
        mobilePage = (MobilePageObject) accountDashboardPage.openDynamicTab(driver, "Mobile");

        log.info("TC_01 - STEP 2: Click to 'Add To Card' button of Iphone product");
        shoppingCartPage = mobilePage.clickToAddToCartButtonOfIPhone();

        log.info("TC_01 - STEP 3: Verify Iphone product is displayed");
        verifyTrue(shoppingCartPage.isIPhoneProductDisplayed());

        log.info("TC_01 - STEP 4: Click to 'Continue Shopping' link");
        mobilePage = shoppingCartPage.clickToContinueShoppingLink();

        log.info("TC_01 - STEP 5: Click to 'Add To Card' button of Samsung product");
        shoppingCartPage = mobilePage.clickToAddToCartButtonOfSamsungGalaxy();

        log.info("TC_01 - STEP 6: Verify Samsung product is displayed");
       verifyTrue(shoppingCartPage.isSamsungProductDisplayed());

        log.info("TC_01 - STEP 7: Click to 'Continue Shopping' link");
        mobilePage = shoppingCartPage.clickToContinueShoppingLink();

        log.info("TC_01 - STEP 8: Click to 'Add To Card' button of Sony Xperia product");
        shoppingCartPage = mobilePage.clickToAddToCartButtonOfSonyXperia();

        log.info("TC_01 - STEP 9: Verify Sony Xperia product is displayed");
        verifyTrue(shoppingCartPage.isSonyProductDisplayed());

        log.info("TC_01 - STEP 10: Click to 'Empty Cart' link");
        shoppingCartPage.clickToEmptyCartLink();

        log.info("TC_01 - STEP 10: Verify 'no items...' message is displayed");
        verifyEquals(shoppingCartPage.getTextNoItemMessage(), "You have no items in your shopping cart.");
    }

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
