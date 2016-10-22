package sample;

import java.util.Date;
import java.sql.Timestamp;
/**
 * Created by Payton on 10/13/16.
 * Information Holder
 */
public class WaterSourceReport {
    //Security Log Variables
    //private WaterSourceActivity activity;
    private Timestamp sourceTimeStamp;
    private int reportNumber;
    private static int sourceReportTotal;
    private static int activeSourceReports;
    private static int deactivatedSourceReports;

    // Report Variables
    private double latitudeCoord;
    private double longitutdeCoord;
    private double virusPPM;
    private double comtaminantPPM;
    private User userWhoCreated;
    private String consumableCondition;
    private String waterCondition;
    private String waterSource;

    // Safty Variables
    private User workerWhoVerified;
    private boolean acceptedByWorkers;
    private boolean flagByUser;
    private boolean flagByWorker;
    private int totalFlagCount;
    private int userFlagCount;
    private int workerFlagCount;
    private boolean banned;

    WaterSourceReport(Timestamp timestamp, User user, Double _longitutdeCoord,
                      Double _latitudeCoord) {
        //Water Report Data Initilized
        this.sourceTimeStamp = timestamp;
        this.userWhoCreated = user;
        this.longitutdeCoord = _longitutdeCoord;
        this.latitudeCoord = _latitudeCoord;

        //Water Report Security Data Initilized
        this.banned = false;
        this.flagByUser = false;
        this.flagByWorker = false;
        this.sourceReportTotal++;
        this.activeSourceReports = this.sourceReportTotal;
        this.reportNumber = activeSourceReports;
    }

    /**
     * The water Source Report Constructor
     * @param timestamp time time stamp when the water source report is created
     * @param user the user who created the report
     */
    WaterSourceReport(Timestamp timestamp, User user) {
        this(timestamp, user, 0.0, 0.0);
    }


    public String getConsumableCondition() {
        return consumableCondition;
    }

    public String getWaterCondition() {
        return waterCondition;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public int getTotalFlagCount() {
        return totalFlagCount;
    }

    public void setTotalFlagCount(int totalFlagCount) {
        this.totalFlagCount = totalFlagCount;
    }

    public int getUserFlagCount() {
        return userFlagCount;
    }

    public void setUserFlagCount(int userFlagCount) {
        this.userFlagCount = userFlagCount;
    }

    public int getWorkerFlagCount() {
        return workerFlagCount;
    }

    public void setWorkerFlagCount(int workerFlagCount) {
        this.workerFlagCount = workerFlagCount;
    }

    public void setConsumableCondition(String consumableCondition) {
        this.consumableCondition = consumableCondition;
    }

    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public User getWorkerWhoVerified() {
        return workerWhoVerified;
    }

    public void setWorkerWhoVerified(User workerWhoVerified) {
        this.workerWhoVerified = workerWhoVerified;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public Timestamp getSourceTimeStamp() {
        return sourceTimeStamp;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public static int getSourceReportTotal() {
        return sourceReportTotal;
    }

    public static void setSourceReportTotal(int sourceReportTotal) {
        WaterSourceReport.sourceReportTotal = sourceReportTotal;
    }

    public static int getActiveSourceReports() {
        return activeSourceReports;
    }

    public static void setActiveSourceReports(int activeSourceReports) {
        WaterSourceReport.activeSourceReports = activeSourceReports;
    }

    public static int getDeactivatedSourceReports() {
        return deactivatedSourceReports;
    }

    public static void setDeactivatedSourceReports(int deactivatedSourceReports) {
        WaterSourceReport.deactivatedSourceReports = deactivatedSourceReports;
    }

    public double getLatitudeCoord() {
        return latitudeCoord;
    }

    public void setLatitudeCoord(double latitudeCoord) {
        this.latitudeCoord = latitudeCoord;
    }

    public double getLongitutdeCoord() {
        return longitutdeCoord;
    }

    public void setLongitutdeCoord(double longitutdeCoord) {
        this.longitutdeCoord = longitutdeCoord;
    }

    public double getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public double getComtaminantPPM() {
        return comtaminantPPM;
    }

    public void setComtaminantPPM(double comtaminantPPM) {
        this.comtaminantPPM = comtaminantPPM;
    }

    public boolean isAcceptedByWorkers() {
        return acceptedByWorkers;
    }

    public void setAcceptedByWorkers(boolean acceptedByWorkers) {
        this.acceptedByWorkers = acceptedByWorkers;
    }

    public boolean isFlagByUser() {
        return flagByUser;
    }

    public void setFlagByUser(boolean flagByUser) {
        this.flagByUser = flagByUser;
    }

    public boolean isFlagByWorker() {
        return flagByWorker;
    }

    public void setFlagByWorker(boolean flagByWorker) {
        this.flagByWorker = flagByWorker;
    }

    public User getUserWhoCreated() {
        return userWhoCreated;
    }

    public void setUserWhoCreated(User userWhoCreated) {
        this.userWhoCreated = userWhoCreated;
    }

    public String getDescription() {
        String string = "<p>Report Number: " + reportNumber + "</p>" +
                "<p>Report Time: " + sourceTimeStamp + "</p>" +
                "<p>Location: " + latitudeCoord + ", " + longitutdeCoord + "</p>" +
                "<p>Virus PPM: " + virusPPM + "</p>" +
                "<p>Contaminant PPM: " + comtaminantPPM + "</p>";
        return string;
    }
}
