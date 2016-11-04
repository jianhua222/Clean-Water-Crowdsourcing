package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.*;

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
    private RadioButton changeLoction;

    @FXML
    private Button submitH2OReport;

    @FXML
    private Button cancelReportBtn;

    @FXML
    private Button backBtn;

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
    private TextField locationOfReport;

    @FXML
    private Label VirusLabel;

    @FXML
    private Label ContaminantLabel;

    @FXML
    private void initialize() {
        //Get Date Of Creation
        Date creationDate = new Date();
        Timestamp _creationDate = new Timestamp(creationDate.getTime());
        this.timeStamp.setText(_creationDate.toString());

        //Pass user info here
        // How to pass this information? Ans: User Management
        // Pull User Info Here
        this.userWhoCreatedReport.setText(UserManagement.getUser().getUserName());

        this.newReport = new WaterSourceReport(_creationDate, UserManagement.getUser());

        //Show Report Number
        this.reportNumber.setText(String.valueOf(newReport.getReportNumber()));

        //Show Location
        //Augment this to pull current location
        this.locationOfReport.setText(new String("33.7756, 84.3963"));

        //Initilize ComboBoxes
        ObservableList<String> consumableConditionStuff = comboBoxConsumableConditionIteams();
        this.consumableConditionComboBox.setItems(consumableConditionStuff);
        consumableConditionComboBox.setPromptText("Select Consumability");

        ObservableList<String> sourceTypeStuff = comboBoxSourceTypeIteams();
        this.sourceTypeComboBox.setItems(sourceTypeStuff);
        sourceTypeComboBox.setPromptText("Select Source");

        ObservableList<String> waterConditionStuff = comboBoxWaterConditionIteams();
        this.h20SourceConditionComboBox.setItems(waterConditionStuff);
        h20SourceConditionComboBox.setPromptText("Select Condition");

        User currentUser = UserManagement.getUser();
        if (currentUser instanceof Worker || currentUser instanceof Manager ) {
            virusPPM.setText("00.00");
            contaminantPPM.setText("00.00");
        } else {
            //Users can not submit virus/contaminant information
            virusPPM.setText("00.00");
            contaminantPPM.setText("00.00");
            VirusLabel.setVisible(false);
            ContaminantLabel.setVisible(false);
            virusPPM.setVisible(false);
            contaminantPPM.setVisible(false);
        }
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
        newReport.setSourceReportTotal(newReport.getSourceReportTotal() - 1);
        initialize();
    }
    /**
     * Back button pressed
     */
    @FXML
    private void handelBackButtonPressed() {
        try {
            newReport.setSourceReportTotal(newReport.getSourceReportTotal() - 1);
            Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
            Stage primaryStage = (Stage) backBtn.getScene().getWindow();
            primaryStage.setTitle("Water Source Report View");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
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
            if (sourceTypeComboBox.getValue() != null) {
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

            if (locationOfReport.getText() != null) {
                String location = locationOfReport.getText();
                if (location.contains(", ")) {
                    String[] locs = location.split(", ");
                    newReport.setLatitudeCoord(Double.parseDouble(locs[0]));
                    newReport.setLongitutdeCoord(Double.parseDouble(locs[1]));
                }
            }

            WaterReportManagement.addReport(this.newReport);
            initialize();
        } else {
            //virusPPM.setText("00.00");
            //contaminantPPM.setText("00.00");
        }
    }

    /**
     *  Test the input for contaminants
     * @return ture is the input is valid and false if otherwise
     */
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
