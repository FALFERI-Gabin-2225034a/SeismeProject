package fr.amu.iut.seismeproject;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class SisMaticView implements Initializable {
    private SisMaticViewModel viewModel;
    @FXML
    private MapView mapView;

    public SisMaticView() {
        viewModel = new SisMaticViewModel();
    }

    public void setViewModel(SisMaticViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void initMap() {
        /* Création du point central sur la france avec latitude et longitude */
        MapPoint mapPoint = new MapPoint(46.227638, 2.213749);

        // MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);
        // mapView.addLayer(mapLayer);

        /* Zoom de 5 */
        mapView.setZoom(5);

        /* Centre la carte sur le point */
        mapView.flyTo(0, mapPoint, 0.1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMap();
    }
}
