package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * Created by Payton on 10/18/16.
 * This is controller allows users to traverse through a list of reports
 */
public class WaterSourceReportViewOnlyController {

    @FXML
    Button returnToListViewBtn;

    @FXML
    Button backReportBtn;

    @FXML
    Button nextReportBtn;

    @FXML
    private Label timeStamp;

    @FXML
    private Label userWhoCreatedReport;

    @FXML
    private Label reportNumber;

    @FXML
    private Label locationOfReport;

    @FXML
    private Label sourceTypeLabel;

    @FXML
    private Label conditionTypeLabel;

    @FXML
    private Label consumableTypeLabel;

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
        this.timeStamp.setText(pulledReport.getSourceTimeStamp().toString());
        this.userWhoCreatedReport.setText(UserManagement.getUser().getUserName());
        Integer pulledReportNumber = pulledReport.getReportNumber();
        this.reportNumber.setText(pulledReportNumber.toString());
        this.locationOfReport.setText(pulledReport.getLongitutdeCoord() + " "
                + pulledReport.getLatitudeCoord());
        this.sourceTypeLabel.setText(pulledReport.getWaterSource());
        this.conditionTypeLabel.setText(pulledReport.getWaterCondition());
        this.consumableTypeLabel.setText(pulledReport.getConsumableCondition());
    }

    @FXML
    private void handelReturnToListViewBtnPressed() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("WaterSourceList.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Water Source List View");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("I/O ERROR in handelReturnToListViewBtnPressed"
                    + "() in WaterSourceList.fxml");
        }
    }

    @FXML
    private void handelBackPressed() {
        int reportNumberDesired = pulledReport.getReportNumber() - 1;
        if (reportNumberDesired == 0) {
            reportNumberDesired = pulledReport.getSourceReportTotal();
        }
        pulledReport = WaterReportManagement.getReport(reportNumberDesired);
        initialize();
    }

    @FXML
    private void handelNextButtonPressed() {
        //pull the next listed report
        int reportNumberDesired = pulledReport.getReportNumber() + 1;
        if (reportNumberDesired == pulledReport.getSourceReportTotal() + 1) {
            reportNumberDesired = 1;
        }
        pulledReport = WaterReportManagement.getReport(reportNumberDesired);
        initialize();
    }
}
