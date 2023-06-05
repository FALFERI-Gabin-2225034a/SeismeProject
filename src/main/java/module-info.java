module fr.amu.iut.seismeproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens fr.amu.iut.seismeproject to javafx.fxml;
    exports fr.amu.iut.seismeproject;
}