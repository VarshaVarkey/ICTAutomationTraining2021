import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import lombok.val;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class HomePage<allcities> extends BasePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By imgChennaiCity = By.xpath("//img[@alt='CHEN']");
    private By drpdownCityName = By.xpath("//span[contains(@class,'ellipsis')]");
    private By searchKey = By.xpath("//ul/li//span[@class='sc-bqjOQT hUgGbt']");
    private By movieName = By.xpath("//div/h1[@class='styles__EventHeading-qswwm9-6 mptsd']");
    private By movieFailMessage = By.xpath("//ul/li/span[@class='sc-bqjOQT daJpxV']");
    private By selectOffer = By.xpath("//input[@id='ajax-typeahead']");
    private By offerIcici = By.xpath("//h4[contains(text(),'ICICI Bank Credit Card 25%')]");
    private By offerFailMessage = By.xpath("//div[@class='tt-no-results']");
    private By footerTextKey=By.xpath("//div[@class='sc-bMVAic bOhLXm']");
    private By listShow=By.xpath("//header/div[2]//div[2]/div/a[1]");

    //Todo parametrise city selection
    public void selectCity(String cityName) {
        click(imgChennaiCity);
        MyReport.extentTest.log(Status.INFO, "Selected City " + cityName);

    }

    public void verifyCitySelection(String cityName) throws IOException {
        String actualCity = getText(drpdownCityName);
        //todo generalise below statement

        try {
            Assert.assertEquals(actualCity, cityName, "City Name not matching, actual city is " + actualCity);
            MyReport.extentTest.log(Status.PASS, "City verified as " + actualCity, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "City Name not matching, actual city is " + actualCity, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    public void searchaMovie(String movieName) throws IOException {

        click(searchKey);
        MyReport.extentTest.log(Status.INFO, "Selected City " + movieName);
    }

    public void verifyMovieSearch(String movie) throws IOException {
        String actualMovie = getText(movieName);
        try {
            Assert.assertEquals(actualMovie, movie, "Movie Name not matching, actual movie is " + actualMovie);
            MyReport.extentTest.log(Status.PASS, "Movie name verified as " + actualMovie, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "Movie Name not matching, actual Movie name is " + actualMovie, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    public void verifyMovieSearchFail(String message) throws IOException {
        String actualMessage = getText(movieFailMessage);
        try {
            Assert.assertEquals(actualMessage, message, "Movie search fail message is not displayed " + actualMessage);
            MyReport.extentTest.log(Status.PASS, "Movie search fail message verified as " + actualMessage, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "Movie search fail message is not displayed " + actualMessage, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    public void selectOfferMenu(String offer) {
        click(selectOffer);
        MyReport.extentTest.log(Status.INFO, "Selected from menu: " + offer);
    }

    public void verifyOfferSelected(String offerName) throws IOException {
        String actualOffer = getText(offerIcici);
        try {
            Assert.assertEquals(actualOffer, offerName, "Offer Name not matching, actual offer is " + actualOffer);
            MyReport.extentTest.log(Status.PASS, "Offer name verified as " + actualOffer, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "Offer Name not matching, actual Movie name is " + actualOffer, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    public void verifyOfferSelectedFail() throws IOException {
        String actualOfferMessage = getText(offerFailMessage);
        try {
            Assert.assertTrue(actualOfferMessage.equalsIgnoreCase("No matching results"));
            MyReport.extentTest.log(Status.PASS, "Offer search failed message verified as " + actualOfferMessage, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "Offer failed message not displayed " + actualOfferMessage, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }

    public void verifyHomeMainMenu(WebElement menuValue) throws IOException
    {
         try {
             Assert.assertTrue(menuValue.isDisplayed(),"Home Page Main Menu name mismatching "+menuValue.getText());
             MyReport.extentTest.log(Status.PASS, "Home Page Main Menu verified " + menuValue.getText(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            }

            //todo use listeners to capture failure
            catch (Exception e) {
               MyReport.extentTest.log(Status.FAIL, "Home Page Main Menu name mismatching " + menuValue.getText(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            }
         }
    public void verifyFooterText(String footerText) throws IOException {
        String actualFooterText=getText(footerTextKey);
        try {

           Assert.assertTrue(actualFooterText.contains(footerText), "Footer Text not matching, actual footer text is " + actualFooterText);
           MyReport.extentTest.log(Status.PASS, "Footer text  verified as " + actualFooterText, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "Footer Text not matching, actual footer text is " + actualFooterText, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }
    public void selectListShow() throws IOException{
        click(listShow);
    }
    public void listWhatToHost(WebElement hostname) throws IOException{
        try {
            Assert.assertTrue(hostname.isDisplayed(),"What can you host options mismatching "+hostname.getText());
            MyReport.extentTest.log(Status.PASS, "What can you host options verified " + hostname.getText(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }

        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "What can you host options mismatching " + hostname.getText(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }
    public void listServicesOffer(WebElement serviceName) throws IOException{
        try {
            Assert.assertTrue(serviceName.isDisplayed(),"What services offered options mismatching "+serviceName.getText());
            MyReport.extentTest.log(Status.PASS, "What services offered options verified " + serviceName.getText(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }

        //todo use listeners to capture failure
        catch (Exception e) {
            MyReport.extentTest.log(Status.FAIL, "What services offered options mismatching " + serviceName.getText(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }
}