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
        primaryStage.setTitle("SisMatic");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SisMaticView.fxml"));
        Parent root = loader.load();

        // Liaison du modèle (Model) et du vue-modèle (ViewModel) à la vue (View)
        SisMaticView view = loader.getController();
        SisMaticViewModel viewModel = new SisMaticViewModel();
        view.setViewModel(viewModel);
        SisMaticModel model = new SisMaticModel();
        viewModel.setModel(model);

        Scene scene = new Scene(root, 400, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
