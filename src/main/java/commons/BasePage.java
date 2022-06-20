package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {

    protected final Log log;

    protected BasePage() {
        log = LogFactory.getLog(getClass());
    }

    public void setImplicitWait(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void waitAlertPresent(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public String getAlertText(WebDriver driver) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void sendKeyToAlert(WebDriver driver, String text) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }


    public List<WebElement> finds(WebDriver driver, String xpathValue){
        return driver.findElements(byXpath(xpathValue));
    }

    public void sendKeyToElement(WebDriver driver, String xpathValue, String value) {
        element = find(driver, xpathValue);
        element.clear();
        element.sendKeys(value);
    }

    public void selectItemInDropdown(WebDriver driver, String xpathValue, String itemValue) {
        select = new Select(find(driver, xpathValue));
        select.selectByVisibleText(itemValue);
    }

    public void deSelectItemInDropdown(WebDriver driver, String xpathValue, String itemValue) {
        select = new Select(find(driver, xpathValue));
        select.deselectByValue(itemValue);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String xpathValue) {
        select = new Select(find(driver, xpathValue));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isMultipleDropdown(WebDriver driver, String xpathValue) {
        select = new Select(find(driver, xpathValue));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        find(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

        elements  = finds(driver, childItemLocator);
        for (WebElement item : elements) {
            if (item.getText().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementAttribute(WebDriver driver, String xpathValue, String attributeName){
        return find(driver, xpathValue).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String xpathValue){
        return find(driver, xpathValue).getText();
    }

    public int countElementNumber(WebDriver driver, String xpathValue) {
        return finds(driver, xpathValue).size();
    }

    public void checkToCheckBox(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        if(!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToCheckBox(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        if(element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementEnabled(WebDriver driver, String xpathValue) {
        return find(driver, xpathValue).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String xpathValue) {
        return find(driver, xpathValue).isSelected();
    }

    public void switchToFrameOrIframe(WebDriver driver, String xpathValue) {
        driver.switchTo().frame(find(driver, xpathValue));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String xpathValue) {
        action = new Actions(driver);
        action.moveToElement(find(driver, xpathValue)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String xpathValue,  Keys key) {
        action = new Actions(driver);
        action.sendKeys(find(driver, xpathValue), key);
    }

    public void clickToElementByJS(WebDriver driver, String xpathValue) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", find(driver, xpathValue));
    }

    public void scrollToElement(WebDriver driver, String xpathValue) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
    }

    public void sendKeyToElementByJS(WebDriver driver, String xpathValue, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, xpathValue));
    }

    public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, xpathValue));
    }

    public void highlightElement(WebDriver driver, String xpathValue) {
        element = find(driver, xpathValue);
        jsExecutor = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void waitElementVisible(WebDriver driver, String xpathValue) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
    }

    public void waitAllElementVisible(WebDriver driver, String xpathValue) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
    }

    public void waitAllElementVisible(WebDriver driver, String xpathValue, String...values) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(getDynamicLocator(xpathValue, values))));
    }

    public void waitElementInvisible(WebDriver driver, String xpathValue) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
    }

    public By byXpath(String xpathValue) {
        return By.xpath(xpathValue);
    }

    public void waitElementClickable(WebDriver driver, WebElement element) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    public void waitElementClickable(WebDriver driver, String xpathValue) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    public WebElement find(WebDriver driver, String xpathValue) {
        return driver.findElement(byXpath(xpathValue));
    }

    public void clickToElement(WebDriver driver, String xpathValue) {
        try {
            find(driver, xpathValue).click();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    public void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    public void waitElementVisible(WebDriver driver, WebElement invalidPasswordMessage) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOf(invalidPasswordMessage));
    }

    public String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }

    public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public boolean isElementDisplayed(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    public String getDynamicLocator(String xpathValue, String... values) {
        xpathValue = String.format(xpathValue, (Object[]) values);
        return xpathValue;
    }

    public void clickToElement(WebDriver driver, String xpathValue, String... values) {
        find(driver, getDynamicLocator(xpathValue, values)).click();
    }

    public void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(getDynamicLocator(xpathValue, values))));
    }

    public void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait
                .until(ExpectedConditions.visibilityOfElementLocated(byXpath(getDynamicLocator(xpathValue, values))));
    }

    public boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
        return find(driver, getDynamicLocator(xpathValue, values)).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return find(driver, locator).isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
        catch (StaleElementReferenceException ex) {
            return false;
        }
    }

    public void overrideImplicitTimeOut(WebDriver driver, long timeInSecond){
        driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideImplicitTimeOut(driver, shortTimeout);
        elements = finds(driver, locator);
        overrideImplicitTimeOut(driver, longTimeout);
        if (elements.size()==0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isControlUndisplayed(WebDriver driver, String locator, String...values) {
        overrideImplicitTimeOut(driver, shortTimeout);
        elements = finds(driver, getDynamicLocator(locator, values));
        overrideImplicitTimeOut(driver, longTimeout);
        if (elements.size()==0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void sendKeyToElement(WebDriver driver, String xpathValue, String value, String... values) {
        element = find(driver, getDynamicLocator(xpathValue, values));
        element.clear();
        element.sendKeys(value);
    }

    public List<WebElement> finds(WebDriver driver, String xpathValue, String... values) {
        return driver.findElements(byXpath(getDynamicLocator(xpathValue, values)));
    }

    public int countElementNumber(WebDriver driver, String xpathValue, String... values) {
        return finds(driver, getDynamicLocator(xpathValue, values)).size();
    }

    public String getDirectorySlash(String folderName) {
        if (isMac() || isUnix() || isSolaris()) {
            folderName = "/" + folderName + "/";
        } else {
            folderName = "\\" + folderName + "\\";
        }
        return folderName;
    }

    public boolean isWindows() {
        return (osName.toLowerCase().indexOf("win") >= 0);
    }

    public boolean isMac() {
        return (osName.toLowerCase().indexOf("mac") >= 0);
    }

    public boolean isUnix() {
        return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0
                || osName.toLowerCase().indexOf("aix") > 0);
    }

    public boolean isSolaris() {
        return (osName.toLowerCase().indexOf("sunos") >= 0);
    }

/*    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        sendkeyToElement(driver, AbstracPageUI.UPLOAD_FILE_TYPE, fullFileName);
    }*/

    public boolean isDataStringSortedAscending(WebDriver driver, String locator) {
        ArrayList<String> arrayList = new ArrayList<>();

        List<WebElement> elementList = finds(driver, locator);

        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }
        System.out.println("---The data on UI---");
        for (String name : arrayList) {
            System.out.println(name);
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }
        Collections.sort(sortedList);
        System.out.println("Data sorted ASC");
        for (String name : sortedList) {
            System.out.println(name);
        }
        return sortedList.equals(arrayList);
    }

    public boolean isDataStringSortedDescending(WebDriver driver, String locator) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elementList = finds(driver, locator);
        for (WebElement element : elementList) {
            arrayList.add(element.getText());
        }
        System.out.println("---The data on UI---");
        for (String name : arrayList) {
            System.out.println(name);
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String child : arrayList) {
            sortedList.add(child);
        }
        //Sort ASC
        Collections.sort(sortedList);
        System.out.println("Data sorted ASC");
        for (String name : sortedList) {
            System.out.println(name);
        }
        Collections.reverse(sortedList);
        System.out.println("Data sorted DES");
        for (String name : sortedList) {
            System.out.println(name);
        }
        return sortedList.equals(arrayList);
    }

    public boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
        ArrayList<Float> arrayList = new ArrayList<Float>();
        List<WebElement> elementList = finds(driver, locator);
        for (WebElement element : elementList) {
            arrayList.add(Float.parseFloat(element.getText().replace("$","").trim()));
        }
        System.out.println("---The data on UI---");
        for (Float name : arrayList) {
            System.out.println(name);
        }
        ArrayList<Float> sortedList = new ArrayList<>();
        for (Float child : arrayList) {
            sortedList.add(child);
        }
        //Sort ASC
        Collections.sort(sortedList);
        System.out.println("Data sorted ASC");
        for (Float name : sortedList) {
            System.out.println(name);
        }
        return sortedList.equals(arrayList);
    }

    public boolean isDataFloatSortedDescending(WebDriver driver, String locator) {
        ArrayList<Float> arrayList = new ArrayList<Float>();
        List<WebElement> elementList = finds(driver, locator);
        for (WebElement element : elementList) {
            arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
        }
        System.out.println("---The data on UI---");
        for (Float name : arrayList) {
            System.out.println(name);
        }
        ArrayList<Float> sortedList = new ArrayList<>();
        for (Float child : arrayList) {
            sortedList.add(child);
        }
        //Sort ASC
        Collections.sort(sortedList);
        System.out.println("Data sorted ASC");
        for (Float name : sortedList) {
            System.out.println(name);
        }
        Collections.reverse(sortedList);
        System.out.println("Data sorted DES");
        for (Float name : sortedList) {
            System.out.println(name);
        }
        return sortedList.equals(arrayList);
    }

    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    String osName = System.getProperty("os.name");
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private Actions action;
    private Alert alert;
    private WebElement element;
    private List<WebElement> elements;
    private Select select;
    private WebDriverWait explicitWait;
    private JavascriptExecutor jsExecutor;
}



