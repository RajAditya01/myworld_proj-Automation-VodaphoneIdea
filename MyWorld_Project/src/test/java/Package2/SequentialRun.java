package Package2;

import Package.ReportClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SequentialRun extends ReportClass {
    public static WebDriver driver33=null;
    public static WebDriver driver=null;
    public static String text1;
    public static String substr1;
    public static String text2;
    public static String substr;

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

    public String getcurrentdateandtime(){
        String str = null;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
        Date date = new Date();
        str= dateFormat.format(date);
        str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        return str;
    }

    //@Test(priority = 1)
    public void FacilitiesHelpdeskSubformsasComplaint() throws Exception {

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        FileInputStream filepath = new FileInputStream(prop.getProperty("MyWorld_InputExcel"));
        Workbook fileworkbook = WorkbookFactory.create(filepath);
        Sheet filesheet = fileworkbook.getSheet("Sheet1");
        int sheetrowcount = filesheet.getLastRowNum();
        String  TestCasesID = filesheet.getRow(1).getCell(0).getStringCellValue();
        String  TestDescription = filesheet.getRow(1).getCell(0).getStringCellValue();
        //  extentTest = extentReports.createTest("Test Case 1", "Verify Transfer Letter");
        extentTest = extentReports.createTest(TestCasesID, TestDescription);
     //   extentTest = extentReports.createTest("Test Case 32", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
     /*   File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
*/
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver));

        captureScreenshot(driver);
        driver.findElement(By.xpath("//*[@id=\"4\"]/b")).click();


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[@id='358']/div")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

      //  driver.findElement(By.xpath("//a[contains(text(),'Raise Request')]")).click();

        WebElement element= driver.findElement(By.xpath("//a[contains(text(),'Raise Request')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//select[@id='typeOfBuilding']")).sendKeys("Branch Office");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//input[@id='location']")).sendKeys("PUNE");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//input[@id='buildingName']")).sendKeys("OZONE");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//input[@id='deskNo']")).sendKeys("16491A1213");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Select s1 = new Select(driver.findElement(By.id("workDesc")));
        Thread.sleep(1000);
        s1.selectByIndex(1);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Select s2 = new Select(driver.findElement(By.id("subWorkDesc")));
        Thread.sleep(1000);
        s2.selectByIndex(1);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys("Adding");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("td:nth-child(2) > a:nth-child(1) > img")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);

        driver.findElement(By.xpath("//tr[@id='submitSection']/td/a/img")).click();

        WebDriverWait ds3 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds3.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
        captureScreenshot(driver);

        Thread.sleep(1000);

        WebElement k = driver.findElement(By.cssSelector("span.headlink"));

        text1 = k.getText();

        substr1 = text1.substring(43, 46);
        System.out.println("Value: " +substr1);
        driver.close();
    }
//@Test(priority = 2)
    public void ViewRequestforComplaint() throws Exception {
    extentTest = extentReports.createTest("Test Case 33", "Verify that the  employee able to view the request for Facilities HelpDesk");
    extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver));

        captureScreenshot(driver);
        driver.findElement(By.xpath("//*[@id=\"4\"]/b")).click();



        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[@id='358']/div")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    WebElement element= driver.findElement(By.xpath("//a[contains(text(),'View Requests')]"));
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();", element);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        System.out.println(text1);
        driver.findElement(By.xpath("//a[contains(text(),'" + substr1 + "')]")).click();

        driver.close();

    }
//@Test(priority = 3)
    public void ApproveRequestascomplaint() throws Exception {
    extentTest = extentReports.createTest("Test Case 34", "Verify that the “Circle Admin Spoc” able to submit  the request that raised by the employee by selecting the status as  In progress.");
    extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);


    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver));

        captureScreenshot(driver);
        driver.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='358']/div")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    driver.findElement(By.xpath("//a[contains(text(),'Approve Request')]")).click();

        WebElement element= driver.findElement(By.xpath("//a[contains(text(),'Approve Request')]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

       // driver.findElement(By.xpath("//a[contains(text(),'" + substr1 + "')]")).click();
    System.out.println("Substring value: "+substr1);
    driver.findElement(By.linkText(substr1)).click();

    captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select s2 = new Select(driver.findElement(By.id("status_17")));
        Thread.sleep(1000);
        s2.selectByIndex(1);

        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,250)", "");

        Thread.sleep(1000);

        // driver.findElement(By.xpath("//a[contains(text(),'"+substr1+"')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='approverRemark']")).sendKeys("Approved");
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("td > a:nth-child(1) > img")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        WebDriverWait ds3 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds3.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

        Thread.sleep(1000);
        driver.close();

    }
   //@Test (priority = 4)
    public void GiftHospitality1() throws Exception {
       extentTest = extentReports.createTest("Test Case 35", "Verify that the employee can download the Gift or Hospitality Status Report");
       extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver));

        captureScreenshot(driver);
        driver.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='389']/div")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    driver.findElement(By.xpath("//a[@title='See Status']")).click();
       WebElement element= driver.findElement(By.xpath("//a[@title='See Status']"));
       JavascriptExecutor executor = (JavascriptExecutor)driver;
       executor.executeScript("arguments[0].click();", element);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

       // driver.findElement(By.xpath("//input[@id='image']")).click();

           WebElement element1= driver.findElement(By.xpath("//input[@id='image']"));
           JavascriptExecutor executor1 = (JavascriptExecutor)driver;
           executor.executeScript("arguments[0].click();", element1);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

       captureScreenshot(driver);

        driver.close();

    }
//@Test (priority = 5)
    public void GiftHospitality2() throws Exception {
        extentTest = extentReports.createTest("Test Case 36", "Verify that the employee can download the Gift or Hospitality Status Report  by selecting the from date and to date and status as pending");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        //extentTestThread.get().addScreenCaptureFromPath(captureScreen(driver));

        captureScreenshot(driver);
        driver.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='389']/div")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

    //    driver.findElement(By.xpath("//a[@title='See Status']")).click();
        WebElement element= driver.findElement(By.xpath("//a[@title='See Status']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement element1= driver.findElement(By.xpath("//input[@id='image']"));
        JavascriptExecutor executor1 = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element1);
        captureScreenshot(driver);
        driver.close();
    }

    //@Test(priority = 6)
    public void StopPay1() throws Exception {
        extentTest = extentReports.createTest("Test Case 37", "Verify that resignation details should be available in Stop Pay Tracker if it is fully approved.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        driver.findElement(By.xpath("//a[@id='402']/div")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        captureScreenshot(driver);
        //     driver.findElement(By.xpath("//a[@title='Stop Pay Tracker']")).click();

        WebElement element = driver.findElement(By.xpath("//a[@title='Stop Pay Tracker']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();

        WebElement element1 = driver.findElement(By.xpath("(//button[@type='button'])[3]"));
        JavascriptExecutor executor1 = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element1);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.switchTo().alert().accept();
        driver.findElement(By.id("calimage")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/div/select")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/div/select[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div[2]/button[2]")).click();
        driver.findElement(By.linkText("Download Report")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.close();

    }

    //@Test(priority = 7)
    public void UploadPayrollFile() throws Exception {
        extentTest = extentReports.createTest("Test Case 38", "HR:Verify that the HR able to download the upload payroll input file");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='402']/div")).click();

        driver.findElement(By.xpath("//a[@title='Payroll Input']")).click();
        captureScreenshot(driver);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@onclick='doSubmitDownload()']")).click();

    }

    //@Test(priority = 8)
    public void StopPayTrackerEdit() throws Exception {
        extentTest = extentReports.createTest("Test Case 39", "HR:Verify that the HR able to view the Stop Pay Tracker Report by selecting month and year.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='402']/div")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Stop Pay Cycle')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//table[@id='stopPayEmpList']/tbody/tr[3]/td/a")).click();
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        driver.findElement(By.xpath("//img[@alt='Calendar']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'1')]")).click();
        driver.findElement(By.xpath("(//img[@alt='Calendar'])[2]")).click();
        driver.findElement(By.cssSelector("a.ui-state-default.ui-state-highlight")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
        driver.switchTo().window(winHandleBefore);
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.close();
    }

    //@Test(priority = 9)
    public void HROfflineResgination1() throws Exception {
        extentTest = extentReports.createTest("Test Case 40", "R should have option add the record into the tracker and that should be visible in the tracker.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='402']/div")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Payroll Input')]")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//img[@alt='Calendar']")).click();
        // driver.findElement(By.xpath("//*[@id=\"calimage\"]")).click();
        // driver.findElement(By.id("calimage"));
        //// *[@id="calimage"]//*[@id="calimage"]

        Thread.sleep(1000);
        // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
        driver.switchTo().window(winHandleBefore);
        driver.findElement(By.xpath("//div[@id='PersonalVisaLetter']/table/tbody/tr[2]/td[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.close();

    }

    //@Test(priority = 10)
    public void DownloadStopPayTracker() throws Exception {
        extentTest = extentReports.createTest("Test Case 41", "HR:Verify that HR should able to download Download Report for Stop Pay tracker.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='402']/div")).click();
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Stop Pay Tracker')]")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("(//img[@alt='Calendar'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);

        driver.close();


    }

    //@Test(priority = 11)
    public void StopPay2() throws Exception {
        extentTest = extentReports.createTest("Test Case 42", "Admin:Verify that Admin should have access to edit the Stop Pay Cycle.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("s01");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='402']/div")).click();

        driver.findElement(By.xpath("//a[@title='Stop Pay Cycle']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        captureScreenshot(driver);

        driver.findElement(By.xpath("//a[normalize-space()='Update']")).click();

        driver.findElement(By.xpath("//input[@id='datepicker1']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

        driver.findElement(By.xpath("//option[@value='11']")).click();

        driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

        driver.findElement(By.xpath("//option[@value='2023']")).click();

        driver.findElement(By.xpath("//a[normalize-space()='1']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//input[@id='datepicker2']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

        driver.findElement(By.xpath("//option[@value='11']")).click();

        driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

        driver.findElement(By.xpath("//option[@value='2023']")).click();

        driver.findElement(By.xpath("//a[normalize-space()='30']")).click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Update']")).click();

        driver.findElement(By.xpath("//body[1]/div[3]/div[3]/div[1]/button[1]")).click();
        captureScreenshot(driver);
        Thread.sleep(2000);


        driver.close();


    }

    //@Test(priority = 12)
    public void BookConferenceRoom() throws Exception {
        extentTest = extentReports.createTest("Test Case 43", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("ss12");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='35']/div")).click();


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        // xpath=//a[contains(text(),'View Letter')]

        driver.findElement(By.linkText("Book a Conference Room")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//td/a/img")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a/font/b")).click();

        Thread.sleep(1000);

        driver.switchTo().window(winHandleBefore);
        Thread.sleep(1000);

        driver.findElement(By.name("bookingHour")).click();

        Select partners19 = new Select(driver.findElement(By.name("bookingHour")));
        Thread.sleep(1000);
        // partners19.selectByValue("l3");

        partners19.selectByIndex(16);
        // driver.findElement(By.name("bookingHour")).sendKeys("11");
        driver.findElement(By.xpath("//select[@name='bookingMinutes']")).click();
        driver.findElement(By.name("bookingMinutes")).sendKeys("45");
        driver.findElement(By.xpath("//select[@name='bookingToHour']")).click();
        // driver.findElement(By.name("bookingToHour")).sendKeys("16");
        driver.findElement(By.name("bookingToHour")).sendKeys(prop.getProperty("Booking_hours"));

        driver.findElement(By.xpath("//select[@name='bookingToMinutes']")).click();
        driver.findElement(By.name("bookingToMinutes")).sendKeys("30");
        driver.findElement(By.xpath("//select[@name='lstPSA']")).click();
        driver.findElement(By.name("lstPSA")).sendKeys("Chennai");
        driver.findElement(By.xpath("//select[@name='lstRoom']")).click();
        driver.findElement(By.name("lstRoom")).sendKeys("chennai room 1");
        driver.findElement(By.xpath("//textarea[@id='bookingReason']")).click();
        driver.findElement(By.name("bookingReason")).sendKeys("test");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.findElement(By.xpath("//*[@id=\"conferenceTable\"]/tbody/tr[4]/td/input")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        Thread.sleep(10000);

        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        captureScreenshot(driver);
        driver.close();

    }

    //@Test(priority = 13)
    public void SeeBooking() throws Exception {

        extentTest = extentReports.createTest("Test Case 44", "Verify that employee is able the see the bookings after Book a Conference Room.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='35']/div")).click();


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("See Bookings")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.findElement(By.xpath(
                        "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/form/table/tbody/tr/td[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a"))
                .click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        Thread.sleep(10000);
        captureScreenshot(driver);

        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        driver.close();

    }

    //@Test(priority = 14)
    public void CancelBooking() throws Exception {
        extentTest = extentReports.createTest("Test Case 45", "Verify that employee is able the Cancel a booking after Book a Conference Room");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("http://10.94.134.21:9080/EPortal/Login/MyLogin.jsp");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys("ss12");
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys("world123");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='35']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Cancel Booking")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//td/a/img")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebDriverWait ds7 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds7.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        captureScreenshot(driver);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        driver.close();
    }

    //@Test(priority = 15)
    public void BookRoomRevisited() throws Exception {
        extentTest = extentReports.createTest("Test Case 46", "Verify that employee can book the Book by Room-Revisited in Book a Conference Room");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='35']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Book Room-Revisited")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("chennai room 1")).click();
        captureScreenshot(driver);
        Thread.sleep(1000);
        driver.close();
    }

    //@Test(priority = 16)

    public void NotSubmittedResignation() throws Exception {

        extentTest = extentReports.createTest("Test Case 47", "Verify that  employee will not fill all mandatory fields and not able to submit the resignation form");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // notsubmitting
        captureScreenshot(driver);
        driver.findElement(By.linkText("Submit My Resignation")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");
        Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        WebDriverWait ds212 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds212.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.close();
    }

    //@Test(priority = 17)

    public void SubmitMyResignation() throws Exception {

        extentTest = extentReports.createTest("Test Case 48", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Submit my resignation or resubmit regisation form


        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Submit My Resignation")).click();

        captureScreenshot(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("lakshmi@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtAddress']")).sendKeys("VKP1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtReason']")).sendKeys("Carrer Growth");
        Thread.sleep(1000);
        captureScreenshot(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        driver.close();
    }

    //@Test(priority = 18)
    public void AftersubmitResignationStatus() throws Exception {

        extentTest = extentReports.createTest("Test Case 49", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("See Status")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        captureScreenshot(driver);
        driver.close();
    }


   //@Test(priority = 19)
    public void RejectByHR() throws Exception {
        extentTest = extentReports.createTest("Test Case 50", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.linkText("Approve resignation Pending...")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[@id='selectFromDate']/img")).click();
        Thread.sleep(1000);
        // driver.findElement(By.id("txtFromDate")).sendKeys("03/06/2022");
        // driver.findElement(By.xpath("//a/font/b")).click();

        driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
        Thread.sleep(1000);
        // driver.findElement(By.id("txtToDate")).sendKeys("03/06/2022");
        driver.findElement(By.xpath("//a[@id='selectToDate']/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".ui-state-highlight")).click();
        // driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText(prop.getProperty("NameLink"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Y']")).click();
        Thread.sleep(1000);
      //  driver.findElement(By.xpath("//input[@id='prating']")).sendKeys("8");
      //  Thread.sleep(1000);
      //  driver.findElement(By.xpath("//select[@id='taneltC']")).sendKeys("Discover Trainee");
     //   Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtComments']")).sendKeys("All the best ");
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        WebDriverWait ds21 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds21.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(100);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.close();
    }

  //@Test(priority = 20)
    public void ReSubmittedResignation() throws Exception {
        extentTest = extentReports.createTest("Test Case 51", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // Submit my resignation or resubmit regisation form
        driver.findElement(By.linkText("Submit My Resignation")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("lakshmi@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtAddress']")).sendKeys("VKP1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtReason']")).sendKeys("Career Growth");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        captureScreenshot(driver);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        driver.close();
    }

    //@Test(priority = 21)
    public void ApprovedByHR() throws Exception {
        extentTest = extentReports.createTest("Test Case 52", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Approve resignation Pending...")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='selectFromDate']/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='selectToDate']/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".ui-state-highlight")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText(prop.getProperty("NameLink"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='N']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='radResigType']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//select[@id='voluntry']")).sendKeys("Career Growth");
        Thread.sleep(1000);
     //   driver.findElement(By.xpath("//input[@id='prating']")).sendKeys("8");
     //   Thread.sleep(1000);
     //   driver.findElement(By.xpath("//select[@id='taneltC']")).sendKeys("Discover Trainee");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtComments']")).sendKeys("All the best ");
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        WebDriverWait ds25 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds25.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.close();
    }

    //@Test(priority = 22)
    public void AfterApproveHRResignationStatus() throws Exception {
        extentTest = extentReports.createTest("Test Case 53", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("See Status")).click();
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.close();
    }

       //@Test(priority = 23)
    public void RejectedByManager() throws Exception {
        extentTest = extentReports.createTest("Test Case 54", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Manger"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.findElement(By.linkText("Approve Resignation Pending...")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Click')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='N']")).click();
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebDriverWait ds2121 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds2121.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.close();
    }

    //@Test(priority = 24)
    public void ReSubmittedResignation1() throws Exception {
        extentTest = extentReports.createTest("Test Case 55", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // Submit my resignation or resubmit regisation form
        driver.findElement(By.linkText("Submit My Resignation")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("lakshmi@gmail.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtAddress']")).sendKeys("VKP1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtReason']")).sendKeys("Career Growth");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        captureScreenshot(driver);
        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        driver.close();
    }

    //@Test(priority = 25)
    public void ApprovedByHR1() throws Exception {
        extentTest = extentReports.createTest("Test Case 56", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Approve resignation Pending...")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='selectFromDate']/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='selectToDate']/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".ui-state-highlight")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText(prop.getProperty("NameLink"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='N']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='radResigType']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//select[@id='voluntry']")).sendKeys("Career Growth");
        Thread.sleep(1000);
        //   driver.findElement(By.xpath("//input[@id='prating']")).sendKeys("8");
        //   Thread.sleep(1000);
        //   driver.findElement(By.xpath("//select[@id='taneltC']")).sendKeys("Discover Trainee");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[@id='txtComments']")).sendKeys("All the best ");
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        WebDriverWait ds25 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds25.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.close();
    }

     //@Test(priority = 26)
    public void ApprovedByManager() throws Exception {
        extentTest = extentReports.createTest("Test Case 57", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Manger"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.findElement(By.linkText("Approve Resignation Pending...")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Click')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@value='Y']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("dor1")).click();
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.findElement(By.cssSelector(".ui-state-highlight")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
        WebDriverWait ds0 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds0.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Thread.sleep(10000);
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }

        //@Test(priority = 27)
    public void AfterApproveManagerStatus() throws Exception {
        extentTest = extentReports.createTest("Test Case 58", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Manger"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("See Status")).click();
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();

    }

    @Test(priority = 28)
    public void IntitiateKeepEmailIdBlank() throws Exception {

        extentTest = extentReports.createTest("Test Case 59", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Initiate My Exit Clearance")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='remedyNumber']")).click();
        driver.findElement(By.xpath("//input[@id='remedyNumber']")).sendKeys("REQ456789");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyExitSurveySubmit")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyclaimSubmt")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyHondoverAssineeIdVal")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyGenIDVal")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("HondoverAssineeemailId")).click();
        captureScreenshot(driver);
        driver.findElement(By.id("btnSubmitForm")).click();
        SequentialRun.resolveAllAlerts(driver, 20, false);

        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }
    @Test(priority = 29)
    public void IntitiateKeepEmailIdPersonal() throws Exception {

        extentTest = extentReports.createTest("Test Case 60", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Initiate My Exit Clearance")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='remedyNumber']")).click();
        driver.findElement(By.xpath("//input[@id='remedyNumber']")).sendKeys("REQ456789");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyExitSurveySubmit")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyclaimSubmt")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyHondoverAssineeIdVal")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyGenIDVal")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("HondoverAssineeemailId")).click();
        captureScreenshot(driver);
        driver.findElement(By.id("HondoverAssineeemailId")).sendKeys("lakshmi@gmail.com");
        driver.findElement(By.id("btnSubmitForm")).click();
        SequentialRun.resolveAllAlerts(driver, 20, false);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }
    @Test(priority = 30)
    public void IntitiateClearanceReport() throws Exception {

        extentTest = extentReports.createTest("Test Case 61", "Verify Facilities Help desk Subforms as Complaint Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 10, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='30']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Initiate My Exit Clearance")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@id='remedyNumber']")).click();
        driver.findElement(By.xpath("//input[@id='remedyNumber']")).sendKeys("REQ456789");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyExitSurveySubmit")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyclaimSubmt")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyHondoverAssineeIdVal")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("verifyGenIDVal")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("HondoverAssineeemailId")).click();
        driver.findElement(By.id("HondoverAssineeemailId")).sendKeys("myworldams@vodafoneidea.com");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("isKRCCSubmittedYes")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("btnSubmitForm")).click();
        SequentialRun.resolveAllAlerts(driver, 20, false);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }

    //@Test(priority =31)
    public  void PersonalVisaLetterCheck1() throws Exception{
        extentTest = extentReports.createTest("Test Case 62", "Verify that the if leaves approved in EVO Portal user should able to select YES from dropdown");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select s = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
        s.selectByVisibleText("Yes");
        captureScreenshot(driver);
        driver.close();
    }
    //@Test(priority =32)
    public  void PersonalVisaLetterCheck2() throws Exception{
        extentTest = extentReports.createTest("Test Case 63", "Verify that You Want Family details on letter if travelling with family  is available user wants to select dropdown as YES");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        captureScreenshot(driver);
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
        s.selectByVisibleText("Yes");
        Thread.sleep(1000);
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }
    //@Test(priority =33)
    public  void PersonalVisaLetterCheck3() throws Exception{
        extentTest = extentReports.createTest("Test Case 64", "Verify that View Family Details link is available and user able to click on view details .");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'View familly details')]")).click();
        Thread.sleep(1000);
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();

    }
    //@Test(priority =34)
    public  void PersonalVisaLetterCheck4() throws Exception{
        extentTest = extentReports.createTest("Test Case 65", "Verify that Country/Place of visit is available and user wants to select the from date and to date");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
        s.selectByVisibleText("Yes");
        Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
        s1.selectByVisibleText("Yes");
        driver.findElement(By.id("place")).click();
        driver.findElement(By.id("place")).sendKeys("India");
        Thread.sleep(1000);
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();

    }

    //@Test(priority =35)
    public  void PersonalVisaLetterCheck5() throws Exception{
        extentTest = extentReports.createTest("Test Case 66", "Verify that the To Date should not less than the from date while request a Request a Bonafide Letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
        s.selectByVisibleText("Yes");
        Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
        s1.selectByVisibleText("Yes");
        driver.findElement(By.id("place")).click();
        driver.findElement(By.id("place")).sendKeys("India");
        driver.findElement(By.xpath("//a[contains(text(),'View familly details')]")).click();
        Select s2 = new Select(driver.findElement(By.id("depenSelect")));
        s2.selectByVisibleText("Father");
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
        driver.findElement(By.id("datepicker1")).click();
        driver.findElement(By.xpath("//a[contains(text(),'17')]")).click();
        driver.findElement(By.id("datepicker2")).click();
        driver.findElement(By.xpath("//a[contains(text(),'2')]")).click();
        driver.findElement(By.xpath("//a[@onclick='submitPersonalLetter()']")).click();
        WebDriverWait ds1 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds1.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();

    }
    //@Test(priority =36)
    public  void PersonalVisaLetterCheck6() throws Exception{
        extentTest = extentReports.createTest("Test Case 67", "Verify that travel with Family is selected and Family details are not added and submit the request, then error message throw in employee reference letter");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
        s.selectByVisibleText("Yes");
        Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
        s1.selectByVisibleText("Yes");
        driver.findElement(By.id("place")).click();
        driver.findElement(By.id("place")).sendKeys("India");
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[@onclick='submitPersonalLetter()']")).click();
        WebDriverWait ds1 = new WebDriverWait(driver ,Duration.ofSeconds(45));
        ds1.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();

    }
    //@Test(priority =37)
    public  void PersonalVisaLettersubmit() throws Exception{
        extentTest = extentReports.createTest("Test Case 68", "Verify that the user is able to submit Request a Bonafide Letter.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
        Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
        // s.selectByValue("Yes");
        s.selectByVisibleText("Yes");
        Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
        // s.selectByValue("Yes");
        s1.selectByVisibleText("Yes");
        driver.findElement(By.id("place")).click();
        driver.findElement(By.id("place")).sendKeys("India");
        Thread.sleep(1000);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//a[contains(text(),'View familly details')]")).click();
        Select s2 = new Select(driver.findElement(By.id("depenSelect")));
        s2.selectByVisibleText("Father");
        driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();
        driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
        driver.findElement(By.id("datepicker1")).click();
        driver.findElement(By.xpath("//a[contains(text(),'11')]")).click();
        driver.findElement(By.id("datepicker2")).click();
        driver.findElement(By.xpath("//a[contains(text(),'14')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@onclick='submitPersonalLetter()']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        captureScreenshot(driver);
        driver.close();

    }
    //@Test(priority =38)
    public  void RequestBonafideLetter() throws Exception{
        extentTest = extentReports.createTest("Test Case 69", "Verify that if the employee is selecting Type of Letter as Reference Letter then employee has to select if signature required is YES or NO.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("loan1")).click();

        driver.findElement(By.id("addReq")).click();
        captureScreenshot(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();

    }
    //@Test(priority =39)
    public  void ReferenceLetterCheck1() throws Exception{
        extentTest = extentReports.createTest("Test Case 70", "Verify that if employee select type of letter as Reference Letter then Employee need to select a Purpose from list menu and it will be printed as it is in the letter.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//tr[2]/td/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//tr[3]/td/button")).click();
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.findElement(By.xpath("//tr[4]/td/button")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//tr[5]/td/button")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//tr[6]/td/button")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//tr[7]/td/button")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//tr[8]/td/button")).click();
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        driver.close();
    }
    //@Test(priority =40)
    public  void ReferenceLetterCheck2() throws Exception{
        extentTest = extentReports.createTest("Test Case 71", "Verify that if the purpose selected is Others then user need to enter description in textbox and it will be printed as it is in the letter.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//tr[8]/td/button")).click();
        driver.findElement(By.id("otherLetter")).click();
        driver.findElement(By.id("otherLetter")).sendKeys("test1");
        Thread.sleep(2000);
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }
    //@Test(priority =41)
    public  void ReferenceLetterCheck3() throws Exception{
        extentTest = extentReports.createTest("Test Case 72", "Verify that if the employee select the purpose as loan then employee need to select type of loan as (Vehicle or Home or Personal).");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.findElement(By.xpath("//input[@id='loan1']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//td[3]/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='Loan']/table/tbody/tr/td[4]/input")).click();
        Thread.sleep(2000);
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }
    //@Test(priority =42)
    public  void ReferenceLetterCheck4() throws Exception{
        extentTest = extentReports.createTest("Test Case 73", "Verify that the employee need to select Address in the letter as   NO in Employment Reference Letters menu.");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.findElement(By.xpath("//input[@id='loan1']")).click();
        Select s = new Select(driver.findElement(By.id("addReq")));
        s.selectByVisibleText("No");
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        captureScreenshot(driver);
        driver.close();
    }
    //@Test(priority =43)
    public  void EMPREFLETTEROPENREQUEST() throws Exception{
        extentTest = extentReports.createTest("Test Case 74", "Verify that user able to view the open request  Employment Reference Letters");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("View Open Requests")).click();
        captureScreenshot(driver);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.close();
    }
    //@Test(priority =44)
    public  void ProcessEmployeeRequest() throws Exception{
        extentTest = extentReports.createTest("Test Case 75", "Verify that the HRSSC SPOC will process the  Request in Employment Reference Letters .");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Process Employee Letter Requ...")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("datepicker1")).click();
        driver.findElement(By.xpath("//span[contains(.,'Prev')]")).click();
        driver.findElement(By.linkText("1")).click();
        driver.findElement(By.id("calimage1")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".ui-state-highlight")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Submit")).click();
        Thread.sleep(1000);
     /*   driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.id("comment")).sendKeys("Approve");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Approve')]")).click(); */
        captureScreenshot(driver);
        driver.close();
    }
    //@Test(priority =45)
    public  void SubmitBonafied() throws Exception{
        extentTest = extentReports.createTest("Test Case 76", "Verify that the user able to fill all the  mandatory details and submit the report");
        extentTestThread.set(extentTest);
        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        Thread.sleep(2000);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='399']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();
        driver.findElement(By.id("loan1")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select s = new Select(driver.findElement(By.id("addReq")));
        s.selectByVisibleText("No");
        driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();// a[contains(text(),'Submit')]
        WebDriverWait ds212 = new WebDriverWait(driver, Duration.ofSeconds(45));
        ds212.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        captureScreenshot(driver);
        driver.close();
    }

    //@Test(priority =46)
    public void FindPeople() throws Exception {

        extentTest = extentReports.createTest("Test Case 77", "Verify that Employee should be able to FindPeople.");
        extentTestThread.set(extentTest);

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        //driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("IntitiateClearanceReport"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='42']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='txtName']")).sendKeys("sumit");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//p/a/img")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.close();
    }
    //@Test(priority =47)
    public void WorkingAtVodafoneletter() throws Exception {

        extentTest = extentReports.createTest("Test Case 78", "Verify that the employee needs to download IT Policy Report according to circle.");
        extentTestThread.set(extentTest);

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        //driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("IntitiateClearanceReport"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='371']/div")).click();
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.close();
    }
    //@Test(priority =48)
    public void EmployeeSeparationLetter() throws Exception {

        extentTest = extentReports.createTest("Test Case 79", "Verify that the employee needs to download Employee separation pdf");
        extentTestThread.set(extentTest);

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        //driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("IntitiateClearanceReport"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Employee Separation")).click();
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(7000);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.close();
    }
    @Test(priority =49)
    public void WrongEmpnoAppointmentLetter() throws Exception {
        extentTest = extentReports.createTest("Test Case 80", "Verify that when user enter wrong employee no. for downloading appointment letter then error message will be thrown");
        extentTestThread.set(extentTest);

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        //driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("IntitiateClearanceReport"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='347']/div")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Admin View Compensation Lett...')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//textarea[@id='expep']")).sendKeys("Lakshmi");
        Thread.sleep(7000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Select drop = new Select(driver.findElement(By.xpath("//select[@id='letterType']")));
        Thread.sleep(1000);
        drop.selectByIndex(1);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
        driver.switchTo().alert().accept();
        Thread.sleep(7000);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.close();
    }
    @Test(priority =50)
    public void MyWork9() throws Exception {
        extentTest = extentReports.createTest("Test Case 81", "Verify that the user can download the Goal seeting user guide  under My Performance section");
        extentTestThread.set(extentTest);

        File configFile1 = new File("config1.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();
        prop.load(inputStream1);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
        //driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("IntitiateClearanceReport"));
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("txtEmpPassword")));
        password.sendKeys(prop.getProperty("Password"));
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='I1']")));
        Submit.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // for accepting the alerts
        SequentialRun.resolveAllAlerts(driver, 20, false);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@id='390']//div[contains(text(),'MyPerformance')]")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@title='Access MyPerformance Guides']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        Thread.sleep(7000);
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        captureScreenshot(driver);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.close();
    }

}
