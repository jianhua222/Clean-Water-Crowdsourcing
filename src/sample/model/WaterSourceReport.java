package sample.model;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * Created by Payton on 10/13/16.
 * Information Holder
 */
public class WaterSourceReport implements Serializable {
    //Security Log Variables
    //private WaterSourceActivity activity;
    private Timestamp sourceTimeStamp;
    private int reportNumber;

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

    /**
     * Constructor for water source report.
     * @param timestamp the timestamp
     * @param user the user
     * @param longitutdeCoord the longitude
     * @param latitudeCoord the latitude
     */
    public WaterSourceReport(Timestamp timestamp,
                             User user,
                             Double longitutdeCoord, Double latitudeCoord) {
        //Water Report Data Initilized
        this.sourceTimeStamp = timestamp;
        this.userWhoCreated = user;
        this.longitutdeCoord = longitutdeCoord;
        this.latitudeCoord = latitudeCoord;

        //Water Report Security Data Initilized
        this.banned = false;
        this.flagByUser = false;
        this.flagByWorker = false;
        this.reportNumber =
                WaterReportManagement.getReportList().getBackingArray().size();
    }

    /**
     * The water Source Report Constructor
     * @param timestamp time time stamp when the water source report is created
     * @param user the user who created the report
     */
    public WaterSourceReport(Timestamp timestamp, User user) {
        this(timestamp, user, 0.0, 0.0);
    }

    /**
     * getter for condition
     * @return the consumable condition
     */
    public String getConsumableCondition() {
        return consumableCondition;
    }

    /**
     * getter for water condition
     * @return the water condition
     */
    public String getWaterCondition() {
        return waterCondition;
    }

    /**
     * getter for water source
     * @return the water source
     */
    public String getWaterSource() {
        return waterSource;
    }

    /**
     * setter for consumable condition
     * @param consumableCondition the consumable condition
     */
    public void setConsumableCondition(String consumableCondition) {
        this.consumableCondition = consumableCondition;
    }

    /**
     * setter for consumable condition
     * @param waterCondition the water Condition
     */
    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }

    /**
     * setter for water source
     * @param waterSource the water source
     */
    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    /**
     * getter for time stamp
     * @return the timestamp
     */
    public Timestamp getSourceTimeStamp() {
        return sourceTimeStamp;
    }

    /**
     * getter for report number
     * @return the report number
     */
    public int getReportNumber() {
        return reportNumber;
    }

    /**
     * getter for latitude
     * @return the latitude
     */
    public double getLatitudeCoord() {
        return latitudeCoord;
    }

    /**
     * setter for condition
     * @param latitudeCoord the latitude
     */
    public void setLatitudeCoord(double latitudeCoord) {
        this.latitudeCoord = latitudeCoord;
    }

    /**
     * getter for longitude
     * @return the longitude
     */
    public double getLongitutdeCoord() {
        return longitutdeCoord;
    }

    /**
     * setter for longitude
     * @param longitutdeCoord the longitude
     */
    public void setLongitutdeCoord(double longitutdeCoord) {
        this.longitutdeCoord = longitutdeCoord;
    }

    /**
     * getter for the virus PPM
     * @return the VirusPPM
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /**
     * setter for virus PPM
     * @param  virusPPM virus PPM
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * getter for contaminant PPM
     * @return the contaminant PPM
     */
    public double getComtaminantPPM() {
        return comtaminantPPM;
    }

    /**
     * setter for Contaminant PPM
     * @param comtaminantPPM the containment PPM
     */
    public void setComtaminantPPM(double comtaminantPPM) {
        this.comtaminantPPM = comtaminantPPM;
    }

    /**
     * getter for consumable condition
     * @return the user who created the report
     */
    public User getUserWhoCreated() {
        return userWhoCreated;
    }

    /**
     * method to return description of the report
     * @return the description
     */
    public String getDescription() {
        String string = "<p>Report Number: " + reportNumber + "</br>"
                + "Report Time: " + sourceTimeStamp + "</br>"
                + "Location: " + latitudeCoord
                + ", " + longitutdeCoord + "</br>"
                + "Consumable condition: " + consumableCondition + "</br>"
                + "Water Condition: " + waterCondition + "</br>"
                + "Water Source: " + waterSource + "</br>"
                + "Virus PPM: " + virusPPM + "</br>"
                + "Contaminant PPM: " + comtaminantPPM + "</p>";
        return string;
    }

    /**
     * to string method for the water report
     * @return string representation of reports
     *
     */
    public String toString() {
        return "Report number: " + Integer.toString(reportNumber);
    }
}
