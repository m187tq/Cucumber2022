package pages;


import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.globalVars;

import java.io.IOException;
import java.util.List;

public class AccountCreatePage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(AccountCreatePage.class);
    AccountSuccessPage asPage = new AccountSuccessPage();

    String loginName = "covid19" + generateRandomString(5);
    String email = System.currentTimeMillis() + "Apr";
    public AccountCreatePage() throws IOException {
        super();

    }

    // URL, LOGO, TEXTS//
    public final String URL = "https://automationteststore.com/index.php?rt=account/create";
    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/h1/span[1]/text()")
    public WebElement yourAccountHasBeenCreatedTxt;
    @FindBy(xpath = "//a[contains(text(),'Register')]")
    public WebElement registerLinkText;
    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/h1")
    public WebElement createAccountTxt;
    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/p[1]")
    public WebElement ifYouAlreadyHaveAnAccountWithUs;
    @FindBy(xpath = "//h4[contains(text(),'Your Personal Details')]")
    public WebElement yourPersonalDetailsTxt;
    @FindBy(xpath = "//input[@id='AccountFrm_firstname']")
    public WebElement firstNameTextBox;

    //**************Your Personal Details**************//
    @FindBy(xpath = "//input[@id='AccountFrm_lastname']")
    public WebElement lastNameTextBox;
    @FindBy(xpath = "//input[@id='AccountFrm_email']")
    public WebElement emailTextBox;
    @FindBy(xpath = "//input[@id='AccountFrm_telephone']")
    public WebElement telephoneTextBox;
    @FindBy(xpath = "//input[@id='AccountFrm_fax']")
    public WebElement faxTextBox;
    @FindBy(xpath = "//h4[contains(text(),'Your Address')]")
    public WebElement yourAddressText;
    @FindBy(css = "#AccountFrm_company")
    public WebElement companyTextBox;

    //**************Your Address*****************//
    @FindBy(css = "#AccountFrm_address_1")
    public WebElement address1TextBox;
    @FindBy(xpath = "//input[@id='AccountFrm_address_2']")
    public WebElement address2TextBox;
    @FindBy(css = "#AccountFrm_city")
    public WebElement cityTextBox;
    @FindBy(xpath = "//select[@id='AccountFrm_zone_id']")
    public WebElement regionStateDropdownBtn;
    @FindBy(xpath = "//input[@id='AccountFrm_postcode']")
    public WebElement zipCodeTextBox;
    @FindBy(xpath = "//select[@id='AccountFrm_country_id']")
    public WebElement countryDropdownBtn;
    @FindBy(xpath = "//h4[contains(text(),'Login Details')]")
    public WebElement loginDetailsSectionText;
    @FindBy(css = "#AccountFrm_loginname")
    public WebElement loginNameTextBox;

    //**************Login Details**************//
    @FindBy(css = "#AccountFrm_password")
    public WebElement passwordTextBox;
    @FindBy(css = "#AccountFrm_confirm")
    public WebElement passwordConfirmTextBox;
    @FindBy(xpath = "//h4[contains(text(),'Newsletter')]")
    public WebElement newsletterText;
    @FindBy(css = "#AccountFrm_newsletter1")
    public WebElement subscribeYesRadioButton;

    //**************Newsletter**********************//
    @FindBy(css = "#AccountFrm_newsletter0")
    public WebElement subscribeNORadioButton;
    @FindBy(css = "#AccountFrm_agree")
    public WebElement agreeToPrivacyPolicyButton;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[5]/div[1]/label[1]")
    public WebElement agreeToPrivacyPolicyText;
    // Summit button //
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[5]/div[1]/div[1]/button[1]")
    public WebElement continueBtn;



    //**********ERRORS ********************//
    @FindBy(css = "#AccountFrm_firstname")
    public WebElement firstNameErrorTxt;
    @FindBy(css = "#AccountFrm_lastname")
    public WebElement lastNameErrorTxt;
    @FindBy(css = "#AccountFrm_email")
    public WebElement emailErrorTxt;
    @FindBy(css = "#AccountFrm_address_1")
    public WebElement address1ErrorTxt;
    @FindBy(css = "#AccountFrm_city")
    public WebElement cityErrorTxt;
    @FindBy(css = "#AccountFrm_zone_id")
    public WebElement regionStateErrorTxt;
    @FindBy(css = "#AccountFrm_postcode")
    public WebElement zipCodeErrorTxt;
    @FindBy(css = "#AccountFrm_password")
    public WebElement passwordErrorTxt;
    @FindBy(xpath = "//b[contains(text(),'Privacy Policy')]")
    public WebElement privacyPolicyErrorTxt;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]")
    public WebElement youMustAgreeToThePrivacyPolicyErrorTxt;
    @FindBy(xpath = "//span[contains(text(),'')]")
    public List<WebElement> allHighlightedWarningRedMessageErrorTxt;
    @FindBy(xpath = "//span[contains(text(),'First Name must be between 1 and 32 characters!')]")
    public WebElement firstNameRedMessages;
    @FindBy(xpath = "//span[contains(text(),'Last Name must be between 1 and 32 characters!')]")
    public WebElement lastNameRedMessages;

    //**********RED WARNING MESSAGES**********//
    @FindBy(xpath = "//span[contains(text(),'Email Address does not appear to be valid!')]")
    public WebElement emailRedMessages;
    @FindBy(xpath = "//span[contains(text(),'Address 1 must be between 3 and 128 characters!')]")
    public WebElement address1RedMessages;
    @FindBy(xpath = "//span[contains(text(),'City must be between 3 and 128 characters!')]")
    public WebElement cityRedMessages;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[2]/fieldset[1]/div[6]/span[1]")
    public WebElement zipCodeRedMessages;
    @FindBy(xpath = "//span[contains(text(),'Please select a region / state!')]")
    public WebElement StateProvinceRedMessages;
    @FindBy(xpath = "//span[contains(text(),'Login name must be alphanumeric only and between 5')]")
    public WebElement loginNameRedMessages;
    @FindBy(xpath = "//span[contains(text(),'Password must be between 4 and 20 characters!')]")
    public WebElement passwordRedMessages;
    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div[1]")
    public WebElement agreeToPrivacyPolicyRedMessages;


    public String createAccountText(String arg0) {
        act.fluentWait(getDriver(),createAccountTxt, globalVars.getDefaultExplicitTimeout());
        log.info("Got the element text: " + createAccountTxt.getText());
        return createAccountTxt.getText();
    }
    public String getIfYouAlreadyHaveAnAccountWithUsTxt() {
        log.info("Got the element text: " + ifYouAlreadyHaveAnAccountWithUs.getText());
        return getText(ifYouAlreadyHaveAnAccountWithUs);
    }
    public String getYourPersonalDetailsTxt(String arg1) {
        log.info("Got the element text: " + yourPersonalDetailsTxt.getText());
        return getText(yourPersonalDetailsTxt);
    }
    public void enterFirstName(String firstname) throws Exception {
        log.info("Entered text :" + firstname);
        sendKeysToWebElement(firstNameTextBox, firstname);

    }
    public void enterLastName(String lastName) throws Exception {
        log.info("Entered text :" + lastName);
        sendKeysToWebElement(lastNameTextBox, lastName);

    }
    public void enterFreshEmailAddress() throws Exception {
        sendKeysToWebElement(emailTextBox, email);
        log.info("Entered text :" + email);
    }
    public void enterTelephoneNumber(String telephoneNumber) throws Exception {
        log.info("Entered text :" + telephoneNumber);
        sendKeysToWebElement(telephoneTextBox,telephoneNumber);
    }
    public void enterFaxNumber(String faxNumber) throws Exception {
        sendKeysToWebElement(faxTextBox, faxNumber);
        log.info("Entered text :" + faxNumber);
    }

    public void enterYourPersonalDetailsSection(String firstName, String lastName, String telephone, String fax) throws Exception {
        enterFirstName(firstName);
        log.info("Entered text :" + firstName);
        enterLastName(lastName);
        log.info("Entered text :" + lastName);
        enterFreshEmailAddress();
        log.info("Entered text => entered Fresh EmailAddress...");
        enterTelephoneNumber(telephone);
        log.info("Entered text :" + telephone);
        enterFaxNumber(fax);
        log.info("Entered text :" + fax);

    }
    public String getYourAddressTxt() {
        log.info("Got the element text: " + yourAddressText.getText());
        return getText(yourAddressText);
    }
    public void enterCompanyName(String companyName) throws Exception {
        sendKeysToWebElement(companyTextBox, companyName);
        log.info("Entered text :" + companyName);
    }
    public void enterAddress1(String address1) throws Exception {
        sendKeysToWebElement(address1TextBox, address1);
        log.info("Entered text :" + address1);
    }
    public void enterAddress2(String address2) throws Exception {
        sendKeysToWebElement(address2TextBox, address2);
        log.info("Entered text :" + address2);
    }
    public void enterCity(String yourCity) throws Exception {
        sendKeysToWebElement(cityTextBox, yourCity);
        log.info("Entered text :" + yourCity);
    }

    public void selectRegionState(String yourRegionOrState) {
        selectByVisibleText(yourRegionOrState, regionStateDropdownBtn);
        log.info("Selected Visible Tex :" + yourRegionOrState);
    }
    public void enterZipCode(String ZipCode) throws Exception {
        sendKeysToWebElement(zipCodeTextBox, ZipCode);
        log.info("Entered text :" + ZipCode);
    }

    public void selectCountry(String countryName) {
        selectByVisibleText(countryName, countryDropdownBtn);
        log.info("Selected Visible Text :" + countryName);
    }
    public String getloginDetailsSectionText(String arg0) throws IOException {
        log.info("Got the element text: " + loginDetailsSectionText.getText());
        return getText(loginDetailsSectionText);
    }

    public void enterLoginName() throws Exception {
        sendKeysToWebElement(loginNameTextBox, ("Covid" + generateRandomNumber(5)));
        log.info("Entered generated Random loginName:" + "Covid" + generateRandomNumber(5));
    }
    public void enterPassword(String arg0) throws Exception {
        sendKeysToWebElement(passwordTextBox, arg0);
        log.info("Entered password text :" + arg0);
    }
    public void enterPasswordConfirm(String arg0) throws Exception {
       sendKeysToWebElement(passwordConfirmTextBox, arg0);
        log.info("Entered Confirm password text :" + arg0);
    }

    public String getNewsletterTxt() throws IOException {
        log.info("Got the element text: " + newsletterText.getText());
        return getText(newsletterText);

    }

    public void tickOnSubscribeAsYes() {
        waitForWebElementAndClick(subscribeYesRadioButton);
        log.info("Waited and clicked on the element: "+ subscribeYesRadioButton.getText());
    }

    public boolean validateSubscribeYesTicked() {
        log.info("Selected visible Radio Btn :" + subscribeYesRadioButton.getText());
        return isSelected(getDriver(),subscribeYesRadioButton);

    }

    public void clickOnSubscribeAsNo() {
        waitForWebElementAndClick(subscribeNORadioButton);
        log.info("Waited and clicked on the element: "+ subscribeNORadioButton.getText());

    }
    public void checkOnIAgreeToPrivacyPolicyRadioButton() {
        waitForWebElementAndClick(agreeToPrivacyPolicyButton);
        log.info("Waited and clicked on the element: "+ agreeToPrivacyPolicyButton.getText());
    }
    public void clickOnIAgreeToPrivacyPolicyRadioButton() {
        waitForWebElementAndClick(agreeToPrivacyPolicyButton);
        log.info("Waited and clicked on the element: "+ agreeToPrivacyPolicyButton.getText());

    }
    public AccountSuccessPage clickOnContinueButton() throws IOException, InterruptedException {
        waitFor(continueBtn);
        waitForWebElementAndClick(continueBtn);
        waitFor(asPage.accountHasBeenCreatedHeadingTxt);
        log.info("Waited for element on AccountSuccessPage : "+ asPage.accountHasBeenCreatedHeadingTxt.getText());
        return new AccountSuccessPage();
    }

    //******************SETTERS for Error*****************************//

    public void getFirstNameRedErrorTxt() throws Exception {
        // basePage.sendKeysToWebElement("    ");
    }

    public void getLastNameRedErrorTxt() throws Exception {
        // sendKeysToWebElement(lastNameErrorTxt,"    ");
    }

    public void getEmailRedErrorTxt() throws Exception {
        // sendKeysToWebElement(emailErrorTxt,"    ");
    }

    public void getAddress1RedErrorTxt() throws Exception {
        // sendKeysToWebElement(address1ErrorTxt,"    ");
    }

    public void getCityRedErrorTxt() throws Exception {
        //boolean result = cityErrorTxt.isDisplayed();
        // sendKeysToWebElement(cityErrorTxt,"    ");
        // checkPoint.mark("City",result,"City is Displayed");
    }

    public void selectRegionStateRedErrorTxt() throws Exception {
        //regionStateErrorTxt
    }

    public void getZipCodeRedErrorTxt() throws Exception {
        //sendKeysToWebElement(zipCodeErrorTxt,"    ");
    }

    public void getPasswordRedErrorTxt() throws Exception {

    }

    public void getPrivacyPolicyTxtRedErrorTxt() throws Exception {

    }

    public WebElement getErrorYouMustAgreeToThePrivacyPolicyRedErrorTxt() throws Exception {
        return youMustAgreeToThePrivacyPolicyErrorTxt;
    }

//===========================================================================================================//

    public boolean verifyFirstNameRedMessages() {
        return firstNameRedMessages.isDisplayed();

    }

    public boolean verifyLastNameRedMessages() {
        return lastNameRedMessages.isDisplayed();

    }

    public boolean verifyEmailRedMessages() {
        return emailRedMessages.isDisplayed();

    }

    public boolean verifyAddress1RedMessages() {
        return address1RedMessages.isDisplayed();

    }

    public boolean verifyCityRedMessages() {
        return cityRedMessages.isDisplayed();

    }

    public boolean verifyZipCodeRedMessages() {
        return zipCodeRedMessages.isDisplayed();
    }

    public boolean verifyStateProvinceRedMessages() {
        return StateProvinceRedMessages.isDisplayed();

    }

    public boolean verifyLoginNameRedMessages() {
        return loginNameRedMessages.isDisplayed();

    }

    public boolean verifyPasswordRedMessages() {
        return passwordRedMessages.isDisplayed();
    }

    public boolean verifyAgreeToPrivacyPolicyRedMessages() {
        return agreeToPrivacyPolicyRedMessages.isDisplayed();

    }

}


