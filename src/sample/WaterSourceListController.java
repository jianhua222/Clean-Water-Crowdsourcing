package sample;

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
 * This class is the controller class for the WaterSourceList.fxml file
 */
public class WaterSourceListController {

    @FXML
    Button dropMapOfWaterReportsBtnl;

    @FXML
    Button waterReportVsTimeShowGraphBtn;

    @FXML
    Button addWaterSourceReportBtn;

    @FXML
    Button backToMainScreenBtn;

    @FXML
    ListView<Button> waterReportListViewBtnForm;

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

    @FXML
    private void initialize() {
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
        if (isEmpty == false) {
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
                displayText = "Report Num: ";
                reportNumberPulled = pulledReport.getReportNumber();
                displayText += reportNumberPulled.toString() + " ";
                displayText += pulledReport.getWaterSource() + " ";
                timeStampOfReport = pulledReport.getSourceTimeStamp().toString();
                dayOfReport = timeStampOfReport.substring(8,10);
                MonthOfReport = timeStampOfReport.substring(5,7);
                timeReportCreated = timeStampOfReport.substring(11,16);
                displayText +=  MonthOfReport + "/" + dayOfReport + " "
                        + timeReportCreated;
                btn.setText(displayText);
                btn.setOpacity(1.0);
            }
        } else {
            for (Button btn : reportBtnArrayList) {
                btn.setOpacity(0.0);
            }
            Button btn = reportBtnArrayList.get(0);
            btn.setText("No Reports Exist");
            btn.setOpacity(1.0);
        }

    }

    /**
     *  called when the user presses a waterReportBtn
     */
    @FXML
    private void handelReportBtn1Pressed() {
        WaterSourceReport pulledReport = WaterReportManagement.getReport(1);
        //Pass the pulled Report Data To The FXML file
    }

    /**
     *  called when the user presses a waterReportBtn
     */
    @FXML
    private void handelReportBtn2Pressed() {
        //Pull up the appropriate fxml and display contents
    }

    /**
     *  called when the user presses a waterReportBtn
     */
    @FXML
    private void handelBackBtnToMainScreenPressed() {
        //Switch to main screen
    }
}
