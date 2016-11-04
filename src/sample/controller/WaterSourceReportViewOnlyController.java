package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.model.*;
import sample.model.UserManagement;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;

import java.io.IOException;


/**
 * Created by Payton on 10/18/16.
 * This is controller allows users to traverse through a list of reports
 */
public class WaterSourceReportViewOnlyController {

    @FXML
    Button returnToListViewBtn;

    @FXML
    Button backReportBtn;

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
    private Label VirusLabel;

    @FXML
    private Label ContaminantLabel;

    @FXML
    private Label VirusValue;

    @FXML
    private Label ContaminantValue;

    @FXML
    private Label locationLabel;


    private WaterSourceReport pulledReport;

    /**
     *
     * @param givenReport The desired report to be read
     */
    public void setPulledReport(WaterSourceReport givenReport) {
        this.pulledReport = givenReport;
    }

    @FXML
    private void initialize() {
        this.pulledReport = WaterReportManagement.getCurrentReport();
        this.timeStamp.setText(pulledReport.getSourceTimeStamp().toString());
        this.userWhoCreatedReport.setText(pulledReport.getUserWhoCreated().getUserName());
        Integer pulledReportNumber = pulledReport.getReportNumber();
        this.reportNumber.setText(pulledReportNumber.toString());
        this.locationLabel.setText(pulledReport.getLatitudeCoord() + "ºN, " + pulledReport.getLongitutdeCoord() + "ºW");
        this.sourceTypeLabel.setText(pulledReport.getWaterSource());
        this.conditionTypeLabel.setText(pulledReport.getWaterCondition());
        this.consumableTypeLabel.setText(pulledReport.getConsumableCondition());
        // if user then hard code to zero
        User author = pulledReport.getUserWhoCreated();
        if (author instanceof Worker || author instanceof Manager) { // Add or manager
            Double virusValue = pulledReport.getVirusPPM();
            this.VirusValue.setText(virusValue.toString());
            Double contaminantValue = pulledReport.getComtaminantPPM();
            this.ContaminantValue.setText(contaminantValue.toString());
        } else {
            VirusLabel.setVisible(false);
            ContaminantLabel.setVisible(false);
            VirusValue.setVisible(false);
            ContaminantValue.setVisible(false);
        }
    }

    @FXML
    private void handelReturnToListViewBtnPressed() {
        WaterSourceListController controller = new WaterSourceListController();
        controller.init((Stage) returnToListViewBtn.getScene().getWindow());
    }

    @FXML
    private void handelBackPressed() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
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
