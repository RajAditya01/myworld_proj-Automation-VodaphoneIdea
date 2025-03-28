
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MethodClass extends MainClass {
    // FacilityHelpDesk
    public static String substr1;
    public static String text1;
    // Clearance report
    public static String text2;
    public static String substr;

    public static File downloadFolder = new File("C://MyWorldAutomation//MyWorldScreenshots//Download_Reports//");

    public static void takeScreenShot(String filePath) throws IOException {
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

    public static void resolveAllAlerts(WebDriver driver, int timeout, boolean accept) {
        while (isAlertPresent(driver, timeout)) {
            resolveAlert(driver, accept);
        }
    }

    private static void resolveAlert(WebDriver driver, boolean accept) {
        if (accept) {
            driver.switchTo().alert().accept();
        } else {
            driver.switchTo().alert().dismiss();
        }
    }

    public static void WorkingAtVodafoneIDea() throws IOException, InterruptedException {

        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        prop.load(inputStream1);

//		System.setProperty("webdriver.chrome.driver","C:\\Myworld\\Drivers\\chromedriver.exe");

        System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);

        driver.navigate().to(prop.getProperty("AppURL"));

        driver.manage().window().maximize();
        // Enter user name
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID
                + ".png");

        Thread.sleep(3000);


        if (keyword.equalsIgnoreCase("SubmitBonafied") || keyword.equalsIgnoreCase("PersonalVisaLetterCheck1")
                || keyword.equalsIgnoreCase("PersonalVisaLetterCheck2")
                || keyword.equalsIgnoreCase("PersonalVisaLetterCheck3")
                || keyword.equalsIgnoreCase("PersonalVisaLetterCheck4")
                || keyword.equalsIgnoreCase("PersonalVisaLetterCheck6")
                || keyword.equalsIgnoreCase("PersonalVisaLetterCheck5")
                || keyword.equalsIgnoreCase("PersonalVisaLettersubmit")
                || keyword.equalsIgnoreCase("ReferenceLetterCheck1")
                || keyword.equalsIgnoreCase("ReferenceLetterCheck2")
                || keyword.equalsIgnoreCase("ReferenceLetterCheck3")
                || keyword.equalsIgnoreCase("ReferenceLetterCheck4")
                || keyword.equalsIgnoreCase("RequestBonafideLetter")) {

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitBonafied"));
        }

        else if (keyword.equalsIgnoreCase("NotSubmittedResignation") || keyword.equalsIgnoreCase("SubmitMyResignation")
                || keyword.equalsIgnoreCase("ReSubmittedResignation")
                || keyword.equalsIgnoreCase("ReSubmittedResignation1")
                || keyword.equalsIgnoreCase("AftersubmitResignationStatus")
                || keyword.equalsIgnoreCase("AfterApproveHRResignationStatus")
                || keyword.equalsIgnoreCase("AfterApproveManagerStatus")
                || keyword.equalsIgnoreCase("NotSubmittingIntitiateClearanceReport")) {

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("SubmitResignation"));
        } else if (keyword.equalsIgnoreCase("RejectByHR") || keyword.equalsIgnoreCase("ApprovedByHR")
                || keyword.equalsIgnoreCase("ApprovedByHR1") || keyword.equalsIgnoreCase("BookConferenceRoom")
                || keyword.equalsIgnoreCase("SeeBooking") || keyword.equalsIgnoreCase("CancelBooking")
                || keyword.equalsIgnoreCase("BookRoomRevisited") || keyword.equalsIgnoreCase("ViewRoomDetails")
                || keyword.equalsIgnoreCase("ProcessEmployeeRequest") || keyword.equalsIgnoreCase("HRBPForRetain")
                || keyword.equalsIgnoreCase("UploadPayrollFile")
                || keyword.equalsIgnoreCase("DownloadStopPayTracker")
                || keyword.equalsIgnoreCase("HROfflineResgination1")) {

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));
            Thread.sleep(1000);
        } else if (keyword.equalsIgnoreCase("RejectedByManager") || keyword.equalsIgnoreCase("ApprovedByManager")) {

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Manger"));
            Thread.sleep(1000);
        } else {

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
            Thread.sleep(1000);
        }
        Thread.sleep(3000);

        driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

        takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID
                + ".png");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='I1']")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // for accepting the alerts

        MethodClass.resolveAllAlerts(driver, 10, false);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id=\"4\"]/b")).click();

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // ||keyword.equalsIgnoreCase("ManagerApproveITClearence")
    }

    public static String TransferLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            WorkingAtVodafoneIDea();
            Thread.sleep(10000);
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

            return "Fail";
        }

        return "Pass";
    }

    public static String LoginValidation() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

//			System.setProperty("webdriver.chrome.driver","C:\\Myworld\\Drivers\\chromedriver.exe");

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // Enter password
            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("wrong_pwd"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(5000);
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String AppointmentLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='383']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.xpath("//a[contains(text(),'View Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'Download')]

            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(7000);

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

            driver.close();
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PendingApprovals() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='140']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)", "");

            Thread.sleep(3000);

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0,350)", "");

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String GenPDFLeavingUs() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Generate PDF Clearance Form')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//td/a/img")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(12000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ViewMySurvey() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='269']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'View My Survey')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(9000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String BandDeginationLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

//			System.setProperty("webdriver.chrome.driver","C:\\Myworld\\Drivers\\chromedriver.exe");

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Pay and Benefits')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='388']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.xpath("//a[contains(text(),'View Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'Download')]

            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(9000);

            Thread.sleep(9000);

            Thread.sleep(9000);

            Thread.sleep(9000);

            Thread.sleep(9000);

            Thread.sleep(4000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ViewMyLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);
            // Transfer Letter

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='347']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[contains(text(),'View My Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);
            Select se = new Select(driver.findElement(By.xpath("//select[@name='frmMyLetter']")));
            se.selectByIndex(3);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//td[2]/a/img")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String AnnualPayLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);
            // Transfer Letter

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='347']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[contains(text(),'Annual Pay Review letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Select se9 = new Select(driver.findElement(By.xpath("//select[@id='year']")));
            se9.selectByIndex(1);

            Thread.sleep(4000);

            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

            Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(3000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String AnnualPerformancePay() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            // Transfer Letter

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='347']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'View Annual Performance Pay')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // driver.findElement(By.id("year")).sendKeys("2018-19");

            Select se9 = new Select(driver.findElement(By.xpath("//select[@id='year']")));
            se9.selectByIndex(1);

            Thread.sleep(4000);

            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

            Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(8000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String AdminViewCompLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='347']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Admin View Compensation Lett...')]")).click();

            Select se1 = new Select(driver.findElement(By.xpath("//select[@id='letterType']")));
            se1.selectByIndex(1);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//textarea[@id='expep']")).sendKeys("sumit");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy
            // hh.mm.ss aaa") + " " + TestCasesID
            // + ".png");

            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();

            Thread.sleep(8000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String FindPeople() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='42']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.xpath("//input[@name='txtName']")).sendKeys("sumit");

            Thread.sleep(1000);

            // xpath=//a[contains(text(),'Download')]

            driver.findElement(By.xpath("//p/a/img")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(7000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ViewPolicey() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='381']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.xpath("//a[contains(text(),'View Policies')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(7000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String DomesticTicketDetails() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);
            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
            Thread.sleep(2000);
            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Business Travel')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Foreign Travel")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.xpath("//a[contains(text(),'View Ticket Details')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(7000);
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String NotSubmittedResignation() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // notsubmitting

            driver.findElement(By.linkText("Submit My Resignation")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");

            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();

            WebDriverWait ds212 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds212.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";

        }

        return "Pass";
    }

    public static String SubmitMyResignation() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Submit my resignation or resubmit regisation form

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Submit My Resignation")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("6378989701");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("lakshmi@gmail.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtAddress']")).sendKeys("VKP1");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtReason']")).sendKeys("Carrer Growth");
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();

            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);
            // String resignationID=
            // driver.findElement(By.xpath("//td[@id='spsuccessMsg']")).getText();
            /*
             * WebElement p=driver.findElement(By.xpath("//td[@id='spsuccessMsg']"));
             * //getText() to obtain text
             *
             * String s= p.getText(); Thread.sleep(1000); System.out.println(s);
             * Thread.sleep(1000);
             * driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
             */

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String AftersubmitResignationStatus() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Submit my resignation or resubmit regisation form

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("See Status")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String RejectByHR() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Approve resignation Pending...")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
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
            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
            Thread.sleep(1000);
            driver.findElement(By.linkText(prop.getProperty("NameLink"))).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@value='Y']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@id='prating']")).sendKeys("8");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//select[@id='taneltC']")).sendKeys("Discover Trainee");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtComments']")).sendKeys("All the best ");
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
            WebDriverWait ds21 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds21.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
            Thread.sleep(10000);
            driver.close();
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            return "Fail";
        }

        return "Pass";
    }

    public static String ReSubmittedResignation() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Submit my resignation or resubmit regisation form

            driver.findElement(By.linkText("Submit My Resignation")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("lakshmi@gmail.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtAddress']")).sendKeys("VKP1");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtReason']")).sendKeys("Career Growth");
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    // approving HR

    public static String ApprovedByHR() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.linkText("Approve resignation Pending...")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='selectFromDate']/img")).click();
            Thread.sleep(1000);
            // driver.findElement(By.id("txtFromDate")).sendKeys("03/06/2022");
            driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
            Thread.sleep(1000);
            // driver.findElement(By.id("txtToDate")).sendKeys("03/06/2022");
            driver.findElement(By.xpath("//a[@id='selectToDate']/img")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".ui-state-highlight")).click();
            // driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
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
            driver.findElement(By.xpath("//input[@id='prating']")).sendKeys("8");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//select[@id='taneltC']")).sendKeys("Discover Trainee");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtComments']")).sendKeys("All the best ");
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();

            WebDriverWait ds25 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds25.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(10000);
            // driver.close();
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }
    // seeing status after approving of HR

    public static String AfterApproveHRResignationStatus() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Submit my resignation or resubmit regisation form

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(2000);

            driver.findElement(By.linkText("See Status")).click();
            Thread.sleep(2000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    // RejectingByManager

    public static String RejectedByManager() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.linkText("Approve Resignation Pending...")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Click')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@value='N']")).click();
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            WebDriverWait ds2121 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds2121.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            Thread.sleep(10000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(10000);
            // driver.close();
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ReSubmittedResignation1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Submit my resignation or resubmit regisation form

            driver.findElement(By.linkText("Submit My Resignation")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//input[@id='phoneNumber']")).sendKeys("7337527596");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("lakshmi@gmail.com");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtAddress']")).sendKeys("VKP1");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtReason']")).sendKeys("Career Growth");
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ApprovedByHR1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Approve resignation Pending...")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='selectFromDate']/img")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
            // driver.findElement(By.xpath("//a[contains(text(),'4')]")).click();
            /// driver.findElement(By.id("txtFromDate")).sendKeys("03/06/2022");
            // driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
            Thread.sleep(1000);
            // driver.findElement(By.id("txtToDate")).sendKeys("03/06/2022");

            driver.findElement(By.xpath("//a[@id='selectToDate']/img")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".ui-state-highlight")).click();
            // driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();

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
            driver.findElement(By.xpath("//input[@id='prating']")).sendKeys("8");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//select[@id='taneltC']")).sendKeys("Discover Trainee");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='txtComments']")).sendKeys("All the best ");
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            WebDriverWait ds25 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds25.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            Thread.sleep(10000);
            // driver.close();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(10000);

            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ApprovedByManager() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Approve Resignation Pending...")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//a[contains(text(),'Click')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@value='Y']")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("dor1")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".ui-state-highlight")).click();
            // driver.findElement(By.xpath(prop.getProperty("Resubmit_Resignation"))).click();
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//img[@id='btnSubmit']")).click();
            WebDriverWait ds0 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds0.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            Thread.sleep(10000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(10000);

            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String AfterApproveManagerStatus() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Submit my resignation or resubmit regisation form

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("See Status")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String NotSubmittingIntitiateClearanceReport() throws Exception {

        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.findElement(By.xpath("//a[@id='30']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[contains(text(),'Initiate My Exit Clearance')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.findElement(By.id("btnSubmitForm")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            WebDriverWait ds3 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds3.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();

            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";

    }

    public static String SubmitBonafied() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.id("loan1")).click();

            Select s = new Select(driver.findElement(By.id("addReq")));
            // s.selectByValue("Yes");

            s.selectByVisibleText("No");

            driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();// a[contains(text(),'Submit')]


            WebDriverWait ds212 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds212.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);



            Thread.sleep(3000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLetterCheck1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Select s = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));

            s.selectByVisibleText("Yes");

            // driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();//a[contains(text(),'Submit')]
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.close();

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLetterCheck2() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));

            s.selectByVisibleText("Yes");

            // driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();//a[contains(text(),'Submit')]
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.close();

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLetterCheck3() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Select s = new Select(driver.findElement(By.xpath("//a[contains(text(),'View
            // familly details')]")));

            // s.selectByVisibleText("Yes");

            // driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();//a[contains(text(),'Submit')]

            driver.findElement(By.xpath("//a[contains(text(),'View familly details')]")).click();

            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.close();

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLetterCheck4() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Select s = new Select(driver.findElement(By.xpath("//a[contains(text(),'View
            // familly details')]")));

            // s.selectByVisibleText("Yes");

            // driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();//a[contains(text(),'Submit')]

            Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
            // s.selectByValue("Yes");

            s.selectByVisibleText("Yes");

            Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
            // s.selectByValue("Yes");

            s1.selectByVisibleText("Yes");

            driver.findElement(By.id("place")).click();

            driver.findElement(By.id("place")).sendKeys("India");
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLetterCheck6() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Select s = new Select(driver.findElement(By.xpath("//a[contains(text(),'View
            // familly details')]")));

            // s.selectByVisibleText("Yes");

            // driver.findElement(By.xpath("//a[contains(text(),'Submit')]")).click();//a[contains(text(),'Submit')]

            Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
            // s.selectByValue("Yes");

            s.selectByVisibleText("Yes");

            Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
            // s.selectByValue("Yes");

            s1.selectByVisibleText("Yes");

            driver.findElement(By.id("place")).click();

            driver.findElement(By.id("place")).sendKeys("India");

            driver.findElement(By.xpath("//a[@onclick='submitPersonalLetter()']")).click();

            BufferedImage imageq = new Robot()
                    .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(imageq, "png", new File(prop.getProperty("Screenshot_DownloadPath")
                    + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));

            WebDriverWait ds1 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds1.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();

            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.close();

            // Thread.sleep(7000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLetterCheck5() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
            // s.selectByValue("Yes");

            s.selectByVisibleText("Yes");

            Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
            // s.selectByValue("Yes");

            s1.selectByVisibleText("Yes");

            driver.findElement(By.id("place")).click();

            driver.findElement(By.id("place")).sendKeys("India");

            driver.findElement(By.xpath("//a[contains(text(),'View familly details')]")).click();

            Select s2 = new Select(driver.findElement(By.id("depenSelect")));

            s2.selectByVisibleText("Father");

            driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();

            driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();

            driver.findElement(By.id("datepicker1")).click();

            driver.findElement(By.xpath("//a[contains(text(),'17')]")).click();

            driver.findElement(By.id("datepicker2")).click();

            driver.findElement(By.xpath("//a[contains(text(),'2')]")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@onclick='submitPersonalLetter()']")).click();

            BufferedImage image1 = new Robot()
                    .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image1, "png", new File(prop.getProperty("Screenshot_DownloadPath")
                    + now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));

            WebDriverWait ds1 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds1.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PersonalVisaLettersubmit() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//button[contains(.,'Personal Visa Letter')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Select s = new Select(driver.findElement(By.xpath("//select[@name='familyReq']")));
            // s.selectByValue("Yes");

            s.selectByVisibleText("Yes");

            Select s1 = new Select(driver.findElement(By.xpath("//select[@name='leaveStatus']")));
            // s.selectByValue("Yes");

            s1.selectByVisibleText("Yes");

            driver.findElement(By.id("place")).click();

            driver.findElement(By.id("place")).sendKeys("India");

            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//a[contains(text(),'View familly details')]")).click();

            Select s2 = new Select(driver.findElement(By.id("depenSelect")));

            s2.selectByVisibleText("Father");

            driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();

            driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();

            driver.findElement(By.id("datepicker1")).click();

            driver.findElement(By.xpath("//a[contains(text(),'11')]")).click();

            driver.findElement(By.id("datepicker2")).click();

            driver.findElement(By.xpath("//a[contains(text(),'14')]")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@onclick='submitPersonalLetter()']")).click();
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // driver.close();

            Thread.sleep(7000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ReferenceLetterCheck1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//tr[2]/td/button")).click();
            /*
             * BufferedImage imagec1 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec1, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             *
             */
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//tr[3]/td/button")).click();

            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            /*
             * BufferedImage imagec2 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec2, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//tr[4]/td/button")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            /*
             * BufferedImage imagec3 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec3, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//tr[5]/td/button")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            /*
             * BufferedImage imagec4 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec4, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//tr[6]/td/button")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            /*
             * BufferedImage imagec5 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec5, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//tr[7]/td/button")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            /*
             * BufferedImage imagec6 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec6, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//tr[8]/td/button")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

            driver.close();
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ReferenceLetterCheck2() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//tr[8]/td/button")).click();
            driver.findElement(By.id("otherLetter")).click();

            driver.findElement(By.id("otherLetter")).sendKeys("ParallelRunClass");

            Thread.sleep(2000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);
            /*
             * BufferedImage imagec7 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec7, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            // driver.close();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ReferenceLetterCheck3() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//input[@id='loan1']")).click();

            Thread.sleep(2000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//td[3]/input")).click();

            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@id='Loan']/table/tbody/tr/td[4]/input")).click();
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);
            /*
             * BufferedImage imagec7 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec7, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            // driver.close();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ReferenceLetterCheck4() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Request a Bonafide Letter')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//input[@id='loan1']")).click();

            Select s = new Select(driver.findElement(By.id("addReq")));

            s.selectByVisibleText("Yes");

            Thread.sleep(2000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            /*
             * BufferedImage imagec7 = new Robot().createScreenCapture(new
             * Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
             * ImageIO.write(imagec7, "png", new
             * File(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png"));
             */
            // driver.close();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String BookConferenceRoom() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='35']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.linkText("Book a Conference Room")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//td/a/img")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            String winHandleBefore = driver.getWindowHandle();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            Thread.sleep(2000);
            // String str=now("dd");

            // TODO Auto-generated method stub
            /*
             * int d=9, d2=31;
             *
             * if (d<=9) { String str= now("dd"); String substr=str.substring(1);
             * System.out.println(substr); Thread.sleep(1000);
             * driver.findElement(By.xpath("//b[normalize-space()='"+substr+"']")).click();
             * } else if (d2>=d) { String str1= now("dd"); System.out.println(str1);
             * driver.findElement(By.xpath("//b[normalize-space()='"+str1+"']")).click();
             *
             * } else { System.out.println("not working"); }
             */

            driver.findElement(By.xpath("//a/font/b")).click();

            // driver.findElement(By.xpath(prop.getProperty("Booking_conference"))).click();
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
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//*[@id=\"conferenceTable\"]/tbody/tr[4]/td/input")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(10000);

            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String SeeBooking() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='35']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.linkText("See Bookings")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath(
                            "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/form/table/tbody/tr/td[3]/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/a"))
                    .click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(10000);

            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String CancelBooking() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='35']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Cancel Booking")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//td/a/img")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            WebDriverWait ds7 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds7.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();

            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String BookRoomRevisited() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='35']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Book Room-Revisited")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("chennai room 1")).click();

            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            // JavascriptExecutor js = (JavascriptExecutor) driver;
            // js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");

            /*
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             *
             * driver.findElement(By.xpath("//div[4]/div/table/tbody/tr/td[4]")).click();
             *
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             *
             * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
             *
             * driver.findElement(By.xpath(
             * "//div[@id='agendaDayCalendar']/div[2]/div/table/tbody/tr/td/div/div")).click
             * ();
             */

            /*
             * Set<String> windows = driver.getWindowHandles();
             *
             * Iterator<String>it = windows.iterator();
             *
             * String parentId = it.next();
             *
             * driver.findElement(By.xpath("//textarea[@id='bookingReason']")).click();
             * driver.findElement(By.xpath("//textarea[@id='bookingReason']")).sendKeys(
             * "Approve");
             *
             *
             *
             * String childId = it.next();
             *
             * driver.switchTo().window(childId);
             */

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            driver.close();

        } catch (Throwable t) {
            return "Fail";
        }

        return "Pass";
    }

    public static String ViewRoomDetails() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='35']/div")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.linkText("View Room Details")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(10000);

            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

        } catch (Throwable t) {
            return "Fail";
        }

        return "Pass";
    }

    public static String EMPREFLETTEROPENREQUEST() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // Config part
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            /*
             * WebDriverWait ds3 = new WebDriverWait(driver, 45);
             * ds3.until(ExpectedConditions.alertIsPresent());
             *
             * driver.switchTo().alert().accept();
             */
            // Transfer Letter
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.linkText("View Open Requests")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // xpath=//a[contains(text(),'Download')]

            Thread.sleep(10000);

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            return "Fail";
        }

        return "Pass";
    }

    public static String RequestBonafideLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.linkText("Request a Bonafide Letter")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.id("loan1")).click();

            driver.findElement(By.id("addReq")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(10000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(10000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String ProcessEmployeeRequest() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='399']/div")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // xpath=//a[contains(text(),'View Letter')]

            driver.findElement(By.linkText("Process Employee Letter Requ...")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.id("datepicker1")).click();
            driver.findElement(By.xpath("//span[contains(.,'Prev')]")).click();
            driver.findElement(By.linkText("1")).click();
            driver.findElement(By.id("calimage1")).click();

            /*
             * int d1=31,d=9;
             *
             * if(d<=9) { String str= now("dd"); String substr=str.substring(1);
             * System.out.println(substr);
             *
             * driver.findElement(By.xpath("//a[contains(text(),'"+substr+"')]")).click(); }
             * else if (d1>=10) { String str1= now("dd"); System.out.println(str1);
             * driver.findElement(By.linkText("20")).click();
             * driver.findElement(By.xpath("//a[contains(text(),'"+str1+"')]")).click(); }
             * else { System.out.println("not working"); }
             */
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".ui-state-highlight")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.linkText("Submit")).click();

            takeScreenShot("C:\\MyWorldAutomation\\MyworldScreenshots\\Downloads\\" + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + "ProcessEmployeeRequest7" + ".png");
            driver.findElement(By.linkText("Click")).click();

            Thread.sleep(1000);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.id("comment")).sendKeys("Approve");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Approve')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }
    /*
     * public static String MyRecognigation() throws Exception { try {
     * SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa"); File configFile1 = new
     * File("config.properties"); InputStream inputStream1 = new
     * FileInputStream(configFile1); Properties prop = new Properties();
     * prop.load(inputStream1);
     *
     * WorkingAtVodafoneIDea();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[@id='400']/div")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[contains(text(),'Access MyRecognition')]")).
     * click(); driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * Thread.sleep(1000);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     *
     * driver.close();
     *
     * SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa"); } catch (Throwable t) {
     * SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa"); return "Fail"; } return
     * "Pass"; } public static String MyLearning() throws Exception { try {
     * SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa"); File configFile1 = new
     * File("config.properties"); InputStream inputStream1 = new
     * FileInputStream(configFile1); Properties prop = new Properties();
     * prop.load(inputStream1);
     *
     * WorkingAtVodafoneIDea();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[@id='394']/div")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[contains(text(),'Access My Learning')]")).
     * click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[contains(text(),'Link')]")).click();
     *
     * Thread.sleep(1000);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * Thread.sleep(1000); driver.close();
     *
     * SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa"); } catch (Throwable t) {
     * return "Fail"; } return "Pass"; } public static String MyTeamLearning()
     * throws Exception { try { SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
     * File configFile1 = new File("config.properties"); InputStream inputStream1 =
     * new FileInputStream(configFile1); Properties prop = new Properties();
     * prop.load(inputStream1);
     *
     * WorkingAtVodafoneIDea();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[@id='394']/div")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//td[3]/a")).click();
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     * driver.findElement(By.xpath("//a[contains(text(),'Link')]")).click();
     *
     * Thread.sleep(1000);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * Thread.sleep(1000); driver.close();
     *
     * SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa"); } catch (Throwable t) {
     * return "Fail"; } return "Pass"; } public static String MyRecruitement()
     * throws Exception { try { SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
     * File configFile1 = new File("config.properties"); InputStream inputStream1 =
     * new FileInputStream(configFile1); Properties prop = new Properties();
     * prop.load(inputStream1);
     *
     * WorkingAtVodafoneIDea();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[@id='393']/div")).click();
     *
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     *
     * driver.findElement(By.xpath("//a[contains(text(),'MyRecruitment')]")).click()
     * ; driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     * driver.findElement(By.xpath("//a[contains(text(),'Link')]")).click();
     *
     * Thread.sleep(1000);
     * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
     * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
     * Thread.sleep(1000);
     *
     * driver.close();
     *
     * SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa"); } catch (Throwable t) {
     * SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa"); return "Fail"; } return
     * "Pass"; }
     */

    public static String GiftHospitality() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[@id='389']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Gift and Hospitality Form')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String GiftHospitality1() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[@id='389']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@title='See Status']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//input[@id='image']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String GiftHospitality2() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[@id='389']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@title='See Status']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//select[@id='cbStatus']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//option[@value='P']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//input[@id='image']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }


    public static String OrganizationCharts() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.linkText("Policies and FAQs")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='382']/div")).click();

            Thread.sleep(1000);

            //driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'View Organization Charts')]")).click();


            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.xpath("//font[contains(.,'Download')]")).click();

            Thread.sleep(2000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String ITPolicyReport1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='362']/div")).click();
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[contains(text(),'Generate IT Policy Report')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//select[@name='txtcircle']")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//img[@onclick='return hide();']")).click();

            Thread.sleep(2000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String HSWConsequenceManagementMatrixPolicyReport() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[@id='362']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[contains(text(),'Generate HSW Consequence Man...')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//select[@name='txtcircle']")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(8000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//img[@onclick='return hide();']")).click();
            Thread.sleep(1000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(8000);

            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String WorkingAtVodafoneletter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='371']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(7000);
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);

            driver.close();
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String CarrerPerformanceLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//a[@id='374']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1005)");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(7000);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(7000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String PayAndBenefits() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='373']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            /*
             * JavascriptExecutor js = (JavascriptExecutor) driver;
             * js.executeScript("window.scrollBy(0,1005)");
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             * Thread.sleep(7000);
             *
             * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
             */

            Thread.sleep(7000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String EmployeeSeparationLetter() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("Employee Separation")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            /*
             * JavascriptExecutor js = (JavascriptExecutor) driver;
             * js.executeScript("window.scrollBy(0,1005)");
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             * Thread.sleep(7000);
             *
             * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
             */

            Thread.sleep(7000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String BusinessTravel() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'Policies and FAQs')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='372']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            /*
             * JavascriptExecutor js = (JavascriptExecutor) driver;
             * js.executeScript("window.scrollBy(0,1005)");
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             * Thread.sleep(7000);
             *
             * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
             */

            Thread.sleep(7000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String FacilitiesHelpdeskSubformsasComplaint() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[@id='358']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Raise Request')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
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

            driver.findElement(By.xpath("//tr[@id='submitSection']/td/a/img")).click();

            WebDriverWait ds3 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds3.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();

            Thread.sleep(1000);

            WebElement k = driver.findElement(By.cssSelector("span.headlink"));

            text1 = k.getText();
            // Thread.sleep(1000);
            substr1 = text1.substring(43, 46);
            System.out.println(substr1);
            Thread.sleep(1000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String ViewRequestforComplaint() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[@id='358']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'View Requests')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(1000);
            System.out.println(text1);
            driver.findElement(By.xpath("//a[contains(text(),'" + substr1 + "')]")).click();

            // driver.findElement(By.cssSelector(".bodycopy:nth-child(2) a")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(3000);
            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String MyWork1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 30, false);

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//textarea[@id='expep']")).click();

            driver.findElement(By.xpath("//td/textarea[@id='expep']")).sendKeys(prop.getProperty("E1"));

            driver.findElement(By.xpath("//select[@id='letterType']")).click();

            driver.findElement(By.xpath("//td/select/option[@value='Transition']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String MyWork2() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//textarea[@id='expep']")).click();

            driver.findElement(By.xpath("//td/textarea[@id='expep']")).sendKeys(prop.getProperty("E1"));

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            driver.findElement(By.xpath("//select[@id='letterType']")).click();

            driver.findElement(By.xpath("//td/select/option[@value='Promotion']")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//div[@id='promotionCycle']")).click();

            //driver.findElement(By.xpath("//select[@id='promocycle']")).click();

            driver.findElement(By.xpath("//option[@value=\"Feb'23\"]")).click();

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String MyWork4() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//textarea[@id='expep']")).click();

            driver.findElement(By.xpath("//td/textarea[@id='expep']")).sendKeys(prop.getProperty("E1"));

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            driver.findElement(By.xpath("//select[@id='letterType']")).click();

            driver.findElement(By.xpath("//option[@value='Compensation']")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//select[@id='compcycle']")).click();

            //driver.findElement(By.xpath("//select[@id='promocycle']")).click();

            driver.findElement(By.xpath("//option[@value='2021-22']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String StopPayTrackerEdit() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);
            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));
            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(options);
            driver.navigate().to(prop.getProperty("AppURL"));
            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);
            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
            Thread.sleep(2000);
            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);
            // Transfer Letter
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//b[contains(.,'Working at Vodafone Idea')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//a[@id='402']/div")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//a[contains(text(),'Stop Pay Cycle')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
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
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String UploadPayrollFile() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // Click on Login button
            driver.findElement(By.xpath("//a[@id='402']/div")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            //a[@id='402']/div

            driver.findElement(By.xpath("//a[@title='Payroll Input']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[@onclick='doSubmitDownload()']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String DownloadStopPayTracker() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);
            WorkingAtVodafoneIDea();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // Click on Login button
            driver.findElement(By.xpath("//a[@id='402']/div")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // a[@id='402']/div
            driver.findElement(By.xpath("//a[contains(text(),'Stop Pay Tracker')]")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("(//img[@alt='Calendar'])[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String WrongEmpnoAppointmentLetter() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);
            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));
            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(options);
            driver.navigate().to(prop.getProperty("AppURL"));
            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);
            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));
            Thread.sleep(2000);
            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);
            // Transfer Letter
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//a[@id='347']/div")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[contains(text(),'Admin View Compensation Lett...')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//textarea[@id='expep']")).sendKeys("Lakshmi");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Thread.sleep(8000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Select drop = new Select(driver.findElement(By.xpath("//select[@id='letterType']")));
            Thread.sleep(1000);
            drop.selectByIndex(1);
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[contains(text(),'Download')]")).click();
            driver.switchTo().alert().accept();
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            driver.close();
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }

    public static String HROfflineResgination1() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);
            WorkingAtVodafoneIDea();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // Click on Login button
            driver.findElement(By.xpath("//a[@id='402']/div")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            // a[@id='402']/div
            driver.findElement(By.xpath("//a[contains(text(),'Payroll Input')]")).click();
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
            Thread.sleep(2000);

            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//img[@alt='Calendar']")).click();
            //driver.findElement(By.xpath("//*[@id=\"calimage\"]")).click();
            //driver.findElement(By.id("calimage"));
            ////*[@id="calimage"]//*[@id="calimage"]

            Thread.sleep(1000);
            //driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            String winHandleBefore = driver.getWindowHandle();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            Thread.sleep(1000);
            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            driver.switchTo().window(winHandleBefore);
            driver.findElement(By.xpath("//div[@id='PersonalVisaLetter']/table/tbody/tr[2]/td[2]/a")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);
            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);
            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);
            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }



    public static String MyWork5() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='APR Report']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            String winHandleBefore = driver.getWindowHandle();
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

            driver.findElement(By.xpath("//input[@id='datepicker1']")).click();

            //driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

            //driver.findElement(By.xpath("//option[@value='0']")).click();

            driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

            driver.findElement(By.xpath("//option[@value='2023']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='1']")).click();

            driver.findElement(By.xpath("//input[@id='datepicker2']")).click();

            //driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

            //driver.findElement(By.xpath("//option[@value='11']")).click();

            //driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

            //driver.findElement(By.xpath("//option[@value='2024']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='1']")).click();
            driver.switchTo().window(winHandleBefore);

            driver.findElement(By.xpath("//option[@value='All']")).click();


            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[normalize-space()='Search']")).click();

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String MyWork3() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 50, false);

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//textarea[@id='expep']")).click();

            driver.findElement(By.xpath("//td/textarea[@id='expep']")).sendKeys(prop.getProperty("E1"));

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            driver.findElement(By.xpath("//select[@id='letterType']")).click();

            driver.findElement(By.xpath("//option[@value='Compensation']")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//select[@id='compcycle']")).click();

            //driver.findElement(By.xpath("//select[@id='promocycle']")).click();

            driver.findElement(By.xpath("//option[@value='2021-22']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }


    public static String MyWork6() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//textarea[@id='expep']")).click();

            driver.findElement(By.xpath("//td/textarea[@id='expep']")).sendKeys(prop.getProperty("E2"));

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


            driver.findElement(By.xpath("//select[@id='letterType']")).click();

            driver.findElement(By.xpath("//option[@value='CombCompensation']")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//select[@id='CombineCompcycle']")).click();

            //driver.findElement(By.xpath("//select[@id='promocycle']")).click();

            driver.findElement(By.xpath("//option[@value='2022-23']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String MyWork7() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Admin View Compensation Letter']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//textarea[@id='expep']")).click();

            driver.findElement(By.xpath("//td/textarea[@id='expep']")).sendKeys(prop.getProperty("E3"));

            driver.findElement(By.xpath("//select[@id='letterType']")).click();

            driver.findElement(By.xpath("//option[@value='CombCompensation']")).click();

            driver.findElement(By.xpath("//select[@id='CombineCompcycle']")).click();

            //driver.findElement(By.xpath("//select[@id='promocycle']")).click();

            driver.findElement(By.xpath("//option[@value='2022-23']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            driver.switchTo().alert().accept();


            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }


    public static String MyWork8() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='rewards statement']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            driver.findElement(By.xpath("//select[@id='year']")).click();

            driver.findElement(By.xpath("//option[@value='Select']")).click();

            driver.findElement(By.xpath("//option[@value='2022-23']")).click();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String MyWork9() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[contains(.,'My Work')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@id='390']//div[contains(text(),'MyPerformance')]")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[@title='Access MyPerformance Guides']")).click();
            Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(2000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[normalize-space()='Download']")).click();
            Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");


            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String StopPay() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//div[contains(text(),'Monthly Payroll Activit...')]")).click();
            ///driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Stop Pay Tracker']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//input[@id='datepicker1']")).click();

            driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

            //driver.findElement(By.xpath("//option[@value='11']")).click();

            driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

            driver.findElement(By.xpath("//option[@value='2024']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String StopPay1() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("ApprovedHR"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//div[contains(text(),'Monthly Payroll Activit...')]")).click();
            ///driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Stop Pay Tracker']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//input[@id='datepicker1']")).click();

            //driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

            //driver.findElement(By.xpath("//option[@value='11']")).click();

            driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

            driver.findElement(By.xpath("//option[@value='2024']")).click();

            driver.findElement(By.xpath("//button[normalize-space()='Done']")).click();





            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[normalize-space()='Download Report']")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }

    public static String StopPay2() throws Exception {
        try {

            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();

            prop.load(inputStream1);

            System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverURL"));

            Map<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder + File.separator + "downloadFiles");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);

            driver = new ChromeDriver(options);

            driver.navigate().to(prop.getProperty("AppURL"));

            driver.manage().window().maximize();
            // Enter user name
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpCode")).sendKeys(prop.getProperty("Admin"));

            Thread.sleep(1000);

            driver.findElement(By.name("txtEmpPassword")).sendKeys(prop.getProperty("Password"));
            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // Click on Login button
            driver.findElement(By.xpath("//input[@name='I1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            // for accepting the alerts
            MethodClass.resolveAllAlerts(driver, 20, false);

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//b[normalize-space()='Working at Vodafone Idea']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//div[contains(text(),'Monthly Payroll Activit...')]")).click();
            ///driver.findElement(By.linkText("My Rewards")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            Thread.sleep(7000);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[@title='Stop Pay Cycle']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//a[normalize-space()='Update']")).click();


            driver.findElement(By.xpath("//input[@id='datepicker1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

            driver.findElement(By.xpath("//option[@value='11']")).click();

            driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

            driver.findElement(By.xpath("//option[@value='2023']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='1']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");

            driver.findElement(By.xpath("//input[@id='datepicker2']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")).click();

            driver.findElement(By.xpath("//option[@value='11']")).click();

            driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")).click();

            driver.findElement(By.xpath("//option[@value='2023']")).click();

            driver.findElement(By.xpath("//a[normalize-space()='30']")).click();

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[normalize-space()='Update']")).click();

            driver.findElement(By.xpath("//body[1]/div[3]/div[3]/div[1]/button[1]")).click();

            Thread.sleep(2000);
            // SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            driver.close();
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }

        return "Pass";
    }



    public static String ApproveRequestascomplaint() throws Exception {
        try {
            SingleTCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            File configFile1 = new File("config.properties");
            InputStream inputStream1 = new FileInputStream(configFile1);
            Properties prop = new Properties();
            prop.load(inputStream1);

            WorkingAtVodafoneIDea();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.findElement(By.xpath("//a[@id='358']/div")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'Approve Request')]")).click();

            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            driver.findElement(By.xpath("//a[contains(text(),'" + substr1 + "')]")).click();

            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250)", "");


            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Select s2 = new Select(driver.findElement(By.xpath("//select[@id='status_17']")));
            Thread.sleep(1000);
            s2.selectByIndex(1);

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0,250)", "");

            Thread.sleep(1000);

            // driver.findElement(By.xpath("//a[contains(text(),'"+substr1+"')]")).click();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            takeScreenShot(prop.getProperty("Screenshot_DownloadPath") + now("dd.MMM.yyyy hh.mm.ss aaa") + " "
                    + TestCasesID + ".png");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//textarea[@id='approverRemark']")).sendKeys("Approved");
            Thread.sleep(1000);
            /*
             * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             */
            System.out.println(123);

            driver.findElement(By.cssSelector("td > a:nth-child(1) > img")).click();
            System.out.println(123);

            WebDriverWait ds3 = new WebDriverWait(driver, Duration.ofSeconds(45));
            ds3.until(ExpectedConditions.alertIsPresent());

            driver.switchTo().alert().accept();

            /*
             * driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
             * takeScreenShot(prop.getProperty("Screenshot_DownloadPath") +
             * now("dd.MMM.yyyy hh.mm.ss aaa") + " " + TestCasesID + ".png");
             */

            Thread.sleep(3000);
            driver.close();

            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
        } catch (Throwable t) {
            SingleTCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");

            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(SingleTCStartTime);
            Date date2 = format.parse(SingleTCEndTime);

            int totaltime = (int) ((date2.getTime() - date1.getTime()) / 1000);

            int sec = (int) (totaltime % 60);
            int min = (int) ((totaltime / 60) % 60);

            String concattime = String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime = String.valueOf(concattime);
            return "Fail";
        }
        return "Pass";
    }
}