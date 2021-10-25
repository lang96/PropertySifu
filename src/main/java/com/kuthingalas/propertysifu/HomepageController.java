package com.kuthingalas.propertysifu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Bungalow","Apartment");
    ObservableList<String> amenitiesList = FXCollections.observableArrayList("Swimming Pool","Park");
    ObservableList<String> sortPrice = FXCollections.observableArrayList("Low to High","High to Low");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially" ,"Furnished");


//    @FXML
//    private CheckBox sial;

    @FXML
    Button profile , confirmBtn ;

    @FXML
    private Hyperlink tologbtn;

    @FXML
    private TextArea test1;

    @FXML
    private Label label;

    @FXML
    public ComboBox <String> proptype;
    @FXML
    private ComboBox<String> amenlist;
    @FXML
    private ComboBox<String> price;
    @FXML
    private ComboBox<String> bedrooms;
    @FXML
    private ComboBox<String> bathrooms;
    @FXML
    private ComboBox<String> furnilist;

    @FXML
    public void tologin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginpage.fxml"));

        Stage window = (Stage) tologbtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    @FXML
    private void dolist(ActionEvent event){

        String s = proptype.getSelectionModel().getSelectedItem().toString();
        test1.setText( "" + s);
    }

    @FXML
    private void dolist2(){

        test1.setText("" + amenlist.getValue());
    }

    @FXML
    private void pricelist(){

    }

    @FXML
    private void bedlist(){

    }

    @FXML
    private void bathlist(){

    }

    @FXML
    private void furni(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        proptype.setItems(propertyList);
        amenlist.setItems(amenitiesList);
        price.setItems(sortPrice);
        bedrooms.setItems(numBedrooms);
        bathrooms.setItems(numBathrooms);
        furnilist.setItems(furniture);
    }

// Checkbox function
//    @FXML
//    void Select(ActionEvent event) {
//
//        if (sial.isSelected()){
//            String s = sial.getText();
//            label.setText(s);
//        }else {
//            label.setText("");
//        }
//
//    }
}

