
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReport extends MainClass{

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static void ReportInExcel() throws InvalidFormatException, IOException {
        //Blank workbook
        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        prop.load(inputStream1);
        FileInputStream filepath1 = new FileInputStream(prop.getProperty("Summary_Report_Excel"));
        Workbook fileworkbook = WorkbookFactory.create(filepath1);
        //	Sheet filesheet = fileworkbook.createSheet("Sheet1");

        Sheet filesheet = fileworkbook.getSheet("Sheet1");

        //This data needs to be written (Object[])

        ArrayList<Object[]> data=new ArrayList<Object[]>();

        data.add(new Object[]{"Test ID","Description","Test Steps","Status"});

        for (int i = 0; i < testID.size(); i++) {

            data.add(new Object[]{testID.get(i),Description.get(i),TC_Steps.get(i), teststatus.get(i)});

        }


        //Iterate over data and write to sheet
        int rownum = 1;
        for (Object[] countries : data)
        {
            Row row = filesheet.createRow(rownum++);

            int cellnum = 1;
            for (Object obj : countries)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);

            }
        }


        fileworkbook.setSheetName(0, "GenerateTestReport");

        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(prop.getProperty("Summary_Report_Excel")));
            fileworkbook.write(out);
            out.close();

            System.out.println("Status Printed in Excel Report");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

