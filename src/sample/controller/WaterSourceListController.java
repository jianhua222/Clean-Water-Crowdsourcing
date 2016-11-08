package sample.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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
    ListView<String> listViewFX;

    public void initialize() {
        if (WaterReportManagement.getAllReports() == null) {
            System.out.println("There are currently no reports to view");
        } else {
            ObservableList<String> oList = FXCollections.observableArrayList();
            for (WaterSourceReport wr : WaterReportManagement.getAllReports()
                    ) {
                oList.add(wr.toString());
            }
            listViewFX.setItems(oList);
        }
    }

    @FXML
    public void reportPressed() {

        String reNum = listViewFX.getSelectionModel().getSelectedItem();
        int some = (int) reNum.charAt(reNum.length() - 1);
        WaterSourceReport toShow = WaterReportManagement.getReport(some);
        WaterReportManagement.setCurrentReport(toShow);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/WaterSourceReportViewOnly.fxml"));
            Stage primaryStage = (Stage) backToMainScreenBtn.getScene().getWindow();;
            primaryStage.setTitle("Water Report");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("WaterSourceList.fxml I/O ERROR");
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

    @FXML
    private void backBut() {
        try {
            Stage primaryStage = (Stage) dropMapOfWaterReportsBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
            primaryStage.setTitle("Main Screen");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException f) {
            f.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }

    @FXML
    private void addRep() {
        try {
            Stage primaryStage = (Stage) dropMapOfWaterReportsBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/WaterSourceReport.fxml"));
            primaryStage.setTitle("Add a report");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException f) {
            f.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }

}
