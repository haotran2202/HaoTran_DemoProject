package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Step;
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
import pageObjects.DemoProject.PageGeneratorManager;

public abstract class BasePage {

    protected final Log log;

    protected BasePage() {
        log = LogFactory.getLog(getClass());
    }

    protected void setImplicitWait(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    protected void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void waitAlertPresent(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        alert.accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    protected String getAlertText(WebDriver driver) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        return alert.getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String text) {
        waitAlertPresent(driver);
        alert = driver.switchTo().alert();
        alert.sendKeys(text);
    }

    protected void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }


    private By getByLocator(String locatorType) {
        By by = null;
//        System.out.println("Locator type =" + locatorType);
        if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("//")) {
            by = By.xpath(locatorType);
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;
    }

    private WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    private List<WebElement> getListElement(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected List<WebElement> finds(WebDriver driver, String xpathValue) {
        return driver.findElements(byXpath(xpathValue));
    }


    protected void sendKeyToElement(WebDriver driver, String locatorType, String value) {
        element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(value);
    }

    protected void selectItemInDropdown(WebDriver driver, String locatorType, String itemValue) {
        select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(itemValue);
    }

    protected void deSelectItemInDropdown(WebDriver driver, String locatorType, String itemValue) {
        select = new Select(getWebElement(driver, locatorType));
        select.deselectByValue(itemValue);
    }

    protected String getSelectedItemInDropdown(WebDriver driver, String locatorType) {
        select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isMultipleDropdown(WebDriver driver, String locatorType) {
        select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

        elements = getListElement(driver, childItemLocator);
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

    protected void sleepInSecond(long timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    protected int countElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    protected void checkToCheckBox(WebDriver driver, String locator) {
        element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckToCheckBox(WebDriver driver, String locator) {
        element = getWebElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    protected void switchToFrameOrIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }

    protected void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locator), key);
    }

    protected void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    protected void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    protected void highlightElement(WebDriver driver, String locator) {
        element = getWebElement(driver, locator);
        jsExecutor = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void waitElementVisible(WebDriver driver, String xpathValue) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(xpathValue)));
        } catch (Exception e) {
            log.debug("Wait for element visible with error: " + e.getMessage());
        }
    }

    protected void waitAllElementVisible(WebDriver driver, String xpathValue) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
    }

    protected void waitAllElementVisible(WebDriver driver, String xpathValue, String... values) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(getDynamicLocator(xpathValue, values))));
    }

    protected void waitElementInvisible(WebDriver driver, String xpathValue) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        overrideImplicitTimeOut(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
        overrideImplicitTimeOut(driver, longTimeout);
    }

    protected By byXpath(String xpathValue) {
        return By.xpath(xpathValue);
    }

    protected void waitElementClickable(WebDriver driver, WebElement element) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.debug("Wait for element clickable with error: " + e.getMessage());
        }
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
        } catch (Exception e) {
            log.debug("Wait for element clickable with error: " + e.getMessage());
        }
    }

    protected WebElement find(WebDriver driver, String xpathValue) {
        return driver.findElement(byXpath(xpathValue));
    }

    protected void clickToElement(WebDriver driver, String locatorType) {
        try {
            getWebElement(driver, locatorType).click();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    protected void clickToElement(WebDriver driver, WebElement element) {
        element.click();
    }

    protected void waitElementVisible(WebDriver driver, WebElement element) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            log.debug("Wait for element visible with error: " + e.getMessage());
        }
    }

    protected String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }

    protected void sendkeyToElement(WebDriver driver, WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            log.debug("Element is not displayed with error: " + e.getMessage());
            return false;
        }

    }

    protected String getDynamicLocator(String xpathValue, String... values) {
        xpathValue = String.format(xpathValue, (Object[]) values);
        return xpathValue;
    }

    protected void clickToElement(WebDriver driver, String xpathValue, String... values) {
        find(driver, getDynamicLocator(xpathValue, values)).click();
    }

    protected void waitElementClickable(WebDriver driver, String xpathValue, String... values) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(getDynamicLocator(xpathValue, values))));
        } catch (Exception e) {
            log.debug("Wait for element clickable with error: " + e.getMessage());
        }

    }

    protected void waitElementVisible(WebDriver driver, String xpathValue, String... values) {
        try {
            explicitWait = new WebDriverWait(driver, longTimeout);
            explicitWait
                    .until(ExpectedConditions.visibilityOfElementLocated(byXpath(getDynamicLocator(xpathValue, values))));
        } catch (Exception e) {
            log.debug("Wait for element visible with error: " + e.getMessage());
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String xpathValue, String... values) {
        try {
            return find(driver, getDynamicLocator(xpathValue, values)).isDisplayed();
        } catch (Exception e) {
            log.debug("Element is not displayed with error: " + e.getMessage());
            return false;
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        try {
            return getWebElement(driver, locator).isDisplayed();
        } catch (NoSuchElementException ex) {
            log.debug("Element is not displayed with error: " + ex.getMessage());
            return false;
        } catch (StaleElementReferenceException ex) {
            log.debug("Element is not displayed with error: " + ex.getMessage());
            return false;
        }
    }

    protected void overrideImplicitTimeOut(WebDriver driver, long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(timeInSecond, TimeUnit.SECONDS);
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideImplicitTimeOut(driver, shortTimeout);
        elements = getListElement(driver, locator);
        overrideImplicitTimeOut(driver, longTimeout);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean isControlUndisplayed(WebDriver driver, String locator, String... values) {
        overrideImplicitTimeOut(driver, shortTimeout);
        elements = finds(driver, getDynamicLocator(locator, values));
        overrideImplicitTimeOut(driver, longTimeout);
        if (elements.size() == 0) {
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

    protected List<WebElement> finds(WebDriver driver, String xpathValue, String... values) {
        return driver.findElements(byXpath(getDynamicLocator(xpathValue, values)));
    }

    protected int countElementNumber(WebDriver driver, String xpathValue, String... values) {
        return finds(driver, getDynamicLocator(xpathValue, values)).size();
    }

    protected String getDirectorySlash(String folderName) {
        if (isMac() || isUnix() || isSolaris()) {
            folderName = "/" + folderName + "/";
        } else {
            folderName = "\\" + folderName + "\\";
        }
        return folderName;
    }

    protected boolean isWindows() {
        return (osName.toLowerCase().indexOf("win") >= 0);
    }

    protected boolean isMac() {
        return (osName.toLowerCase().indexOf("mac") >= 0);
    }

    protected boolean isUnix() {
        return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0
                || osName.toLowerCase().indexOf("aix") > 0);
    }

    protected boolean isSolaris() {
        return (osName.toLowerCase().indexOf("sunos") >= 0);
    }

/*    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        String filePath = System.getProperty("user.dir") + getDirectorySlash("uploadFiles");
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = fullFileName + filePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        sendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
    }*/

    protected boolean isDataStringSortedAscending(WebDriver driver, String locator) {
        ArrayList<String> arrayList = new ArrayList<>();

        List<WebElement> elementList = getListElement(driver, locator);

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

    protected boolean isDataStringSortedDescending(WebDriver driver, String locator) {
        ArrayList<String> arrayList = new ArrayList<>();
        List<WebElement> elementList = getListElement(driver, locator);
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

    protected boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
        ArrayList<Float> arrayList = new ArrayList<Float>();
        List<WebElement> elementList = getListElement(driver, locator);
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
        return sortedList.equals(arrayList);
    }

    protected boolean isDataFloatSortedDescending(WebDriver driver, String locator) {
        ArrayList<Float> arrayList = new ArrayList<Float>();
        List<WebElement> elementList = getListElement(driver, locator);
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

    // functions to open pages
    @Step("Open {1} tab")
    public BasePage openDynamicTab(WebDriver driver, String tabName) {
        waitAllElementVisible(driver, BasePageUI.DYNAMIC_TAB, tabName);
        clickToElement(driver, BasePageUI.DYNAMIC_TAB, tabName);
        switch (tabName) {
            case "Mobile":
                return PageGeneratorManager.getMobilePage(driver);
            case "TV":
                return PageGeneratorManager.getTVPage(driver);
            default:
                return null;
        }
    }

    @Step("Open {1} page from Dashboard")
    public BasePage openDynamicAccountPage(WebDriver driver, String pageName) {
        waitAllElementVisible(driver, BasePageUI.DYNAMIC_ACCOUNT_PAGE_BY_TEXT, pageName);
        clickToElement(driver, BasePageUI.DYNAMIC_ACCOUNT_PAGE_BY_TEXT, pageName);
        switch (pageName) {
            case "Mobile":
                return PageGeneratorManager.getMobilePage(driver);
            case "TV":
                return PageGeneratorManager.getTVPage(driver);
            default:
                return null;
        }
    }

    @Step("Input to textbox has ID attribute: {1} with value: {2}")
    public void inputToTextboxByIdAttribute(WebDriver driver, String idAttribute, String value) {
        waitElementVisible(driver, getDynamicLocator(BasePageUI.DYNAMIC_TEXTBOX_BY_ID, idAttribute));
        sendKeyToElement(driver, getDynamicLocator(BasePageUI.DYNAMIC_TEXTBOX_BY_ID, idAttribute), value);
    }

    @Step("Input to textbox has Name attribute: {1} with value: {2}")
    public void inputToTextboxByNameAttribute(WebDriver driver, String nameAttribute, String value) {
        waitElementVisible(driver, getDynamicLocator(BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, nameAttribute));
        sendKeyToElement(driver, getDynamicLocator(BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, nameAttribute), value);
    }

    @Step("Click to button has title: {1}")
    public void clickToButtonByTitle(WebDriver driver, String buttonTitle) {
        waitElementVisible(driver, getDynamicLocator(BasePageUI.DYNAMIC_BUTTON_BY_TITLE, buttonTitle));
        clickToElement(driver, getDynamicLocator(BasePageUI.DYNAMIC_BUTTON_BY_TITLE, buttonTitle));
    }

    @Step("Click to button has title: {1}")
    public void clickToLinkByTitle(WebDriver driver, String linkTitle) {
        waitElementVisible(driver, getDynamicLocator(BasePageUI.DYNAMIC_LINK_BY_TITLE, linkTitle));
        clickToElement(driver, getDynamicLocator(BasePageUI.DYNAMIC_LINK_BY_TITLE, linkTitle));
    }

    @Step("Click to link has value: {1}")
    public void clickToLinkByValue(WebDriver driver, String linkValue) {
        waitElementVisible(driver, getDynamicLocator(BasePageUI.DYNAMIC_LINK_BY_TEXT, linkValue));
        clickToElement(driver, getDynamicLocator(BasePageUI.DYNAMIC_LINK_BY_TEXT, linkValue));
    }

    protected long shortTimeout = GlobalConstants.getGlobalConstants().getShortTimeout();
    String osName = System.getProperty("os.name");
    protected long longTimeout = GlobalConstants.getGlobalConstants().getLongTimeout();
    protected Actions action;
    protected Alert alert;
    protected WebElement element;
    protected List<WebElement> elements;
    protected Select select;
    protected WebDriverWait explicitWait;
    protected JavascriptExecutor jsExecutor;
}



