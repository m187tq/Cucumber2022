package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class CheckoutSuccessPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(CheckoutSuccessPage.class);
    IndexPage indexPage = new IndexPage();
    public CheckoutSuccessPage() throws IOException {
        super();
    }
    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/h1/span[1]")
    WebElement yourOrderHasBeenProcessedHeadingTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div/section/p[2]")
    WebElement OrderNumberText;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div/section/p[3]")
    WebElement viewInvoiceText;

    @FindBy(xpath = "//p[contains(text(),'Thank you for shopping with us!')]")
    WebElement thankYouForShoppingWithUsTxt;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div/section/p[3]")
    WebElement invoicePageLink;

    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/section[1]/a[1]")
    WebElement continueBtn;

    public String getOrderHasBeenProcessedHeading() {
        log.info("Getting element text..." + yourOrderHasBeenProcessedHeadingTxt.getText());
        return getText(yourOrderHasBeenProcessedHeadingTxt);

    }
    public String getOrderNumber() {
        log.info("Getting element text..." + OrderNumberText.getText());
        return getText(OrderNumberText);

    }
    public String getViewInvoice() {
        log.info("Getting element text..." + viewInvoiceText.getText());
        return getText(viewInvoiceText);
    }
    public String getThankYouForShoppingWithUsTxt() {
        log.info("Getting element text..." + thankYouForShoppingWithUsTxt.getText());
        return thankYouForShoppingWithUsTxt.getText();
    }
    public InvoiceOrderPage clickOnInvoicePageLink() throws IOException {
        log.info("Waiting to click on the element...");
        waitForWebElementAndClick(invoicePageLink);
        return new InvoiceOrderPage();
    }
    public IndexPage clickOnContinueBtn() throws InterruptedException, IOException {
        waitFor(continueBtn);
        log.info("Waiting to click on the element...");
        waitForWebElementAndClick(continueBtn);
        log.info("Successfully clicked on the WebElement: " + "<" + continueBtn.toString() + ">");
        waitFor(indexPage.logoImage);
        log.info("Clicked and returning a new Index Page");
        return new IndexPage();

    }


}
