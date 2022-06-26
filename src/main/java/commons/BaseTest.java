package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

//        switch (browser){
//            case CHROME:
//                WebDriverManager.chromedriver().create();
//
//                break;
//            case CHROME_HEADLESS:
//                WebDriverManager.chromedriver().create();
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("headless");
//                options.addArguments("window-size=1920x1080");
//                break;
//            default:
//                throw new RuntimeException("Browser name is not valid");
//        }

        if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            setDriver(new ChromeDriver());
        } else if (browser == Browser.CHROME_HEADLESS) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            setDriver(new ChromeDriver(options));
        }
        else if (browser == Browser.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            setDriver(new FirefoxDriver());
        }
        else if (browser == Browser.EDGE) {
            WebDriverManager.edgedriver().setup();
            setDriver(new EdgeDriver());
        }
        else if (browser == Browser.IE) {
            WebDriverManager.iedriver().arch32().setup();
            setDriver(new InternetExplorerDriver());
        }
        else {
            throw new RuntimeException("Please input your browser name!");
        }
        System.out.println("Driver at Abstract test=" + getDriver().toString());
        getDriver().manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        getDriver().get(appUrl);
        return getDriver();
    }

    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = getDriver().toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }

            if (getDriver() != null) {
                getDriver().manage().deleteAllCookies();
                getDriver().quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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