package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class CheckoutConfirmationPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(CheckoutConfirmationPage.class);
    CheckoutCartPage checkoutCartPage = new CheckoutCartPage();
    PaymentModeEditPage paymentModeEditPage = new  PaymentModeEditPage();
    CheckoutSuccessPage checkoutSuccessPage = new CheckoutSuccessPage();
    public CheckoutConfirmationPage() throws IOException {
        super();
    }

    private String checkoutConfirmationPageURL = "https://automationteststore.com/index.php?rt=checkout/confirm";

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/h1[1]/span[1]")
    private WebElement checkoutConfirmationTxt;

    @FindBy(xpath = "//span[contains(text(),'Order Summary')]")
    private WebElement orderSummaryTxt;

    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
    private WebElement qtyProductWithStockLocations;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")
    private List<WebElement> ShippingPaymentItemsInYourCartText;

    @FindBy(xpath = "/html/body/div/header/div[2]/div/div[2]/ul/li")
    private WebElement currencyDropdownBtn;

    @FindBy(css = "#checkout_btn")
    private WebElement confirmOrderBtn;

    @FindBy(xpath = "/html/body/div/header/div[2]/div/div[3]/ul/li/a")
    private WebElement itemsCartIcon;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/h1/span[1]")
    private WebElement checkoutConfirmionHeadingTxt;

    @FindBy(css = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/p")
    private WebElement clickingConfirmOrderReturnPolicyText;

    @FindBy(xpath = "//b[contains(text(),'Return Policy')]")
    private WebElement returnPolicyTxtLink;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/table[1]/tbody/tr/td[4]/a")
    private WebElement editShipmentIcon;

    @FindBy(xpath = "//h4[contains(text(),'Shipping')]")
    private WebElement shippingTxt;

    @FindBy(css = "//h4[contains(text(),'Payment')]")
    private WebElement paymentTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/table[2]/tbody/tr/td[4]/a[1]")
    private WebElement editPaymentIcon;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/table[2]/tbody/tr/td[4]/a[2]")
    private WebElement editCouponIcon;

    @FindBy(linkText = "Items in your cart")
    private WebElement itemsInYourCartTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/h4[3]/a")
    private WebElement editCartIcon;

    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/table[3]/tbody[1]/tr[1]/td[2]")
    private WebElement itemsInYourCart;

    @FindBy(css = "#back")
    private WebElement backArrowBtn;

    @FindBy(css = "span.bold.totalamout")
    private List<WebElement> totalPriceAndAmountTxt;
    @FindBy(xpath = "//b[contains(text(),'$')]")
    private WebElement unitPriceTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/div/div[1]/table/tbody/tr[4]/td[1]/span")
    private WebElement totalPriceTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div[1]/div/div[2]/div/div[1]/table/tbody/tr[4]/td[2]/span")
    private WebElement amountTxt;

    public String getCheckoutConfirmationPageURL() {
        return checkoutConfirmationPageURL;
    }

    public boolean validateCheckoutConfirmationTxt() {
        return act.isDisplayed(getDriver(), checkoutConfirmationTxt);
    }

    public boolean validateUserOncheckoutConfirmatioPage() {
        return isDisplayed(orderSummaryTxt);
    }

    public WebElement getQtyProductWithStockLocations() {
        return qtyProductWithStockLocations;
    }

    public List<WebElement> getShippingPaymentItemsInYourCartText() {
        return ShippingPaymentItemsInYourCartText;
    }

    public void selectCurrencyDropdownBtn(String currenyName) {
        waitFor(currencyDropdownBtn);
        selectByVisibleText(currenyName, currencyDropdownBtn);
        log.info("Selected visible text :" + currenyName);
    }

    public CheckoutCartPage clickOnItemsCartIcon() throws IOException {
        waitForWebElementAndClick(itemsCartIcon);
        log.info("Waiting to click on the element...");
        waitFor(checkoutCartPage.shoppingCartTxt);
        log.info("Returning to new CheckoutCartPage");
        return new CheckoutCartPage();
    }

    public boolean validateCheckoutConfirmationHeadingTxt() {
        log.info("Element are Displayed...");
        return isDisplayed(checkoutConfirmionHeadingTxt);

    }

    public boolean validateClickingConfirmOrderReturnPolicyText() {
        log.info("Element are Displayed...");
        return isDisplayed(getDriver(), clickingConfirmOrderReturnPolicyText);

    }

    public void clickOnReturnPolicyTxtLink() {
        waitForWebElementAndClick(returnPolicyTxtLink);
        log.info("Waited and clicked on the element...");

    }
    public CheckoutShippingModeEditPage clickEditShipmentIcon() throws IOException {
        waitForWebElementAndClick(editShipmentIcon);
        log.info("Waiting to click on the element...");
        log.info("Returning to new CheckoutShippingModeEditPage...");
        return new CheckoutShippingModeEditPage();
    }
    public boolean validateShippingTxtIsDisplayed() {
        log.info("The element is Displayed as: " + "<" + shippingTxt.getText() + ">");
        return isDisplayed(shippingTxt);

    }
    public String getPaymentTxt() {
        log.info("Getting element text..." + paymentTxt.getText());
        return paymentTxt.getText();

    }
    public PaymentModeEditPage clickOnEditPaymentIcon() throws IOException {
        waitForWebElementAndClick(editPaymentIcon);
        log.info("Waiting to click on the element...");
        return new PaymentModeEditPage();
    }

    public PaymentModeEditPage clickOnEditCouponIcon() throws IOException {
        log.info("Waiting to click on the element...");
        waitForWebElementAndClick(editCouponIcon);
        log.info("Returning to new PaymentModeEditPage");
        return new PaymentModeEditPage();
    }

    public String getItemsInYourCartTxt() {
        log.info("Getting element text..." + itemsInYourCartTxt.getText());
        return itemsInYourCartTxt.getText();

    }

    public CheckoutCartPage clickOnEditCartIcon() throws IOException {
        waitForWebElementAndClick(editCartIcon);
        log.info("Clicked and returning a new PaymentModeEditPage");
        return new CheckoutCartPage();

    }
    public String itemsInYourCartTxt() {
        log.info("Getting element text..." + itemsInYourCart.getText());
        return itemsInYourCart.getText();
    }

    public PaymentModeEditPage clickOnBackArrowBtn() throws IOException {
        waitForWebElementAndClick(backArrowBtn);
        log.info("Clicked and returning a new PaymentModeEditPage");
        return new PaymentModeEditPage();
    }

    public String gettotalPriceTxt() {
        log.info("Getting element text..." + totalPriceTxt.getText());
        return totalPriceTxt.getText();

    }
    public String getAmountTxt() {
        log.info("Getting element text..." + amountTxt.getText());
        return amountTxt.getText();

    }

    public double getUnitPrice() {
        String unitPrice1=unitPriceTxt.getText();
        String unit=unitPrice1.replaceAll("[^a-zA-Z0-9]","");
        double finalUnitPrice=Double.parseDouble(unit);
        return finalUnitPrice/100;
    }

    public double getTotalPrice() {
        String totalPrice1=totalPriceTxt.getText();
        String tot=totalPrice1.replaceAll("[^a-zA-Z0-9]","");
        double finalTotalPrice=Double.parseDouble(tot);
        return finalTotalPrice/100;
    }

    public CheckoutSuccessPage clickOnConfirmOrderBtn() throws Throwable {
        waitAndClickElement(confirmOrderBtn);
        waitFor(checkoutSuccessPage.OrderNumberText);
        log.info("Clicked and returning a new CheckoutSuccessPage...");
        return new CheckoutSuccessPage();
    }

}
