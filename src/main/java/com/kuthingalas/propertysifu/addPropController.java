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
    ObservableList<String> numBedroom2 = FXCollections.observableArrayList("Studio","1","2","3","4","5");
    ObservableList<String> numBathroom2 = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture2 = FXCollections.observableArrayList("Unfurnished","Partially" ,"Furnished");

    @FXML
    public ComboBox<String> propType2;
    @FXML
    private ComboBox<String> bedroom2;
    @FXML
    private ComboBox<String> bathroom2;
    @FXML
    private ComboBox<String> furnishList2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        propType2.setItems(propertyList2);
        bedroom2.setItems(numBedroom2);
        bathroom2.setItems(numBathroom2);
        furnishList2.setItems(furniture2);
    }

}
