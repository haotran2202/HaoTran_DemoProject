package DemoProject.Register;

import commons.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.DemoProject.CreateAnAccountPageObject;
import pageObjects.DemoProject.HomePageObject;
import pageObjects.DemoProject.LoginPageObject;


public class TS_01_CreateNewAccount extends BaseTest {
    WebDriver driver;
    String firstName, middleName, lastName, email, password, confirmPassword;
    HomePageObject homePage;
    LoginPageObject loginPage;
    CreateAnAccountPageObject createAnAccountPage;

    @Parameters({"browser" , "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        WebDriverManager.chromedriver().setup();
        firstName = "First";
        middleName = "Middle";
        lastName = "Last";
        email = "testing" + randomNumber() + "@gmail.com";
        password = "123456";
        confirmPassword = "123456";
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Create_New_Account() {
        createAnAccountPage = loginPage.clickToCreateAnAccountButton();
        createAnAccountPage.inputToDynamicTextbox("firstname",firstName);
        createAnAccountPage.inputToDynamicTextbox("firstname", middleName);
        createAnAccountPage.inputToDynamicTextbox("lastname",lastName);
        createAnAccountPage.inputToDynamicTextbox("email_address",email);
        createAnAccountPage.inputToDynamicTextbox("password",password);
        createAnAccountPage.inputToDynamicTextbox("confirmation",confirmPassword);
        createAnAccountPage.clickToRegisterButton();
        Assert.assertTrue(createAnAccountPage.isSuccessMessageDisplayed());
    }



    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }




}