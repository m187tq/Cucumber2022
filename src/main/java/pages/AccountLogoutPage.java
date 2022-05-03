package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.globalVars;

import java.io.IOException;

public class AccountLogoutPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(AccountLogoutPage.class);
    public AccountLogoutPage() throws IOException {
        super();
    }

    @FindBy(xpath = "//p[contains(text(),'You have been logged off your account. It is now s')]")
    public WebElement saveToLogoutTxt;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/section[1]/a[1]")
    public WebElement logoutAccountContinueBtn;

    @FindBy(css = ".heading1")
    public WebElement accountLogoutTxt;

    public boolean validateSuccessfulLogOffProcess() throws InterruptedException {
        log.info("Element are Displayed...");
        return isDisplayed(getDriver(), saveToLogoutTxt);
    }

    public boolean validateAccountLogoutTxtIsDisplayed() {
        log.info("element is Displayed...");
        return isDisplayed(getDriver(), accountLogoutTxt);
    }

    public String getCurrentUrl() throws InterruptedException {
        log.info("Found(Got) the following URL...");
        return getDriver().getCurrentUrl();
    }
    public IndexPage clickOnLogoutContinueButton() throws IOException {
        IndexPage indexPage = new IndexPage();
        explicitWait(getDriver(), logoutAccountContinueBtn, globalVars.getDefaultExplicitTimeout());
        waitAndClickElement(logoutAccountContinueBtn);
        log.info("Successfully clicked on the WebElement: " + "<" + logoutAccountContinueBtn.toString() + ">");
        fluentWait(getDriver(), indexPage.logoImage, globalVars.getDefaultExplicitTimeout());
        log.info("Returning new IndexPage...");
        return new IndexPage();

    }
    public boolean validateLogoutContinueBtnIsDisplayed() {
        explicitWait(getDriver(), logoutAccountContinueBtn, globalVars.getDefaultExplicitTimeout());
        log.info("Waiting for element to Displayed..");
        log.info("Element Displayed as: " + "<" + logoutAccountContinueBtn.toString() + ">");
        return isDisplayed(getDriver(), logoutAccountContinueBtn);
    }


}
