import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookMyShow {

    public WebDriver driver;

    @BeforeMethod
    @Parameters({"URLlink"})
    public void openBrowser(String URLlink){
        driver=BrowserResume.openBrowser(URLlink);

    }

    @DataProvider(name="citycheck")
    public static Object[][] cityCheck() {
        return new Object[][]{{"Chennai"}};
    }
    /*@DataProvider(name="homemainmenu")
    public static Object[][] MainMenuCheck() {

        return new Object[][]{{"Movies"},{"Stream"},{"Events"},{"Plays"},{"Sports"},{"Activities"},{"Fanhood"},{"Buzz"}};
    }*/
    @Test(dataProvider = "citycheck")
    public void verifyCityName(String cityName) throws IOException {
        MyReport.createTest("Verify City Name-Positive");
        HomePage homePage = new HomePage(driver);
        //todo parametrise this get from dataprovider
        homePage.selectCity("Chennai");
        homePage.verifyCitySelection(cityName);
    }

    @Test
          public void searchMovie() throws IOException
        {
                MyReport.createTest("Verify Searching Movie-Positive");
                HomePage homePage = new HomePage(driver);
                homePage.selectCity("Chennai");
                driver.findElement(By.xpath("//header//span[@id='4']")).click();
                driver.findElement(By.xpath("//input[@class='sc-fihHvN bSUQJL']")).sendKeys("Karnan");
                homePage.searchaMovie("Karnan");
                homePage.verifyMovieSearch("Karnan");
        }
    @Test
    public void searchMovieFail() throws IOException
    {
        MyReport.createTest("Verify Searcing Movie-Negative");
        HomePage homePage = new HomePage(driver);
        homePage.selectCity("Chennai");
        driver.findElement(By.xpath("//header//span[@id='4']")).click();
        driver.findElement(By.xpath("//input[@class='sc-fihHvN bSUQJL']")).sendKeys("112233");
        homePage.verifyMovieSearchFail("No Results found");
    }
    @Test
    public void SearchCastCrew() throws IOException {
        MyReport.createTest("Verify Searching CastnCrew-Positive");
        MovieCastCrew castCrew = new MovieCastCrew(driver);
        castCrew.selectCity("Chennai");
        driver.findElement(By.xpath("//header//span[@id='4']")).click();
        driver.findElement(By.xpath("//input[@class='sc-fihHvN bSUQJL']")).sendKeys("Onward");
        castCrew.searchaMovie("Onward");
        castCrew.verifyCastCrew("Tom Holland","Mychael Danna");

    }
    @Test
    public void SearchCastCrewFail() throws IOException {
        MyReport.createTest("Verify Searching CastnCrew-Negative");
        MovieCastCrew castCrew = new MovieCastCrew(driver);
        castCrew.selectCity("Chennai");
        driver.findElement(By.xpath("//header//span[@id='4']")).click();
        driver.findElement(By.xpath("//input[@class='sc-fihHvN bSUQJL']")).sendKeys("Onward");
        castCrew.searchaMovie("Onward");
        castCrew.verifyCastCrew("Tom Cruise","Michael Jackson");

    }
    @Test
    public void SearchOffers() throws IOException {
        MyReport.createTest("Verify Searching Offers-Positive");
        HomePage homePage = new HomePage(driver);
        homePage.selectCity("Chennai");
        driver.findElement(By.xpath("//*[@id=\"super-container\"]//div[2]/div/a[3]")).click();
        homePage.selectOfferMenu("Offer");
        driver.findElement(By.xpath("//input[@id='ajax-typeahead']")).sendKeys("ICICI Bank Credit Card 25%");
        driver.findElement(By.xpath("//strong[@class='tt-highlight']"));
        homePage.verifyOfferSelected("ICICI BANK CREDIT CARD 25% DISCOUNT OFFER");
    }

    @Test
    public void SearchOffersFailed() throws IOException{
        MyReport.createTest("Verify Searching Offers-Positive");
        HomePage homePage = new HomePage(driver);
        homePage.selectCity("Chennai");
        driver.findElement(By.xpath("//*[@id=\"super-container\"]//div[2]/div/a[3]")).click();
        homePage.selectOfferMenu("Offer");
        driver.findElement(By.xpath("//input[@id='ajax-typeahead']")).sendKeys("Kotak Mahindra Offer");
        homePage.verifyOfferSelectedFail();

    }
    @Test
    public void validateMainMenu() throws IOException{
        MyReport.createTest("Validate Home Page Menu");
        HomePage homePage = new HomePage(driver);
        List<WebElement> actualMenu=driver.findElements(By.xpath("//div/a[@class='sc-iGrrsa FYYCw']"));
        //int menuSize =actualMenu.size();
        for(WebElement menuValue:actualMenu) {
           homePage.verifyHomeMainMenu(menuValue);
        }
    }
    @Test
    public void checkFooterText() throws IOException {
        MyReport.createTest("Validate Home Page Menu");
        HomePage homePage = new HomePage(driver);
        homePage.selectCity("Chennai");
        String footerText="The content and images used on this site are copyright protected and copyrights vests " +
                "with the respective owners. The usage of the content and images on this website is intended to " +
                "promote the works and no endorsement of the artist shall be implied. " +
                "Unauthorized use is prohibited and punishable by law.";
        // Javascript executor
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        homePage.verifyFooterText(footerText);
    }
    @Test
    public void listWhatToHost() throws IOException{
        MyReport.createTest("Validate List Show-What To Host");
        HomePage homePage = new HomePage(driver);
        homePage.selectCity("Chennai");
        homePage.selectListShow();
        List<WebElement> listHost=driver.findElements(By.xpath("//div[@class='__box']//div[@class='__txt']"));
        for(WebElement hostname:listHost)
        {
            homePage.listWhatToHost(hostname);
        }

    }
    @Test
    public void listServicesOffered() throws IOException{
        MyReport.createTest("Validate List Show-What Services Offered");
        HomePage homePage = new HomePage(driver);
        homePage.selectCity("Chennai");
        homePage.selectListShow();
        List<WebElement> listService=driver.findElements(By.xpath("//div[@class='__box __card-color']//div[@class='__txt']"));
        for(WebElement servicename:listService)
        {
            homePage.listWhatToHost(servicename);
        }

    }

    @AfterMethod
    public void closeBrowser(){
        BrowserResume.closeBrowser(driver);
    }

}