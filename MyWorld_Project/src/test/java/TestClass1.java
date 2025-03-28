
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;

public class TestClass1 {

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

        FileInputStream filepath = new FileInputStream("C://MyWorldAutomation//MyWorldData.xlsx");

        Workbook fileworkbook = WorkbookFactory.create(filepath);
        Sheet filesheet = fileworkbook.getSheet("Sheet1");


        int sheetrowcount = filesheet.getLastRowNum();
        int colCount = filesheet.getRow(0).getLastCellNum();
        // System.out.println(sheetrowcount);
        double[][] testData = new double[sheetrowcount][colCount];
        try {

            for (int i = 0; i < sheetrowcount; i++) {
                Row row = filesheet.getRow(i );
                if (row != null) {
                    for (int j = 0; j < colCount; j++) {
                        Cell cell = row.getCell(j );
                        if (cell != null) {
                            try {
                                if (cell.getCellType().equals(CellType.NUMERIC)) {
                                    testData[i][j] = Double.valueOf(cell.getNumericCellValue());
                                }
                            } catch (IllegalStateException e) {
                                System.out.println("Cell data is not a double");
                            }
                            System.out.println(testData[i][j]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {



        }
    }
}
