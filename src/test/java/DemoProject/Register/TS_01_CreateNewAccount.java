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
        log.info("Pre-Condition - STEP 1: Open browser " + browserName + " and navigate to " + appUrl);
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        log.info("Pre-Condition - STEP 2: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Create_New_Account() {
        log.info("TC_01 - STEP 1: Click to 'Create An Account' button");
        createAnAccountPage = loginPage.clickToCreateAnAccountButton();

        log.info("TC_01 - STEP 2: Input to First Name textbox with value: " + firstName);
        createAnAccountPage.inputToDynamicTextbox("firstname",firstName);

        log.info("TC_01 - STEP 3: Input to Middle Name textbox with value: " + middleName);
        createAnAccountPage.inputToDynamicTextbox("middlename", middleName);

        log.info("TC_01 - STEP 4: Input to Last Name textbox with value: " + middleName);
        createAnAccountPage.inputToDynamicTextbox("lastname",lastName);

        log.info("TC_01 - STEP 5: Input to Email textbox with value: " + email);
        createAnAccountPage.inputToDynamicTextbox("email_address",email);

        log.info("TC_01 - STEP 6: Input to Password textbox with value: " + password);
        createAnAccountPage.inputToDynamicTextbox("password",password);

        log.info("TC_01 - STEP 7: Input to Confirm Password textbox with value: " + confirmPassword);
        createAnAccountPage.inputToDynamicTextbox("confirmation",confirmPassword);

        log.info("TC_01 - STEP 8: Click to 'Register' button");
        createAnAccountPage.clickToRegisterButton();

        log.info("TC_01 - STEP 9: Verify Success message is displayed");
        verifyTrue(createAnAccountPage.isSuccessMessageDisplayed());
    }

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
