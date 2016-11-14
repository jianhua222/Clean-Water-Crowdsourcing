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
    private final Timestamp sourceTimeStamp;
    private int reportNumber;




    // Report Variables
    private double latitudeCoordinate;
    private double longitudeCoordinate;
    private double virusPPM;
    private double contaminantPPM;
    private final User userWhoCreated;
    private String consumableCondition;
    private String waterCondition;
    private String waterSource;

    /**
     * Constructor for water source report.
     * @param timestamp the timestamp
     * @param user the user
     * @param longitudeCoordinate the longitude
     * @param latitudeCoordinate the latitude
     */
    private WaterSourceReport(Timestamp timestamp,
                             User user,
                             Double longitudeCoordinate,
                              Double latitudeCoordinate) {
        //Water Report Data Initialized
        this.sourceTimeStamp = timestamp;
        this.userWhoCreated = user;
        this.longitudeCoordinate = longitudeCoordinate;
        this.latitudeCoordinate = latitudeCoordinate;

        //Water Report Security Data Initialized
        this.reportNumber =
                WaterReportManagement.getReportList().getBackingArray().size();
    }
    public WaterSourceReport(){
        sourceTimeStamp = null;
        userWhoCreated = null;
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
    public double getLatitudeCoordinate() {
        return latitudeCoordinate;
    }

    /**
     * setter for condition
     * @param latitudeCoordinate the latitude
     */
    public void setLatitudeCoordinate(double latitudeCoordinate) {
        this.latitudeCoordinate = latitudeCoordinate;
    }

    /**
     * getter for longitude
     * @return the longitude
     */
    public double getLongitudeCoordinate() {
        return longitudeCoordinate;
    }

    /**
     * setter for longitude
     * @param longitudeCoordinate the longitude
     */
    public void setLongitudeCoordinate(double longitudeCoordinate) {
        this.longitudeCoordinate = longitudeCoordinate;
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
    public double getContaminantPPM() {
        return contaminantPPM;
    }

    /**
     * setter for Contaminant PPM
     * @param contaminantPPM the containment PPM
     */
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
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

        return "<p>Report Number: " + reportNumber + "</br>"
                + "Report Time: " + sourceTimeStamp + "</br>"
                + "Location: " + latitudeCoordinate
                + ", " + longitudeCoordinate + "</br>"
                + "Consumable condition: " + consumableCondition + "</br>"
                + "Water Condition: " + waterCondition + "</br>"
                + "Water Source: " + waterSource + "</br>"
                + "Virus PPM: " + virusPPM + "</br>"
                + "Contaminant PPM: " + contaminantPPM + "</p>";
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
