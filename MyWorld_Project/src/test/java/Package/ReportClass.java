package Package;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;
import org.apache.poi.util.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Random;

public class ReportClass {
    public static ThreadLocal<ExtentTest> extentTestThread=new ThreadLocal<>();
    public static ExtentSparkReporter extentSparkReporter;
    public static ExtentReports extentReports;
    public  static ExtentTest extentTest;
        @BeforeSuite
        public void startReporter() {
          //  extentSparkReporter = new ExtentSparkReporter("C://Users//SambaranChatterjee//IdeaProjects//MyWorld_Project//extentReport123.html");
            extentReports = new ExtentReports();
            extentSparkReporter  = new ExtentSparkReporter(System.getProperty("user.dir") + "./test-output/extentReport.html");
        //    extentSparkReporter  = new ExtentSparkReporter(".Reports/AutomationReport.html");

            extentReports.attachReporter(extentSparkReporter);


            //configuration items to change the look and feel
            //add content, manage tests etc
            extentSparkReporter.config().setDocumentTitle("Automation Report");
            extentSparkReporter.config().setReportName("MyWorld Automation Report");
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        //    extentSparkReporter.config().setAutoCreateRelativePathMedia(true);
        }

        @AfterMethod
        public void getResult(ITestResult result) {
            if (result.getStatus() == ITestResult.FAILURE) {

                extentTestThread.get().log(Status.FAIL, result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {

                extentTestThread.get().log(Status.PASS, result.getTestName());
            } else {

                extentTestThread.get().log(Status.SKIP, result.getTestName());
            }
        }
    public void captureScreenshot(WebDriver driver)
    {
        try
        {

            String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots";
            //     String screenshotPath = "./test-output/screenshots";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            System.out.println("Adding screenshot to extent report");
            String screenshotName = "screenshot_" + new Random().nextInt(999) ;
            screenshotPath = screenshotPath + File.separator + screenshotName;
            Files.copy(screenshot, new File(screenshotPath));
            //  extentTest.addScreenCaptureFromPath(screenshotPath);
            InputStream is = new FileInputStream(screenshotPath);
            byte[] ss= IOUtils.toByteArray(is);
            String base64= Base64.getEncoder().encodeToString(ss);
       //     extentTestThread.get().addScreenCaptureFromPath(screenshotPath);
            extentTestThread.get().addScreenCaptureFromBase64String("data:image/png;base64,"+base64);

        } catch (IOException  e)
        {
            e.printStackTrace();
        }
    }
    @AfterSuite
    public void tearDown() {
        //to write or update test information to the reporter
        System.out.println("Executed after suite");
        extentReports.flush();
    }
    }
