package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.controller.GMapsController;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Payton on 10/18/16.
 * This class is the controller class for the WaterSourceList.fxml file
 */
public class WaterSourceListController {

    @FXML
    Button dropMapOfWaterReportsBtn;

    @FXML
    Button waterReportVsTimeShowGraphBtn;

    @FXML
    Button addWaterSourceReportBtn;

    @FXML
    Button backToMainScreenBtn;

    @FXML
    Button reportBtn1;

    @FXML
    Button reportBtn2;

    @FXML
    Button reportBtn3;

    @FXML
    Button reportBtn4;

    @FXML
    Button reportBtn5;

    @FXML
    Button reportBtn6;

    @FXML
    Button reportBtn7;

    @FXML
    Button reportBtn8;

    @FXML
    Button reportBtn9;

    @FXML
    Button reportBtn10;

    @FXML
    Button reportBtn11;

    @FXML
    Button reportBtn12;

    @FXML
    Button reportBtn13;

    private static ArrayList<Button> reportBtnArrayList = new ArrayList<Button>();

    public void initialize() {
        reportBtnArrayList.add(reportBtn1);
        reportBtnArrayList.add(reportBtn2);
        reportBtnArrayList.add(reportBtn3);
        reportBtnArrayList.add(reportBtn4);
        reportBtnArrayList.add(reportBtn5);
        reportBtnArrayList.add(reportBtn6);
        reportBtnArrayList.add(reportBtn7);
        reportBtnArrayList.add(reportBtn8);
        reportBtnArrayList.add(reportBtn9);
        reportBtnArrayList.add(reportBtn10);
        reportBtnArrayList.add(reportBtn11);
        reportBtnArrayList.add(reportBtn12);
        reportBtnArrayList.add(reportBtn13);

        boolean isEmpty = WaterReportManagement.isEmpty();
        if (!isEmpty) {
             int numOfBtns2Populate = WaterReportManagement.getReport(1).getActiveSourceReports();
            if (numOfBtns2Populate > 13) {
                numOfBtns2Populate = 13;
            }
            Button btn;
            String displayText;
            Integer reportNumberPulled;
            WaterSourceReport pulledReport;
            String timeStampOfReport;
            String dayOfReport;
            String MonthOfReport;
            String timeReportCreated;
            for(int i = 0; i < 13; i++) {
                btn = reportBtnArrayList.get(i);
                pulledReport = WaterReportManagement.getReport(i + 1);
                if (pulledReport != null) {
                    displayText = "Report Num: ";
                    reportNumberPulled = pulledReport.getReportNumber();
                    displayText += reportNumberPulled.toString() + " ";
                    displayText += pulledReport.getWaterSource() + " ";
                    timeStampOfReport = pulledReport.getSourceTimeStamp().toString();
                    dayOfReport = timeStampOfReport.substring(8, 10);
                    MonthOfReport = timeStampOfReport.substring(5, 7);
                    timeReportCreated = timeStampOfReport.substring(11, 16);
                    displayText += MonthOfReport + "/" + dayOfReport + " "
                            + timeReportCreated;
                    btn.setText(displayText);
                    System.out.println("Hello " + btn.getText());
                }
            }
        } else {
            Button btn = reportBtnArrayList.get(0);
            btn.setText("No Reports Exist");
        }
    }

    public void init(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/WaterSourceList.fxml"));
            Stage primaryStage = stage;
            primaryStage.setTitle("Water Source List");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("WaterSourceList.fxml I/O ERROR");
        }
    }

    /**
     *  called when the user presses a waterReportBtn
     */
    @FXML
    private void handelReportBtn1Pressed() {
        WaterSourceReport pulledReport = WaterReportManagement.getReport(1);
        if(pulledReport != null) {
            WaterReportManagement.setCurrentReport(pulledReport);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/WaterSourceReportViewOnly.fxml"));
                Stage primaryStage = (Stage) reportBtn1.getScene().getWindow();
                primaryStage.setTitle("Water Source Report View");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
        } else {
            System.out.println("handelReportBtn1Pressed report number 1 is null!!");
        }
    }

    /**
     *  called when the user presses a waterReportBtn
     */
    @FXML
    private void handelReportBtn2Pressed() {
        WaterSourceReport pulledReport = WaterReportManagement.getReport(2);
        if (pulledReport != null) {
            WaterReportManagement.setCurrentReport(pulledReport);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/WaterSourceReportViewOnly.fxml"));
                Stage primaryStage = (Stage) reportBtn2.getScene().getWindow();
                primaryStage.setTitle("Water Source Report View");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
        } else {
            System.out.println("handelReportBtn2Pressed report number 1 is null!!");
        }
    }

    /**
     *  called when the user presses a waterReportBtn
     */
    @FXML
    private void handelBackBtnToMainScreenPressed() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
            Stage primaryStage = (Stage) backToMainScreenBtn.getScene().getWindow();
            primaryStage.setTitle("Main Screen");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }

    /**
     * initializes the google map
     */
    @FXML
    private void initializeMapBtnPressed() {
        Stage stage = (Stage) dropMapOfWaterReportsBtn.getScene().getWindow();
        ArrayList<WaterSourceReport> reports = WaterReportManagement.getAllReports();
        GMapsController controller = new GMapsController();
        controller.setWaterReports(reports);
        controller.setPrimaryStage(stage);
        controller.mapInitialized();
    }
}