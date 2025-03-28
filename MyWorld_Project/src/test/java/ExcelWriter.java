
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;


public class ExcelWriter extends  MainClass {


    //    public static void main(String[] args) throws IOException, InvalidFormatException {

    public static void ReportingMethod() throws IOException, InvalidFormatException, InterruptedException {

        // Create a Workbook
        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

        prop.load(inputStream1);

        FileInputStream filepath1 = new FileInputStream(prop.getProperty("Summary_Report_Excel"));
        Workbook workbook = WorkbookFactory.create(filepath1);

        //   Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

	        /* CreationHelper helps us create instances of various things like DataFormat,
	           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        //	Workbook workbook = new XSSFWorkbook();

        CreationHelper createHelper = workbook.getCreationHelper();

        Thread.sleep(2000);

        // Create a Sheet
        Sheet sheet = workbook.createSheet(TestCasesID);

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        /*
         * // Create cells for (int i = 0; i < columns.length; i++) { Cell cell =
         * headerRow.createCell(i); cell.setCellValue(columns[i]);
         * cell.setCellStyle(headerCellStyle); }
         */

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd.MM.yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        int rowNum1 = 1;
        int cellid = 0;

        ArrayList<Object[]> data1=new ArrayList<Object[]>();

        data1.add(new Object[]{"Test Steps"});

        List<String> data2 = new ArrayList<String>();
/*
			for (int i = 0; i < testID.size(); i++) {

				data1.add(new Object[]{TC_Steps.get(i)});
				StringBuffer buffer = new StringBuffer();

					buffer.append(TC_Steps.get(i));
					String Contents = (buffer.toString());

					String[] res = Contents.split("\\,");

					for(String myStr: res)
					{

						 data2.add(myStr);
					}


			}
*/
        ArrayList<Object[]> data=new ArrayList<Object[]>();

        data.add(new Object[]{"Status"});

        for (int i = 0; i < testID.size(); i++) {

            data.add(new Object[]{teststatus.get(i)});

        }

        File file = new File(prop.getProperty("Screenshot_DownloadPath"));
        List<String> filesContainingSubstring = new ArrayList<String>();

        if( file.exists() && file.isDirectory() )
        {
            String[] files = file.list(); //get the files in String format.
            for( String fileName : files )
            {
                if( fileName.contains(TestCasesID) )
                    filesContainingSubstring.add( fileName );
            }
        }

        int cellnum = 1;
        int cellnum1 = 1;

        for (Object[] x : data)
        {
            Row row1 = sheet.createRow(rowNum1++);

            for (Object obj : x)
            {
                Cell cell1 = row1.createCell(cellnum1);
                if(obj instanceof String)
                    cell1.setCellValue((String)obj);
            }
        }

        for(int i=0; i<filesContainingSubstring.size();i++ )
        {

            Row row = sheet.createRow(rowNum++);
            row.setHeight((short) 5000);

            //============= Inserting image - START
            /* Read input PNG / JPG Image into FileInputStream Object*/
            InputStream my_banner_image = new FileInputStream(prop.getProperty("Screenshot_DownloadPath")+filesContainingSubstring.get(i));


            /* Convert picture to be added into a byte array */
            byte[] bytes = IOUtils.toByteArray(my_banner_image);
            /* Add Picture to Workbook, Specify picture type as PNG and Get an Index */
            int my_picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            /* Close the InputStream. We are ready to attach the image to workbook now */
            my_banner_image.close();
            /* Create the drawing container */
            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
            /* Create an anchor point */
            //============= Inserting image - END

            //========adding image START
            XSSFClientAnchor my_anchor = new XSSFClientAnchor();
            /* Define top left corner, and we can resize picture suitable from there */

            my_anchor.setCol1(5); //Column B
            my_anchor.setRow1(rowNum-1); //Row 3
            my_anchor.setCol2(15); //Column C
            my_anchor.setRow2(rowNum); //Row 4

            rowNum=rowNum+7;

            /* Invoke createPicture and pass the anchor point and ID */
            XSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
            //========adding image END
        }

        // Resize all columns to fit the content size
        /*
         * for (int i = 0; i < columns.length; i++) { sheet.autoSizeColumn(i); }
         */



        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(prop.getProperty("Summary_Report_Excel"));
        workbook.write(fileOut);
        fileOut.close();

    }
}
