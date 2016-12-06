package sample.controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import sample.model.WaterReportManagement;
import sample.model.WaterSourceReport;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by David on 10/22/16.
 */
public class GMapsController implements MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private ArrayList<WaterSourceReport> waterReports;

    private double latCenter;

    private double lngCenter;

    private Stage primaryStage;

    private InfoWindow currentInfoWindow;

    private WaterSourceReport currentReport;

    private Label l;

    @Override
    public void mapInitialized() {
        mapView = new GoogleMapView();
        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();
        l = new Label("Select marker first");
        l.setVisible(false);
        //Set the initial properties of the map.
        mapView.addMapInializedListener(() -> {

            LatLong center = new LatLong(latCenter, lngCenter);

            MapOptions options = new MapOptions()
                    .center(center)
                    .zoom(12)
                    .overviewMapControl(true)
                    .panControl(true)
                    .rotateControl(true)
                    .scaleControl(true)
                    .streetViewControl(false)
                    .zoomControl(true)
                    .mapType(MapTypeIdEnum.SATELLITE);

            map = mapView.createMap(options);
            addMarkers();
        });

        Button btnShowReport = new Button("Show Selected Report");
        btnShowReport.setOnAction(e -> {
            showSpecificReport();
        });
        Button btnBack = new Button("Back to Main Screen");
        btnBack.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(
                        getClass().getResource("/mainScreen.fxml"));
                primaryStage.setTitle("Main Screen");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();

            } catch (IOException f) {
                f.printStackTrace();
                System.out.println("I/O ERROR");
            }
        });
        HBox box = new HBox();
        box.setSpacing(30.0);
        box.getChildren().addAll(btnShowReport, l, btnBack);

        tb.getItems().addAll(box);

        bp.setTop(tb);
        bp.setCenter(mapView);
        bp.setMaxSize(600, 400);

        primaryStage.setTitle("Water Source Map View");
        primaryStage.setScene(new Scene(bp));
        primaryStage.show();
    }

    /**
     * This method sets up the primary stage.
     *
     * @param stage the stage the method takes in
     */
    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    /**
     * This method sets the water reports.
     *
     *@param reports the array list of reports.
     */
    public void setWaterReports(ArrayList<WaterSourceReport> reports) {
        if (reports == null) {
            latCenter = 33.7756;
            lngCenter = 84.3963;
        } else {
            waterReports = reports;
            double latTotal = 0;
            double lngTotal = 0;
            for (WaterSourceReport report : reports) {
                latTotal = latTotal + report.getLatitudeCoordinate();
                lngTotal = lngTotal + report.getLongitudeCoordinate();
            }
            latCenter = latTotal / reports.size();
            lngCenter = lngTotal / reports.size();
        }
    }

    /**
     * This method adds the markers to the google map.
     *
     */
    private void addMarkers() {
        waterReports.forEach(this::addMarker);
    }

    /**
     * Adds one marker to the map.
     * @param report the given report
     */
    private void addMarker(WaterSourceReport report) {
        LatLong location = new LatLong(
                report.getLatitudeCoordinate(),
                report.getLongitudeCoordinate());

        //Add markers to the map
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        Marker marker = new Marker(markerOptions);
        map.addUIEventHandler(marker,
                UIEventType.click, (JSObject obj) -> {
                WaterReportManagement.setCurrentReport(report);
                l.setVisible(false);
                if (currentInfoWindow != null) {
                    currentInfoWindow.close();
                }
                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content(report.getDescription());

                InfoWindow infoWindow = new InfoWindow(infoWindowOptions);

                currentInfoWindow = infoWindow;
                currentReport = report;
                infoWindow.open(map, marker);
            });
        //map.addUIEventHandler(marker, UIEventType.alert);

        map.addMarker(marker);
    }

    /**
     * Shows a specific report.
     *
     */
    private void showSpecificReport() {
        if (currentReport != null) {
            l.setVisible(false);
            WaterReportManagement.setCurrentReport(currentReport);
            try {
                Parent root = FXMLLoader.load(
                        getClass().getResource(
                                "/WaterSourceReportViewOnly.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Water Source Report View");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
            primaryStage.close();
        } else {
            l.setVisible(true);
        }
    }
}
