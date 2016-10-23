package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

    @Override
    public void mapInitialized() {
        mapView = new GoogleMapView();
        //Set the initial properties of the map.
        mapView.addMapInializedListener(() -> {

            LatLong center = new LatLong(latCenter, lngCenter);

            MapOptions options = new MapOptions()
                    .center(center)
                    .mapMarker(true)
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

        Scene scene = new Scene(mapView);

        primaryStage = new Stage();
        primaryStage.setTitle("Water Source Map View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setWaterReports(ArrayList<WaterSourceReport> reports) {
        waterReports = reports;
        double latTotal = 0;
        double lngTotal = 0;
        for (WaterSourceReport report : reports) {
            latTotal = latTotal + report.getLatitudeCoord();
            lngTotal = lngTotal + report.getLongitutdeCoord();
        }
        latCenter = latTotal / reports.size();
        lngCenter = lngTotal / reports.size();
    }

    private void addMarkers() {
        for (WaterSourceReport report : waterReports) {
            addMarker(report);
        }

    }

    /**
     * Adds one marker to the map.
     * @param report
     */
    private void addMarker(WaterSourceReport report) {
        LatLong location = new LatLong(report.getLatitudeCoord(), report.getLongitutdeCoord());

        //Add markers to the map
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        Marker marker = new Marker(markerOptions);
//        ScriptEngineManager factory = new ScriptEngineManager();
//        ScriptEngine engine = factory.getEngineByName("JavaScript");
        map.addUIEventHandler(marker,
                UIEventType.click, (JSObject obj) -> {
                    WaterReportManagement.setCurrentReport(report);
                    if (currentInfoWindow != null) {
                        currentInfoWindow.close();
                    }
                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                    infoWindowOptions.content(report.getDescription());

                    InfoWindow infoWindow = new InfoWindow(infoWindowOptions);

                    currentInfoWindow = infoWindow;
                    infoWindow.open(map, marker);
                });
        //map.addUIEventHandler(marker, UIEventType.alert);

        map.addMarker(marker);
    }

    private void showSpecificReport(WaterSourceReport report) {
        if (report != null) {
            WaterReportManagement.setCurrentReport(report);
            try {
                Parent root = FXMLLoader.load(getClass().getResource("WaterSourceReportViewOnly.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Water Source Report View");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
        } else {
            System.out.println("report is null!!");
        }
        primaryStage.close();
    }
}
