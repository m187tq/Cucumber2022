package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.globalVars;

import java.io.IOException;
import java.util.List;

public class AccountPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(AccountPage.class);
    AccountEditPage accountEditPage = new AccountEditPage();
    AccountLogoutPage accountLogoutPage = new AccountLogoutPage();

    public AccountPage() throws IOException {
            super();
    }

    @FindBy(css = ".maintext")
    public WebElement userProfileMyAccountTxt;

    @FindBy(css = "h1.heading")
    public WebElement myAccountTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[2]/div[1]/div/ul/li[1]")
    public WebElement accountDashboardTxt;

    @FindBy(linkText = "Logoff")
    public WebElement logoffBtn;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[2]/div[1]")
    public List<WebElement> sideWidgetAccountDashboardLinks;

    @FindBy(css = "body.account-account:nth-child(2) div.container-fixed:nth-child(1) div.container-fluid div.column_right.col-md-3.col-xs-12.mt20 div.sidewidt div.myaccountbox ul.side_account_list li:nth-child(3) a:nth-child(1) > i.fa.fa-edit.fa-fw")
    public WebElement editAccountDetailsLink;

    @FindBy(id = "customer_menu_top")
    public WebElement welcomeBackTxt;

    @FindBy(linkText = "Logoff")
    public WebElement logout;

    @FindBy(css = ".alert.alert-success")
    public WebElement successYourAccountSuccessfullyUpdatedTxt;


    public boolean isAccountDashboardTxtDisplayed() {
        waitFor(accountDashboardTxt);
        return isDisplayed(getDriver(),accountDashboardTxt);
    }

    public AccountLogoutPage clickOnLogoffBtn() throws IOException {
        waitFor(logoffBtn);
        log.info("Waiting to click on the element...");
        waitForWebElementAndClick(logoffBtn);
        log.info("return a new AccountLogoutPage...");
        return new AccountLogoutPage();
    }

    public boolean isLogoffBtnDisplayed() {
        return act.isDisplayed(getDriver(), logoffBtn);
    }

    public void verifyAllSideWidgetElementsInAccountPageLinks() {
        List<WebElement> links = sideWidgetAccountDashboardLinks;
        for (int i = 0; i < links.size(); i = i++) {
            String listOfElements = links.get(i).getText();
            System.out.println("Size Of Side Widget List => " + links.size());
            log.info("Size Of Side Widget List => " + links.size());
            break;
        }

    }

    public void ClickOnLogOffLinkInSideWidgetList() {
        List<WebElement> ListSideWidget = getDriver().findElements(By.cssSelector(".side_account_list"));
        for (int i = 0; i < ListSideWidget.size(); i = i++) {
            String listName = ListSideWidget.get(i).getText();
            if (listName.contains("Logoff")) {
                getDriver().findElements(By.linkText("Logoff")).get(i).click();
                break;
            }

        }

    }

    public boolean validateUserAccountTextIsDisplayed() throws InterruptedException {
        waitFor(myAccountTxt);
        log.info("element is Displayed.."+myAccountTxt.getText());
        return isDisplayed(getDriver(), myAccountTxt);
    }

    public String userProfileInfoMessage() {
        waitFor(userProfileMyAccountTxt);
        log.info("Getting element text: " + userProfileMyAccountTxt.getText());
        return userProfileMyAccountTxt.getText();
    }
    public boolean homePageWelcomeMessage(String arg1) throws InterruptedException {
        fluentWait(getDriver(), welcomeBackTxt, globalVars.getDefaultExplicitTimeout());
        log.info("The element is Displayed as : " + "<" + welcomeBackTxt.getText() + ">");
        return isDisplayed(getDriver(), welcomeBackTxt);
    }

    public String accountDashboardMessage() {
        log.info("Getting element text: " + accountDashboardTxt.getText());
        return getText(accountDashboardTxt);
    }

    public AccountLogoutPage clickOnLogoutBtn() throws IOException {
        waitFor(logout);
        waitAndClickElement(logout);
        log.info("Successfully clicked on the WebElement: " + "<" + logout.toString() + ">");
        waitFor(accountLogoutPage.accountLogoutTxt);
        return new AccountLogoutPage();
    }
    public AccountEditPage clickOnEditAccountDetailsLink() throws IOException, InterruptedException {
        waitFor(editAccountDetailsLink);
        waitAndClickElement(editAccountDetailsLink);
        log.info("Successfully clicked on the WebElement: " + "<" + editAccountDetailsLink.toString() + ">");
        waitFor(accountEditPage.firstNameBox);
        log.info("Returning new AccountEdit Page and wait for "+accountEditPage.myaccountInformationHeadingtext);
        return new AccountEditPage();

    }
    public String accountProfileSuccessfulUpdateMessage() {
        waitFor(successYourAccountSuccessfullyUpdatedTxt);
        log.info("Got the element text: " + successYourAccountSuccessfullyUpdatedTxt.getText());
        return successYourAccountSuccessfullyUpdatedTxt.getText();

    }

}