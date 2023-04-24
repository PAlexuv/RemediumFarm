package PageObjects.BasePO;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Set;

public class BasePoRF {

    protected static WebDriver driver;
    protected static Logger log = LogManager.getLogger();

    public static String browser;
    public static String baseUrl;
    public static Properties properties;

    public final String SCREENSHOT_PATH = "C:\\Automation\\IntelliJProjects\\RemediumFarm\\ScreenshotsRF";
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy_HH-mm-ss");
//    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");


    //*************** BASE START ****************//loadProp, OpenBrowser, gotoHomepage with assertion that we're not hacked

    public void loadProperties() {
        FileInputStream fis = null;
        try {
            properties = new Properties();
            fis = new FileInputStream("C:\\Automation\\IntelliJProjects\\RemediumFarm\\src\\main\\java\\Config\\config.properties");
            properties.load(fis);

            browser = properties.getProperty("browserRF");
            baseUrl = properties.getProperty("baseUrl");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openBrowser() {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
        } else {
            log.error("Set browser to Chrome...");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    public void closeBrowser(){
        driver.quit();
    }

    public Boolean gotoHomepage() {
        try {
            loadProperties();
            openBrowser();
            driver.get(baseUrl);
            log.info("Opened url: " + baseUrl);
            Assert.assertEquals(baseUrl, "https://www.remediumfarm.ro/");
        } catch (Exception e) {
            log.error("Unable to navigate to " + baseUrl);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void takeScreenshotNamed(String testname){

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        LocalDateTime currentTime = LocalDateTime.now();
        String formattedCurrentDate = currentTime.format(dateTimeFormatter);
//        String screenshotNamedFormatted = SCREENSHOT_PATH + "\\" + testname + "_" + formattedCurrentDate + ".jpeg";
        String screenshotNamedFormatted = SCREENSHOT_PATH + "\\TestName_" + testname + "_" + formattedCurrentDate + ".jpeg";

        File savedScreenshot = new File(screenshotNamedFormatted);

        try{
            FileUtils.copyFile(file, savedScreenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Take screenshot of failed test: " + screenshotNamedFormatted);
    }

    public void takeScreenshotGeneral(){
        LocalDateTime currentTime = LocalDateTime.now();
        takeScreenshotNamed(currentTime.format(dateTimeFormatter));
    }
    public void takeScreenshotElement(By locator){
        WebElement element = driver.findElement(locator);
        String locatorString = element.getAccessibleName();
        takeScreenshotNamed(locatorString);
    }

    //++++++++++++ Actions on page ++++++++++++++++++++++++++++++++++++

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void clickTab(By locator) {
        driver.findElement(locator).sendKeys(Keys.TAB);
    }
    public void clickEnter(By locator) {
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public void hoverOverElement(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }

    public void scrollElementIntoView(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(locator);
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollPage(int x, int y) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy (" + x + "," + y + ") ");
    }

    public void dragAndDropByOffset(By locator, int x, int y) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.dragAndDropBy(element, x, y).perform();
    }

    public void dismissPopup() {
        driver.switchTo().alert().dismiss();
    }

    public void acceptPopup() {
        driver.switchTo().alert().accept();
    }

    public String getPopupText() {
        return driver.switchTo().alert().getText();
    }

    public void setPopupText(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    public void switchFramesByIndex(int frameNr) {
        driver.switchTo().frame(frameNr);
    }
    public void switchToFramesByLocator(WebElement locator){
        driver.switchTo().frame(locator);
    }

    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    public void selectDropdownByValue(By locator, String value){
        Select selectElement = new Select(driver.findElement(locator));
        selectElement.selectByValue(value);
    }
    public void selectDropdownByVisibleText(By locator, String visibleText){
        Select selectElement = new Select(driver.findElement(locator));
        selectElement.selectByVisibleText(visibleText);
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //**************** Waits *******************

    public void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementText(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    public void waitForElementTextFluentWait(By locator, String text) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.textToBe(locator, text));
    }
    // *****************************************

    //------------------- Cookies -------------------

    public void acceptCookies(By locator) {
        click(locator);
    }

    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }
    //-----------------------------------------------

    //............... Handle Text - GetText SetText GetTitle page ................
    public String getTextByValue(By locator) {
        return driver.findElement(locator).getAttribute("value");
    }

    public String getTextByText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void setText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        clickTab(locator);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
    //.....................................................

    //****************** Window Handles |goBack|goToUrl|newTab|nrOfWindowsOpened|closeBrowser| ****************
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public int getNrOfOpenWindows() {
        return driver.getWindowHandles().size();
    }

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window open");
    }
    public void goToUrl(String url){
        driver.get(url);
    }
    public void goBack(){
        driver.navigate().back();
    }
    public void closePage(){
        driver.close();
    }
    //****************************************************************
}










    // click tab hoverOverElement scrollElementIntoView scrollPage dragAndDropByOffset dismissPopup acceptPopup getPopupText setPopupText switchFramesByIndex
    // switchToDefaultFrame selectDropdownByValue selectDropdownByVisibleText waitForElement waitForElementText waitForElementTextFluentWait
    // acceptCookies getCookie setCookie getTextByValue getTextByText setText getPageTitle getWindowHandle getWindowHandles
    //getNrOfOpenWindows openNewTab goToUrl goBack closePage closeBrowser takeScreenshotNamed takeScreenshotGeneral takeElementScreenshot

    //********************* TAKE SCREENSHOTS ************************
//    public void takeScreenshotGeneral(){
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy_HH-mm-ss");
//        LocalDateTime dateTime = LocalDateTime.now();
//        takeScreenshotGeneral();
//    }
////        TakesScreenshot screenshot = (TakesScreenshot)driver;
////        File file = screenshot.getScreenshotAs(OutputType.FILE); *** logic behind the next line of code:

