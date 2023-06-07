package fr.amu.iut.seismeproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SisMaticView implements Initializable {
    private SisMaticViewModel viewModel;
    @FXML
    private VBox menu;
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
            menu.setPrefWidth(350);
        }
        else {
            for (Button button : listButtons)
                viewModel.transformMenuButton(false, button);
            menu.setAlignment(Pos.TOP_CENTER);
            menu.setPrefWidth(100);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
