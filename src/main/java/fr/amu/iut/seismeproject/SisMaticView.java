package fr.amu.iut.seismeproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import java.util.ResourceBundle;

public class SisMaticView implements Initializable {
    private SisMaticViewModel viewModel;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane file;
    @FXML
    private AnchorPane uploadFile;
    @FXML
    private Label fileName;
    @FXML
    private AnchorPane drag;
    @FXML
    private AnchorPane map;
    @FXML
    private MapView mapView;

    private boolean mapIsInit = false;

    @FXML
    private AnchorPane dashboard;
    @FXML
    private Button buttonMenu;
    @FXML
    private Button buttonFile;
    @FXML
    private Button buttonMap;
    @FXML
    private Button buttonDashboard;

    public SisMaticView() {
        viewModel = new SisMaticViewModel();
    }

    public void setViewModel(SisMaticViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void showMenu() {
        ArrayList<Button> listButtons = new ArrayList<>();
        listButtons.add(buttonMenu);
        listButtons.add(buttonFile);
        listButtons.add(buttonMap);
        listButtons.add(buttonDashboard);
        if (buttonMenu.getText() == "") {
            for (Button button : listButtons)
                viewModel.transformMenuButton(true, button);
            menu.setAlignment(Pos.TOP_LEFT);
            menu.setPrefWidth(300);
        }
        else {
            for (Button button : listButtons)
                viewModel.transformMenuButton(false, button);
            menu.setAlignment(Pos.TOP_CENTER);
            menu.setPrefWidth(100);
        }
    }

    @FXML
    private void showFile() {
        if (viewModel.getModel().getFileCSV().getName() == "") {
            file.setVisible(true);
            file.setDisable(false);
        }
        else {
            uploadFile.setVisible(true);
            uploadFile.setDisable(false);
        }
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
    }

    @FXML
    private void showMap() {
        if (viewModel.getModel().getFileCSV().getName() == "") {
            file.setVisible(false);
            file.setDisable(true);
        }
        else {
            uploadFile.setVisible(false);
            uploadFile.setDisable(true);
        }
        map.setVisible(true);
        map.setDisable(false);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
    }

    @FXML
    private void showDashboard() {
        if (viewModel.getModel().getFileCSV().getName() == "") {
            file.setVisible(false);
            file.setDisable(true);
        }
        else {
            uploadFile.setVisible(false);
            uploadFile.setDisable(true);
        }
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(true);
        dashboard.setDisable(false);
    }

    @FXML
    private void importCSV() throws IOException {
        if (viewModel.importCSV()) {
            this.showUploadFile();
        }
    }

    @FXML
    private void dragOver(DragEvent event) {
        //drag.setVisible(true);
        viewModel.handleDragOver(event);
    }

    @FXML
    private void dragExited(DragEvent event) {
        //drag.setVisible(false);
        event.consume();
    }

    @FXML
    private void dragDropped(DragEvent event) throws IOException {
        //drag.setVisible(false);
        viewModel.handleDragDropped(event);
        if(viewModel.handleDragDropped(event)) {
            this.showUploadFile();
        }
    }

    @FXML
    private void delFile() {
        uploadFile.setVisible(false);
        uploadFile.setDisable(true);
        file.setVisible(true);
        file.setDisable(false);
        fileName.setText("");
        viewModel.getModel().setFileCSV(new File(""));
        viewModel.getModel().setDataKeys(new ArrayList<>());
        viewModel.getModel().setData(new HashMap<>());
    }
    private void showUploadFile() {
        uploadFile.setVisible(true);
        uploadFile.setDisable(false);
        file.setVisible(false);
        file.setDisable(true);
        fileName.setText(viewModel.getModel().getFileCSV().getName());
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
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
        drag.setVisible(false);
        uploadFile.setVisible(false);
        uploadFile.setDisable(true);
        initMap();
    }
}
