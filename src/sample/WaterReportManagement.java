package sample;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Payton on 10/13/16.
 * Structurer
 */
public class WaterReportManagement {
    private WaterSourceReport currentReport;
    private static ArrayList<WaterSourceReport> totalReports = new ArrayList<>();

    WaterReportManagement(WaterSourceReport waterSourceReport) {
        this.currentReport = waterSourceReport; //References to the same object
        this.totalReports.add(currentReport);
    }

    /**
     * This method returns a water source report if there exists a report with a matching
     * report number in the array list of totalReports.
     * @param reportNumber The report number of a water source report
     * @return Water Source Report with the matching report number else returns null
     */
    public static WaterSourceReport getReport(int reportNumber) {
        if (totalReports.isEmpty()) {
            return null;
        } else {
            WaterSourceReport temp;
            for (int i = 0; i < totalReports.size(); i++) {
                temp = totalReports.get(i);
                if (temp.getReportNumber() == reportNumber) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * Returns true if totalReports list is empty and false otherwise
     * @return
     */
    public static boolean isEmpty() {
        return totalReports.size() == 0;
    }

    public static void saveReport(String timestamp, String userName, String reportNumber, String location,
                                  String sourceType, String condition, String consumable, String Virus,
                                  String contaminant) throws ParseException {
        double latitude = Double.parseDouble(location.substring(0,location.indexOf(",")));
        double longitude = Double.parseDouble(location.substring(location.indexOf(" ")));

        // Create new report
        SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date lFromDate1 = datetimeFormatter1.parse(timestamp);

        WaterSourceReport report = new WaterSourceReport(new Timestamp(lFromDate1.getTime()),
                UserManagement.getUser(userName), longitude, latitude);
        totalReports.add(report);

        // Missing to set all the other variables
    }

}
