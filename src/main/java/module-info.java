module com.kuthingalas.propertysifu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires json.simple;

    exports com.kuthingalas.propertysifu;
    opens com.kuthingalas.propertysifu to javafx.fxml;
}