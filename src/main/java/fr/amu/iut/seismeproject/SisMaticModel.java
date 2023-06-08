package fr.amu.iut.seismeproject;

import com.gluonhq.maps.MapLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SisMaticModel {
    private File fileCSV = new File("");

    private ArrayList<String> dataKeys;

    private Map<String, ArrayList<String>> data;

    private ArrayList<ArrayList<String>> dataInLine;

    private ArrayList<MapLayer> layerChildrens;

    public SisMaticModel() {
        data = new HashMap<>();
        dataKeys = new ArrayList<>();
        layerChildrens = new ArrayList<>();
    }

    public void setFileCSV(File fileCSV) {
        this.fileCSV = fileCSV;
    }

    public File getFileCSV() {
        return fileCSV;
    }

    public void setDataKeys(ArrayList<String> dataKeys) {
        this.dataKeys = dataKeys;
    }
    public ArrayList<String> getDataKeys() {
        return dataKeys;
    }

    public void setData(Map<String, ArrayList<String>> data) {
        this.data = data;
    }

    public Map<String, ArrayList<String>> getData() {
        return data;
    }

    public ArrayList<MapLayer> getLayerChildrens() {
        return layerChildrens;
    }

    public void setLayerChildrens(ArrayList<MapLayer> layerChildrens) {
        this.layerChildrens = layerChildrens;
    }

    public ArrayList<ArrayList<String>> getDataInLine() {
        return dataInLine;
    }

    public void setDataInLine(ArrayList<ArrayList<String>> dataInLine) {
        this.dataInLine = dataInLine;
    }
}
