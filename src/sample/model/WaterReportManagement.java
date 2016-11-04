package sample.model;

import java.util.ArrayList;

/**
 * Created by Payton on 10/13/16.
 * Structurer
 */
public class WaterReportManagement {
    public static WaterSourceReport currentReport;
    private static ArrayList<WaterSourceReport> totalReports = new ArrayList<>();

    /**
     * the constructor for Creating an instance of the water source report management
     * @param waterSourceReport the first report to be placed in the list of reports
     */
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
     * This method returns the list of water source reports.
     * @return Water Source Reports if it's not empty else returns null
     */
    public static ArrayList<WaterSourceReport> getAllReports() {
        if (totalReports.isEmpty()) {
            return null;
        } else {
            return totalReports;
        }
    }

    /**
     * Returns true if totalReports list is empty and false otherwise
     * @return
     */
    public static boolean isEmpty() {
        return totalReports.size() == 0;
    }

    /**
     * this method sets the current report
     * @param givenReport the given report becomes the current report
     */
    public static void setCurrentReport(WaterSourceReport givenReport) {
        currentReport = givenReport;
    }

    /**
     * The current report is returned
     * @return the current report in report management
     */
    public static WaterSourceReport getCurrentReport() {
        return currentReport;
    }

    /**
     * this method adds the given report to the array list of reports
     * @param givenReport the given report is added
     */
    public static void addReport(WaterSourceReport givenReport) {
        //givenReport.setReportNumber(givenReport.getReportNumber() + 1);
        totalReports.add(givenReport);
    }
}
