package DemoProject.common;

import commons.BaseTest;
import environmentConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.DemoProject.CreateAnAccountPageObject;
import pageObjects.DemoProject.HomePageObject;
import pageObjects.DemoProject.LoginPageObject;
import utilities.DataUtil;

public class Common_01_CreateNewAccount extends BaseTest {
    WebDriver driver;
    DataUtil fakeData = DataUtil.getData();
    String firstName, middleName, lastName;
    public static String email, password;
    HomePageObject homePage;
    LoginPageObject loginPage;
    CreateAnAccountPageObject createAnAccountPage;
    Environment environment;

    @Parameters({"browser"})
    @BeforeTest
    public void beforeTest(String browserName) {
        String prefixPropertiesName = System.getProperty("envMaven");
        System.out.println("Server name run by command line: " + prefixPropertiesName);
        ConfigFactory.setProperty("envOwner", prefixPropertiesName);
        environment = ConfigFactory.create(Environment.class);

        firstName = fakeData.getFirstName();
        middleName = fakeData.getMiddleName();
        lastName = fakeData.getLastName();
        email = fakeData.getEmailAddress();
        password = fakeData.getPassword();

        log.info("Register - STEP 01: Open browser " + browserName + " and navigate to " + environment.appUrl());
        driver = getBrowserDriver(browserName, environment.appUrl());
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);

        log.info("Register - STEP 2: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();

        log.info("Register - STEP 3: Click to 'Create An Account' button");
        loginPage.clickToLinkByTitle(driver, "Create an Account");
        createAnAccountPage = new CreateAnAccountPageObject(driver);
        showBrowserConsoleLogs(driver);

        log.info("Register - STEP 4: Input to First Name textbox with value: " + firstName);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "firstname", firstName);

        log.info("Register - STEP 5: Input to Middle Name textbox with value: " + middleName);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "middlename", middleName);

        log.info("Register - STEP 6: Input to Last Name textbox with value: " + middleName);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "lastname", lastName);

        log.info("Register - STEP 7: Input to Email textbox with value: " + email);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "email_address", email);

        log.info("Register - STEP 8: Input to Password textbox with value: " + password);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "password", password);

        log.info("Register - STEP 9: Input to Confirm Password textbox with value: " + password);
        createAnAccountPage.inputToTextboxByIdAttribute(driver, "confirmation", password);

        log.info("Register - STEP 10: Click to 'Register' button");
        createAnAccountPage.clickToButtonByTitle(driver, "Register");

        log.info("Register - STEP 11: Verify Success message is displayed");
        verifyTrue(createAnAccountPage.isSuccessMessageDisplayed());

        closeBrowserAndDriver();
    }

}
