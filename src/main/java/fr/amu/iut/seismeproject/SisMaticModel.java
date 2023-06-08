package fr.amu.iut.seismeproject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SisMaticModel {
    private File fileCSV = new File("");

    private ArrayList<String> dataKeys;

    private Map<String, ArrayList<String>> data;

    public SisMaticModel() {
        data = new HashMap<>();
        dataKeys = new ArrayList<>();
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
}
