package fr.amu.iut.seismeproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class SisMaticViewModel {
    private SisMaticModel model;

    public SisMaticViewModel() {
        model = new SisMaticModel();
    }

    public SisMaticViewModel(SisMaticModel model) {
        this.model = model;
    }

    public SisMaticModel getModel() {
        return model;
    }

    public void transformMenuButton(boolean isVoid, Button button) {
        if (isVoid)
            button.setText("\t" + button.getId().substring(6));
        else
            button.setText("");
    }

    public boolean importCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            model.setFileCSV(selectedFile);
            return true;
        }
        return false;
    }

    public void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }
    public boolean handleDragDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasFiles()) {
            List<File> files = dragboard.getFiles();
            boolean isOnlyCSV = files.stream().allMatch(file -> file.getName().toLowerCase().endsWith(".csv"));
            if (isOnlyCSV) {
                if (files.size() == 1) {
                    File file = files.get(0);
                    model.setFileCSV(file);
                    success = true;
                }
            }
        }
        event.setDropCompleted(success);
        event.consume();
        return success;
    }
}
