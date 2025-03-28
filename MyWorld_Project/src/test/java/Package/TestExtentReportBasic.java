package Package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestExtentReportBasic extends ReportClass {
    public static WebDriver driver1=null;


    //@Test
    public void testPassed() throws IOException, InterruptedException {
        extentTest = extentReports.createTest("Test Case 1", "This test case has passed");
     //   Assert.assertTrue(true);
        WebDriverManager.firefoxdriver().setup();
        driver1 = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        Actions builder = new Actions(driver1);

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver1.manage().window().maximize();
        captureScreenshot(driver1);
        Thread.sleep(3000);
    }

    //@Test
    public void testFailed() throws IOException, InterruptedException {
        extentTest = extentReports.createTest("Test Case 2", "This test case has failed");
    //    Assert.assertTrue(false);
        WebDriverManager.firefoxdriver().setup();
        driver1 = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        Actions builder = new Actions(driver1);

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver1.manage().window().maximize();
        captureScreenshot(driver1);
        Thread.sleep(3000);
    }

    //@Test
    public void testSkipped() throws IOException, InterruptedException {
        extentTest = extentReports.createTest("Test Case 3", "This test case has been skipped");
      //  throw new SkipException("The test has been skipped");
        WebDriverManager.firefoxdriver().setup();
        driver1 = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        Actions builder = new Actions(driver1);

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver1.manage().window().maximize();
        captureScreenshot(driver1);
        Thread.sleep(3000);
    }
}