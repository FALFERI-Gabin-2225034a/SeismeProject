package fr.amu.iut.seismeproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SisMaticViewModel {
    private SisMaticModel model;

    public SisMaticViewModel() {
        model = new SisMaticModel();
    }
    public void setModel(SisMaticModel model) {
        this.model = model;
    }

    public void transformMenuButton(boolean isVoid, Button button) {
        if (isVoid)
            button.setText("\t" + button.getId().substring(6));
        else
            button.setText("");
    }
}
