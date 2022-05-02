package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.globalVars;

import java.io.IOException;

public class AccountSuccessPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(AccountSuccessPage.class);
    public AccountSuccessPage() throws IOException {
        super();
    }


    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/h2[1]/span[1]")
    public WebElement myAccountText;

    @FindBy(css = ".btn.btn-default.mr10")
    public WebElement continueAccountSuccessBtn;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/h1")
    public WebElement accountHasBeenCreatedHeadingTxt;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/div/section/p[2]")
    WebElement congratulationsYourNewAccountHasBeenSuccessfullyCreatedTxt;

    public boolean validateMyAccountTxtIsDisplayed() {
        waitFor(myAccountText);
        log.info("Waiting for element to Displayed.."+myAccountText.getText());
        return isDisplayed(getDriver(), myAccountText);
    }

    public String getAccountHasBeenCreatedHeadingTxt() {
        waitHelper.waitForElement(accountHasBeenCreatedHeadingTxt,globalVars.getExplicitWait());
        log.info("Waiting and getting element text..." + accountHasBeenCreatedHeadingTxt.getText());
        return accountHasBeenCreatedHeadingTxt.getText();
    }
    public WebElement congratulationsYourNewAccountHasBeenSuccessfullyCreatedTxt() {
        fluentWait(getDriver(), accountHasBeenCreatedHeadingTxt, globalVars.getExplicitWait());
        log.info("Waited and getting element text..." + accountHasBeenCreatedHeadingTxt.getText());
        return congratulationsYourNewAccountHasBeenSuccessfullyCreatedTxt;
    }

    public AccountPage clickOnContinueAccountSuccessBtn() throws IOException, InterruptedException {
        waitForWebElementAndClick(continueAccountSuccessBtn);
        log.info("Waiting to click on the element...");
        log.info("Clicked and returning new Account Page...");
        return new AccountPage();
    }


}
