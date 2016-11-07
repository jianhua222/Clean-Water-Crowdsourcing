package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import sample.main.Main;

import java.io.IOException;


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
        Axis XAxis = new NumberAxis();
        Axis YAxis = new CategoryAxis();
        BarChart graph = new BarChart(XAxis, YAxis);
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("november", 20));
        graph.getData().addAll(series1);
        try {
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            primaryStage.setTitle("Main Screen");
            primaryStage.setScene(new Scene(graph, 600, 400));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }


}
