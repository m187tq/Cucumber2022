package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class AccountLoginPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(AccountLoginPage.class);
    AccountPage accountPage = new AccountPage();
    public AccountLoginPage() throws IOException {
    super();
    }

    @FindBy(id = "loginFrm_loginname")
    public WebElement loginNameTxtField;
    @FindBy(css = "#loginFrm_password")
    public WebElement passwordTxtField;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/fieldset[1]/button[1]")
    public WebElement loginBtn;

    // successful Login Variables declaration //
    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div[1]")
    public WebElement loginWithBlanksCredentialsTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div[1]/text()")
    public WebElement loginWithBadPasswordTxt;
    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div[1]")
    public WebElement errorIncorrectLoginOrPasswordProvidedTxt;

    // Registration Variables declaration //

    @FindBy(xpath = "//div[contains(text(),'By creating an account you will be able to shop fa')]")
    public WebElement createAnAccountTxt;
    @FindBy(xpath = "//h2[contains(text(),'Returning Customer')]")
    public WebElement returningCustomerTxt;
    @FindBy(xpath = "//a[contains(text(),'Forgot your passwordErrorTxt?')]")
    public WebElement forgotYourPasswordLink;

    // Unsuccessful Login attempts Variables declaration //
    @FindBy(xpath = "//a[contains(text(),'Forgot your Login?')]")
    public WebElement forgotYourLoginLink;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/fieldset[1]/div[1]/label[1]")
    public WebElement registerAccountCheckBoxTxt;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/fieldset[1]/button[1]")
    public WebElement continueRegisterAccountBtn;

    @FindBy(xpath = "//h2[contains(text(),'I am a new customer.')]")
    public WebElement newCustomerTxt;
    @FindBy(id = "accountFrm_accountguest")
    public WebElement guestCheckoutRadioBtn;
    public boolean validateGuestCheckoutRadioBtnIsEnabled() {
        return isEnabled(getDriver(), guestCheckoutRadioBtn);
    }

    // Forget Password Variable declaration //

    @FindBy(xpath = "//*[@id='email_create']")
    public WebElement registrationEmailAddress;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/fieldset[1]/button[1]")
    public WebElement registerAccountContinueBtn;


    public void enterLoginName(String LoginName) throws Exception {
        log.info("Sent data to the element: " + LoginName);
        sendKeysToWebElement(loginNameTxtField,LoginName);
    }
    public void enterPassword(String Password) throws Exception {
        sendKeysToWebElement(passwordTxtField, Password);
        log.info("Sent data to the element: " + Password);
    }
    public AccountPage clickOnLoginBtn() throws IOException, InterruptedException {
        waitAndClickElement(loginBtn);
        log.info("Waited and clicked on the element...");
        waitFor(accountPage.welcomeBackTxt);
        log.info("Returning to new AccountPage.welcomeBackTxt");
        return new AccountPage();
    }
    public AccountLoginPage clickOnLoginButton() throws InterruptedException, IOException {
        waitAndClickElement(loginBtn);
        log.info("Waited and clicked on the element...");
        waitFor(errorIncorrectLoginOrPasswordProvidedTxt);
        log.info("Waited for element on AccountLoginPage : " + errorIncorrectLoginOrPasswordProvidedTxt.getText());
        return new AccountLoginPage();
    }
    public boolean validateLoginBtnIsEnabled() {
        log.info("Element is enabled ...");
        return isEnabled(loginBtn);
    }

    public void goToLoginPageEnterLoginNameAndPasswordThenClickOnLoginBtn(String loginName, String password) throws Exception {
        enterLoginName(loginName);
        enterPassword(password);
        clickOnLoginBtn();
        log.info("Successfully loged in....");
    }
    public void clickForgetYourLogin() {
        waitForWebElementAndClick(forgotYourLoginLink);
        log.info("Waited and clicked on the element: "+ forgotYourLoginLink.getText());
    }
    public void clickForgetYourPassword() {
       waitForWebElementAndClick(forgotYourPasswordLink);
       log.info("Waited and clicked on the element: "+ forgotYourPasswordLink.getText());
    }
    public String getForgotYourPasswordTxtLink() {
        log.info("Gotten element text..." + forgotYourPasswordLink);
        return getText(forgotYourPasswordLink);
    }
    public String getForgotYourLogin() {
        log.info("Gotten element text..." + forgotYourPasswordLink);
        return getText(forgotYourLoginLink);
    }
    public String errorIncorrectLoginPasswordProvidedConfirmationMessage(String loginValidationErrorMessage) {
        log.info("Gotten element text..." + errorIncorrectLoginOrPasswordProvidedTxt);
        return getText(errorIncorrectLoginOrPasswordProvidedTxt);

    }
    public String validateErrorIncorrectLoginPasswordProvidedConfirmationMessage() {
        waitFor(errorIncorrectLoginOrPasswordProvidedTxt);
        log.info("Waited and Got element text..." + errorIncorrectLoginOrPasswordProvidedTxt);
        return getText(errorIncorrectLoginOrPasswordProvidedTxt);
    }
    public String currentPageTitle(String pageTitle) {
        log.info("Getting currentPageTitle..."+ pageTitle);
        return getTitle();
    }
  public boolean verifyReturningCustomerTxtIsDisplayed(String arg1) {
      waitFor(returningCustomerTxt);
      log.info("Waited and verify element is Displayed: "+ arg1);
      return isDisplayed(returningCustomerTxt);
  }
    public boolean verifyRegisterAccountTextCheckBoxIsDisplayed() {
        waitFor(registerAccountCheckBoxTxt);
        log.info("Waited and verify element is Displayed: "+ registerAccountCheckBoxTxt.getText());
        return registerAccountCheckBoxTxt.isDisplayed();
    }
    public boolean validateNewCustomerTxtIsDisplayed(String arg0) {
        waitFor(newCustomerTxt);
        log.info("Waited and verify element is Displayed: "+ newCustomerTxt.getText());
        return isDisplayed(newCustomerTxt);
    }
    public AccountCreatePage clickOnContinueRegisterAccountBtn() throws IOException, InterruptedException {
        AccountCreatePage acp = new AccountCreatePage();
        waitFor(continueRegisterAccountBtn);
        log.info("Waited and clicked on the element...");
        continueRegisterAccountBtn.click();
        waitFor(acp.createAccountTxt);
        log.info("clicked and waited for element on returning to AccountSuccessPage...");
        return new AccountCreatePage();
    }
    public String getCreateAnAccountTxt() {
        log.info("Getting element text..." + createAnAccountTxt);
        return getText(createAnAccountTxt);

    }


}
