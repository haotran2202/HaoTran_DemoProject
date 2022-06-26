package DemoProject.Register;

import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.DemoProject.AccountDashboardPageObject;
import pageObjects.DemoProject.HomePageObject;
import pageObjects.DemoProject.LoginPageObject;


public class TS_02_Login extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    AccountDashboardPageObject accountDashboardPage;

    @Parameters({"browser" , "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Login() {
        loginPage.inputToDynamicTextbox("email", GlobalConstants.USERNAME);
        loginPage.inputToDynamicTextbox("pass",GlobalConstants.PASSWORD);
        accountDashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(accountDashboardPage.isDashboardTitleDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }




}