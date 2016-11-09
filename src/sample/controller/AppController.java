package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.main.Main;
import sample.model.User;
import sample.model.UserManagement;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Ashwin on 9/23/16.
 */
public class AppController {
    // **Note Change UI to where we are only changing scenes
    // and not creating new stages each time.

    @FXML
    private javafx.scene.control.Button logoutButton;

    @FXML
    private javafx.scene.control.Button graphfx;

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
            System.out.println("mainStage error");
        }
    }

    /**
     * This method sets up the stage and screen for showing the user info.
     *
     */
    @FXML
    public void initialize() {
        User currentUser = UserManagement.getUser();
        if (!currentUser.getType().equals("Manager")) {
            graphfx.setVisible(false);
        }
        //System.out.println("Inside Initialize");
    }

    /**
     * This method sets up the stage and screen for showing the user info.
     *
     */
    @FXML
    private void userInfo() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/UserInfo.fxml"));
            Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
            primaryStage.setTitle("User Information");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("userInfo Error");
        }
    }

    /**
     * This method takes user to report screen.
     *
     */
    @FXML
    private void toReportScreen() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/WaterSourceReport.fxml"));
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

    /**
     * This method shows the graph.
     *
     */

    @FXML
    private void showGraph() {
        AnchorPane ap = new AnchorPane();
        ListView<String> list = new ListView<>();
        if (WaterReportManagement.getAllReports() == null) {
            System.out.println("There are currently no reports to view");
        } else {
            ArrayList<Double> lats = new ArrayList<Double>();
            ArrayList<ArrayList<Double>> virusvalues =
                    new ArrayList<ArrayList<Double>>();
            //Array List within Array List to hold months within each location
            ArrayList<ArrayList<String>> months =
                    new ArrayList<ArrayList<String>>();
            ObservableList<String> locations =
                    FXCollections.observableArrayList();
            for (WaterSourceReport wr : WaterReportManagement.getAllReports()) {
                if (lats.contains(wr.getLatitudeCoord())) {
                    // duplicate locations
                    int index = lats.indexOf(wr.getLatitudeCoord());
                    //check for duplicate months
                    String s = getMonth(wr.getSourceTimeStamp().toString());
                    if (months.get(index).contains(s)) {
                        //average values for that month
                        //Not exactly an average, but shhhhhh!
                        int monthIndex = months.get(index).indexOf(s);
                        double dub = virusvalues.get(index).get(monthIndex);
                        virusvalues.get(index).set(
                                monthIndex, (dub + wr.getVirusPPM()) / 2);
                    } else {
                        // not a duplicate month
                        months.get(index).add(s);
                        virusvalues.get(index).add(wr.getVirusPPM());
                    }

                } else {
                    locations.add("Location: "
                            + String.valueOf(wr.getLatitudeCoord()
                            + "," + String.valueOf(wr.getLongitutdeCoord())));
                    lats.add(wr.getLatitudeCoord());
                    ArrayList<Double> val = new ArrayList<>();
                    val.add(wr.getVirusPPM());
                    ArrayList<String> temp = new ArrayList<>();
                    String s = getMonth(wr.getSourceTimeStamp().toString());
                    temp.add(s);
                    months.add(temp);
                    virusvalues.add(val);

                }
            }
            list.setItems(locations);
            Button backButton = new Button("Back");
            AnchorPane.setRightAnchor(backButton, 65.0);
            ap.getChildren().addAll(list, backButton);
            try {
                Stage primaryStage =
                        (Stage) logoutButton.getScene().getWindow();
                primaryStage.setTitle(
                        "Select Location to see a graph of PPM Values");
                primaryStage.setScene(new Scene(ap, 600, 400));
                primaryStage.show();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
            backButton.setOnAction(e -> {
                try {
                    Parent root = FXMLLoader.load(
                            getClass().getResource("/mainScreen.fxml"));
                    Stage primaryStage =
                            (Stage) backButton.getScene().getWindow();
                    primaryStage.setTitle("Main Screen");
                    primaryStage.setScene(new Scene(root, 600, 400));
                    primaryStage.show();

                } catch (IOException f) {
                    f.printStackTrace();
                    System.out.println("I/O ERROR");
                }
            });
            list.setOnMouseClicked(event -> {
                BorderPane bp = new BorderPane();
                Button backButton2 = new Button("Back");
                backButton2.setOnAction(e -> {
                    try {
                        Parent root = FXMLLoader.load(
                                getClass().getResource("/mainScreen.fxml"));
                        Stage primaryStage =
                                (Stage) backButton2.getScene().getWindow();
                        primaryStage.setTitle("Main Screen");
                        primaryStage.setScene(new Scene(root, 600, 400));
                        primaryStage.show();

                    } catch (IOException f) {
                        f.printStackTrace();
                        System.out.println("I/O ERROR");
                    }
                });
                bp.setTop(backButton2);
                int locindex = list.getSelectionModel().getSelectedIndex();
                //show graph for all reports at this loc
                //graph code
                CategoryAxis month = new CategoryAxis();
                NumberAxis ppm = new NumberAxis();
                BarChart<String, Number> graph =
                        new BarChart<String, Number>(month, ppm);
                XYChart.Series series1 = new XYChart.Series<>();
                //XYChart.Series series2 = new XYChart.Series<>();
                for (int i = 0; i < months.get(locindex).size(); i++) {
                    series1.getData().addAll(
                            new XYChart.Data(months.get(locindex).get(i),
                                    virusvalues.get(locindex).get(i)));
                }
                graph.getData().addAll(series1);
                bp.setCenter(graph);
                try {
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
    }

    /**
     * This gets the month based on a string.
     *@param s the string to convert
     *@return the month of the given string.
     */
    private String getMonth(String s) {
        String sub = s.substring(5, 7);
        String month;
        switch (sub) {
        case "11":
            month = "November";
            break;
        case "12":
            month = "December";
            break;
        default:
            month = "October";
            System.out.println("Fell to default");
        }
        return month;
    }


}
