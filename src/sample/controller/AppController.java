package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import sample.main.Main;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;


/**
 * Created by Ashwin on 9/23/16.
 */
public class AppController {
    // **Note Change UI to where we are only changing scenes
    // and not creating new stages each time.

    @FXML
    private javafx.scene.control.Button logoutButton;

    /**
     * This method handles closing the main app, when the user chooses to do so.
     *
     */
    @FXML
    private void something() {
        try {
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            Main mainclass = new Main();
            mainclass.start(primaryStage);
        } catch (Exception e) {
        }
    }

    /**
     * This method sets up the stage and screen for showing the user info.
     *
     */
    @FXML
    private void userInfo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/UserInfo.fxml"));
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            primaryStage.setTitle("User Information");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            //System.out.println("I/O ERROR");
        }
    }

    @FXML
    private void toReportScreen() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/WaterSourceReport.fxml"));
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            primaryStage.setTitle("Water Source Report");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }

    /**
     * This method sets up the stage and screen for showing the user info.
     *
     */
    @FXML
    private void toWaterSourceList() {
        WaterSourceListController controller = new WaterSourceListController();
        controller.init((Stage) logoutButton.getScene().getWindow());
    }

    @FXML
    private void showGraph() {
        List<String> months = new ArrayList<>();
        List<Double> vvalues = new ArrayList<>(); //virus ppm
        List<Double> cvalues = new ArrayList<>(); //containment ppm
        Timestamp timestamp;
        for (WaterSourceReport report: WaterReportManagement.getAllReports()) {
            vvalues.add(report.getVirusPPM());
            cvalues.add(report.getVirusPPM());
            timestamp = report.getSourceTimeStamp();
            months.add(getMonth(timestamp.toString()));

        }
        //ObservableList<String> obslist = FXCollections.observableList(months);
        CategoryAxis month = new CategoryAxis();
        NumberAxis ppm = new NumberAxis();
        BarChart<String, Number> graph = new BarChart<String,Number>(month, ppm);
        XYChart.Series series1 = new XYChart.Series<>();
        XYChart.Series series2 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data(months.get(0),vvalues.get(0)));
        series2.getData().add(new XYChart.Data(months.get(0),cvalues.get(0)));
        //series1.getData().add(new XYChart.Data(months.get(1),ppmvalues.get(1)));
        graph.getData().addAll(series1, series2);
        try{
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            primaryStage.setTitle("Water Source Report");
            primaryStage.setScene(new Scene(graph, 600, 400));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }
    private String getMonth(String s) {
        String sub = s.substring(5,7);
        String month;
        switch (sub) {
            case "11":
                month = "November";
                break;
            default:
                month = "November";
                System.out.println("Fell to default");
        }
        return month;
    }


}
