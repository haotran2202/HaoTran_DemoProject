package DemoProject.Register;

import commons.BaseTest;
import environmentConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.DemoProject.CreateAnAccountPageObject;
import pageObjects.DemoProject.HomePageObject;
import pageObjects.DemoProject.LoginPageObject;
import utilities.DataUtil;

public class TS_01_CreateNewAccount extends BaseTest {
    WebDriver driver;
    DataUtil fakeData = DataUtil.getData();
    String firstName, middleName, lastName, email, password;
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
        log.info("Pre-Condition - STEP 01: Open browser " + browserName + " and navigate to " + environment.appUrl());
        driver = getBrowserDriver(browserName, environment.appUrl());
        homePage = pageObjects.DemoProject.PageGeneratorManager.getHomePage(driver);
        log.info("Pre-Condition - STEP 2: Click to 'My Account' link");
        loginPage = homePage.clickToMyAccountLink();
    }

    @Test
    public void TC_01_Create_New_Account() {
        log.info("TC_01 - STEP 1: Click to 'Create An Account' button");
        createAnAccountPage = loginPage.clickToCreateAnAccountButton();

        log.info("TC_01 - STEP 2: Input to First Name textbox with value: " + firstName);
        createAnAccountPage.inputToDynamicTextbox("firstname", firstName);

        log.info("TC_01 - STEP 3: Input to Middle Name textbox with value: " + middleName);
        createAnAccountPage.inputToDynamicTextbox("middlename", middleName);

        log.info("TC_01 - STEP 4: Input to Last Name textbox with value: " + middleName);
        createAnAccountPage.inputToDynamicTextbox("lastname", lastName);

        log.info("TC_01 - STEP 5: Input to Email textbox with value: " + email);
        createAnAccountPage.inputToDynamicTextbox("email_address", email);

        log.info("TC_01 - STEP 6: Input to Password textbox with value: " + password);
        createAnAccountPage.inputToDynamicTextbox("password", password);

        log.info("TC_01 - STEP 7: Input to Confirm Password textbox with value: " + password);
        createAnAccountPage.inputToDynamicTextbox("confirmation", password);

        log.info("TC_01 - STEP 8: Click to 'Register' button");
        createAnAccountPage.clickToRegisterButton();

        log.info("TC_01 - STEP 9: Verify Success message is displayed");
        verifyTrue(createAnAccountPage.isSuccessMessageDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
