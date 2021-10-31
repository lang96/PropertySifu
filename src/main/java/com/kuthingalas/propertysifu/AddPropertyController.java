package com.kuthingalas.propertysifu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.kuthingalas.propertysifu.HomepageController.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class AddPropertyController implements Initializable {


    ObservableList<String> propertyList2 = FXCollections.observableArrayList("Bungalow","Apartment","Condominium");
    ObservableList<String> status1 = FXCollections.observableArrayList("Active","Inactive");
    ObservableList<String> numBedroom2 = FXCollections.observableArrayList("Studio","1","2","3","4","5");
    ObservableList<String> numBathroom2 = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furni = FXCollections.observableArrayList("Unfurnished","Partially" ,"Furnished");



    @FXML
    public ComboBox<String> propType2;
    @FXML
    private ComboBox<String> status2;
    @FXML
    private ComboBox<String> bedroom2;
    @FXML
    private ComboBox<String> bathroom2;
    @FXML
    private ComboBox<String> furnishList2;

    @FXML
    private Button back10;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        propType2.setItems(propertyList2);
        status2.setItems(status1);
        bedroom2.setItems(numBedroom2);
        bathroom2.setItems(numBathroom2);
        furnishList2.setItems(furni);


    }

    public void toagentHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) back10.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }

}
