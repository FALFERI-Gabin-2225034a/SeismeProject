package fr.amu.iut.seismeproject;

import javafx.scene.Scene;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void initData(File file) throws IOException {
        boolean isFirstLine = true;
        try (Reader reader = new FileReader(file);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                List<String> line = new ArrayList<>();
                for (int i = 0 ; i < csvRecord.size() ; i++) {
                    if (isFirstLine) {
                        model.getDataKeys().add(csvRecord.get(i));
                        model.getData().put(csvRecord.get(i), new ArrayList<String>());
                    }
                    else {
                        String key = model.getDataKeys().get(i);
                        model.getData().get(key).add(csvRecord.get(i));
                    }
                }
                isFirstLine = false;
            }

        }
    }

    public boolean importCSV() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            model.setFileCSV(selectedFile);
            initData(selectedFile);
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
    public boolean handleDragDropped(DragEvent event) throws IOException {
        Dragboard dragboard = event.getDragboard();
        boolean success = false;
        if (dragboard.hasFiles()) {
            List<File> files = dragboard.getFiles();
            boolean isOnlyCSV = files.stream().allMatch(file -> file.getName().toLowerCase().endsWith(".csv"));
            if (isOnlyCSV) {
                if (files.size() == 1) {
                    File file = files.get(0);
                    model.setFileCSV(file);
                    initData(file);
                    success = true;
                }
            }
        }
        event.setDropCompleted(success);
        event.consume();
        return success;
    }
}
