package sample;
import java.util.ArrayList;

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

}
