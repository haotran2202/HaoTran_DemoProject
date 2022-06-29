package SauceDemoProject.Sorting;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.SauceLabs.LoginPageObject;
import pageObjects.SauceLabs.ProductPageObject;

public class TS_01_Sorting extends BaseTest {
	WebDriver driver;
	ProductPageObject productPage;
	LoginPageObject loginPage;

	@Parameters({"browser" , "appUrl", "userName", "password"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl, String userName, String password) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = pageObjects.SauceLabs.PageGeneratorManager.getLoginPage(driver);
		loginPage.sendkeyToUserNameTextbox(userName);
		loginPage.sendkeyToPasswordTextbox(password);
		loginPage.clickToLoginButton();
	}

	@Test
	public void TC_01_Sort_Product_Name() {
		productPage = pageObjects.SauceLabs.PageGeneratorManager.getProductPage(driver);
		log.info("Verify - Step 1: Select 'Name (A to Z)' option in sort drop-down");
		productPage.selectItemInProductSortDropdown("Name (A to Z)");

		log.info("Verify - Step 2: Verify product names are sorted ASC");
		verifyTrue(productPage.areProductNameSortedAscending());
		
		log.info("Verify - Step 3: Select 'Name (Z to A)' option in sort drop-down");
		productPage.selectItemInProductSortDropdown("Name (Z to A)");

		log.info("Verify - Step 4: Verify product names are sorted DES");
		verifyTrue(productPage.areProductNameSortedDescending());
	}

	@Test
	public void TC_02_Sort_Product_Price() {
		log.info("Verify - Step 1: Select 'Price (low to high)' option in sort drop-down");
		productPage.selectItemInProductSortDropdown("Price (low to high)");

		log.info("Verify - Step 2: Verify product prices are sorted ASC");
		verifyTrue(productPage.areProductPriceSortedAscending());
		
		log.info("Verify - Step 3: Select 'Price (high to low)' option in sort drop-down");
		productPage.selectItemInProductSortDropdown("Price (high to low)");

		log.info("Verify - Step 4: Verify product prices are sorted DES");
		verifyTrue(productPage.areProductPriceSortedDescending());
	}

	@AfterClass (alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}