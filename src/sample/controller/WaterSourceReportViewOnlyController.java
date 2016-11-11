package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.model.Manager;
import sample.model.User;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;
import sample.model.Worker;

import java.io.IOException;


/**
 * Created by Payton on 10/18/16.
 * This is controller allows users to traverse through a list of reports
 */
public class WaterSourceReportViewOnlyController {

    @FXML
    private Button returnToListViewBtn;

    @FXML
    private Button backReportBtn;

    @FXML
    private Label timeStamp;

    @FXML
    private Label userWhoCreatedReport;

    @FXML
    private Label reportNumber;

    @FXML
    private Label sourceTypeLabel;

    @FXML
    private Label conditionTypeLabel;

    @FXML
    private Label consumableTypeLabel;

    @FXML
    private Label otherVirusLabel;

    @FXML
    private Label otherContaminantLabel;

    @FXML
    private Label otherVirusValue;

    @FXML
    private Label soContaminantValue;

    @FXML
    private Label locationLabel;

    /**
     *
     * initializes the water report for viewing
     */
    @FXML
    private void initialize() {
        WaterSourceReport pulledReport;
        pulledReport = WaterReportManagement.getCurrentReport();
        //timeStamp.setText(pulledReport.getSourceTimeStamp().toString());
        userWhoCreatedReport.setText(
                pulledReport.getUserWhoCreated().toString());
        Integer pulledReportNumber = pulledReport.getReportNumber();
        this.reportNumber.setText(pulledReportNumber.toString());
        this.locationLabel.setText(
                pulledReport.getLatitudeCoord()
                        + "ºN, " + pulledReport.getLongitutdeCoord() + "ºW");
        this.sourceTypeLabel.setText(pulledReport.getWaterSource());
        this.conditionTypeLabel.setText(pulledReport.getWaterCondition());
        this.consumableTypeLabel.setText(pulledReport.getConsumableCondition());
        // if user then hard code to zero
        User author = pulledReport.getUserWhoCreated();
        if (author instanceof Worker
                || author instanceof Manager) { // Add or manager
            Double otherVirusValue = pulledReport.getVirusPPM();
            this.otherVirusValue.setText(otherVirusValue.toString());
            Double soContaminantValue = pulledReport.getComtaminantPPM();
            this.soContaminantValue.setText(soContaminantValue.toString());
        } else {
            otherVirusLabel.setVisible(false);
            otherContaminantLabel.setVisible(false);
            otherVirusValue.setVisible(false);
            soContaminantValue.setVisible(false);
        }
    }

    /**
     *
     * the button handler for returning to the list
     */
    @FXML
    private void handelReturnToListViewBtnPressed() {
        WaterSourceListController controller = new WaterSourceListController();
        controller.init((Stage) returnToListViewBtn.getScene().getWindow());
    }

    /**
     *
     * the handler when the back button is pressed
     */
    @FXML
    private void handelBackPressed() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/mainScreen.fxml"));
            Stage primaryStage = (Stage) backReportBtn.getScene().getWindow();
            primaryStage.setTitle("Main Screen");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException f) {
            f.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }
}
