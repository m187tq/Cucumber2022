package pages;

import actiondriver.Action;
import drivers.DriverFactory;
import helper.assertion.AssertionHelper;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import utilities.datarepo;
import utils.globalVars;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BasePage extends DriverFactory {
    public static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(globalVars.explicitWait));
    public static AssertionHelper assertionHelper = new AssertionHelper();
    public static WaitHelper waitHelper = new WaitHelper(getDriver());

    public static Action act = new Action();

    public BasePage() throws IOException {
        PageFactory.initElements(getDriver(), this);
    }

    public static WebDriver getDriver() {
        return DriverFactory.getDriver();

    }
    public void navigateToIndexPage(String url) {
        // // log.info("navigate To IndexPage....: " +url);
        getDriver().get(url);
    }
    public void goToIndexPage(String Url) {
        // // log.info("navigating To Index Page....: " +Url);
        getDriver().get(Url);
    }
    public String getCurrentPageTitle(String arg1) throws InterruptedException {
        // // log.info("got current Page Title....: " +arg1);
        return getDriver().getTitle();
    }
    public String getCurrentPageUrl(String Url) throws InterruptedException {
        // // log.info("got current Page Url....: " + Url);
        return getDriver().getCurrentUrl();
    }
    public String getPageTitle() {
        // // log.info("page title is: " + getDriver().getTitle());
        return getDriver().getTitle();
    }
    public String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);

    }
    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);

    }
    public void sendKeys(By by, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }
    public void sendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }
    public void waitFor(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }
    public void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }
    public void waitForWebElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void waitForAlert_And_ValidateText(String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, text);
    }

    /**********************************************************************************
     **CLICK METHODS
     **********************************************************************************/
    public void waitAndClickElement(WebElement element) {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                clicked = true;
            } catch (Exception e) {
                // // log.info("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            attempts++;
        }
    }

    public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                // // log.info("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                // // log.info("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<"+ by.toString() + ">");
            }
            attempts++;
        }
    }

    public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
        Wait<WebDriver> tempWait = new WebDriverWait(getDriver(), Duration.ofSeconds(globalVars.getExplicitWait()));
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
            list.sendKeys(textToSearchFor);
            list.sendKeys(Keys.ENTER);
            // // log.info("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
        } catch (Exception e) {
            // // log.info("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
            Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }


    public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
        try {
            final WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(globalVars.getExplicitWait()));
            customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
            locator.click();
            // // log.info("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">"+ ", using a custom Timeout of: " + timeout);
        } catch (Exception e) {
            // // log.info("Unable to click on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
            Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
        }
    }
    /**********************************************************************************
     **ACTION METHODS
     **********************************************************************************/

    public void actionMoveAndClick(WebElement element) throws Exception {
        Actions ob = new Actions(getDriver());
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            ob.moveToElement(element).click().build().perform();
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
            if (elementPresent == true) {
                ob.moveToElement(elementToClick).click().build().perform();
            }
        } catch (Exception e) {
            // // log.info("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public void actionMoveAndClickByLocator(By element) throws Exception {
        Actions ob = new Actions(getDriver());
        try {
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            if (elementPresent == true) {
                WebElement elementToClick = getDriver().findElement(element);
                ob.moveToElement(elementToClick).click().build().perform();
                // // log.info("Action moved and clicked on the following element, using By locator: " + "<" + element.toString() + ">");
            }
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = getDriver().findElement(element);
            ob.moveToElement(elementToClick).click().build().perform();
            // // log.info("(Stale Exception) - Action moved and clicked on the following element, using By locator: "+ "<" + element.toString() + ">");
        } catch (Exception e) {
            // // log.info("Unable to Action Move and Click on the WebElement using by locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
        }
    }

    /**********************************************************************************
     **SEND KEYS METHODS /
     **********************************************************************************/
    public void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
        try {
            this.WaitUntilWebElementIsVisible(element);
            element.clear();
            element.sendKeys(textToSend);
        } catch (Exception e) {
            Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }
    /**********************************************************************************
     **JS METHODS & JS SCROLL
     **********************************************************************************/
    public void scrollToElementByWebElementLocator(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, -400)");
        } catch (Exception e) {
            Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }

    public void jsPageScroll(int numb1, int numb2) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
            // // log.info("Succesfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
        } catch (Exception e) {
            // // log.info("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
            Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
        }
    }

    public void waitAndClickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement staleElement = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
            if (elementPresent == true) {
                js.executeScript("arguments[0].click();", elementPresent);
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }
    /**********************************************************************************
     **WAIT METHODS
     **********************************************************************************/
    public boolean WaitUntilWebElementIsVisible(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (Exception e) {
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean isElementClickable(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean waitUntilPreLoadElementDisappears(By element) {
        return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**********************************************************************************
     **PAGE METHODS
     **********************************************************************************/
    public BasePage loadUrl(String url) throws Exception {
        getDriver().get(url);
        // log.info("loading Page Url....: " + url);
        return new BasePage();
    }

    public String getCurrentURL() {
        try {
            String url = getDriver().getCurrentUrl();
            return url;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String waitForSpecificPage(String urlToWaitFor) {
        try {
            String url = getDriver().getCurrentUrl();
            this.wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
            return urlToWaitFor;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**********************************************************************************
     **ALERT & POPUPS METHODS
     **********************************************************************************/

    public boolean checkPopupIsVisible() {
        try {
            @SuppressWarnings("unused")
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            System.err.println("Error came while waiting for the alert popup to appear. " + e.getMessage());
        }
        return false;
    }

    public boolean isAlertPresent() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void closeAlertPopupBox() throws AWTException, InterruptedException {
        try {
            Alert alert = this.wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (UnhandledAlertException f) {
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            // // log.info("Unable to close the popup");
            Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
        }
    }

    /***EXTENT REPORT****************************************************************/
    public static String returnDateStamp(String fileExtension) {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
        return date;
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        } finally {
            is.close();
            os.close();
        }
    }

    public static void copyLatestExtentReport() throws IOException {
        Date d = new Date();
        String date = d.toString().replace(":", "_").replace(" ", "_");
        File source = new File(System.getProperty("user.dir") + "\\output\\report.html");
        File dest = new File(System.getProperty("user.dir") + "\\output\\" + date.toString() + ".html");
        copyFileUsingStream(source, dest);
    }

//=====================================================================//
// AssertionHelper
//=====================================================================//

    public static void verifyText(String s1, String s2){
        // // log.info("veryfing test: "+ s1 + " with "+ s2);
        Assert.assertEquals(s1, s1);
    }

    public static void markPass(){
        // // log.info("making script PASS..");
        Assert.assertTrue(true);
    }

    public static void markPass(String message){
        // // log.info("making script PASS.."+ message);
        Assert.assertTrue(true, message);
    }

    public static void markFail(){
        // // log.info("making script FAIL..");
        Assert.assertTrue(false);
    }

    public static void markFail(String message){
        // // log.info("making script FAIL.."+message);
        Assert.assertTrue(false, message);
    }

    public static void verifyTrue(boolean status){
        Assert.assertTrue(status);
    }

    public static void verifyFalse(boolean status){
        Assert.assertFalse(status);
    }

    public static void verifyNull(String s1){
        // // log.info("verify object is null..");
        Assert.assertNull(s1);
    }

    public static void verifyNotNull(String s1){
        // // log.info("verify object is not null..");
        Assert.assertNotNull(s1);
    }

    public static void fail(){
        Assert.assertTrue(false);
    }

    public static void pass(){
        Assert.assertTrue(true);
    }

    public static void updateTestStatus(boolean status){
        if(status){
            pass();
        }
        else{
            fail();
        }
    }


//=====================================================================//
// VerificationHelper
//=====================================================================//

    public boolean isDisplayed(WebElement element){
        try{
            element.isDisplayed();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean isEnabled(WebElement element){
        try{
            element.isEnabled();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    public boolean isNotDisplayed(WebElement element){
        try{
            element.isDisplayed();
            return false;
        }
        catch(Exception e){
            return true;
        }
    }

    public String readValueFromElement(WebElement element){
        if(null == element){
            // // log.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if(status){
            // // log.info("element text is .."+element.getText());
            return element.getText();
        }
        else{
            return null;
        }
    }
    public String getText(WebElement element){
        if(null == element){
            return null;
        }
        boolean status = isDisplayed(element);
        if(status){
            return element.getText();
        }
        else{
            return null;
        }
    }

    public String getTitle(){
        return getDriver().getTitle();
    }

//=====================================================================//

    public void setImplicitWait(long timeout, TimeUnit unit) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(datarepo.SIXTY_SECONDS));
    }

    /**
     * This will help us to get WebDriverWait object
     *
     * @param timeOutInSeconds
     * @param pollingEveryInMiliSec
     * @return
     */
    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(datarepo.SIXTY_SECONDS));
        wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    public void WaitForElementVisibleWithPollingTime(
            WebElement element, int timeOutInSeconds,
            int pollingEveryInMiliSec) {
            WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
            wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This method will make sure elementToBeClickable
     *
     * @param element
     * @param timeOutInSeconds
     */
    public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(datarepo.SIXTY_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
        // // log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(datarepo.SIXTY_SECONDS));
        boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
        // // log.info("element is invisibile now");
        return status;
    }

    public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(datarepo.SIXTY_SECONDS));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    private Wait<WebDriver> getfluentWait(int timeOutInSeconds, Duration pollingEveryInMiliSec) {
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
        return fWait;
    }

    public WebElement waitForElement(WebElement element, int timeOutInSeconds){
        Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, Duration.ofSeconds(60));
        fwait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void pageLoadTime(long timeout, TimeUnit unit){
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(datarepo.SIXTY_SECONDS));
    }

    public void clickOnLinkByText(String Text) {
        getDriver().findElement(By.linkText(Text)).click();
    } public static boolean type(WebElement ele, String text) {
        boolean flag = false;
        try {
            flag = ele.isDisplayed();
            ele.clear();
            ele.sendKeys(text);

            flag = true;
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {

            } else {
            }

        }
        return flag;
    }

    public static boolean selectBySendKeys(String value, WebElement ele) {
        boolean flag = false;
        try {
            ele.sendKeys(value);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                // System.out.println("Select value from the DropDown" + value);
            } else {
                // System.out.println("Not Selected value from the DropDown");
                // throw new ElementNotFoundException("", "", "")
            }
        }
    }

    public static boolean selectByIndex(WebElement element, int index) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByIndex(index);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                // System.out.println("Option selected by Index: " + "<" + index + ">");
            } else {
                // System.out.println("Option not selected by Index: " + "<" + index + ">");
            }
        }
    }

    public static boolean selectByValue(WebElement element, String value) {
        boolean flag = false;
        try {
            Select s = new Select(element);
            s.selectByValue(value);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                // System.out.println("Option selected by Value:" + "<" + value + ">");
            } else {
                // System.out.println("Option not selected by Value");
            }
        }
    }

    public static boolean selectByVisibleText(String visibletext, WebElement ele) {

        boolean flag = false;
        try {
            Select s = new Select(ele);
            s.selectByVisibleText(visibletext);
            flag = true;
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (flag) {
                // System.out.println("Option selected by VisibleText: " + "<" + visibletext + ">");
            } else {
                // System.out.println("Option not selected by VisibleText");
            }
        }
    }

    public static void fluentWait(WebDriver driver, WebElement element, int timeOut) {
        Wait<WebDriver> wait = null;
        try {
            wait = new FluentWait<WebDriver>((WebDriver) driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (Exception e) {
        }
    }
    public static String getCurrentTime() {
        Logger log = LoggerHelper.getLogger(BasePage.class);
        // System.out.println("CurrentTime performed....");
        // log.info("CurrentTime performed....");
        String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
        return currentDate;
    }
    public static boolean isPresent(WebDriver webdriver, By selector) {
        try {
            webdriver.findElement(selector);
        } catch (NoSuchElementException e) {
            // if element not exist return false
            return false;
        }
        return true;
    }

    public static WebElement waitToBeClickable(WebDriver driver, By selector, int waitInterval) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(30))).until(ExpectedConditions.elementToBeClickable(selector));
        return element;
    }

    public static WebElement waitForElementPresence(WebDriver driver, By selector, int waitInterval) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(30))).until(ExpectedConditions.presenceOfElementLocated(selector));
        return element;
    }

    public static void waitForTitle(WebDriver driver, String title, int waitInterval) {
        (new WebDriverWait(driver, Duration.ofSeconds(30))).until(ExpectedConditions.titleIs(title));
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     * @param info
     */
    public static void sleep(long msec, String info) {
        if (info != null) {
            // System.out.println("Waiting " + (msec * .001) + " seconds :: " + info);
        }
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Sleep for specified number of milliseconds
     * @param msec
     */
    public static void sleep(long msec) {
        sleep(msec, null);
    }

    public static void click(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        // System.out.println("clicking on the element....: " + ele.getText());
        act.moveToElement(ele).click().build().perform();
    }

    public static boolean findElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            ele.isDisplayed();

            flag = true;
        } catch (Exception e) {

            flag = false;
        } finally {
            if (flag) {
                //// System.out.println("Successfully Found element as :" + ele.getText());
            } else {
                // System.out.println("Unable to locate element at");
            }
        }
        return flag;
    }

    public static boolean isDisplayed(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isDisplayed();
            if (flag) {
                // System.out.println("The element is Displayed as:  " + "<" + ele.getText() + ">");
            } else {
                // System.out.println("The element is not Displayed");
            }
        } else {
            // System.out.println("Not displayed: " + ele.getText());
        }
        return flag;
    }

    public static boolean isSelected(WebDriver driver, WebElement ele) {

        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isSelected();
            if (flag) {
                // System.out.println("The element is Selected:  " + "<" + ele + ">");
            } else {
                // System.out.println("The element is not Selected");
            }
        } else {
            // System.out.println("Not selected ");
        }
        return flag;
    }

    public static boolean isEnabled(WebDriver driver, WebElement ele) {
        boolean flag = false;
        flag = findElement(driver, ele);
        if (flag) {
            flag = ele.isEnabled();
            if (flag) {
                // System.out.println("The element is Enabled: " + "<" + ele.getText() + ">");
            } else {
                // System.out.println("The element is not Enabled: " + "<" + ele.getText() + ">");
            }
        } else {
            // System.out.println("Not Enabled ");
        }
        return flag;
    }



    public static boolean JSClick(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", ele);
            // driver.executeAsyncScript("arguments[0].click();", element);

            flag = true;

        } catch (Exception e) {
            throw e;

        } finally {
            if (flag) {
                // System.out.println("Click Action is performed");
                // log.info("Click Action is performed");
            } else if (!flag) {
                // System.out.println("Click Action is not performed");
            }
        }
        return flag;
    }

    public static boolean switchToFrameByIndex(WebDriver driver, int index) {
        boolean flag = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
            driver.switchTo().frame(index);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                // System.out.println("Frame with index \"" + index + "\" is selected");
                // log.info("Frame with index \"" + index + "\" is selected");
            } else {
                // System.out.println("Frame with index \"" + index + "\" is not selected");
                // log.info("Frame with index \"" + index + "\" is not selected");

            }
        }
    }
    public static boolean switchToFrameById(WebDriver driver, String idValue) {
        boolean flag = false;
        try {
            driver.switchTo().frame(idValue);
            flag = true;
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                // System.out.println("Frame with Id \"" + idValue + "\" is selected");
                // log.info("Frame with Id \"" + idValue + "\" is selected");

            } else {
                // System.out.println("Frame with Id \"" + idValue + "\" is not selected");
                // log.info("Frame with Id \"" + idValue + "\" is not selected");
            }
        }
    }

    /**
     * This method switch the to frame using frame Name.
     *
     * @param nameValue : Frame Name wish to switch
     */

    public static boolean switchToFrameByName(WebDriver driver, String nameValue) {

        boolean flag = false;
        try {
            driver.switchTo().frame(nameValue);
            flag = true;
            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (flag) {
                // System.out.println("Frame with Name \"" + nameValue + "\" is selected");
                // log.info("Frame with Name \"" + nameValue + "\" is selected");

            } else if (!flag) {
                // System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
                // log.info("Frame with Name \"" + nameValue + "\" is not selected");

            }
        }
    }

    public static boolean switchToDefaultFrame(WebDriver driver) {
        boolean flag = false;
        try {
            driver.switchTo().defaultContent();
            flag = true;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (flag) {
                // log.info("SelectFrame: Frame with Name is selected ");
                // System.out.println("SelectFrame: Frame with Name is selected");
            } else if (!flag) {
                // log.info("SelectFrame: The Frame is not selected ");
                // System.out.println("SelectFrame => The Frame is not selected");
            }
        }
    }

    public static void mouseOverElement(WebDriver driver, WebElement element) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                // System.out.println(" MouserOver Action is performed on: " + element.getText());
            } else {
                // System.out.println("MouseOver axe is not performed on");
            }
        }
    }

    public static boolean moveToElement(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            // WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", ele);
            Actions actions = new Actions(driver);
            // actions.moveToElement(driver.findElement(locator)).build().perform();
            actions.moveToElement(ele).build().perform();
            flag = true;
            // System.out.println("MouseOver axe is not performed on");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean mouseover(WebDriver driver, WebElement ele) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(ele).build().perform();
            flag = true;
            return true;

        } catch (Exception e) {
            return false;
        } finally {
            // System.out.println("MouseOver Action performed on");
            // log.info("MouseOver Action performed on");

            if (flag) {
                // System.out.println("MouseOver Action performed on");
            } else {

            }

        }
    }
    public void explicitWait(WebDriver driver, WebElement element, int timeOut ) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(),Duration.ofSeconds(globalVars.getExplicitWait()));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isElementPresent(WebElement webElement) {
        try {
            boolean isPresent = webElement.isDisplayed();
            return isPresent;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public static void waitForPageToLoad()
    {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void openPage(String url) {
        System.out.println(url);
        System.out.println(getDriver());
        getDriver().get(url);
    }

    public static boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())) {
            System.out.println("Actual Text From Web Application UI   --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> : " + expText);
            System.out.println("### Verification Contains !!!");
            return true;
        } else {
            System.out.println("Actual Text From Web Application UI   --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> : " + expText);
            System.out.println("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    /**
     * Checks whether actual string matches with expected string and prints both in log
     *
     * @param actualText - actual Text picked up from application under Test
     * @param expText    - expected Text to be matched with actual text
     * @return
     */
    public static boolean verifyTextMatch(String actualText, String expText) {
        if (actualText.equals(expText)) {
            System.out.println("Actual Text From Web Application UI   --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> : " + expText);
            System.out.println("### Verification MATCHED !!!");
            return true;
        } else {
            System.out.println("Actual Text From Web Application UI   --> : " + actualText);
            System.out.println("Expected Text From Web Application UI --> : " + expText);
            System.out.println("### Verification DOES NOT MATCH !!!");
            return false;
        }
    }

    /**
     * getOrVerify actual list contains items of the expected list
     *
     * @param actList
     * @param expList
     * @return
     */
    public static Boolean verifyListContains(java.util.List<String> actList, java.util.List<String> expList) {
        int expListSize = expList.size();
        for (int i = 0; i < expListSize; i++) {
            if (!actList.contains(expList.get(i))) {
                return false;
            }
        }
        System.out.println("Actual List Contains Expected List !!!");
        return true;
    }

    /***
     * getOrVerify actual list matches expected list
     * @param actList
     * @param expList
     * @return
     */
    public static Boolean verifyListMatch(java.util.List<String> actList, java.util.List<String> expList) {
        boolean found = false;
        int actListSize = actList.size();
        int expListSize = expList.size();
        if (actListSize != expListSize) {
            return false;
        }

        for (int i = 0; i < actListSize; i++) {
            found = false;
            for (int j = 0; j < expListSize; j++) {
                if (verifyTextMatch(actList.get(i), expList.get(j))) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            System.out.println("Actual List Matches Expected List !!!");
            return true;
        } else {
            System.out.println("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    /**
     * Verifies item is present in the list
     *
     * @param actList
     * @param item
     * @return boolean result
     */
    public static Boolean verifyItemPresentInList(java.util.List<String> actList, String item) {
        int actListSize = actList.size();
        for (int i = 0; i < actListSize; i++) {
            if (!actList.contains(item)) {
                System.out.println("Item is NOT present in List !!!");
                return false;
            }
        }
        System.out.println("Item is present in List !!!");
        return true;
    }



}