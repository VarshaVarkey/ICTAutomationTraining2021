import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
public class MovieCastCrew extends BasePage{
    private WebDriver driver;
    public MovieCastCrew(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    private By imgChennaiCity=By.xpath("//img[@alt='CHEN']");
    private By searchKey=By.xpath("//ul/li//span[@class='sc-bqjOQT hUgGbt']");
    private By actorCast =By.xpath("//*[@id='component-3']/section/div[2]/div/a[1]/h5[1]");
    private By musicCrew=By.xpath("//*[@id='component-4']/section/div[2]/div/a[3]/h5[1]");
    public void searchaMovie(String movieName) throws IOException{

        click(searchKey);
        MyReport.extentTest.log(Status.INFO,"Selected City "+movieName);
    }

    public void selectCity(String cityName){
        click(imgChennaiCity);
        MyReport.extentTest.log(Status.INFO,"Selected City "+cityName);

    }
    public void verifyCastCrew(String actor,String music) throws IOException {
        String actualActor=getText(actorCast);
        String actualMusic=getText(musicCrew);
        SoftAssert softAssertion= new SoftAssert();
        //todo generalise below statement

        try{
            softAssertion.assertEquals(actualActor,actor,"Invalid Search for the actor"+actualActor);
            MyReport.extentTest.log(Status.PASS,"Invalid Search for the actor "+actor, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            softAssertion.assertEquals(actualMusic,music,"Invalid search for the musician"+actualMusic);
            MyReport.extentTest.log(Status.PASS,"Invalid Search for the actor "+music, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
        //todo use listeners to capture failure
        catch (AssertionError ae){

            //MyReport.extentTest.log(Status.FAIL,"Actor Name not matching, actual city is "+actualActor,MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }

    }
}
