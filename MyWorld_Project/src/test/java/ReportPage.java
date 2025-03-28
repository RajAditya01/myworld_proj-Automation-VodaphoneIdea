
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class ReportPage extends  MainClass {

    public static String now(String dateFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());

    }

    public static void GenerateReport() throws IOException {

        File configFile1 = new File("config.properties");
        InputStream inputStream1 = new FileInputStream(configFile1);
        Properties prop = new Properties();

//	public static void main(String[] args) throws IOException {
//	File filename = new File("C:\\MyWorld\\MyWorldScreenshots\\template.html");
//	String htmlString = FileUtils.readFileToString(filename);

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Chatterjee2S2\\Desktop\\MyWorld\\MyWorldScreenshots\\"+"Report"+now("dd.MMM.yyyy hh.mm.ss aaa")+".html"));

        bw.write("<html>\r\n"
                + "<head>\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "\r\n");
        //		+ "<h2 style=\"font-size:32px\"><center>Automation Report</center></h2>\r\n"

        bw.append("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u>Automation Test Results</u></b></h4>\n");
        bw.append("<table width='100%' valign='top'><tr><td>");
        bw.append("<table  border=1 cellspacing=1 cellpadding=1 align='left' valign='top'>\n");
        bw.append("<tr>\n");

        bw.append("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Run Details :</u></h4>\n");
        bw.append("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\n");
        bw.append("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+now("dd-MMM-yyyy")+"</b></td>\n");
        bw.append("</tr>\n");
        bw.append("<tr>\n");

        bw.append("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\n");

        bw.append("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+TCStartTime+"</b></td>\n");
        bw.append("</tr>\n");
        bw.append("<tr>\n");


        bw.append("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\n");
        bw.append("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+TCEndTime+"</b></td>\n");
        bw.append("</tr>\n");
        bw.append("<tr>\n");


        //RUNTIME-STArtTIME

        bw.append("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Total Run Time</b></td>\n");
        bw.append("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+TCRunTime +" mins"+"</b></td>\n");
        bw.append("</tr>\n");
        bw.append("<tr>\n");




        bw.append("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Application Environment</b></td>\n");
        bw.append("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+"SIT"+"</b></td>\n");
        bw.append("</tr>\n");
        bw.append("<tr>\n");

        //  bw.append("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></td>\n");
        // bw.append("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+RELEASE+"</b></td>\n");
        bw.append("</tr>\n");

        // update the pass fail

        bw.append("</table></td>\n");


        bw.append("<td><table  border=1 cellspacing=1 cellpadding=1 align=right valign='top'  >\n");
        bw.append("<tr>\n");
        bw.append("<td width=150 colspan='2' bgcolor=#153E7E style='text-align:center'><FONT COLOR=#E0E0E0 FACE=Arial SIZE=3.75 align='center'><b>Test Summary Report</b></td></tr>\n");
        bw.append("<tr><td width=150 style='text-align:center' bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Test Case Pass</b></td>\n");
        bw.append("<td width=150 style='text-align:center' bgcolor='#33ff49'><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>")
                .append(String.valueOf(counter1))
                .append("</b></td></tr>\n");
        bw.append("<tr><td width=150 style='text-align:center' bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Test Case Fail</b></td>\n");
        bw.append("<td width=150 style='text-align:center' bgcolor='#FF0000' ><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>")
                .append(String.valueOf(counter2))
                .append("</b></td></tr>\n");
        bw.append("<tr><td width=150 style='text-align:center' bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Total</b></td>\n");
        bw.append("<td width=150 style='text-align:center'><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>")
                .append(String.valueOf(counter2+counter1))
                .append("</b></td></tr>\n");
        bw.append("</tr></table></td></tr></table>");

        bw.append("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Download Detail Summary Report:</u></h4>\n");
        bw.append("<button class=\"btn\" onclick=\"document.getElementById('link').click()\">Download</button>\r\n"
                + "<a id=\"link\" href=\"C:\\Users\\Chatterjee2S2\\Desktop\\MyWorld\\MyWorldScreenshots\\Report Excel\\Summary_Report.xlsx\" download hidden></a>");

        bw.append("<br><br>");


        bw.append("<table  border=1 cellspacing=1 cellpadding=1 width=100%>\n");
        bw.append("<tr>\n");
        bw.append("<th width=7%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test CaseID</b></td>\n");

        bw.append("<th width=20% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Case Description</b></td>\n");
        bw.append("<th width=30% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Test Steps</b></td>\n");
        bw.append("<th width=5%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\n");
        bw.append("<th width=18% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run Start Time</b></td>\n");
        bw.append("<th width=18% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Run End Time</b></td>\n");
        bw.append("<th width=18% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Single TC Run Time</b></td>\n");

        bw.append("</tr>\n");

        for (int i = 0; i < testID.size(); i++) {
            bw.append("<tr><td>")
                    .append(testID.get(i))
                    .append("</td><td>")
                    .append(Description.get(i))
                    .append("</td><td>")
                    .append(TC_Steps.get(i))
                    .append("</td><td>")
                    .append(teststatus.get(i))
                    .append("</td><td>")
                    .append(StartTime.get(i))
                    .append("</td><td>")
                    .append(EndTime.get(i))
                    .append("</td><td>")
                    .append(TestRunTime.get(i)+" mins")
                    .append("</td></tr>");

        }


        bw.append("</table>");
        bw.append("<br>" +
                "<br>"+
                "<br>"+
                "<br>");




        /*
         * bw.append("<br>"+ "<br>"+
         * " <a href=\"C:\\MyWorld\\MyWorldScreenshots\\myFile1.xlsx\" target=\"_blank\">Download Summary Report</a>"
         * );
         */

//	bw.append("form method=\"get\" action=\"C:\\MyWorld\\MyWorldScreenshots\\myFile1.xlsx\"");

//	bw.append("<button class=\"btn\" type=\"submit\" style=\"width:30%\" \"hight:20%\"><a href=\"C:\\MyWorld\\MyWorldScreenshots\\myFile1.xlsx\" target=\"_blank\">Download Summary Report</a></button>");

//	bw.append("<button type=\"submit\" onclick=\"window.open('C:\\MyWorld\\MyWorldScreenshots\\myFile1.xlsx')\">Download!</button>");



        bw.append("<br>"+
                "<br>"+
                "<br>"+
                "</body>" +
                "</html>");
        bw.close();

        System.out.println("Final report generated");

        System.out.println("Pass count:"+counter1);

        System.out.println("Fail count:"+counter2);

    }
}
