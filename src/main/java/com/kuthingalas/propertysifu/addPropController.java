package com.kuthingalas.propertysifu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class addPropController implements Initializable {

    ObservableList<String> propertyList2 = FXCollections.observableArrayList("Bungalow","Apartment");
    ObservableList<String> numBedrooms2 = FXCollections.observableArrayList("Studio","1","2","3","4","5");
    ObservableList<String> numBathrooms2 = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture2 = FXCollections.observableArrayList("Unfurnished","Partially" ,"Furnished");

    @FXML
    public ComboBox<String> proptype2;
    @FXML
    private ComboBox<String> bedrooms2;
    @FXML
    private ComboBox<String> bathrooms2;
    @FXML
    private ComboBox<String> furnilist2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        proptype2.setItems(propertyList2);
        bedrooms2.setItems(numBedrooms2);
        bathrooms2.setItems(numBathrooms2);
        furnilist2.setItems(furniture2);
    }

}
