package com.steps;


import helper.wait.WaitHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;
import utilities.datarepo;

import java.io.IOException;


public class AllSteps extends BasePage {
    String email = "covid" + generateRandomNumber(5) + "@gmail.com";

    public AllSteps() throws IOException {
        super();
    }
    private WebDriver driver = getDriver();
    private TopNaviPage topNaviPage;
    private AccountPage accountPage;
    private AccountLoginPage accountLoginPage;
    private AccountCreatePage accountCreatePage;
    private AccountEditPage accountEditPage;
    private AccountLogoutPage accountLogoutPage;
    private AccountSuccessPage accountSuccessPage;
    private IndexPage indexPage;
    private AddToCartPage addToCartPage;

    private NavigationMenuPage navigationMenuPage;
    private ProductIDPage productIDPage;
    private SearchResultPage searchResultPage;
    private ShippingReturnsPage shippingReturnsPage;
    private InvoiceOrderPage invoiceOrderPage;
    private MiddleMenuNaviPage middleMenuNaviPage;
    private FooterPage footerPage;
    private CheckoutBillingPage checkoutBillingPage;
    private CheckoutCartPage checkoutCartPage;
    private CheckoutConfirmationPage checkoutConfirmationPage;
    private CheckoutCustomerPage checkoutCustomerPage;
    private CheckoutPage checkoutPage;
    private CheckoutShippingModeEditPage checkoutShippingModeEditPage;
    private CheckoutSuccessPage checkoutSuccessPage;
    private PaymentModeEditPage paymentModeEditPage;
    private CartSummary cartSummary;
    private CartPage cartPage;

    public AllSteps(TopNaviPage topNaviPage,
                    AccountLoginPage accountLoginPage,
                    IndexPage indexPage,
                    AccountSuccessPage accountSuccessPage,
                    AccountEditPage accountEditPage,
                    AccountLogoutPage accountLogoutPage,
                    AccountCreatePage accountCreatePage,
                    AccountPage accountPage,
                    AddToCartPage addToCartPage,
                    NavigationMenuPage navigationMenuPage,
                    ProductIDPage productIDPage,
                    SearchResultPage searchResultPage,
                    ShippingReturnsPage shippingReturnsPage,
                    InvoiceOrderPage invoiceOrderPage,
                    MiddleMenuNaviPage middleMenuNaviPage,
                    FooterPage footerPage,
                    CheckoutBillingPage checkoutBillingPage,
                    CheckoutCartPage checkoutCartPage,
                    CheckoutConfirmationPage checkoutConfirmationPage,
                    CheckoutCustomerPage checkoutCustomerPage,
                    CheckoutPage checkoutPage,
                    CheckoutShippingModeEditPage checkoutShippingModeEditPage,
                    PaymentModeEditPage paymentModeEditPage,
                    CartSummary cartSummary,
                    CartPage cartPage)
            throws IOException {
        this.topNaviPage = topNaviPage;
        this.accountLoginPage = accountLoginPage;
        this.indexPage = indexPage;
        this.accountSuccessPage = accountSuccessPage;
        this.accountEditPage = accountEditPage;
        this.accountLogoutPage = accountLogoutPage;
        this.accountPage = accountPage;
        this.addToCartPage = addToCartPage;
        this.productIDPage = productIDPage;
        this.searchResultPage = searchResultPage;
        this.shippingReturnsPage = shippingReturnsPage;
        this.invoiceOrderPage = invoiceOrderPage;
        this.accountCreatePage = accountCreatePage;
        this.middleMenuNaviPage = middleMenuNaviPage;
        this.checkoutBillingPage = checkoutBillingPage;
        this.checkoutCartPage = checkoutCartPage;
        this.checkoutConfirmationPage = checkoutConfirmationPage;
        this.checkoutCustomerPage = checkoutCustomerPage;
        this.checkoutShippingModeEditPage = checkoutShippingModeEditPage;
        this.checkoutPage = checkoutPage;
        this.paymentModeEditPage = paymentModeEditPage;
        this.cartSummary = cartSummary;
        this.navigationMenuPage = navigationMenuPage;
        this.cartPage = cartPage;
        this.footerPage = footerPage;
    }

    @Given("user is on index page url as {string} and page title as {string}")
    public void userIsOnIndexPageUrlAsAndPageTitleAs(String Url, String arg1) throws InterruptedException {
        goToIndexPage(Url);
    }

    @Given("user on index page url as {string} and page title as {string}")
    public void userOnIndexPageUrlAsAndPageTitleAs(String arg0, String arg1) throws InterruptedException {
        goToIndexPage(arg0);
        Assert.assertEquals(getCurrentURL(), arg0);
        Assert.assertEquals(getPageTitle(), arg1);
    }
    @When("user clicks on Login Or Register link")
    public void userClicksOnLoginOrRegisterLink() throws IOException {
        topNaviPage.clickOnLoginRegisterBtn();
    }
    @And("user should be on account login page with option to login as {string} or create an Account as {string}")
    public void userShouldBeOnAccountLoginPageWithOptionToLoginAsOrCreateAnAccountAs(String arg0, String arg1) {
        accountLoginPage.validateNewCustomerTxtIsDisplayed(arg0);
       accountLoginPage.verifyReturningCustomerTxtIsDisplayed(arg1);

    }

    @When("user enters login Name as {string} and password as {string} and clicks on Login button")
    public void user_enters_login_name_as_and_password_as_and_clicks_on_login_button(String string, String string2) throws Exception {
        WaitHelper waitHelper = new WaitHelper(driver);
        waitHelper.waitForElement(accountLoginPage.loginBtn, 60);
        accountLoginPage.enterLoginName(string);
        accountLoginPage.enterPassword(string2);
        accountLoginPage.clickOnLoginBtn();
    }
    @Then("user should be presented with the following validation message as {string}")
    public void user_should_be_presented_with_the_following_validation_message_as(String arg1) throws InterruptedException {
        Assert.assertTrue(accountPage.homePageWelcomeMessage(arg1), arg1);
    }
    @Then("user clicks on edit profile button")
    public void user_clicks_on_edit_profile_button() throws IOException, InterruptedException {
        accountPage.clickOnEditAccountDetailsLink();
    }
    @Then("user enters {string} {string} and EmailAddress telephone as {string} and fax as {string} in their respective box")
    public void user_enters_and_email_address_telephone_as_and_fax_as_in_their_respective_box(String string, String string2, String string3, String string4) throws Exception {
        Assert.assertTrue(accountEditPage.validateUserOnAccountEditPageIsDisplayed());
        accountEditPage.enterFirstNameBox(string);
        accountEditPage.enterLastNameBox(string2);
        accountEditPage.enterFreshEmailBox();
        accountEditPage.enterTelephoneBox(string3);
        accountEditPage.enterFaxBox(string4);
    }
    @And("user clicks on Continue button to confirms profile successful update message as {string}")
    public void userClicksOnContinueButtonToConfirmsProfileSuccessfulUpdateMessageAs(String arg0) throws InterruptedException, IOException {
        accountEditPage.clickOnContinueEditBtn();
        Assert.assertTrue(accountPage.accountProfileSuccessfulUpdateMessage().contains(arg0));
    }
    @When("user clicks on logoff button and logout Continue Button")
    public void user_clicks_on_logoff_button_and_logout_continue_button() throws IOException, InterruptedException {
        accountPage.clickOnLogoffBtn();
        Assert.assertTrue(accountLogoutPage.validateLogoutContinueBtnIsDisplayed());
        accountLogoutPage.clickOnLogoutContinueButton();
    }
    @Then("user is back to index page as {string}")
    public void user_is_back_to_index_page_as(String arg1) throws InterruptedException {
        Assert.assertEquals(indexPage.getCurrentPageUrl(), arg1);
    }

    // NEGATIVE LOGIN UNIQUE STEPS//
    @And("user enters wrong combination of both login Name as {string} or password as {string}")
    public void userEntersWrongCombinationOfBothLoginNameAsOrPasswordAs(String loginName, String password) throws Exception {
        accountLoginPage.enterLoginName(loginName);
        accountLoginPage.enterPassword(password);
    }
    @And("user clicks on Login_Button")
    public void userClicksOnLogin_Button() throws IOException, InterruptedException {
    accountLoginPage.clickOnLoginButton();

    }
    @Then("User gets error message as {string} and still on Home page title as {string}")
    public void userGetsErrorMessageAsAndStillOnHomePageTitleAs(String loginValidationErrorMessage, String pageTitle) {
        Assert.assertTrue(accountLoginPage.errorIncorrectLoginPasswordProvidedConfirmationMessage(loginValidationErrorMessage).contains(loginValidationErrorMessage));
        Assert.assertTrue(accountLoginPage.currentPageTitle(pageTitle).contains(pageTitle));
    }
// REGISTRATION UNIQUE STEPS//

    @And("^user verifies register account radio button is checked and clicks on Continue button$")
    public void userVerifiesRegisterAccountRadioButtonIsCheckedAndClicksOnContinueButton() throws IOException, InterruptedException {
        accountLoginPage.verifyRegisterAccountTextCheckBoxIsDisplayed();
        accountLoginPage.clickOnContinueRegisterAccountBtn();
    }
    @And("user is on account create page and verifies page heading as {string} and page Url contains {string}")
    public void userIsOnAccountCreatePageAndVerifiesPageHeadingAsAndPageUrlContains(String arg0, String arg1) throws InterruptedException {
        accountCreatePage.createAccountText(arg0);
        getDriver().getCurrentUrl().contains(arg1);

    }
    @And("user is in personal details in {string}")
    public void userIsInPersonalDetailsIn(String arg0) {
        String result = accountCreatePage.getYourPersonalDetailsTxt(arg0);
        Assert.assertEquals(result, arg0);

    }
    @And("user enters First Name box as {word}")
    public void userEntersFirstNameBoxAsFirstName(String firstname) throws Exception {
        accountCreatePage.enterFirstName(firstname);
    }
    @And("user enters Last Name box as {word}")
    public void userEntersLastNameBoxAsLastName(String lastName) throws Exception {
        accountCreatePage.enterLastName(lastName);
    }
    @And("user enters fresh email in E-Mail box")
    public void userEntersFreshEmailInEMailBox() {
        sendKeys(accountCreatePage.emailTextBox, email);
    }
    @And("user enters Telephone box as {word}")
    public void userEntersTelephoneBoxAs(String telephoneNumber) throws Exception {
        accountCreatePage.enterTelephoneNumber(telephoneNumber);
    }
    @And("user enters Fax box as {word}")
    public void userEntersFaxBoxAs(String faxNumber) throws Exception {
        accountCreatePage.enterFaxNumber(faxNumber);
    }
    @And("user on Address Section as {string}")
    public void userOnAddressSectionAs(String arg0) throws Exception {
        Assert.assertEquals(accountCreatePage.getYourAddressTxt(),arg0);
    }
    @And("user enters Company name as {word}")
    public void userEntersCompanyNameAsYourCompanyName(String companyName) throws Exception {
        accountCreatePage.enterCompanyName(companyName);
    }
    @And("user enters Address One box as {word}")
    public void userEntersAddressOneBoxAsYourAddress(String address1) throws Exception {
        accountCreatePage.enterAddress1(address1);
    }

    @And("user enters Address Two box as {word}")
    public void userEntersAddressTwoBoxAsYourAddress(String address2) throws Exception {
        accountCreatePage.enterAddress2(address2);
    }
    @And("user enters City box as {word}")
    public void userEntersCityBoxAsYourCity(String yourCity) throws Exception {
        accountCreatePage.enterCity(yourCity);
    }
    @And("user enters Region or State box as {string}")
    public void userEntersRegionOrStateBoxAsYourRegionOrState(String yourRegionOrState ) {
        accountCreatePage.selectRegionState(datarepo.regionState);
    }
    @And("user enters ZIP Code box as {string}")
    public void userEntersZIPCodeBoxAsYourZIPCode(String yourZIPCode) throws Exception {
        accountCreatePage.enterZipCode(yourZIPCode);
    }
    @And("user enters Country box as {string}")
    public void userEntersCountryBoxAs(String arg0) {
        accountCreatePage.selectCountry(arg0);
    }
    @And("user enters all the login requested details as {string}")
    public void userEntersAllTheLoginRequestedDetailsAs() throws Exception {
        Assert.assertTrue(accountCreatePage.loginDetailsSectionText.isDisplayed());
        accountCreatePage.enterLoginName();
        accountCreatePage.enterPassword(datarepo.getPassword());
        accountCreatePage.enterPasswordConfirm(datarepo.getConfirmPassword());
    }
    @And("user enters the following details")
    public void userEntersTheFollowingDetails(DataTable dataTable) throws Exception {
        accountEditPage.userUpdateProfile(dataTable);

    }
    @And("user on login section as {string}")
    public void userOnLoginSectionAs(String arg0) throws IOException {
        Assert.assertEquals(accountCreatePage.getloginDetailsSectionText(arg0), arg0);
    }

    @And("user enters loginName in Login name box")
    public void userEntersLoginNameInLoginNameBox() throws Exception {
        accountCreatePage.enterLoginName();
    }
    @And("user enters password in Password box as {string}")
    public void userEntersPasswordInPasswordBoxAs(String arg0) throws Exception {
        accountCreatePage.enterPassword(arg0);
    }
    @And("user enters Confirm password in Password Confirm box as {string}")
    public void userEntersConfirmPasswordInPasswordConfirmBoxAs(String arg0) throws Exception {
        accountCreatePage.enterPasswordConfirm(arg0);

    }
    @And("^user subscribed to Newsletter and check on Yes$")
    public void userSubscribedToNewsletterAndCheckOnYes() throws IOException {
        accountCreatePage.tickOnSubscribeAsYes();
        Assert.assertTrue(isSelected(getDriver(), accountCreatePage.subscribeYesRadioButton));
    }
    @And("user ticks on read and agree to the Privacy Policy radio button")
    public void userTicksOnReadAndAgreeToThePrivacyPolicyRadioButton() {
        accountCreatePage.checkOnIAgreeToPrivacyPolicyRadioButton();
    }

    @Then("user gets success confirmation message as {string}")
    public void userGetsSuccessConfirmationMessageAs(String Actual) {
        String Expected = accountSuccessPage.getAccountHasBeenCreatedHeadingTxt();
        Assert.assertEquals(Expected, Actual);
    }
    @And("user gets congratulatory message as {string}")
    public void userGetsCongratulatoryMessageAs(String arg0) {
        String result = String.valueOf(accountSuccessPage.congratulationsYourNewAccountHasBeenSuccessfullyCreatedTxt().isDisplayed());
        Assert.assertTrue(Boolean.parseBoolean(result));
    }
    @And("user clicks on ContinueAccountSuccess button")
    public void userClicksOnContinueAccountSuccessButton() throws IOException, InterruptedException {
        accountSuccessPage.clickOnContinueAccountSuccessBtn();
    }


    @When("user clicks on logoff button on Account page")
    public void userClicksOnLogoffButtonOnAccountPage() throws IOException {
        accountPage.clickOnLogoutBtn();

    }
    @And("user clicks on Continue button")
    public void userClicksOnContinueButton() throws IOException, InterruptedException {
        accountCreatePage.clickOnContinueButton();
    }

    @And("user is on home page and presented with welcome message as {string}")
    public void userIsOnHomePageAndPresentedWithWelcomeMessageAs(String arg0) throws InterruptedException {
        Assert.assertTrue(accountPage.homePageWelcomeMessage(arg0),(arg0));

    }


    @And("^user clicks on SignOut button$")
    public void userClicksOnSignOutButton() throws IOException {
        accountPage.clickOnLogoffBtn();
        accountLogoutPage.clickOnLogoutContinueButton();

    }
    @And("user on account logout page url contains {string} and clicks on logout Continue Button")
    public void userOnAccountLogoutPageUrlContainsAndClicksOnLogoutContinueButton(String arg0) throws IOException {
        Assert.assertTrue(act.getCurrentURL(getDriver()).contains(arg0));
        Assert.assertTrue(accountLogoutPage.validateLogoutContinueBtnIsDisplayed());
        accountLogoutPage.clickOnLogoutContinueButton();
    }
    @And("user is back to login page url as {string}")
    public void userIsBackToLoginPageUrlAs(String arg0) {
        Assert.assertEquals(getDriver().getCurrentUrl(), arg0);

    }

}
