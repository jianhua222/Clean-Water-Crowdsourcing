package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.ConsumableCondition;
import sample.model.User;
import sample.model.UserManagement;
import sample.model.WaterCondition;
import sample.model.WaterReportManagement;
import sample.model.WaterSource;
import sample.model.WaterSourceReport;
import sample.model.Worker;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Payton on 10/13/16.
 *
 */
public class WaterSourceReportController {




    private Timestamp otherCreationDate;

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
    private Label newVirusLabel;

    @FXML
    private Label newContaminantLabel;

    /**
     * initializes the water source report
     */
    @FXML
    private void initialize() {
        //Get Date Of Creation

        Date creationDate = new Date();
        this.otherCreationDate = new Timestamp(creationDate.getTime());
        this.timeStamp.setText(otherCreationDate.toString());

        //Pass user info here
        // How to pass this information? Ans: User Management
        // Pull User Info Here
        this.userWhoCreatedReport.setText(
                UserManagement.getUser().getUserName());


        //Show Report Number
        if (WaterReportManagement.getAllReports() == null) {
            this.reportNumber.setText("1");
        } else {
            this.reportNumber.setText(String.valueOf(
                    WaterReportManagement.getAllReports().size()));
        }

        //Show Location
        //Augment this to pull current location
        this.locationOfReport.setText("33.7756, 84.3963");

        //Initialize ComboBoxes
        ObservableList<String> consumableConditionStuff =
                comboBoxConsumableConditionItems();
        this.consumableConditionComboBox.setItems(consumableConditionStuff);
        consumableConditionComboBox.setPromptText("Select Consumability");

        ObservableList<String> sourceTypeStuff = comboBoxSourceTypeIteams();
        this.sourceTypeComboBox.setItems(sourceTypeStuff);
        sourceTypeComboBox.setPromptText("Select Source");

        ObservableList<String> waterConditionStuff =
                comboBoxWaterConditionItems();
        this.h20SourceConditionComboBox.setItems(waterConditionStuff);
        h20SourceConditionComboBox.setPromptText("Select Condition");

        User currentUser = UserManagement.getUser();
        if (currentUser instanceof Worker) {
            virusPPM.setText("00.00");
            contaminantPPM.setText("00.00");
        } else {
            //Users can not submit virus/contaminant information
            virusPPM.setText("00.00");
            contaminantPPM.setText("00.00");
            newVirusLabel.setVisible(false);
            newContaminantLabel.setVisible(false);
            virusPPM.setVisible(false);
            contaminantPPM.setVisible(false);
        }
    }
    /**
     * called automatically after load
     * @return _comboBox as an observable list
     */
    private static ObservableList<String> comboBoxWaterConditionItems() {
        WaterCondition[] tempStrings = WaterCondition.values();
        List<String> tempList = new ArrayList<>();

        for (WaterCondition tempString : tempStrings) {
            tempList.add(tempString.toString());
        }

        //ObservableList<String> temp = FXCollections.observableList(tempList);
        return FXCollections.observableList(tempList);
    }


    /**
     * called automatically after load
     * @return _comboBox as an observable list
     */
    private static ObservableList<String> comboBoxSourceTypeIteams() {
        WaterSource[] tempStrings = WaterSource.values();
        List<String> tempList = new ArrayList<>();

        for (WaterSource tempString : tempStrings) {
            tempList.add(tempString.toString());
        }

        //ObservableList<String> temp = FXCollections.observableList(tempList);
        return FXCollections.observableList(tempList);
    }

    /**
     * called automatically after load
     * @return _comboBox as an observable list
     */
    private static ObservableList<String> comboBoxConsumableConditionItems() {
        ConsumableCondition[] tempStrings = ConsumableCondition.values();
        List<String> tempList = new ArrayList<>();

        for (ConsumableCondition tempString : tempStrings) {
            tempList.add(tempString.toString());
        }
        return FXCollections.observableList(tempList);
    }


    /**
     * Back button pressed
     */
    @FXML
    private void handelBackButtonPressed() {
        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource("/mainScreen.fxml"));
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
        WaterSourceReport newReport;
        //Save the data submitted by user
        if (inputValid()) {
            String consumableCondition;
            if (consumableConditionComboBox.getValue() != null) {
                consumableCondition = consumableConditionComboBox
                        .getValue().toString();
            } else {
                //Implement a UI error for future iteration
                consumableCondition = ConsumableCondition.NA.toString();
            }
            newReport = new WaterSourceReport(
                    this.otherCreationDate, UserManagement.getUser());
            newReport.setConsumableCondition(consumableCondition);

            String h20SourceCondition;
            if (h20SourceConditionComboBox.getValue() != null) {
                h20SourceCondition = h20SourceConditionComboBox
                        .getValue().toString();
            } else {
                //Implement a UI error for future iteration
                h20SourceCondition = WaterCondition.NA.toString();
            }
            newReport.setWaterCondition(h20SourceCondition);

            String sourceType;
            if (sourceTypeComboBox.getValue() != null) {
                sourceType = sourceTypeComboBox
                        .getValue().toString();
            } else {
                sourceType = WaterSource.NA.toString();
            }
            newReport.setWaterSource(sourceType);

            double virusPPMDouble = Double.parseDouble(virusPPM.getText());
            newReport.setVirusPPM(virusPPMDouble);

            double contaminantPPMDouble =
                    Double.parseDouble(contaminantPPM.getText());
            newReport.setContaminantPPM(contaminantPPMDouble);

            if (locationOfReport.getText() != null) {
                String location = locationOfReport.getText();
                if (location.contains(", ")) {
                    String[] locationArray = location.split(", ");
                    newReport.setLatitudeCoordinate(
                            Double.parseDouble(locationArray[0]));
                    newReport.setLongitudeCoordinate(
                            Double.parseDouble(locationArray[1]));
                }
            }

            WaterReportManagement.addReport(newReport);
            initialize();
        } else {
            //virusPPM.setText("00.00");
            //contaminantPPM.setText("00.00");
            int p = 0;
        }
    }

    /**
     *  Test the input for contaminants
     * @return true if the input is valid and false if otherwise
     */
    private boolean inputValid() {
        if (virusPPM.getText() != null && contaminantPPM.getText() != null) {
            String vPPM = virusPPM.getText();
            String cPPM = contaminantPPM.getText();

            vPPM = vPPM.trim();
            vPPM = vPPM.replaceAll("\\s", "");

            cPPM = cPPM.trim();
            cPPM = cPPM.replaceAll("\\s", "");

            String vPPM4Strings = vPPM.substring(0, 5);
            String cPPM4Strings = cPPM.substring(0, 5);

            try {
                int p = 5;
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


}
