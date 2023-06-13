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

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
    private AnchorPane map;
    @FXML
    private MapView mapView;
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
    @FXML
    private Label registeredUsersLabel;
    @FXML
    private Label pendingOrdersLabel;
    @FXML
    private Label totalRevenueLabel;


    private ArrayList<Button> listButtons;

    public SisMaticView() {
        viewModel = new SisMaticViewModel();
    }

    public void setViewModel(SisMaticViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void showMenu() {
        viewModel.changeColorButton(listButtons, buttonMenu);
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
        viewModel.changeColorButton(listButtons, buttonFile);
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
        viewModel.changeColorButton(listButtons, buttonMap);
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
        viewModel.changeColorButton(listButtons, buttonDashboard);
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
            viewModel.placeEpicentre(mapView);
        }
    }

    @FXML
    private void dragOver(DragEvent event) {
        file.setStyle("-fx-background-color: #2A3542; -fx-opacity: 50%;");
        viewModel.handleDragOver(event);
    }

    @FXML
    private void dragExited(DragEvent event) {
        file.setStyle("-fx-background-color: #E9FDFC; -fx-opacity: 100%;");
        event.consume();
    }

    @FXML
    private void dragDropped(DragEvent event) throws IOException {
        file.setStyle("-fx-background-color: #E9FDFC; -fx-opacity: 100%;");
        viewModel.handleDragDropped(event);
        if(viewModel.handleDragDropped(event)) {
            this.showUploadFile();
            viewModel.placeEpicentre(mapView);
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
        viewModel.clearMap(mapView);
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

        /* Zoom de 5 */
        mapView.setZoom(5);

        /* Centre la carte sur le point */
        mapView.flyTo(0, mapPoint, 0.1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listButtons = new ArrayList<>();
        listButtons.add(buttonMenu);
        listButtons.add(buttonFile);
        listButtons.add(buttonMap);
        listButtons.add(buttonDashboard);
        for (Button button : listButtons)
            button.setPrefWidth(100);
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
        uploadFile.setVisible(false);
        uploadFile.setDisable(true);
        initMap();
    }


}
