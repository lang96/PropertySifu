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



public class AdminAddPropertyController implements Initializable {

    ObservableList<String> propertyList3 = FXCollections.observableArrayList("Bungalow","Apartment","Condominium");
    ObservableList<String> status = FXCollections.observableArrayList("Active","Inactive");
    ObservableList<String> numBedroom3 = FXCollections.observableArrayList("Studio","1","2","3","4","5");
    ObservableList<String> numBathroom3 = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture2 = FXCollections.observableArrayList("Unfurnished","Partially" ,"Furnished");

    @FXML
    private ComboBox<String> addProp;
    @FXML
    private ComboBox<String> addStatus;
    @FXML
    private ComboBox<String> addBedroom;
    @FXML
    private ComboBox<String> addBathroom;
    @FXML
    private ComboBox<String> addFurni;
    @FXML
    private Button back5;



    public void toManageProperties2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) back5.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 967, 544));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addProp.setItems(propertyList3);
        addStatus.setItems(status);
        addBedroom.setItems(numBedroom3);
        addBathroom.setItems(numBathroom3);
        addFurni.setItems(furniture2);
    }

}
