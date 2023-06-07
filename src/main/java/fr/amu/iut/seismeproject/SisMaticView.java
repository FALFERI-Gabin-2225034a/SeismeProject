package fr.amu.iut.seismeproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SisMaticView implements Initializable {
    private SisMaticViewModel viewModel;
    @FXML
    private VBox menu;
    @FXML
    private AnchorPane file;
    @FXML
    private AnchorPane map;
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
        file.setVisible(true);
        file.setDisable(false);
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
    }

    @FXML
    private void showMap() {
        file.setVisible(false);
        file.setDisable(true);
        map.setVisible(true);
        map.setDisable(false);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
    }

    @FXML
    private void showDashboard() {
        file.setVisible(false);
        file.setDisable(true);
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(true);
        dashboard.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        map.setVisible(false);
        map.setDisable(true);
        dashboard.setVisible(false);
        dashboard.setDisable(true);
    }
}
