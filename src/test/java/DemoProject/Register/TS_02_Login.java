package DemoProject.Register;

import commons.BaseTest;
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
    String email, password;
    HomePageObject homePage;
    LoginPageObject loginPage;
    AccountDashboardPageObject accountDashboardPage;

    @Parameters({"browser" , "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        email = "test2211@gmail.com";
        password = "123456";
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Login() {
        loginPage.inputToEmailTextBox(email);
        loginPage.inputToPasswordTextBox(password);
        accountDashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(accountDashboardPage.isDashboardTitleDisplayed());
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }




}