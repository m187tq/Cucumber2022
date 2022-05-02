package com.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;

import java.io.IOException;

public class E2ESteps extends BasePage {

    public E2ESteps() throws IOException {
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

    public E2ESteps (TopNaviPage topNaviPage,
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
                      CheckoutSuccessPage checkoutSuccessPage,
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
        this.checkoutSuccessPage = checkoutSuccessPage;
        this.checkoutConfirmationPage = checkoutConfirmationPage;
        this.checkoutCustomerPage = checkoutCustomerPage;
        this.checkoutShippingModeEditPage = checkoutShippingModeEditPage;
        this.checkoutPage = checkoutPage;
        this.paymentModeEditPage = paymentModeEditPage;
        this.cartSummary = cartSummary;
        this.cartPage = cartPage;
        this.navigationMenuPage = navigationMenuPage;
        this.footerPage = footerPage;
    }

    @When("user clicks on search box and enters product name as {string} and click on search button")
    public void userClicksOnSearchBoxAndEntersProductNameAsAndClickOnSearchButton(String keyword) throws Exception {
        act.click(getDriver(), topNaviPage.searchBox);
        topNaviPage.enterItemKeyword(keyword);
        topNaviPage.clickOnSearchBtn();
    }
    @And("user should be able to see search result item and click on add to cart button")
    public void userShouldBeAbleToSeeSearchResultItemAndClickOnAddToCartButton() throws Throwable {
        searchResultPage.productName().isDisplayed();
        searchResultPage.productDetailsImage().isDisplayed();
        searchResultPage.productAvailablity().isDisplayed();
        searchResultPage.addtoCartBtn().isDisplayed();
        searchResultPage.clickOnAddToCartBtn();
    }
    @And("user enters a number in quantity box to increases items by {string} and clicks on remove icon to remove from cart")
    public void userEntersANumberInQuantityBoxToIncreasesItemsByAndClicksOnRemoveIconToRemoveFromCart(String arg0) throws Exception {
        checkoutCartPage.validateUserOnShoppingCartPage();
        checkoutCartPage.validateRemoveIconBtnisEnabled();
        checkoutCartPage.enterAddQuantityBox(arg0);
        checkoutCartPage.clickOnUpdateBtn();
        checkoutCartPage.clickOnRemoveIconBtn();
        Assert.assertTrue(checkoutCartPage.validateYourShoppingCartIsEmptyIsDisplayed());
    }
    @And("user starts all over again from the previous steps as {string} and increases items by {string}")
    public void userStartsAllOverAgainFromThePreviousStepsAsAndIncreasesItemsBy(String arg0, String arg1) throws Exception {
        act.click(getDriver(), topNaviPage.searchBox);
        topNaviPage.enterItemKeyword(arg0);
        topNaviPage.clickOnSearchBtn();
        searchResultPage.clickOnAddToCartBtn();
        checkoutCartPage.enterAddQuantityBox(arg1);
        checkoutCartPage.clickOnUpdateBtn();
    }
    @And("user verifies the product item by item image, unit price, quantity and grand total")
    public void userVerifiesTheProductItemByItemImageUnitPriceQuantityAndGrandTotal() {
        checkoutCartPage.validateProductImageIsDisplayed();
        checkoutCartPage.getProductPrice();
        checkoutCartPage.getSubPrice();
        checkoutCartPage.getProductTotalPrice();
        checkoutCartPage.getProductGrandTotalPrice();

    }
    @And("user clicks on the Checkout button")
    public void userClicksOnTheCheckOutButton() throws IOException, InterruptedException {
        checkoutCartPage.clickOnCheckoutBtn();
    }

    @And("user on checkout confirmation page and validates all the order details displayed")
    public void userOnCheckoutConfirmationPageAndValidatesAllTheOrderDetailsDisplayed() throws Throwable {

    }
    @And("user clicks on confirm order button")
    public void userClicksOnConfirmOrderButton() throws Throwable {
        checkoutConfirmationPage.clickOnConfirmOrderBtn();

    }
    @And("user presented with a checkout success page as {string}")
    public void userPresentedWithACheckoutSuccessPageAs(String arg0) {

    }

    @Then("user gets an order number as {string} and thank message displayed as {string}")
    public void userGetsAnOrderNumberAsAndThankMessageDisplayedAs(String arg0, String arg1) {

    }
    @And("user clicks on continue and logout button")
    public void userClicksOnContinueAndLogoutButton() throws IOException, InterruptedException {
        checkoutSuccessPage.clickOnContinueBtn();

    }
    @Then("user is back to home page")
    public void userIsBackToHomePage() {

    }

}
