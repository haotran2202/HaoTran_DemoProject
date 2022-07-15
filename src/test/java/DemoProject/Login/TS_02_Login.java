package DemoProject.Login;

import DemoProject.common.Common_01_CreateNewAccount;
import commons.BaseTest;
import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
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
        log.info("Pre-Condition - Step 01: Open browser: " + browserName + " and navigate to " + appUrl);
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        log.info("Pre-Condition - STEP 02: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Login() {
        log.info("TC_01 - STEP 1: Input to Email textbox with value: "+ Common_01_CreateNewAccount.email);
        loginPage.inputToTextboxByIdAttribute(driver,"email", Common_01_CreateNewAccount.email);

        log.info("TC_01 - STEP 2: Input to Password textbox with value: "+ Common_01_CreateNewAccount.password);
        loginPage.inputToTextboxByIdAttribute(driver,"pass",Common_01_CreateNewAccount.password);

        log.info("TC_01 - STEP 3: Click to 'Login' button");
        loginPage.clickToButtonByTitle(driver, "Login");
        accountDashboardPage = new AccountDashboardPageObject(driver);

                log.info("TC_01 - STEP 4: Verify Dashboard title is displayed");
        verifyTrue(accountDashboardPage.isDashboardTitleDisplayed());
    }

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
