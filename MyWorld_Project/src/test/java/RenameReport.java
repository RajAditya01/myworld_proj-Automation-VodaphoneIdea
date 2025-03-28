
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class RenameReport extends MainClass{

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    public static void SummaryExcel() throws InvalidFormatException, IOException {
        //Blank workbook
        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        prop.load(inputStream1);
        FileInputStream filepath1 = new FileInputStream(prop.getProperty("Summary_Report_Excel"));
        Workbook fileworkbook = WorkbookFactory.create(filepath1);
        FileOutputStream out1 = new FileOutputStream(new File(prop.getProperty("Summary_Report_Excel_Rename")+ now("dd.MMM.yyyy hh.mm.ss aaa")+".xlsx"));
        fileworkbook.write(out1);
        out1.close();



    }
}
