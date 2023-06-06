package fr.amu.iut.seismeproject;

import javafx.beans.property.StringProperty;
import javafx.concurrent.Worker;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class SisMaticViewModel {
    private SisMaticModel model;

    public SisMaticViewModel() {
        model = new SisMaticModel();
    }
    public SisMaticViewModel(SisMaticModel model) {
        this.model = model;
    }

    public void createWebView(WebView webView) {
        WebEngine webEngine = webView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("viewModel", this);
                webEngine.executeScript("viewModel.demarcateFrance(webView);");
            }
        });
        loadHtmlFile(webEngine);
    }

    public void demarcateFrance(WebView webView) {
        WebEngine webEngine = webView.getEngine();
    }
    private void loadHtmlFile(WebEngine webEngine) {
        String htmlFilePath = getClass().getResource("carteFrance.html").toExternalForm();
        webEngine.load(htmlFilePath);
    }

}
