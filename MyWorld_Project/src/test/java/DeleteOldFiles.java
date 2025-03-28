import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DeleteOldFiles {

    public static void DeleteMethod() throws IOException, InvalidFormatException, InterruptedException {

//	public static void main(String[] args) throws IOException, InvalidFormatException, InterruptedException {

	/*
		File f= new File("C:\\MyWorld\\MyWorldScreenshots\\Report Excel\\Summary_Report.xlsx");
		if(f.delete())
		{
		System.out.println(f.getName() + " deleted");
		}
		else
		{
		System.out.println("failed");
		}

		Thread.sleep(3000);
        */
        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        prop.load(inputStream1);

        File f1= new File(prop.getProperty("Screenshot_DownloadPath"));
        for(File file: f1.listFiles())
            if (!file.isDirectory()) {
                file.delete();
                System.out.println("Old Screen shots deleted");
            }
            else {
                System.out.println("Failed to delete old image files");
            }

        File f2= new File(prop.getProperty("Report_Excel"));
        for(File file: f2.listFiles())
            if (!file.isDirectory()) {
                file.delete();
                System.out.println("Old Artefacts file deleted");
            }
            else {
                System.out.println("Failed to delete old files");
            }

        // FileInputStream filepath2 = new FileInputStream("C:\\MyWorld\\MyWorldScreenshots\\Summary_Report.xlsx");

        //  Workbook workbook12 = WorkbookFactory.create(filepath2);

        Workbook workbook = new XSSFWorkbook();

        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("sheet1");


        FileOutputStream out = new FileOutputStream(prop.getProperty("Summary_Report_Excel"));
        workbook.write(out);
        out.close();





    }
}
