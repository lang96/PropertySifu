package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.kuthingalas.propertysifu.data.DataOperation.*;



public class EditPropertyController {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment","Bungalow","Condominium","Semi-Detached","Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("Studio","1","2","3","4","5+");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5+");
    ObservableList<String> facilitiesList = FXCollections.observableArrayList("Air-conditioning","Gym","Swimming Pool","Car park","Playground","Security");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially furnished","Furnished");
    ObservableList<String> stat = FXCollections.observableArrayList("Available","Rented Out");

    @FXML
    private Button agentBackHomeBtn, agentConfirmBtn;

    @FXML
    private TextField propID, firstAdd, secondAdd, propArea, propPSF, propRent;

    @FXML
    private CheckBox fac1, fac2, fac3, fac4, fac5, fac6;

    @FXML
    public ComboBox<String> propType;
    @FXML
    public ComboBox<String> propStat;
    @FXML
    private ComboBox<String> propBed;
    @FXML
    private ComboBox<String> propBath;
    @FXML
    private ComboBox<String> propFurnish;


    public void initData(Property property) {

        propID.setText(property.getPropertyID());
        propType.getSelectionModel().select(property.getPropertyType());
        propStat.getSelectionModel().select(property.getStatusDesc());
        firstAdd.setText(property.getFirstAddress());
        secondAdd.setText(property.getSecondAddress());
        propBed.getSelectionModel().select(String.valueOf(property.getBedroom()));
        propBath.getSelectionModel().select(String.valueOf(property.getBathroom()));
        propArea.setText(String.valueOf(property.getArea()));
        propPSF.setText(String.valueOf(property.getPsfRate()));
        propRent.setText(String.valueOf(property.getRentalRate()));

        if (property.getFurnishing() == 0) {
            propFurnish.getSelectionModel().select("Unfurnished");
        } else if (property.getFurnishing() == 1) {
            propFurnish.getSelectionModel().select("Partially furnished");
        } else {
            propFurnish.getSelectionModel().select("Furnished");
        }

    }

    public void toAgentHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentBackHomeBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }

    public void toConfirm() throws IOException {

        //updatePropertyType();
        //updatePropertyAddress();
        //updatePropertyStatus();
        //updatePropertyFacilities();
        //updatePropertyDetails();

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentConfirmBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }

}
