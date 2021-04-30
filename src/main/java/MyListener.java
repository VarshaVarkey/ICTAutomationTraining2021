import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class MyListener implements ITestListener{
    String filePath = "D:\\SCREENSHOTS\\Screenshot";
    WebDriver driver=null;

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult result)
    {
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        System.out.println("***** Success*****");
        System.out.println("*****"+result.getName()+" test has passed *****");
        String methodName=result.getName().toString().trim();
        ITestContext context = result.getTestContext();
        takeScreenShot(methodName, driver);

    }

   @Override
    public void onTestFailure(ITestResult result)
    {
        try {
            driver= (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        System.out.println("***** Error*****");
        System.out.println("*****"+result.getName()+" test has failed *****");
        String methodName=result.getName().toString().trim();
        ITestContext context = result.getTestContext();
        takeScreenShot(methodName, driver);
    }
    public void takeScreenShot(String methodName, WebDriver driver) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name
        try {
            FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
            System.out.println("***Placed screen shot in "+filePath+" ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onFinish(ITestContext Result)
    {

    }

    @Override
    public void onStart(ITestContext Result)
    {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
    {

    }

    // When Test case get failed, this method is called.


    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result)
    {

    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {

    }




}
