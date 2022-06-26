package pageObjects.DemoProject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DemoProject.MobilePageUI;

public class TVPageObject extends BasePage {
    WebDriver driver;
    public TVPageObject(WebDriver driver){
        this.driver=driver;
    }


}
