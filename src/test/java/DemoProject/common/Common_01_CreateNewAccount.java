package DemoProject.common;

import commons.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.DemoProject.CreateAnAccountPageObject;
import pageObjects.DemoProject.HomePageObject;
import pageObjects.DemoProject.LoginPageObject;

public class Common_01_CreateNewAccount extends BaseTest {
    WebDriver driver;
    String firstName, middleName, lastName, confirmPassword;
    public static String email, password;
    HomePageObject homePage;
    LoginPageObject loginPage;
    CreateAnAccountPageObject createAnAccountPage;

    @Parameters({"browser" , "appUrl"})
    @BeforeTest
    public void beforeTest(String browserName, String appUrl) {
        WebDriverManager.chromedriver().setup();
        firstName = "First";
        middleName = "Middle";
        lastName = "Last";
        email = "testing" + randomNumber() + "@gmail.com";
        password = "123456";
        confirmPassword = "123456";

        log.info("Register - STEP 01: Open browser " + browserName + " and navigate to " + appUrl);
        driver = getBrowserDriver(browserName, appUrl);
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);

        log.info("Register - STEP 2: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();

        log.info("Register - STEP 3: Click to 'Create An Account' button");
         loginPage.clickToLinkByTitle(driver,"Create an Account");
        createAnAccountPage = new CreateAnAccountPageObject(driver);

        log.info("Register - STEP 4: Input to First Name textbox with value: " + firstName);
        createAnAccountPage.inputToTextboxByIdAttribute(driver,"firstname",firstName);

        log.info("Register - STEP 5: Input to Middle Name textbox with value: " + middleName);
        createAnAccountPage.inputToTextboxByIdAttribute(driver,"middlename", middleName);

        log.info("Register - STEP 6: Input to Last Name textbox with value: " + middleName);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "lastname",lastName);

        log.info("Register - STEP 7: Input to Email textbox with value: " + email);
        createAnAccountPage.inputToTextboxByIdAttribute(driver,"email_address",email);

        log.info("Register - STEP 8: Input to Password textbox with value: " + password);
        createAnAccountPage.inputToTextboxByIdAttribute(driver,"password",password);

        log.info("Register - STEP 9: Input to Confirm Password textbox with value: " + confirmPassword);
        createAnAccountPage.inputToTextboxByIdAttribute(driver,"confirmation",confirmPassword);

        log.info("Register - STEP 10: Click to 'Register' button");
        createAnAccountPage.clickToButtonByTitle(driver, "Register");

        log.info("Register - STEP 11: Verify Success message is displayed");
        verifyTrue(createAnAccountPage.isSuccessMessageDisplayed());

        closeBrowserAndDriver();
    }

}
