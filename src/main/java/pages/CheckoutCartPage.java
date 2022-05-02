package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class CheckoutCartPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(CheckoutCartPage.class);
    AccountLoginPage accountLoginPage = new AccountLoginPage();
    public CheckoutCartPage() throws IOException {
        super();
    }

    @FindBy(xpath="//input[@id='coupon_coupon']")
    public WebElement couponBox;

    @FindBy(css="#apply_coupon_btn")
    public WebElement applyCouponBtn;

    @FindBy(css="#estimate_country")
    public WebElement estimateCountryDropdown;

    @FindBy(css="#estimate_country_zones")
    public WebElement countryZonesDropdown;

    @FindBy(xpath="//input[@id='estimate_postcode']")
    public WebElement estimatePostcodeBox;

    @FindBy(xpath="//tbody/tr[2]/td[1]/div[1]/form[1]/div[2]/div[1]/span[1]/button[1]")
    public WebElement estimateCalculatorBtn;

    @FindBy(xpath="//select[@id='shippings']")
    public WebElement FlatRateShipmentsDropdown;

    @FindBy(xpath="//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[7]/a/i")
    public WebElement removeItemBtn;

    @FindBy(id="cart_update")
    public WebElement updateBtn;

    @FindBy(xpath="//*[@id=\"cart\"]/div/div[3]/div/a[1]")
    public WebElement continueShippingBtn;

    @FindBy(css="#cart_checkout1")
    public WebElement checkoutBtn;

    @FindBy(css=".maintext")
    public WebElement shoppingCartTxt;

    @FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[5]/div[1]/input[1]")
    public WebElement addQuantityBox;

    @FindBy(xpath = "//div[contains(text(),'\tYour shopping cart is empty!\t')]")
    public WebElement yourShoppingCartIsEmptyTxt;

    @FindBy(xpath = "//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[1]/a/img")
    public WebElement productImage;

    @FindBy(xpath = "//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[4]")
    public WebElement productPrice;

    @FindBy(xpath = "//*[@id=\"totals_table\"]/tbody/tr[1]/td[2]/span")
    public WebElement subPrice;

    @FindBy(xpath = "//*[@id=\"cart\"]/div/div[1]/table/tbody/tr[2]/td[6]")
    public WebElement productTotalPrice;

    @FindBy(xpath = "//*[@id=\"totals_table\"]/tbody/tr[3]/td[2]/span")
    public WebElement productGrandTotalPrice;


//==============================================================//

    public boolean validateUserOnShoppingCartPage() {
        log.info("The element is Displayed...");
        return isDisplayed(shoppingCartTxt);
    }

    public void enterCouponNumberOnCouponBox(String CouponNumber) throws Exception {
        sendKeysToWebElement(couponBox, CouponNumber);
        log.info("Sent data to the element: " + CouponNumber);
    }

    public void clickOnApplyCouponBtn() {
        waitForWebElementAndClick(applyCouponBtn);
        log.info("Waited and clicked on the element...");
    }

    public void selectEstimateCountryDropdown(String countryName) {
        selectByVisibleText(countryName, estimateCountryDropdown);
        log.info("Selected visible text :" + countryName);
    }
    public void selectCountryZonesDropdown(String countryZonesName) {
        selectByVisibleText(countryZonesName, countryZonesDropdown) ;
        log.info("Selected visible text :" + countryZonesName);
    }

    public void enterOnEstimatePostcodeBox(String postCode) throws Exception {
        sendKeysToWebElement(estimatePostcodeBox, postCode);
        log.info("Sent data to the element: " + postCode);
    }
    public void clickOnEstimateCalculatorBtn() {
        waitForWebElementAndClick(estimateCalculatorBtn);
        log.info("Waited and clicked on the element...");
    }
    public void selectFlatRateShipmentsDropdown(String flatRateShipment) {
        log.info("Select text from the DropDown: " + flatRateShipment);
        selectByVisibleText(flatRateShipment, FlatRateShipmentsDropdown);
    }
    public boolean validateRemoveIconBtnisEnabled() {
        log.info("Element is Enabled...");
        return isEnabled(getDriver(), removeItemBtn);
    }
    public void clickOnRemoveIconBtn() {
        waitForWebElementAndClick(removeItemBtn);
        log.info("clicked on the element to remove an items...");
    }
    public void clickOnUpdateBtn() throws InterruptedException {
        waitForWebElementAndClick(updateBtn);
        waitFor(updateBtn);
        log.info("Waited and clicked on the element to update cart...");
    }

    public IndexPage clickOnContinueShippingBtn() throws IOException {
        waitForWebElementAndClick(continueShippingBtn);
        log.info("Waiting to click on the element...");
        log.info("Clicked and returning to new Index Page...");
        return new IndexPage();
    }
    public AccountLoginPage clickOnCheckoutBtn() throws IOException, InterruptedException {
        waitForWebElementAndClick(checkoutBtn);
        log.info("Waiting to click on the element...");
        waitFor(accountLoginPage.loginNameTxtField);
        log.info("Clicked and returning to new Account LoginPage...");
        return new AccountLoginPage();
    }
    public void enterAddQuantityBox(String arg1) throws Exception {
        log.info("Waiting to click on the element...");
        waitForWebElementAndClick(addQuantityBox);
        sendKeysToWebElement(addQuantityBox, arg1);
        log.info("clicked on the element and sent data to: " + arg1);
    }
    public boolean validateYourShoppingCartIsEmptyIsDisplayed() {
        log.info("Your Shopping Cart Is Empty and Displayed...");
        return isDisplayed(yourShoppingCartIsEmptyTxt);
    }

    public boolean validateProductImageIsDisplayed() {
        log.info("Your ProductImage is Displayed...");
        return isDisplayed(productImage);

    }
    public String getProductPrice() {
        log.info("Getting element text..." + productPrice.getText());
        return productPrice.getText();

    }
    public String getSubPrice() {
        log.info("Getting element text..." + subPrice.getText());
        return subPrice.getText();

    }
    public String getProductTotalPrice() {
        log.info("Getting element text..." + productTotalPrice.getText());
        return productTotalPrice.getText();

    }
    public String getProductGrandTotalPrice() {
        log.info("Getting element text..." + productGrandTotalPrice.getText());
        return productGrandTotalPrice.getText();

    }

}
