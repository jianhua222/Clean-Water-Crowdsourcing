package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by David on 10/22/16.
 */
public class GMapsController implements MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @Override
    public void mapInitialized() {
        mapView = new GoogleMapView();
        //Set the initial properties of the map.
        mapView.addMapInializedListener(() -> {

            LatLong center = new LatLong(33.7756, 84.3963);

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

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Water Source Map View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addMarkers() {
        LatLong joeSmithLocation = new LatLong(33.7756, 84.3963);
        LatLong joshAndersonLocation = new LatLong(33.7736, 84.3943);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);

        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);

        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
    }
}
