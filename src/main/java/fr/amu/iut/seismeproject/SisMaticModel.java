package fr.amu.iut.seismeproject;

import java.io.File;

public class SisMaticModel {
    private File fileCSV = new File("");

    public void setFileCSV(File fileCSV) {
        this.fileCSV = fileCSV;
    }

    public File getFileCSV() {
        return fileCSV;
    }
}
