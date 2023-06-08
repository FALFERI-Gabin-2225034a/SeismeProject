module fr.amu.iut.seismeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires jdk.jsobject;
    requires com.gluonhq.maps;
    requires commons.csv;

    opens fr.amu.iut.seismeproject to javafx.fxml;
    exports fr.amu.iut.seismeproject;
}