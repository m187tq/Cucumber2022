package pages;

import actiondriver.Action;
import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.datarepo;
import utils.globalVars;

import java.io.IOException;

public class CheckoutPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(CheckoutPage.class);
    Action actor = new Action();

public CheckoutPage() throws IOException {
    super();
}

    @FindBy(xpath="//h2[contains(text(),'Customer')]")
    public WebElement customerHeadingTxt;

    @FindBy(css=".product-column.product-figure")
    public WebElement itemImage;

    @FindBy(xpath="//button[contains(text(),'Sign Out')]")
    public WebElement signOutBtn;

    @FindBy(xpath="//*[@id=\"checkout-app\"]/div/div/div/div/ol/li[3]/div/a/div[3]/button")
    public WebElement billingAddrEditBtn;

    @FindBy(xpath="//body/div[@id='checkout-app']/div[1]/div[1]/div[1]/div[1]/ol[1]/li[2]/div[1]/a[1]/div[3]/button[1]")
    public WebElement ShippingAddressEditBtn;

    @FindBy(css=".checkout-step.optimizedCheckout-checkoutStep.checkout-step--customer")
    public WebElement fullCustomerHeadingTxt;

    @FindBy(id="addressToggle")
    public WebElement shippingAddressTxt;

    @FindBy(xpath="//label[contains(text(),'My billing address is the same as my shipping addr')]")
    public WebElement MyBillingAddressSameShippingAddressTxt;

    @FindBy(xpath="//h2[contains(text(),'Customer')]")
    public WebElement shippingMethodTxt;

    @FindBy(css="#checkout-shipping-continue")
    public WebElement customerContinueBtn;

    @FindBy(xpath="//h3[contains(text(),'Order Summary')]")
    public WebElement orderSummaryTxt;

    @FindBy(css="#cart-edit-link")
    public WebElement editCartLinkTxt;

    @FindBy(xpath="//*[@id=\"checkout-app\"]/div/div/div/aside/article/section[3]/div/div")
    public WebElement totalPrice;

    public boolean isCustomerHeadingTxtDisplayed() {
        return isDisplayed(customerHeadingTxt);
    }

    public boolean isItemImageDisplayed() {
        return isDisplayed(itemImage);
    }

    public void clickOnSignOutBtn() {
        waitForWebElementAndClick(signOutBtn);

    }

    public boolean isSignOutBtnEnabled() {
        return isEnabled(signOutBtn);

    }

    public void clickOnBillingAddrEditBtn() {
        fluentWait(getDriver(), billingAddrEditBtn, globalVars.explicitWait);
        waitForWebElementAndClick(billingAddrEditBtn);

    }

    public void isBillingAddrEditBtnEnabled() {
        isEnabled(billingAddrEditBtn);
    }

    public void isBillingAddrEditBtnDisplayed() {
        isDisplayed(billingAddrEditBtn);
    }

    public boolean isShippingAddrEditBtnDisplayed() {
        return isDisplayed(ShippingAddressEditBtn);
    }

    public boolean isFullCustomerHeadingTxtDisplayed() {
        return isDisplayed(fullCustomerHeadingTxt);

    }
    public boolean isShippingAddressTxtDisplayed() {
        return isDisplayed(shippingAddressTxt);
    }
    public boolean isMyBillingAddressSameShippingAddressTxtDisplayed() {
        return isDisplayed(MyBillingAddressSameShippingAddressTxt);
    }
    public boolean isShippingMethodTxtDisplayed() {
        return isDisplayed(shippingMethodTxt);
    }

    public void clickOnCustomerContinueBtn() {
        waitForWebElementAndClick(customerContinueBtn);
    }
    public boolean isOrderSummaryTxtDisplayed() {
        return  act.isDisplayed(getDriver(),orderSummaryTxt);

    }
    public void clickOnEditCartLinkTxt() {
        waitForWebElementAndClick(editCartLinkTxt);
    }

    public boolean isEditCartLinkTxtDisplayed() {
       return isDisplayed(editCartLinkTxt);
    }

    public boolean isTotalPriceDisplay() {
       return isDisplayed(totalPrice);
    }


}
