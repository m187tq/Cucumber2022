package pages;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.datarepo;

import java.io.IOException;
import java.util.List;

import static utils.globalVars.getDefaultExplicitTimeout;


public class IndexPage extends BasePage {
    public static Logger log = LoggerHelper.getLogger(IndexPage.class);
    TopNaviPage topMenu = new TopNaviPage();
    public IndexPage() throws IOException {
        super();

    }

    public final String url = "https://automationteststore.com/";

    @FindBy(css = ".navbar-header.header-logo")
    public WebElement logoImage;

    @FindBy(name = "")
    public WebElement fastShippingTxt;
    @FindBy(name = "")
    public WebElement easyPaymentsTxt;
    @FindBy(name = "")
    public WebElement shippingOptionsTxt;
    @FindBy(name = "")
    public WebElement largeVarietyTxt;
    @FindBy(name = "")
    public List<WebElement> featuredProducts;
    @FindBy(name = "")
    public WebElement homePageSlider;
    @FindBy(name = "")
    public List<WebElement> containerFeaturedProducts;
    @FindBy(name = "")
    public WebElement footerBanners;
    @FindBy(name = "")
    public List<WebElement> latestProducts;
    @FindBy(name = "")
    public List<WebElement> bestSellerProducts;
    @FindBy(name = "")
    public List<WebElement> specialProducts;
    @FindBy(name = "")
    public List<WebElement> BrandScrollingList;
    @FindBy(name = "")
    public WebElement contactUs;
    @FindBy(name = "")
    public WebElement aboutUs;
    @FindBy(name = "")
    public WebElement testimonials;
    @FindBy(name = "")
    public WebElement newsletterSignUpsTxt;
    @FindBy(name = "")
    public WebElement subscribeNewsletterBox;
    @FindBy(name = "")
    public WebElement subscribeNewsletterBtn;

    // Constructor //


    public String getUrl() {
        return url;
    }


    public boolean isUserOnLandingPageTitle() {
        return getDriver().getTitle().contains(datarepo.expectedTitle);

    }

    public boolean userOnLandingPageUrl() {
        log.info("The current URL was...");
        return getDriver().getCurrentUrl().contains(datarepo.getBaseUrl());

    }

    public void goToUrl(String url) throws InterruptedException {
        log.info("got current Page Url....: " + url);
        getDriver().get(url);
    }

    public String getCurrentPageUrl() throws InterruptedException {
        fluentWait(getDriver(), logoImage, getDefaultExplicitTimeout());
        log.info("The current URL was: " + url );
        return getDriver().getCurrentUrl();
    }

    public String getCurrentPageTitle() {
        return getDriver().getTitle();
    }

/*    public boolean validateFastShippingTxt() {
        return isDisplayed(getDriver(),fastShippingTxt);
    }

    public boolean validateEasyPaymentsTxt() {
        return isDisplayed(getDriver(),easyPaymentsTxt);
    }

    public boolean validateShippingOptionsTxt() {
        return isDisplayed(getDriver(),shippingOptionsTxt);
    }

    public boolean validateLargeVarietyTxt() {
        return isDisplayed(getDriver(),largeVarietyTxt);
    }

    public List<WebElement> validateFeaturedProducts() {
        return featuredProducts;
    }*/

    public WebElement validateHomePageSlider() {
        return homePageSlider;
    }

    public List<WebElement> validateContainerFeaturedProducts() {
        return containerFeaturedProducts;
    }

    public WebElement validateFooterBanners() {
        return footerBanners;
    }

    public List<WebElement> validateLatestProducts() {
        return latestProducts;
    }

    public List<WebElement> validateBestSellerProducts() {
        return bestSellerProducts;
    }

    public List<WebElement> validateSpecialProducts() {
        return specialProducts;
    }

    public List<WebElement> validateBrandScrollingList() {
        return BrandScrollingList;
    }

    public WebElement validateContactUs() {
        return contactUs;
    }

    public WebElement validateAboutUs() {
        return aboutUs;
    }

    public WebElement validateTestimonials() {
        return testimonials;
    }

    public WebElement validateNewsletterSignUpsTxt() {
        return newsletterSignUpsTxt;
    }

    public WebElement validateSubscribeNewsletterBox() {
        return subscribeNewsletterBox;
    }

    public WebElement validateSubscribeNewsletterBtn() {
        return subscribeNewsletterBtn;
    }


    public void clickSearchButton() throws IOException {
        topMenu.clickOnSearchBtn();
    }

 /*   public int getSearchItemsCount() {
        String itemCount = ebayhome_elements.numOfItems.getText().trim();
        String itemCount2 = itemCount.replace(",", "");
        int itemCountInt = Integer.parseInt(itemCount2);
        return itemCountInt;
    }

    public void selectCategoryOption(String option) {
        List<WebElement> cat = ebayhome_elements.catOptions;
        for (WebElement x : cat) {
            if (x.getText().trim().toLowerCase().equals(option.toLowerCase())) {
                x.click();
                break;
            }
        }
    }*/
}

