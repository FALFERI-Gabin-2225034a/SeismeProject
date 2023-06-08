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
}
