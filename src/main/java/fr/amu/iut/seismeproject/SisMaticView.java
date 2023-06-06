package fr.amu.iut.seismeproject;

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
    private WebView webView;

    public SisMaticView() {
        viewModel = new SisMaticViewModel();
    }

    public void setViewModel(SisMaticViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel.createWebView(webView);
    }
}
