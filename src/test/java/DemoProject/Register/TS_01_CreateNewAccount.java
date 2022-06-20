package DemoProject.Register;

import commons.BaseTest;
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
        firstName = "Firt";
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
        createAnAccountPage.inputToFirstNameTextBox(firstName);
        createAnAccountPage.inputToMiddleNameTextBox(middleName);
        createAnAccountPage.inputToLastNameTextBox(lastName);
        createAnAccountPage.inputToEmailTextBox(email);
        createAnAccountPage.inputToPasswordTextBox(password);
        createAnAccountPage.inputToConfirmPasswordTextBox(confirmPassword);
        createAnAccountPage.clickToRegisterButton();
        Assert.assertTrue(createAnAccountPage.isSuccessMessageDisplayed());
    }



    @AfterClass
    public void afterClass() {
        driver.quit();
    }




}