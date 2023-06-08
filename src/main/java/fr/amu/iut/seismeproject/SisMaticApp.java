package fr.amu.iut.seismeproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class SisMaticApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /* Définit la plate-forme pour éviter "javafx.platform is not defined" */
        System.setProperty("javafx.platform", "desktop");

        /*
         * Définit l'user agent pour éviter l'exception
         * "Server returned HTTP response code: 403"
         */
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        primaryStage.setTitle("SisMatic");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SisMaticView.fxml"));
        Parent root = loader.load();

        // Liaison du modèle (Model) et du vue-modèle (ViewModel) à la vue (View)
        SisMaticView view = loader.getController();
        SisMaticModel model = new SisMaticModel();
        SisMaticViewModel viewModel = new SisMaticViewModel(model);
        view.setViewModel(viewModel);

        Scene scene = new Scene(root, 960, 540);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
