package sample.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Payton on 10/13/16.
 * Structurer
 */
public class WaterReportManagement {
    private static WaterSourceReport currentReport;
    private static WaterSourceReportList reportList = initialize();

    /**
     * initilaz
     */
    private static WaterSourceReportList initialize() {
        WaterSourceReportList temp = null;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("ReportBase.ser"));
            temp = (WaterSourceReportList) in.readObject();
        } catch (IOException i) {
            System.out.println("Report base file not found");

        } catch (ClassNotFoundException c) {
            System.out.println();
            c.printStackTrace();
            //return;
        }
        return temp;
    }

    /**
     * This method returns a water source report
     * if there exists a report with a matching
     * report number in the array list of totalReports.
     * @return Water Source Report
     * with the matching report number else returns null
     * @param reportNumber the report number
     */
    public static WaterSourceReport getReport(int reportNumber) {
        return reportList.getBackingArray().get(reportNumber - 48);
    }

    /**
     * This method returns the list of water source reports.
     *
     * @return Water Source Reports if it's not empty else returns null
     */
    public static ArrayList<WaterSourceReport> getAllReports() {
        if (reportList == null) {
            return null;
        }
        return reportList.getBackingArray();
    }



    /**
     * this method sets the current report
     *
     * @param givenReport the given report becomes the current report
     */
    public static void setCurrentReport(WaterSourceReport givenReport) {
        currentReport = givenReport;
    }

    /**
     * The current report is returned
     *
     * @return the current report in report management
     */
    public static WaterSourceReport getCurrentReport() {
        return currentReport;
    }

    /**
     * this method adds the given report to the array list of reports
     *
     * @param givenReport the given report
     */
    public static void addReport(WaterSourceReport givenReport) {
        reportList.addReport(givenReport);
        reportList.save();
        WaterSourceReportList tem = null;
        try {
            FileInputStream fileIn = new FileInputStream("ReportBase.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tem = (WaterSourceReportList) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println();
            c.printStackTrace();
            //return;
        }
        System.out.println(tem);
    }

    /**
     * getter for report List
     *
     * @return the report list
     */
    public static WaterSourceReportList getReportList() {
        return reportList;
    }
}