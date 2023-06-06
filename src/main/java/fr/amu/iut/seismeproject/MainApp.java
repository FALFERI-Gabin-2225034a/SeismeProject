package fr.amu.iut.seismeproject;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainApp extends Application {

    private static final String HTML_FILE_PATH = "carteFrance.html";
    private WebView webView;

    @Override
    public void start(Stage primaryStage) {
        webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("app", this);
                webEngine.executeScript("app.demarcateFrance();");
            }
        });

        StackPane root = new StackPane(webView);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Carte de la France");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadHtmlFile(webEngine, scene);
    }

    public void demarcateFrance() {
        WebEngine webEngine = webView.getEngine();
    }

    private void loadHtmlFile(WebEngine webEngine, Scene scene) {
        try {
            URL url = getClass().getResource(HTML_FILE_PATH);
            URI uri = url.toURI();
            File file = new File(uri);
            String filePath = file.toURI().toString();
            webEngine.load(filePath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
