package DemoProject.Login;

import DemoProject.common.Common_01_CreateNewAccount;
import commons.BaseTest;
import commons.GlobalConstants;
import environmentConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.DemoProject.AccountDashboardPageObject;
import pageObjects.DemoProject.HomePageObject;
import pageObjects.DemoProject.LoginPageObject;

public class TS_02_Login extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;
    AccountDashboardPageObject accountDashboardPage;
    Environment environment;

    @Parameters({"browser"})
    @BeforeTest
    public void beforeTest(String browserName) {
        String prefixPropertiesName = System.getProperty("envMaven");
        System.out.println("Server name run by command line: " + prefixPropertiesName);
        ConfigFactory.setProperty("envOwner", prefixPropertiesName);
        environment = ConfigFactory.create(Environment.class);

        log.info("Pre-Condition - STEP 01: Open browser " + browserName + " and navigate to " + environment.appUrl());
        driver = getBrowserDriver(browserName, environment.appUrl());
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        log.info("Pre-Condition - STEP 02: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Login() {
        log.info("TC_01 - STEP 1: Input to Email textbox with value: " + Common_01_CreateNewAccount.email);
        loginPage.inputToTextboxByIdAttribute(driver, "email", Common_01_CreateNewAccount.email);

        log.info("TC_01 - STEP 2: Input to Password textbox with value: " + Common_01_CreateNewAccount.password);
        loginPage.inputToTextboxByIdAttribute(driver, "pass", Common_01_CreateNewAccount.password);

        log.info("TC_01 - STEP 3: Click to 'Login' button");
        loginPage.clickToButtonByTitle(driver, "Login");
        accountDashboardPage = new AccountDashboardPageObject(driver);

        log.info("TC_01 - STEP 4: Verify Dashboard title is displayed");
        verifyTrue(accountDashboardPage.isDashboardTitleDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
