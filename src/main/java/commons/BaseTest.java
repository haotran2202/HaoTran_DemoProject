package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

    protected final Log log;

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName, String appUrl) {
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            setDriver(new ChromeDriver());
        } else if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            setDriver(new FirefoxDriver());
        } else if (browser == Browser.EDGE) {
            WebDriverManager.edgedriver().setup();
            setDriver(new EdgeDriver());
        } else if (browser == Browser.IE) {
            WebDriverManager.iedriver().arch32().setup();
            setDriver(new InternetExplorerDriver());
        } else {
            throw new RuntimeException("Please input your browser name!");
        }
        System.out.println("Driver at Abstract test=" + getDriver().toString());
        getDriver().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        getDriver().get(appUrl);
        return getDriver();
    }
    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

        private void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    protected int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999999);
    }

}