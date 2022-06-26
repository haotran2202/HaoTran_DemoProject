package DemoProject.Register;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.DemoProject.*;


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
        email = "test2211@gmail.com";
        password = "123456";
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        loginPage = homePage.clickToMyAccountLink();
        loginPage.inputToDynamicTextbox("email",email);
        loginPage.inputToDynamicTextbox("pass",password);
        accountDashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(accountDashboardPage.isDashboardTitleDisplayed());
    }

    @Test
    public void TC_01_Add_Product_To_Cart() {
        mobilePage = (MobilePageObject) accountDashboardPage.openDynamicTab(driver, "Mobile");
        shoppingCartPage = mobilePage.clickToAddToCartButtonOfIPhone();
        Assert.assertTrue(shoppingCartPage.isIPhoneProductDisplayed());
        mobilePage = shoppingCartPage.clickToContinueShoppingLink();
        shoppingCartPage = mobilePage.clickToAddToCartButtonOfSamsungGalaxy();
        Assert.assertTrue(shoppingCartPage.isSamsungProductDisplayed());
        mobilePage = shoppingCartPage.clickToContinueShoppingLink();
        shoppingCartPage = mobilePage.clickToAddToCartButtonOfSonyXperia();
        Assert.assertTrue(shoppingCartPage.isSonyProductDisplayed());
    }



    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }




}