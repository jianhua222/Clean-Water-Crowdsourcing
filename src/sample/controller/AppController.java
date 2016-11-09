package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.main.Main;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.concurrent.ExecutionException;


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
        AnchorPane ap = new AnchorPane();
        ListView list = new ListView();
        if (WaterReportManagement.getAllReports() == null) {
            System.out.println("There are currently no reports to view");
        } else {
            ObservableList<String> oList = FXCollections.observableArrayList();
            for (WaterSourceReport wr : WaterReportManagement.getAllReports()) {
                oList.add(wr.toString());
            }
            list.setItems(oList);
            ap.getChildren().addAll(list);
            try{
                Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
                primaryStage.setTitle("Water Source Report");
                primaryStage.setScene(new Scene(ap, 600, 400));
                primaryStage.show();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
            list.setOnMouseClicked(event -> {
                BorderPane bp = new BorderPane();
                Button backButton = new Button("Back");
                bp.setTop(backButton);
                String reNum = (String) list.getSelectionModel().getSelectedItem();
                int some = (int) reNum.charAt(reNum.length() - 1);
                WaterSourceReport myreport = WaterReportManagement.getReport(some);
                //graph code
                List<String> months = new ArrayList<>();
                List<Double> vvalues = new ArrayList<>(); //virus ppm
                List<Double> cvalues = new ArrayList<>(); //containment ppm
                List<Integer> collisions = new ArrayList<>(); //track number of collision
                String s;
                Timestamp timestamp;
                timestamp = myreport.getSourceTimeStamp();
                s = getMonth(timestamp.toString());
                months.add(s);
                vvalues.add(myreport.getVirusPPM());
                cvalues.add(myreport.getComtaminantPPM());
                collisions.add(1);
                CategoryAxis month = new CategoryAxis();
                NumberAxis ppm = new NumberAxis();
                BarChart<String, Number> graph = new BarChart<String,Number>(month, ppm);
                XYChart.Series series1 = new XYChart.Series<>();
                XYChart.Series series2 = new XYChart.Series<>();
                series1.getData().addAll(new XYChart.Data(months.get(0),vvalues.get(0)));
                series2.getData().addAll(new XYChart.Data(months.get(0),cvalues.get(0)));
                graph.getData().addAll(series1, series2);
                bp.setCenter(graph);
                try{
                    Stage primaryStage = (Stage) ap.getScene().getWindow();
                    primaryStage.setTitle("Water Source Report");
                    primaryStage.setScene(new Scene(bp, 600, 400));
                    primaryStage.show();


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("I/O ERROR");
                }
            });


        }
        /*
        BorderPane bp = new BorderPane();
        List<String> months = new ArrayList<>();
        List<Double> vvalues = new ArrayList<>(); //virus ppm
        List<Double> cvalues = new ArrayList<>(); //containment ppm
        List<Integer> collisions = new ArrayList<>(); //track number of collision
        String s;
        Timestamp timestamp;
        for (WaterSourceReport report: WaterReportManagement.getAllReports()) {
            timestamp = report.getSourceTimeStamp();
            s = getMonth(timestamp.toString());
            if (months.contains(s)) {
                int index = months.indexOf(s);
                double virus = report.getVirusPPM();
                double comtam = report.getComtaminantPPM();
                vvalues.add(index, vvalues.get(index) + virus);
                cvalues.add(index, cvalues.get(index) + comtam);
                collisions.add(index, collisions.get(index) + 1);
            } else {
                months.add(s);
                vvalues.add(report.getVirusPPM());
                cvalues.add(report.getComtaminantPPM());
                collisions.add(1);
            }
        }
        //ObservableList<String> obslist = FXCollections.observableList(months);
        CategoryAxis month = new CategoryAxis();
        NumberAxis ppm = new NumberAxis();
        BarChart<String, Number> graph = new BarChart<String,Number>(month, ppm);
        XYChart.Series series1 = new XYChart.Series<>();
        XYChart.Series series2 = new XYChart.Series<>();
        for(int j = 0; j < collisions.size(); j++) {
            int denom = collisions.get(j);
            vvalues.add(j, vvalues.get(j) / denom);
            cvalues.add(j, cvalues.get(j) / denom);
        }
        for(int i = 0; i < months.size(); i++) {
            series1.getData().addAll(new XYChart.Data(months.get(i),vvalues.get(i)));
            series2.getData().addAll(new XYChart.Data(months.get(0),cvalues.get(i)));
        }
        //series1.getData().add(new XYChart.Data(months.get(1),ppmvalues.get(1)));
        graph.getData().addAll(series1, series2);
        // Back Button
        bp.setCenter(graph);
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
                Stage primaryStage = (Stage) backButton.getScene().getWindow();
                primaryStage.setTitle("Main Screen");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();

            } catch (IOException f) {
                f.printStackTrace();
                System.out.println("I/O ERROR");
            }
        });
        bp.setTop(backButton);
        try{
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            primaryStage.setTitle("Water Source Report");
            primaryStage.setScene(new Scene(bp, 600, 400));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
        */
    }
    private String getMonth(String s) {
        String sub = s.substring(5,7);
        String month;
        switch (sub) {
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
            default:
                month = "November";
                System.out.println("Fell to default");
        }
        return month;
    }


}
