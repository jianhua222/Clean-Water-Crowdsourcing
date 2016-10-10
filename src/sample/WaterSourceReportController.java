package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Payton on 10/13/16.
 * Interfacer
 */
public class WaterSourceReportController {

    User user;

    WaterSourceReport newReport;

    @FXML
    private Label timeStamp;

    @FXML
    private Label userWhoCreatedReport;

    @FXML
    private Label reportNumber;

    @FXML
    private Label locationOfReport;

    @FXML
    private RadioButton changeLoction;

    @FXML
    private Button submitH2OReport;

    @FXML
    private Button cancelReportBtn;

    @FXML
    private ComboBox sourceTypeComboBox;

    @FXML
    private ComboBox h20SourceConditionComboBox;

    @FXML
    private ComboBox consumableConditionComboBox;

    @FXML
    private TextField virusPPM;

    @FXML
    private TextField contaminantPPM;

    @FXML
    private void initialize() {
        //Get Date Of Creation
        Date creationDate = new Date();
        Timestamp _creationDate = new Timestamp(creationDate.getTime());
        this.timeStamp.setText(_creationDate.toString());

        //Pass user info here
        // How to pass this information? Ans: User Management
        // Pull User Info Here
        this.userWhoCreatedReport.setText("Payton Jonson");

        this.newReport = new WaterSourceReport(_creationDate, null);

        //Show Report Number
        this.reportNumber.setText(String.valueOf(newReport.getReportNumber()));

        //Show Location
        //Augment this to pull current location
        this.locationOfReport.setText(new String("33.7756° N, 84.3963° W"));

        //Initilize ComboBoxes
        ObservableList<String> consumableConditionStuff = comboBoxConsumableConditionIteams();
        this.consumableConditionComboBox.setItems(consumableConditionStuff);


        ObservableList<String> sourceTypeStuff = comboBoxSourceTypeIteams();
        this.sourceTypeComboBox.setItems(sourceTypeStuff);


        ObservableList<String> waterConditionStuff = comboBoxWaterConditionIteams();
        this.h20SourceConditionComboBox.setItems(waterConditionStuff);

    }
    /**
     * called automatically after load
     * @return _comboBox as an obseravable list
     */
    private static ObservableList<String> comboBoxWaterConditionIteams() {
        WaterCondition[] tempStrings= WaterCondition.values();
        List<String> tempList = new ArrayList<>();

        for(int i = 0; i < tempStrings.length; i++) {
            tempList.add(tempStrings[i].toString());
        }

        ObservableList<String> temp = FXCollections.observableList(tempList);
        return temp;
    }


    /**
     * called automatically after load
     * @return _comboBox as an obseravable list
     */
    private static ObservableList<String> comboBoxSourceTypeIteams() {
        WaterSource[] tempStrings= WaterSource.values();
        List<String> tempList = new ArrayList<>();

        for(int i = 0; i < tempStrings.length; i++) {
            tempList.add(tempStrings[i].toString());
        }

        ObservableList<String> temp = FXCollections.observableList(tempList);
        return temp;
    }

    /**
     * called automatically after load
     * @return _comboBox as an obseravable list
     */
    private static ObservableList<String> comboBoxConsumableConditionIteams() {
        ConsumableCondition[] tempStrings= ConsumableCondition.values();
        List<String> tempList = new ArrayList<>();

        for(int i = 0; i < tempStrings.length; i++) {
            tempList.add(tempStrings[i].toString());
        }

        ObservableList<String> temp = FXCollections.observableList(tempList);
        return temp;
    }

    /**
     *  called when the user presses cancel
     */
    @FXML
    private void handelCancelPressed() {
        // Forget Current Data and Return to blank report
        newReport.setReportNumber(newReport.getReportNumber() - 1);
        initialize();
    }
    /**
     * Back button pressed
     */
    @FXML
    private void handelBackButtonPressed() {
        //Go to the main screen
        //Reach Out to another class for this

    }

    /**
     * Submit button pressed
     */
    @FXML
    private void handelSubmitButtonPressed() {
        //Save the data submitted by user
        if(inputValid()) {
            String consumableConditon;
            if (consumableConditionComboBox.getValue() != null) {
                consumableConditon = consumableConditionComboBox
                        .getValue().toString();
            } else {
                //Implement a UI error for future iteration
                consumableConditon = ConsumableCondition.NA.toString();
            }
            this.newReport.setConsumableCondition(consumableConditon);

            String h20SourceCondition;
            if (h20SourceConditionComboBox.getValue() != null) {
                h20SourceCondition = h20SourceConditionComboBox
                        .getValue().toString();
            } else {
                //Implement a UI error for future iteration
                h20SourceCondition = WaterCondition.NA.toString();
            }
            this.newReport.setWaterCondition(h20SourceCondition);

            String sourceType;
            if(sourceTypeComboBox.getValue() != null) {
                sourceType = sourceTypeComboBox
                        .getValue().toString();
            } else {
                sourceType = WaterSource.NA.toString();
            }
            this.newReport.setWaterSource(sourceType);

            double virusPPMDouble = Double.parseDouble(virusPPM.getText());
            this.newReport.setVirusPPM(virusPPMDouble);

            double contaminantPPMDouble = Double.parseDouble(contaminantPPM.getText());
            this.newReport.setComtaminantPPM(contaminantPPMDouble);


        } else {


        }
    }

    private boolean inputValid() {
        if (virusPPM.getText() != null && contaminantPPM.getText() != null ) {
            String vPPM = virusPPM.getText();
            String cPPM = contaminantPPM.getText();

            vPPM = vPPM.trim();
            vPPM = vPPM.replaceAll("\\s", "");

            cPPM = cPPM.trim();
            cPPM = cPPM.replaceAll("\\s", "");

            String vPPM4Strings = vPPM.substring(0, 5);
            String cPPM4Strings = cPPM.substring(0, 5);

            try {
                double vPPM4SigFigs = Double.parseDouble(vPPM4Strings);
                double cPPM4SigFigs = Double.parseDouble(cPPM4Strings);
            } catch (Exception e) {
                //On another iteration implement a UI catch
                virusPPM.setText("00.00");
                contaminantPPM.setText("00.00");
                return false;
            }
            virusPPM.setText(vPPM4Strings);
            contaminantPPM.setText(cPPM4Strings);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change Location Button Pressed
     */
    @FXML
    private void handelChangeLocation() {
     // Offer a menu that allows a user to add GPS coordinates
    }

}
