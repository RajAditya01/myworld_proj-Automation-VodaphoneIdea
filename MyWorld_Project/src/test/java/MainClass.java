
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

public class MainClass {

    public static String TestCasesID;
    public static WebDriver driver = null;
    public static String result;
    public static String flagrunmode;
    public static String substr;
    public static String substr1;

    public static String keyword;
    public static String descript;
    public static String Test_Steps;

    public static String TCStartTime;
    public static String TCEndTime;

    public static String SingleTCStartTime;
    public static String SingleTCEndTime;

    public static int counter1 = 0;
    public static int counter2 = 0;

    public static String TCRunTime;

    public static String TotalRunTime;

    public static ArrayList<String> teststatus = new ArrayList<String>();
    public static ArrayList<String> testID = new ArrayList<String>();
    public static ArrayList<String> Description = new ArrayList<String>();
    public static ArrayList<String> StartTime = new ArrayList<String>();
    public static ArrayList<String> EndTime = new ArrayList<String>();
    public static ArrayList<String> TC_Steps = new ArrayList<String>();
    public static ArrayList<String> TestRunTime = new ArrayList<String>();

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.format(cal.getTime());

        // humanReadableFormat(duration);

    }

    /*
     * public static String humanReadableFormat(Duration duration) { return
     * duration.toString() .substring(2) .replaceAll("(\\d[HMS])(?!$)", "$1 ")
     * .toLowerCase(); }
     */

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        // TCStartTime=now("dd.MMM.yyyy hh.mm.ss aaa");

        TCStartTime = now("dd.MMM.yyyy hh.mm.ss aaa");

        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        prop.load(inputStream1);

        DeleteOldFiles.DeleteMethod();

        FileInputStream filepath = new FileInputStream(prop.getProperty("MyWorld_InputExcel"));


        // System.out.println("File fetched");

        Workbook fileworkbook = WorkbookFactory.create(filepath);
        Sheet filesheet = fileworkbook.getSheet("Sheet1");

        // System.out.println("File opened");

        int sheetrowcount = filesheet.getLastRowNum();

        // System.out.println(sheetrowcount);

        try {

            for (int i = 1; i <= sheetrowcount; i++) {
                // int i=1; i&lt;=sheet.getLastRowNum(); i++

                TestCasesID = filesheet.getRow(i).getCell(0).getStringCellValue();

                flagrunmode = filesheet.getRow(i).getCell(1).getStringCellValue();

                keyword = filesheet.getRow(i).getCell(2).getStringCellValue();

                descript = filesheet.getRow(i).getCell(3).getStringCellValue();
                Test_Steps = filesheet.getRow(i).getCell(4).getStringCellValue();

                // System.out.println(keyword);

                // filepath.close();

                System.out.println("Run Mode Flag:" + flagrunmode);

                if (flagrunmode.equalsIgnoreCase("YES")) {
                    Method method = MethodClass.class.getMethod(keyword);

                    result = (String) method.invoke(method);

                    // System.out.println(result);
                    if (result.equalsIgnoreCase("Pass") || result.equalsIgnoreCase("Fail")) {
                        System.out.println(TestCasesID + ":" + result);

                        testID.add(TestCasesID);
                        teststatus.add(result);
                        Description.add(descript);
                        StartTime.add(SingleTCStartTime);
                        EndTime.add(SingleTCEndTime);
                        TestRunTime.add(TCRunTime);
                        TC_Steps.add(Test_Steps);

                    }

                    if (result.equalsIgnoreCase("Pass")) {
                        counter1++;
                    } else if (result.equalsIgnoreCase("Fail")) {
                        counter2++;
                    }

                    ExcelWriter.ReportingMethod();
                }

                else {
                    System.out.println("No Test cases selected");

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            Thread.sleep(5000);

            TCEndTime = now("dd.MMM.yyyy hh.mm.ss aaa");
            // TotalRunTime


            SimpleDateFormat format = new SimpleDateFormat("dd.MMM.yyyy hh.mm.ss aaa");
            Date date1 = format.parse(TCStartTime);
            Date date2 = format.parse(TCEndTime);

            int	totaltime = (int)((date2.getTime() - date1.getTime())/1000);

            int sec = (int) (totaltime % 60);
            int min =(int) ((totaltime / 60) % 60);

            String concattime=String.valueOf(min).concat(".").concat(String.valueOf(sec));
            TCRunTime =String.valueOf(concattime);


            //TotalRunTime = String.valueOf(TCRunTime);

            ExcelReport.ReportInExcel();

            RenameReport.SummaryExcel();

            Thread.sleep(8000);

            ReportPage.GenerateReport();

        }
    }
}
