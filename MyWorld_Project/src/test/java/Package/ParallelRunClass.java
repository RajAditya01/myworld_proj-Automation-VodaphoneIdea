package Package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;

public class ParallelRunClass extends ReportClass {

    public static WebDriver driver1=null;
    public static WebDriver driver2=null;
    public static WebDriver driver3=null;
    public static WebDriver driver4=null;
    public static WebDriver driver5=null;
    public static WebDriver driver6=null;
    public static WebDriver driver7=null;
    public static WebDriver driver8=null;
    public static WebDriver driver9=null;
    public static WebDriver driver10=null;
    public static WebDriver driver11=null;
    public static WebDriver driver12=null;
    public static WebDriver driver13=null;
    public static WebDriver driver14=null;
    public static WebDriver driver15=null;
    public static WebDriver driver16=null;
    public static WebDriver driver17=null;
    public static WebDriver driver18=null;
    public static WebDriver driver19=null;
    public static WebDriver driver20=null;
    public static WebDriver driver21=null;
    public static WebDriver driver22=null;
    public static WebDriver driver23=null;
    public static WebDriver driver24=null;
    public static WebDriver driver25=null;
    public static WebDriver driver26=null;
    public static WebDriver driver27=null;
    public static WebDriver driver28=null;
    public static WebDriver driver29=null;
    public static WebDriver driver30=null;
    public static WebDriver driver31=null;

    private static boolean isAlertPresent(WebDriver driver, int timeout) {
        try {
            Alert a = new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.alertIsPresent());
            if (a != null) {
                return true;
            } else {
                throw new TimeoutException();
            }
        } catch (TimeoutException e) {
            // log the exception;
            return false;
        }
    }
    private static void resolveAlert(WebDriver driver, boolean accept) {
        if (accept) {
            driver.switchTo().alert().accept();
        } else {
            driver.switchTo().alert().dismiss();
        }
    }
    public void takeScreenShot(String filePath,WebDriver driver) throws IOException {
        TakesScreenshot yourScreenshot = (TakesScreenshot) driver;
        File tempfile = yourScreenshot.getScreenshotAs(OutputType.FILE);
        File screenshotImage = new File(filePath);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        FileUtils.moveFile(tempfile, screenshotImage);
    }
    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static void resolveAllAlerts(WebDriver driver, int timeout, boolean accept) {
        while (isAlertPresent(driver, timeout)) {
            resolveAlert(driver, accept);
        }
    }
    public String captureScreen(WebDriver driver) throws IOException {
        TakesScreenshot screen = (TakesScreenshot) driver1;
        File src = screen.getScreenshotAs(OutputType.FILE);
        String dest = "C://MyWorldAutomation//MyWorldScreenshots//Downloads//" + getcurrentdateandtime() + ".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);
        return dest;
    }
    public String getcurrentdateandtime(){
        String str = null;
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str= dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        return str;
    }


//@Test (priority = 1)
    public void TransferLetter() throws InterruptedException, IOException {

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        FileInputStream filepath = new FileInputStream(prop.getProperty("MyWorld_InputExcel"));
        Workbook fileworkbook = WorkbookFactory.create(filepath);
        Sheet filesheet = fileworkbook.getSheet("Sheet1");
        int sheetrowcount = filesheet.getLastRowNum();
        String  TestCasesID = filesheet.getRow(1).getCell(0).getStringCellValue();
        String  TestDescription = filesheet.getRow(1).getCell(4).getStringCellValue();
  //  extentTest = extentReports.createTest("Test Case 1", "Verify Transfer Letter");
        extentTest = extentReports.createTest(TestCasesID, TestDescription);

        extentTestThread.set(extentTest);
        System.out.println("Transfer letter added");


        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver1 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
        Actions builder = new Actions(driver1);

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver1.manage().window().maximize();
        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + "TransferLetter"
      //          + ".png",driver1);
         driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver1));

    captureScreenshot(driver1);
         driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        ParallelRunClass.resolveAllAlerts(driver1, 10, false);
        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver1));

    captureScreenshot(driver1);
        driver1.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.findElement(By.xpath("//a[@id='386']/div")).click();
        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.findElement(By.xpath("//a[contains(text(),'View Letter')]")).click();
        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver1));

  //  extentTestThread.get().log(Status.PASS,"View letter");
    captureScreenshot(driver1);
        driver1.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver1.close();
    }
//@Test (priority = 2)
    public void AppointmentLetter() throws InterruptedException, IOException {
    extentTest = extentReports.createTest("Test Case 2", "Verify Appointment Letter");
    extentTestThread.set(extentTest);
    System.out.println("appointment letter added");
    File configFile2 = new File("config1.properties");
        InputStream inputStream2 = new FileInputStream(configFile2);
        Properties prop2 = new Properties();
        prop2.load(inputStream2);
        WebDriverManager.chromedriver().setup();
        driver2 = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver2, Duration.ofSeconds(10));
        Actions builder = new Actions(driver2);
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver2.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver2.manage().window().maximize();
        driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver2.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement password1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password1.sendKeys(prop2.getProperty("Password"));

        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit2.click();

        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        ParallelRunClass.resolveAllAlerts(driver2, 10, false);

        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
         //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver2));
    captureScreenshot(driver2);
   // extentTestThread.get().log(Status.PASS,"User opened Homepage");
         driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver2.findElement(By.xpath("//*[@id=\"4\"]/b")).click();
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver2));
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver2.findElement(By.xpath("//a[@id='383']/div")).click();
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver2.findElement(By.xpath("//a[contains(text(),'View Letter')]")).click();
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver2));

    captureScreenshot(driver2);
        driver2.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver2.close();
    }
    //@Test (priority = 3)
    public void PendingApprovals() throws InterruptedException, IOException {
    extentTest = extentReports.createTest("Test Case 3", "Verify Pending approvals");
    extentTestThread.set(extentTest);

    File configFile3 = new File("config1.properties");
        InputStream inputStream3 = new FileInputStream(configFile3);
        Properties prop3 = new Properties();
        prop3.load(inputStream3);
        WebDriverManager.chromedriver().setup();
        driver3 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver3, Duration.ofSeconds(10));
        // Create the Actions object
        Actions builder = new Actions(driver3);
        driver3.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver3.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver3.manage().window().maximize();
        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver3.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver3.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement password3 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password3.sendKeys(prop3.getProperty("Password"));

        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Thread.sleep(2000);
   //     takeScreenShot(prop3.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + "TransferLetter"
   //             + ".png",driver3);
        WebElement Submit3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit3.click();

        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        ParallelRunClass.resolveAllAlerts(driver3, 10, false);
        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver3));
        captureScreenshot(driver3);
        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver3.findElement(By.xpath("//*[@id=\"4\"]/b")).click();
        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    //    takeScreenShot(prop3.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + "PendingApprovals"+ ".png",driver3);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver3));
    captureScreenshot(driver3);
        Thread.sleep(2000);
        driver3.findElement(By.xpath("//a[@id='140']/div")).click();
        driver3.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver3;
        js.executeScript("window.scrollBy(0,250)", "");

        Thread.sleep(3000);

        JavascriptExecutor js1 = (JavascriptExecutor) driver3;
        js1.executeScript("window.scrollBy(0,350)", "");
     captureScreenshot(driver3);
        driver3.close();
    }
    
//@Test (priority = 4)
    public void GenPDFLeavingUs() throws InterruptedException, IOException {

    extentTest = extentReports.createTest("Test Case 4", "Verify Generate PDF Leaving us");
    extentTestThread.set(extentTest);
    File configFile4 = new File("config1.properties");
        InputStream inputStream4 = new FileInputStream(configFile4);
        Properties prop4 = new Properties();
        prop4.load(inputStream4);
    WebDriverManager.chromedriver().setup();
    driver4 = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver4, Duration.ofSeconds(10));
        Actions builder = new Actions(driver4);
        driver4.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver4.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver4.manage().window().maximize();
        driver4.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver4.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver4.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement password4 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password4.sendKeys(prop4.getProperty("Password"));

        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

   //     takeScreenShot(prop4.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + "TransferLetter"
   //             + ".png",driver4);

    Thread.sleep(2000);
        WebElement Submit4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit4.click();


        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        ParallelRunClass.resolveAllAlerts(driver4, 10, false);
        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver4));
        captureScreenshot(driver4);
        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver4.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
 //       takeScreenShot(prop4.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + "GenPDFLeavingUs"
 //               + ".png",driver4);
    captureScreenshot(driver4);
    Thread.sleep(2000);
        driver4.findElement(By.xpath("//a[@id='30']/div")).click();
        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver4));
        captureScreenshot(driver4);
        driver4.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver4.close();
    }
//@Test (priority = 5)
    public void  LoginValidation() throws Exception {
    extentTest = extentReports.createTest("Test Case 5", "Login Validation");
    extentTestThread.set(extentTest);
        File configFile5 = new File("config1.properties");
        InputStream inputStream5 = new FileInputStream(configFile5);
        Properties prop5 = new Properties();

        prop5.load(inputStream5);

        WebDriverManager.chromedriver().setup();
        driver5 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver5, Duration.ofSeconds(10));
        // Create the Actions object
        Actions builder = new Actions(driver5);
        driver5.navigate().to(prop5.getProperty("AppURL"));
        driver5.manage().window().maximize();
        driver5.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Thread.sleep(2000);

        driver5.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver5.findElement(By.name("txtEmpCode")).sendKeys("s01");

        driver5.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // Enter password

        WebElement password5 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password5.sendKeys("world");

        driver5.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit5.click();

        driver5.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Thread.sleep(2000);
   //     takeScreenShot(prop5.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + "LoginValidation"
   //             + ".png",driver5);
    captureScreenshot(driver5);
        driver5.close();
    }
//@Test (priority = 6)
    public void BandDeginationLetter() throws Exception {
    extentTest = extentReports.createTest("Test Case 6", "Verify band designation");
    extentTestThread.set(extentTest);
        File configFile6 = new File("config1.properties");
        InputStream inputStream6 = new FileInputStream(configFile6);
        Properties prop6 = new Properties();

        prop6.load(inputStream6);

    WebDriverManager.chromedriver().setup();
    driver6 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver6, Duration.ofSeconds(10));

        Actions builder = new Actions(driver6);

        driver6.navigate().to(prop6.getProperty("AppURL"));
        driver6.manage().window().maximize();
        // Enter user name
        driver6.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  takeScreenShot(prop6.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
      //          + "BandDeginationLetter" + ".png",driver6);
        Thread.sleep(2000);

        driver6.findElement(By.name("txtEmpCode")).sendKeys("s01");
        captureScreenshot(driver6);
        Thread.sleep(2000);

        WebElement password6 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password6.sendKeys(prop6.getProperty("Password"));

        driver6.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  takeScreenShot(prop6.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
      //          + "BandDeginationLetter" + ".png",driver6);

        WebElement Submit6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit6.click();


        driver6.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver6, 10, false);

       // driver6.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver6.findElement(By.xpath("//b[contains(.,'Pay and Benefits')]")).click();

        driver6.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver6);

        driver6.close();
    }
//@Test (priority = 7)
    public void ViewMyLetter() throws Exception {
    extentTest = extentReports.createTest("Test Case 7", "Verify view my Letter page");
    extentTestThread.set(extentTest);
        File configFile7 = new File("config1.properties");
        InputStream inputStream7 = new FileInputStream(configFile7);
        Properties prop7 = new Properties();
        prop7.load(inputStream7);
    WebDriverManager.chromedriver().setup();
    driver7 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver7, Duration.ofSeconds(10));

        Actions builder = new Actions(driver7);

        driver7.navigate().to(prop7.getProperty("AppURL"));

        driver7.manage().window().maximize();
        // Enter user name
        driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop7.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "ViewMyLetter" + ".png",driver7);
        captureScreenshot(driver7);
        Thread.sleep(2000);

        driver7.findElement(By.name("txtEmpCode")).sendKeys(prop7.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password7 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password7.sendKeys(prop7.getProperty("Password"));

        driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  takeScreenShot(prop7.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
      //          + "ViewMyLetter" + ".png",driver7);

        WebElement Submit7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit7.click();

        // Click on Login button
       driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver7, 10, false);

        driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver7.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop7.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "ViewMyLetter" + ".png",driver7);
         captureScreenshot(driver7);
        driver7.findElement(By.xpath("//a[@id='347']/div")).click();

        driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop7.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ViewMyLetter" + ".png",driver7);
        captureScreenshot(driver7);
         driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

  //  driver7.findElement(By.xpath("//a[contains(text(),'View My Letter')]")).click();
    driver7.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    WebElement element= driver7.findElement(By.xpath("//a[contains(text(),'View My Letter')]"));
    JavascriptExecutor executor = (JavascriptExecutor)driver7;
    executor.executeScript("arguments[0].click();", element);

    driver7.close();
    }
   //@Test (priority = 8)
    public void AnnualPayLetter() throws Exception {
        extentTest = extentReports.createTest("Test Case 8", "Verify annual pay Letter page");
        extentTestThread.set(extentTest);
        File configFile8 = new File("config1.properties");
        InputStream inputStream8 = new FileInputStream(configFile8);
        Properties prop8 = new Properties();

        prop8.load(inputStream8);

       WebDriverManager.chromedriver().setup();
       driver8 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver8, Duration.ofSeconds(10));

        Actions builder = new Actions(driver8);

        driver8.navigate().to(prop8.getProperty("AppURL"));

        driver8.manage().window().maximize();
        // Enter user name
        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop8.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "ViewMyLetter" + ".png",driver8);
        captureScreenshot(driver8);
        Thread.sleep(1000);

        driver8.findElement(By.name("txtEmpCode")).sendKeys(prop8.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password8 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password8.sendKeys(prop8.getProperty("Password"));

        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit8.click();


        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver8, 10, false);
        // Transfer Letter

        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver8.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver8.findElement(By.xpath("//a[@id='347']/div")).click();

        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

       WebElement element= driver8.findElement(By.linkText("Annual Pay Review letter"));
        JavascriptExecutor executor = (JavascriptExecutor)driver8;
        executor.executeScript("arguments[0].click();", element);


        driver8.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop8.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ViewMyLetter" + ".png",driver8);
        captureScreenshot(driver8);
        Select se9 = new Select(driver8.findElement(By.xpath("//select[@id='year']")));
        se9.selectByIndex(1);

        Thread.sleep(4000);

        driver8.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

        Thread.sleep(8000);

    //    takeScreenShot(prop8.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ViewMyLetter" + ".png",driver8);
        captureScreenshot(driver8);
        Thread.sleep(3000);

        driver8.close();
    }
   //@Test (priority = 9)
    public void AnnualPerformancePay() throws Exception
    {
        extentTest = extentReports.createTest("Test Case 9", "Verify annual performance pay Letter page");
        extentTestThread.set(extentTest);
        File configFile9 = new File("config1.properties");
        InputStream inputStream9 = new FileInputStream(configFile9);
        Properties prop9 = new Properties();

        prop9.load(inputStream9);

        WebDriverManager.chromedriver().setup();
        driver9 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver9, Duration.ofSeconds(10));

        Actions builder = new Actions(driver9);

        driver9.navigate().to(prop9.getProperty("AppURL"));

        driver9.manage().window().maximize();
        // Enter user name
        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop9.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "AnnualPerformancePay" + ".png",driver9);
        captureScreenshot(driver9);
        Thread.sleep(2000);

        driver9.findElement(By.name("txtEmpCode")).sendKeys(prop9.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password9 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password9.sendKeys(prop9.getProperty("Password"));

        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit9 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit9.click();


        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver9, 10, false);

        // Transfer Letter

        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver9.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver9.findElement(By.xpath("//a[@id='347']/div")).click();

        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement element1= driver9.findElement(By.linkText("View Annual Performance Pay"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver9;
        executor1.executeScript("arguments[0].click();", element1);


        driver9.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  takeScreenShot(prop9.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
      //          + "AnnualPerformancePay" + ".png",driver9);
        captureScreenshot(driver9);
        // driver9.findElement(By.id("year")).sendKeys("2018-19");

        Select se9 = new Select(driver9.findElement(By.xpath("//select[@id='year']")));
        se9.selectByIndex(1);

        Thread.sleep(4000);

        driver9.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

        Thread.sleep(8000);

    //    takeScreenShot(prop9.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "AnnualPerformancePay" + ".png",driver9);
        captureScreenshot(driver9);
        Thread.sleep(8000);

        driver9.close();
    }
//@Test (priority = 10)
    public  void AdminViewCompLetter() throws Exception {
    extentTest = extentReports.createTest("Test Case 10", "Verify Admin View Comp Letter page");
    extentTestThread.set(extentTest);
        File configFile10 = new File("config1.properties");
        InputStream inputStream10 = new FileInputStream(configFile10);
        Properties prop10 = new Properties();

        prop10.load(inputStream10);
    WebDriverManager.chromedriver().setup();
    driver10 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver10, Duration.ofSeconds(10));

        Actions builder = new Actions(driver10);

        driver10.navigate().to(prop10.getProperty("AppURL"));

        driver10.manage().window().maximize();
        // Enter user name
        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

   //     takeScreenShot(prop10.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
   //             + "AdminViewCompLetter" + ".png",driver10);
        captureScreenshot(driver10);
        Thread.sleep(2000);

        driver10.findElement(By.name("txtEmpCode")).sendKeys(prop10.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password10 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password10.sendKeys(prop10.getProperty("Password"));

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit10 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit10.click();

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver10, 10, false);

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver10.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver10.findElement(By.xpath("//a[@id='347']/div")).click();

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement element2= driver10.findElement(By.linkText("Admin View Compensation Lett..."));
        JavascriptExecutor executor2 = (JavascriptExecutor)driver10;
        executor2.executeScript("arguments[0].click();", element2);


        Select se1 = new Select(driver10.findElement(By.xpath("//select[@id='letterType']")));
        se1.selectByIndex(1);

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

   //     takeScreenShot(prop10.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
   //             + "AdminViewCompLetter" + ".png",driver10);
        captureScreenshot(driver10);
        driver10.findElement(By.xpath("//textarea[@id='expep']")).sendKeys("sumit");

        driver10.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver10.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

        driver10.close();
    }
//@Test (priority = 11)
    public void ViewMySurvey() throws Exception {
    extentTest = extentReports.createTest("Test Case 11", "Verify View My Survey page");
    extentTestThread.set(extentTest);
        File configFile11 = new File("config1.properties");
        InputStream inputStream11 = new FileInputStream(configFile11);
        Properties prop11 = new Properties();

        prop11.load(inputStream11);

    WebDriverManager.chromedriver().setup();
    driver11= new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver11, Duration.ofSeconds(10));

        Actions builder = new Actions(driver11);

        driver11.navigate().to(prop11.getProperty("AppURL"));

        driver11.manage().window().maximize();
        // Enter user name
        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Thread.sleep(2000);

        driver11.findElement(By.name("txtEmpCode")).sendKeys(prop11.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password11 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password11.sendKeys(prop11.getProperty("Password"));

        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit11 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit11.click();


        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver11, 10, false);

        driver11.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop11.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //       + "ViewMySurvey" + ".png",driver11);
        captureScreenshot(driver11);
        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver11.findElement(By.xpath("//a[@id='269']/div")).click();

        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //    takeScreenShot(prop11.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //       + "ViewMySurvey" + ".png",driver11);
        captureScreenshot(driver11);
        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement element3 = driver11.findElement(By.xpath("//a[contains(text(),'View My Survey')]"));
        JavascriptExecutor executor3 = (JavascriptExecutor)driver11;
        executor3.executeScript("arguments[0].click();", element3);

        driver11.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver11.close();
    }
   //@Test (priority = 12)
    public void ViewPolicey() throws Exception {
        extentTest = extentReports.createTest("Test Case 12", "Verify View policey page");
        extentTestThread.set(extentTest);
        File configFile12 = new File("config1.properties");
        InputStream inputStream12 = new FileInputStream(configFile12);
        Properties prop12 = new Properties();

        prop12.load(inputStream12);

       WebDriverManager.chromedriver().setup();
       driver12 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver12, Duration.ofSeconds(10));

        Actions builder = new Actions(driver12);

        driver12.navigate().to(prop12.getProperty("AppURL"));

        driver12.manage().window().maximize();
        // Enter user name
        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop12.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ViewPolicey" + ".png",driver12);
        captureScreenshot(driver12);
        Thread.sleep(1000);

        driver12.findElement(By.name("txtEmpCode")).sendKeys(prop12.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password12 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password12.sendKeys(prop12.getProperty("Password"));

        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit12 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit12.click();


        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver12, 10, false);

        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver12.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver12.findElement(By.xpath("//a[@id='381']/div")).click();

        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop12.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "ViewPolicey" + ".png",driver12);
        captureScreenshot(driver12);
        // xpath=//a[contains(text(),'View Letter')]

        driver12.findElement(By.xpath("//a[contains(text(),'View Policies')]")).click();

        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop12.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ViewPolicey" + ".png",driver12);
        captureScreenshot(driver12);
        driver12.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver12.close();

    }
//@Test (priority = 13)
    public void GiftHospitality() throws Exception {
    extentTest = extentReports.createTest("Test Case 13", "Verify Gift Hospitality page");
    extentTestThread.set(extentTest);
        File configFile13 = new File("config1.properties");
        InputStream inputStream13 = new FileInputStream(configFile13);
        Properties prop13 = new Properties();

        prop13.load(inputStream13);

    WebDriverManager.chromedriver().setup();
    driver13 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver13, Duration.ofSeconds(10));

        Actions builder = new Actions(driver13);

        driver13.navigate().to(prop13.getProperty("AppURL"));

        driver13.manage().window().maximize();
        // Enter user name
        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop13.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "GiftHospitality" + ".png",driver13);
        captureScreenshot(driver13);
        Thread.sleep(2000);

        driver13.findElement(By.name("txtEmpCode")).sendKeys(prop13.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password13 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password13.sendKeys(prop13.getProperty("Password"));

        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit13 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit13.click();

        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver13, 10, false);

        driver13.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    //    takeScreenShot(prop13.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "GiftHospitality" + ".png",driver13);
        captureScreenshot(driver13);
        driver13.findElement(By.xpath("//a[@id='389']/div")).click();

        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver13.findElement(By.xpath("//a[contains(text(),'Gift and Hospitality Form')]")).click();

        driver13.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop13.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "GiftHospitality" + ".png",driver13);
        captureScreenshot(driver13);
        driver13.close();
    }
//@Test (priority = 14)
    public void OrganizationCharts() throws Exception {
    extentTest = extentReports.createTest("Test Case 14", "Verify OrganizationCharts page");
    extentTestThread.set(extentTest);
        File configFile14 = new File("config1.properties");
        InputStream inputStream14 = new FileInputStream(configFile14);
        Properties prop14 = new Properties();

        prop14.load(inputStream14);

    WebDriverManager.chromedriver().setup();
    driver14 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver14, Duration.ofSeconds(10));

        Actions builder = new Actions(driver14);

        driver14.navigate().to(prop14.getProperty("AppURL"));

        driver14.manage().window().maximize();
        // Enter user name
        driver14.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop14.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "OrganizationCharts" + ".png",driver14);
        captureScreenshot(driver14);
        Thread.sleep(2000);

        driver14.findElement(By.name("txtEmpCode")).sendKeys(prop14.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password14 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password14.sendKeys(prop14.getProperty("Password"));

        driver14.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit14 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit14.click();

        driver14.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver14, 10, false);

        driver14.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop14.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "OrganizationCharts" + ".png",driver14);
         captureScreenshot(driver14);
        driver14.findElement(By.linkText("Policies and FAQs")).click();

        driver14.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver14.findElement(By.xpath("//a[@id='382']/div")).click();

        Thread.sleep(1000);

        WebElement element14 = driver14.findElement(By.xpath("//a[contains(text(),'View Organization Charts')]"));
        JavascriptExecutor executor14 = (JavascriptExecutor)driver14;
        executor14.executeScript("arguments[0].click();", element14);

        driver14.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop14.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "OrganizationCharts" + ".png",driver14);
         captureScreenshot(driver14);
        Thread.sleep(2000);

        driver14.findElement(By.xpath("//font[contains(.,'Download')]")).click();

        Thread.sleep(2000);

   //     takeScreenShot(prop14.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
   //             + "OrganizationCharts" + ".png",driver14);
        captureScreenshot(driver14);
        driver14.close();

    }
//@Test (priority = 15)
    public void ITPolicyReport1() throws Exception {
    extentTest = extentReports.createTest("Test Case 15", "Verify IT Policy Report page");
    extentTestThread.set(extentTest);
        File configFile15 = new File("config1.properties");
        InputStream inputStream15 = new FileInputStream(configFile15);
        Properties prop15 = new Properties();

        prop15.load(inputStream15);

    WebDriverManager.chromedriver().setup();
    driver15 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver15, Duration.ofSeconds(10));

        Actions builder = new Actions(driver15);

        driver15.navigate().to(prop15.getProperty("AppURL"));

        driver15.manage().window().maximize();
        // Enter user name
        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  takeScreenShot(prop15.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
      //          + "ITPolicyReport1" + ".png",driver15);
        captureScreenshot(driver15);
        Thread.sleep(2000);

        driver15.findElement(By.name("txtEmpCode")).sendKeys(prop15.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password15 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password15.sendKeys(prop15.getProperty("Password"));

        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit15 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit15.click();

        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver15, 10, false);

        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver15.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop15.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ITPolicyReport1" + ".png",driver15);
        captureScreenshot(driver15);
        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver15.findElement(By.xpath("//a[@id='362']/div")).click();
        Thread.sleep(1000);
        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop15.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "ITPolicyReport1" + ".png",driver15);
         captureScreenshot(driver15);

   //     driver15.findElement(By.xpath("//a[contains(text(),'Generate IT Policy Report')]")).click();

        WebElement element15 = driver15.findElement(By.xpath("//a[contains(text(),'Generate IT Policy Report')]"));
        JavascriptExecutor executor15 = (JavascriptExecutor)driver15;
        executor15.executeScript("arguments[0].click();", element15);

        driver15.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
    //     takeScreenShot(prop15.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //        + "ITPolicyReport1" + ".png",driver15);
         captureScreenshot(driver15);
        driver15.close();
    }
   //@Test (priority = 16)
    public void HSWConsequenceManagementMatrixPolicyReport() throws Exception {
        extentTest = extentReports.createTest("Test Case 16", "Verify SWConsequence Management Matrix Policy Report page");
        extentTestThread.set(extentTest);
        File configFile16 = new File("config1.properties");
        InputStream inputStream16 = new FileInputStream(configFile16);
        Properties prop16 = new Properties();

        prop16.load(inputStream16);

       WebDriverManager.chromedriver().setup();
       driver16 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver16, Duration.ofSeconds(10));

        Actions builder = new Actions(driver16);

        driver16.navigate().to(prop16.getProperty("AppURL"));

        driver16.manage().window().maximize();
        // Enter user name
        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop16.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "HSWConsequence" + ".png",driver16);
        captureScreenshot(driver16);
        Thread.sleep(2000);

        driver16.findElement(By.name("txtEmpCode")).sendKeys(prop16.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password16 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password16.sendKeys(prop16.getProperty("Password"));

        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit16 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit16.click();

        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver16, 10, false);

        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver16.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


    //    takeScreenShot(prop16.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "HSWConsequence" + ".png",driver16);
        captureScreenshot(driver16);
        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver16.findElement(By.xpath("//a[@id='362']/div")).click();

        driver16.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

 //       takeScreenShot(prop16.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "HSWConsequence" + ".png",driver16);
        captureScreenshot(driver16);
        WebElement element16 = driver16.findElement(By.xpath("//a[contains(text(),'Generate HSW Consequence Man...')]"));
        JavascriptExecutor executor16 = (JavascriptExecutor)driver16;
        executor16.executeScript("arguments[0].click();", element16);

        driver16.close();
    }
//@Test (priority = 17)
    public void CarrerPerformanceLetter() throws Exception {
    extentTest = extentReports.createTest("Test Case 17", "Verify SWConsequence Management Matrix Policy Report page");
    extentTestThread.set(extentTest);
        File configFile17 = new File("config1.properties");
        InputStream inputStream17 = new FileInputStream(configFile17);
        Properties prop17 = new Properties();

        prop17.load(inputStream17);

    WebDriverManager.chromedriver().setup();
    driver17 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver17, Duration.ofSeconds(10));

        Actions builder = new Actions(driver17);
        driver17.navigate().to(prop17.getProperty("AppURL"));

        driver17.manage().window().maximize();
        // Enter user name
        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

 //       takeScreenShot(prop17.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "CarrerPerformanceLetter" + ".png",driver17);
        captureScreenshot(driver17);
        Thread.sleep(1000);

        driver17.findElement(By.name("txtEmpCode")).sendKeys(prop17.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password17 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password17.sendKeys(prop17.getProperty("Password"));

        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit17 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit17.click();

        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver17, 10, false);

        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver17.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

     //   takeScreenShot(prop17.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "CarrerPerformanceLetter" + ".png",driver17);
         captureScreenshot(driver17);
        Thread.sleep(1000);

        driver17.findElement(By.xpath("//a[@id='374']/div")).click();

        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//        takeScreenShot(prop17.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "CarrerPerformanceLetter" + ".png",driver17);
        captureScreenshot(driver17);
        JavascriptExecutor js = (JavascriptExecutor) driver17;
        js.executeScript("window.scrollBy(0,1005)");
        driver17.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(7000);

//        takeScreenShot(prop17.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "CarrerPerformanceLetter" + ".png",driver17);
        captureScreenshot(driver17);
        driver17.close();
    }
//@Test (priority = 18)
    public void PayAndBenefits() throws Exception {
    extentTest = extentReports.createTest("Test Case 18", "Verify Pay And Benefits Report page");
    extentTestThread.set(extentTest);
        File configFile18 = new File("config1.properties");
        InputStream inputStream18 = new FileInputStream(configFile18);
        Properties prop18 = new Properties();

        prop18.load(inputStream18);

    WebDriverManager.chromedriver().setup();
    driver18 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver18, Duration.ofSeconds(10));

        Actions builder = new Actions(driver18);

        driver18.navigate().to(prop18.getProperty("AppURL"));

        driver18.manage().window().maximize();
        // Enter user name
        driver18.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

 //       takeScreenShot(prop18.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "PayAndBenefits" + ".png",driver18);
        captureScreenshot(driver18);
        Thread.sleep(1000);

        driver18.findElement(By.name("txtEmpCode")).sendKeys(prop18.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password18 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password18.sendKeys(prop18.getProperty("Password"));

        driver18.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit18 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit18.click();


        driver18.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver18, 10, false);

        driver18.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver18.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

        driver18.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        takeScreenShot(prop18.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "PayAndBenefits" + ".png",driver18);
        captureScreenshot(driver18);
        driver18.findElement(By.xpath("//a[@id='373']/div")).click();

        driver18.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(7000);
//        takeScreenShot(prop18.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "PayAndBenefits" + ".png",driver18);
        captureScreenshot(driver18);
        driver18.close();

    }
//@Test (priority = 19)
    public void BusinessTravel() throws Exception {
    extentTest = extentReports.createTest("Test Case 19", "Verify Business Travel page");
    extentTestThread.set(extentTest);
        File configFile19 = new File("config1.properties");
        InputStream inputStream19 = new FileInputStream(configFile19);
        Properties prop19 = new Properties();

        prop19.load(inputStream19);

    WebDriverManager.chromedriver().setup();
    driver19 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver19, Duration.ofSeconds(10));

        Actions builder = new Actions(driver19);

        driver19.navigate().to(prop19.getProperty("AppURL"));

        driver19.manage().window().maximize();
        // Enter user name
        driver19.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        takeScreenShot(prop19.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "BusinessTravel" + ".png",driver19);
        captureScreenshot(driver19);
        Thread.sleep(1000);

        driver19.findElement(By.name("txtEmpCode")).sendKeys(prop19.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password19 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password19.sendKeys(prop19.getProperty("Password"));

        driver19.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit19 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit19.click();

        driver19.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver19, 10, false);

        driver19.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver19.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

        driver19.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        takeScreenShot(prop19.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "BusinessTravel" + ".png",driver19);
        captureScreenshot(driver19);
        driver19.findElement(By.xpath("//a[@id='372']/div")).click();

        driver19.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(7000);
//        takeScreenShot(prop19.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "BusinessTravel" + ".png",driver19);
        captureScreenshot(driver19);
        driver19.close();
    }
   //@Test (priority = 20)
    public void DomesticTicketDetails() throws Exception {
        extentTest = extentReports.createTest("Test Case 20", "Verify Domestic Ticket Details page");
        extentTestThread.set(extentTest);
        File configFile20 = new File("config1.properties");
        InputStream inputStream20 = new FileInputStream(configFile20);
        Properties prop20 = new Properties();

        prop20.load(inputStream20);

       WebDriverManager.chromedriver().setup();
       driver20 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver20, Duration.ofSeconds(10));

        Actions builder = new Actions(driver20);

        driver20.navigate().to(prop20.getProperty("AppURL"));

        driver20.manage().window().maximize();
        // Enter user name
        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        takeScreenShot(prop20.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "DomesticTicketDetails" + ".png",driver20);
        captureScreenshot(driver20);
        Thread.sleep(2000);
        driver20.findElement(By.name("txtEmpCode")).sendKeys(prop20.getProperty("Admin"));
        Thread.sleep(2000);

        WebElement password20 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password20.sendKeys(prop20.getProperty("Password"));

        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit20 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit20.click();

        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver20, 10, false);

        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver20.findElement(By.xpath("//b[contains(.,'Business Travel')]")).click();

        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver20.findElement(By.linkText("Foreign Travel")).click();

        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        takeScreenShot(prop20.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "DomesticTicketDetails" + ".png",driver20);
        captureScreenshot(driver20);
        // xpath=//a[contains(text(),'View Letter')]

        driver20.findElement(By.xpath("//a[contains(text(),'View Ticket Details')]")).click();

        driver20.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        takeScreenShot(prop20.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "DomesticTicketDetails" + ".png",driver20);
        captureScreenshot(driver20);
        driver20.close();
    }
   //@Test (priority = 21)
    public void MyWork1() throws Exception {
        extentTest = extentReports.createTest("Test Case 21", "Verify download transition letter");
        extentTestThread.set(extentTest);
        File configFile21 = new File("config1.properties");
        InputStream inputStream21 = new FileInputStream(configFile21);
        Properties prop21 = new Properties();

        prop21.load(inputStream21);

       WebDriverManager.chromedriver().setup();
       driver21 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver21, Duration.ofSeconds(10));

        Actions builder = new Actions(driver21);

        driver21.navigate().to(prop21.getProperty("AppURL"));

        driver21.manage().window().maximize();
        // Enter user name
        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        takeScreenShot(prop21.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "MyWork1" + ".png",driver21);
        captureScreenshot(driver21);
        Thread.sleep(2000);

        driver21.findElement(By.name("txtEmpCode")).sendKeys(prop21.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password21 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password21.sendKeys(prop21.getProperty("Password"));

        driver21.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit21 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit21.click();

        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver21, 10, false);

        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver21.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        takeScreenShot(prop21.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "MyWork1" + ".png",driver21);
        captureScreenshot(driver21);
        driver21.findElement(By.linkText("My Rewards")).click();

        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(7000);
//        takeScreenShot(prop21.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "MyWork1" + ".png",driver21);
        captureScreenshot(driver21);
        WebElement element21 = driver21.findElement(By.xpath("//a[@title='Admin View Compensation Letter']"));
        JavascriptExecutor executor21 = (JavascriptExecutor)driver21;
        executor21.executeScript("arguments[0].click();", element21);

        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver21.findElement(By.id("expep")).sendKeys("sumit");

        driver21.findElement(By.xpath("//select[@id='letterType']")).click();

        driver21.findElement(By.xpath("//td/select/option[@value='Transition']")).click();

        driver21.findElement(By.xpath("//a[normalize-space()='Download']")).click();

        driver21.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        takeScreenShot(prop21.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "MyWork1" + ".png",driver21);
        captureScreenshot(driver21);
        Thread.sleep(2000);
        // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

        driver21.close();
    }
   //@Test (priority = 22)
    public void MyWork2() throws Exception {
        extentTest = extentReports.createTest("Test Case 22", "Verify download Promotion letter");
        extentTestThread.set(extentTest);
        File configFile22 = new File("config1.properties");
        InputStream inputStream22 = new FileInputStream(configFile22);
        Properties prop22 = new Properties();

        prop22.load(inputStream22);

       WebDriverManager.chromedriver().setup();
       driver22 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver22, Duration.ofSeconds(10));

        Actions builder = new Actions(driver22);

        driver22.navigate().to(prop22.getProperty("AppURL"));

        driver22.manage().window().maximize();
        // Enter user name
        driver22.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop22.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork2" + ".png",driver22);
        captureScreenshot(driver22);
        Thread.sleep(1000);

        driver22.findElement(By.name("txtEmpCode")).sendKeys(prop22.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password22 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password22.sendKeys(prop22.getProperty("Password"));

        driver22.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit22 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit22.click();


        driver22.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver22, 10, false);

        driver22.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver22.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver22.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop22.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork2" + ".png",driver22);
        captureScreenshot(driver22);
        driver22.findElement(By.linkText("My Rewards")).click();

        driver22.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(7000);
 //       takeScreenShot(prop22.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork2" + ".png",driver22);
        captureScreenshot(driver22);
        WebElement element22 = driver22.findElement(By.xpath("//a[@title='Admin View Compensation Letter']"));
        JavascriptExecutor executor22 = (JavascriptExecutor)driver22;
        executor22.executeScript("arguments[0].click();", element22);

        driver22.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver22.findElement(By.id("expep")).sendKeys("sumit");
        driver22.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver22.findElement(By.xpath("//select[@id='letterType']")).click();

        driver22.findElement(By.xpath("//td/select/option[@value='Promotion']")).click();

        driver22.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver22.findElement(By.xpath("//div[@id='promotionCycle']")).click();

        //driver22.findElement(By.xpath("//select[@id='promocycle']")).click();

        driver22.findElement(By.xpath("//option[@value=\"Feb'23\"]")).click();

        driver22.findElement(By.xpath("//a[normalize-space()='Download']")).click();

 //       takeScreenShot(prop22.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork2" + ".png",driver22);
        captureScreenshot(driver22);
        driver22.close();
    }
//@Test (priority = 23)
    public void MyWork3() throws Exception {
    extentTest = extentReports.createTest("Test Case 23", "Verify download Compensation letter");
    extentTestThread.set(extentTest);
        File configFile23 = new File("config1.properties");
        InputStream inputStream23 = new FileInputStream(configFile23);
        Properties prop23 = new Properties();

        prop23.load(inputStream23);
        WebDriverManager.chromedriver().setup();
        driver23 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver23, Duration.ofSeconds(10));

        Actions builder = new Actions(driver23);

        driver23.navigate().to(prop23.getProperty("AppURL"));

        driver23.manage().window().maximize();
        // Enter user name
        driver23.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop23.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork3" + ".png",driver23);
        captureScreenshot(driver23);
        Thread.sleep(1000);

        driver23.findElement(By.name("txtEmpCode")).sendKeys(prop23.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password23 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password23.sendKeys(prop23.getProperty("Password"));

        driver23.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebElement Submit23 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit23.click();


        driver23.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver23, 10, false);

        driver23.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver23.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver23.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop23.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork3" + ".png",driver23);
         captureScreenshot(driver23);
        driver23.findElement(By.linkText("My Rewards")).click();

        driver23.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(7000);
 //       takeScreenShot(prop23.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork3" + ".png",driver23);
        captureScreenshot(driver23);
      //  driver23.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

        WebElement element23 = driver23.findElement(By.xpath("//a[@title='Admin View Compensation Letter']"));
        JavascriptExecutor executor23 = (JavascriptExecutor)driver23;
        executor23.executeScript("arguments[0].click();", element23);

        driver23.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver23.findElement(By.id("expep")).sendKeys("sumit");

        driver23.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        driver23.findElement(By.xpath("//select[@id='letterType']")).click();

        driver23.findElement(By.xpath("//option[@value='Compensation']")).click();

        driver23.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver23.findElement(By.xpath("//select[@id='compcycle']")).click();

        //driver23.findElement(By.xpath("//select[@id='promocycle']")).click();

        driver23.findElement(By.xpath("//option[@value='2021-22']")).click();

        driver23.findElement(By.xpath("//a[normalize-space()='Download']")).click();

 //       takeScreenShot(prop23.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork3" + ".png",driver23);
        captureScreenshot(driver23);
        driver23.close();
    }
//@Test (priority = 24)

public void MyWork4() throws Exception {
    extentTest = extentReports.createTest("Test Case 24", "Verify download Compensation letter");
    extentTestThread.set(extentTest);
    File configFile24 = new File("config1.properties");
    InputStream inputStream24 = new FileInputStream(configFile24);
    Properties prop24 = new Properties();

    prop24.load(inputStream24);
    WebDriverManager.chromedriver().setup();
    driver24 = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver24, Duration.ofSeconds(10));
    Actions builder = new Actions(driver24);

    driver24.navigate().to(prop24.getProperty("AppURL"));

    driver24.manage().window().maximize();
    // Enter user name
    driver24.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

  //  takeScreenShot(prop24.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
  //          + "MyWork4" + ".png",driver24);
    captureScreenshot(driver24);
    Thread.sleep(1000);

    driver24.findElement(By.name("txtEmpCode")).sendKeys(prop24.getProperty("Admin"));

    Thread.sleep(1000);

    WebElement password24 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
    password24.sendKeys(prop24.getProperty("Password"));

    driver24.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    WebElement Submit24 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
    Submit24.click();

    driver24.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    // for accepting the alerts
    ParallelRunClass.resolveAllAlerts(driver24, 10, false);

    driver24.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver24.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

    driver24.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //   takeScreenShot(prop24.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "MyWork4" + ".png",driver24);
    captureScreenshot(driver24);
    driver24.findElement(By.linkText("My Rewards")).click();

    driver24.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    Thread.sleep(7000);
 //   takeScreenShot(prop24.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "MyWork4" + ".png",driver24);
    captureScreenshot(driver24);
    WebElement element24 = driver24.findElement(By.xpath("//a[@title='Admin View Compensation Letter']"));
    JavascriptExecutor executor24 = (JavascriptExecutor)driver24;
    executor24.executeScript("arguments[0].click();", element24);

    driver24.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver24.findElement(By.id("expep")).sendKeys("sumit");

    driver24.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver24.findElement(By.xpath("//select[@id='letterType']")).click();

    driver24.findElement(By.xpath("//option[@value='Compensation']")).click();

    driver24.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver24.findElement(By.xpath("//select[@id='compcycle']")).click();

    //driver24.findElement(By.xpath("//select[@id='promocycle']")).click();

    driver24.findElement(By.xpath("//option[@value='2021-22']")).click();

  //  takeScreenShot(prop24.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
  //          + "MyWork4" + ".png",driver24);
    captureScreenshot(driver24);
    driver24.findElement(By.xpath("//a[normalize-space()='Download']")).click();

    driver24.close();
}

//@Test (priority = 25)
public void MyWork5() throws Exception {
    extentTest = extentReports.createTest("Test Case 25", "Verify that the user can download the APR Report  by selecting circle as all from dropdown");
    extentTestThread.set(extentTest);

    File configFile25 = new File("config1.properties");
    InputStream inputStream25 = new FileInputStream(configFile25);
    Properties prop25 = new Properties();

    prop25.load(inputStream25);

    WebDriverManager.chromedriver().setup();
    driver25 = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver25, Duration.ofSeconds(10));

    Actions builder = new Actions(driver25);

    driver25.navigate().to(prop25.getProperty("AppURL"));

    driver25.manage().window().maximize();
    // Enter user name
    driver25.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //   takeScreenShot(prop25.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
  //          + "MyWork5" + ".png",driver25);
    captureScreenshot(driver25);
    Thread.sleep(1000);

    driver25.findElement(By.name("txtEmpCode")).sendKeys(prop25.getProperty("Admin"));

    Thread.sleep(1000);

    WebElement password25 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
    password25.sendKeys(prop25.getProperty("Password"));

    driver25.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    WebElement Submit25 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
    Submit25.click();

    driver25.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    // for accepting the alerts
    ParallelRunClass.resolveAllAlerts(driver25, 10, false);

    driver25.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver25.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

    driver25.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//    takeScreenShot(prop25.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//            + "MyWork5" + ".png",driver25);
    captureScreenshot(driver25);
    driver25.findElement(By.linkText("My Rewards")).click();

    driver25.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    Thread.sleep(7000);
//    takeScreenShot(prop25.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//            + "MyWork5" + ".png",driver25);
    captureScreenshot(driver25);
    WebElement element25 = driver25.findElement(By.xpath("//a[@title='APR Report']"));
    JavascriptExecutor executor25 = (JavascriptExecutor)driver25;
    executor25.executeScript("arguments[0].click();", element25);

    driver25.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    Thread.sleep(2000);
 //   takeScreenShot(prop25.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "MyWork5" + ".png",driver25);
    captureScreenshot(driver25);
    driver25.close();
}
//@Test (priority = 26)

public void MyWork7() throws Exception {
    extentTest = extentReports.createTest("Test Case 26", "Verify that the user can download the APR Report");
    extentTestThread.set(extentTest);
    File configFile26 = new File("config1.properties");
    InputStream inputStream26 = new FileInputStream(configFile26);
    Properties prop26 = new Properties();

    prop26.load(inputStream26);

    WebDriverManager.chromedriver().setup();
    driver26 = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver26, Duration.ofSeconds(10));

    Actions builder = new Actions(driver26);

    driver26.navigate().to(prop26.getProperty("AppURL"));

    driver26.manage().window().maximize();
    // Enter user name
    driver26.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

  //  takeScreenShot(prop26.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
  //          + "MyWork7" + ".png",driver26);
    captureScreenshot(driver26);
    Thread.sleep(1000);

    driver26.findElement(By.name("txtEmpCode")).sendKeys(prop26.getProperty("Admin"));

    Thread.sleep(1000);

    WebElement password26 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
    password26.sendKeys(prop26.getProperty("Password"));

    driver26.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    WebElement Submit26 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
    Submit26.click();

    driver26.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    // for accepting the alerts
    ParallelRunClass.resolveAllAlerts(driver26, 20, false);

    driver26.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver26.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

    driver26.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //   takeScreenShot(prop26.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "MyWork7" + ".png",driver26);
    captureScreenshot(driver26);
    driver26.findElement(By.linkText("My Rewards")).click();

    driver26.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    WebElement element26 = driver26.findElement(By.xpath("//a[@title='Admin View Compensation Letter']"));
    JavascriptExecutor executor26 = (JavascriptExecutor)driver26;
    executor26.executeScript("arguments[0].click();", element26);

    driver26.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver26.findElement(By.id("expep")).sendKeys("sumit");

    driver26.findElement(By.xpath("//select[@id='letterType']")).click();

    driver26.findElement(By.xpath("//option[@value='CombCompensation']")).click();

    driver26.findElement(By.xpath("//select[@id='CombineCompcycle']")).click();

    //driver26.findElement(By.xpath("//select[@id='promocycle']")).click();

    driver26.findElement(By.xpath("//option[@value='2022-23']")).click();

 //   takeScreenShot(prop26.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "MyWork7" + ".png",driver26);
    captureScreenshot(driver26);
    driver26.findElement(By.xpath("//a[normalize-space()='Download']")).click();

    driver26.close();
}
//@Test (priority = 27)
    public void MyWork8() throws Exception {
    extentTest = extentReports.createTest("Test Case 27", "Verify that the user can download the rewards statement letter");
    extentTestThread.set(extentTest);
        File configFile27 = new File("config1.properties");
        InputStream inputStream27 = new FileInputStream(configFile27);
        Properties prop27 = new Properties();

        prop27.load(inputStream27);

    WebDriverManager.chromedriver().setup();
    driver27 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver27, Duration.ofSeconds(10));

        Actions builder = new Actions(driver27);

        driver27.navigate().to(prop27.getProperty("AppURL"));

        driver27.manage().window().maximize();
        // Enter user name
        driver27.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//        takeScreenShot(prop27.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
//                + "MyWork8" + ".png",driver27);
        captureScreenshot(driver27);
        Thread.sleep(1000);

        driver27.findElement(By.name("txtEmpCode")).sendKeys(prop27.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password27 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password27.sendKeys(prop27.getProperty("Password"));

        driver27.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit27 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit27.click();

        driver27.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver27, 20, false);

        driver27.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver27.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver27.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

  //      takeScreenShot(prop27.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
  //              + "MyWork8" + ".png",driver27);
         captureScreenshot(driver27);
        driver27.findElement(By.linkText("My Rewards")).click();

        driver27.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement element27 = driver27.findElement(By.xpath("//a[@title='rewards statement']"));
        JavascriptExecutor executor27 = (JavascriptExecutor)driver27;
        executor27.executeScript("arguments[0].click();", element27);

        driver27.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop27.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork8" + ".png",driver27);
        captureScreenshot(driver27);

        driver27.findElement(By.xpath("//select[@id='year']")).click();

        driver27.findElement(By.xpath("//option[@value='Select']")).click();

        driver27.findElement(By.xpath("//option[@value='2022-23']")).click();

        driver27.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver27.findElement(By.xpath("//a[normalize-space()='Download']")).click();

        driver27.close();
    }
//@Test (priority = 28)
public void StopPay() throws Exception {
    extentTest = extentReports.createTest("Test Case 28", "Verify that the is successfully navigate to Stop Pay Tracker and selects month and year from drop down.");
    extentTestThread.set(extentTest);
    File configFile28 = new File("config1.properties");
    InputStream inputStream28 = new FileInputStream(configFile28);
    Properties prop28 = new Properties();

    prop28.load(inputStream28);

    WebDriverManager.chromedriver().setup();
    driver28 = new ChromeDriver();

    WebDriverWait wait = new WebDriverWait(driver28, Duration.ofSeconds(10));

    Actions builder = new Actions(driver28);

    driver28.navigate().to(prop28.getProperty("AppURL"));

    driver28.manage().window().maximize();
    // Enter user name
    driver28.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //   takeScreenShot(prop28.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "StopPay" + ".png",driver28);
    captureScreenshot(driver28);
    Thread.sleep(1000);

    driver28.findElement(By.name("txtEmpCode")).sendKeys(prop28.getProperty("Admin"));

    Thread.sleep(1000);

    WebElement password28 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
    password28.sendKeys(prop28.getProperty("Password"));

    driver28.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


    WebElement Submit28 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
    Submit28.click();

    driver28.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    // for accepting the alerts
    ParallelRunClass.resolveAllAlerts(driver28, 10, false);

    driver28.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver28.findElement(By.xpath("//b[contains(.,'Working at Vodafone Idea')]")).click();

    driver28.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    driver28.findElement(By.xpath("//a[@id='402']/div")).click();

    driver28.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    WebElement element28 = driver28.findElement(By.xpath("//a[@title='Stop Pay Tracker']"));
    JavascriptExecutor executor28 = (JavascriptExecutor)driver28;
    executor28.executeScript("arguments[0].click();", element28);


    driver28.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //   takeScreenShot(prop28.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //           + "StopPay" + ".png",driver28);
    captureScreenshot(driver28);
    driver28.close();

}
//@Test (priority = 29)
    public void MyWork9() throws Exception {
    extentTest = extentReports.createTest("Test Case 29", "Verify that the user can download the Goal setting user guide  under My Performance section");
    extentTestThread.set(extentTest);
        File configFile29 = new File("config1.properties");
        InputStream inputStream29 = new FileInputStream(configFile29);
        Properties prop29 = new Properties();

        prop29.load(inputStream29);

    WebDriverManager.chromedriver().setup();
    driver29 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver29, Duration.ofSeconds(10));

        Actions builder = new Actions(driver29);

        driver29.navigate().to(prop29.getProperty("AppURL"));

        driver29.manage().window().maximize();
        // Enter user name
        driver29.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop29.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork9" + ".png",driver29);
        captureScreenshot(driver29);
        Thread.sleep(1000);

        driver29.findElement(By.name("txtEmpCode")).sendKeys(prop29.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password29 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password29.sendKeys(prop29.getProperty("Password"));

        driver29.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit29 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit29.click();

        driver29.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver29, 10, false);

        driver29.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver29.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

        driver29.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop29.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork9" + ".png",driver29);
        captureScreenshot(driver29);
        driver29.findElement(By.xpath("//a[@id='390']//div[contains(text(),'MyPerformance')]")).click();

        driver29.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement element29 = driver29.findElement(By.xpath("//a[@title='Access MyPerformance Guides']"));
        JavascriptExecutor executor29 = (JavascriptExecutor)driver29;
        executor29.executeScript("arguments[0].click();", element29);

        driver29.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

 //       takeScreenShot(prop29.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork9" + ".png",driver29);
        captureScreenshot(driver29);
        driver29.findElement(By.xpath("//a[normalize-space()='Download']")).click();

 //       takeScreenShot(prop29.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
 //               + "MyWork9" + ".png",driver29);
         captureScreenshot(driver29);
        driver29.close();
    }
//@Test (priority = 30)
    public void FindPeople() throws Exception {
    extentTest = extentReports.createTest("Test Case 30", "Verify that Employee should be able to FindPeople.");
    extentTestThread.set(extentTest);
        File configFile30 = new File("config1.properties");
        InputStream inputStream30 = new FileInputStream(configFile30);
        Properties prop30 = new Properties();

        prop30.load(inputStream30);

    WebDriverManager.chromedriver().setup();
    driver30 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver30, Duration.ofSeconds(10));

        Actions builder = new Actions(driver30);

        driver30.navigate().to(prop30.getProperty("AppURL"));

        driver30.manage().window().maximize();
        // Enter user name
        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

  //      takeScreenShot(prop30.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
   //             + "FindPeople" + ".png",driver30);
        captureScreenshot(driver30);
        Thread.sleep(2000);

        driver30.findElement(By.name("txtEmpCode")).sendKeys(prop30.getProperty("Admin"));

        Thread.sleep(2000);

        WebElement password30 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password30.sendKeys(prop30.getProperty("Password"));

        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit30 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit30.click();


        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver30, 10, false);

        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver30.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver30.findElement(By.xpath("//a[@id='42']/div")).click();

        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop30.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "FindPeople" + ".png",driver30);
        captureScreenshot(driver30);
        // xpath=//a[contains(text(),'View Letter')]

        driver30.findElement(By.xpath("//input[@name='txtName']")).sendKeys("sumit");

        Thread.sleep(1000);

        // xpath=//a[contains(text(),'Download')]

        driver30.findElement(By.xpath("//p/a/img")).click();
        driver30.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    takeScreenShot(prop30.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "FindPeople" + ".png",driver30);
        captureScreenshot(driver30);
        driver30.close();
    }
//@Test (priority = 31)
    public void EmployeeSeparationLetter() throws Exception {
    extentTest = extentReports.createTest("Test Case 31", "Verify that the employee needs to download Employee separation pdf");
    extentTestThread.set(extentTest);
        File configFile31 = new File("config1.properties");
        InputStream inputStream31 = new FileInputStream(configFile31);
        Properties prop31 = new Properties();

        prop31.load(inputStream31);

    WebDriverManager.chromedriver().setup();
    driver31 = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver31, Duration.ofSeconds(10));

        Actions builder = new Actions(driver31);

        driver31.navigate().to(prop31.getProperty("AppURL"));

        driver31.manage().window().maximize();
        // Enter user name
        driver31.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

  //      takeScreenShot(prop31.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
   //             + "EmployeeSeparationLetter" + ".png",driver31);
        captureScreenshot(driver31);
        Thread.sleep(1000);

        driver31.findElement(By.name("txtEmpCode")).sendKeys(prop31.getProperty("Admin"));

        Thread.sleep(1000);

        WebElement password31 = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password31.sendKeys(prop31.getProperty("Password"));

        driver31.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        WebElement Submit31 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit31.click();

        driver31.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // for accepting the alerts
        ParallelRunClass.resolveAllAlerts(driver31, 10, false);

        driver31.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver31.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

        driver31.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

   //     takeScreenShot(prop31.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
     //           + "EmployeeSeparationLetter" + ".png",driver31);
         captureScreenshot(driver31);
        driver31.findElement(By.linkText("Employee Separation")).click();

        driver31.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    //    takeScreenShot(prop31.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
    //            + "EmployeeSeparationLetter" + ".png",driver31);
    captureScreenshot(driver31);
        driver31.close();
    }

}