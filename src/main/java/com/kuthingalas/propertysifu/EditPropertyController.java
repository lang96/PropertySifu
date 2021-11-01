package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.kuthingalas.propertysifu.MainApplication.currentUserID;
import static com.kuthingalas.propertysifu.data.DataOperation.*;

/**
 *     Programmer's Name: Arif & Shuhail
 *     Method's Name    : EditPropertyController
 *     Purpose         : Hold objects and for editing property
 */

public class EditPropertyController {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment","Bungalow","Condominium","Semi-Detached","Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5");
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

    /**
     *     Programmer's Name: Arif & Shuhail
     *     Method's Name    : initData
     *     Purpose         : initialize data
     */

    public void initData(Property property) {

        propType.setItems(propertyList);
        propBed.setItems(numBedrooms);
        propBath.setItems(numBathrooms);
        propFurnish.setItems(furniture);
        propStat.setItems(stat);

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

        if (!property.getFacilityList().isEmpty()) {
            for (int i = 0; i < property.getFacilityList().size(); i++) {
                switch (property.getFacilityList().get(i)) {
                    case "Air-conditioning":
                        fac1.setSelected(true);
                        break;
                    case "Car park":
                        fac2.setSelected(true);
                        break;
                    case"Swimming Pool":
                        fac3.setSelected(true);
                        break;
                    case "Gym":
                        fac4.setSelected(true);
                        break;
                    case "Playground":
                        fac5.setSelected(true);
                        break;
                    case "Security":
                        fac6.setSelected(true);
                        break;
                }
            }
        }

        if (property.getFurnishing() == 0) {
            propFurnish.getSelectionModel().select("Unfurnished");
        } else if (property.getFurnishing() == 1) {
            propFurnish.getSelectionModel().select("Partially furnished");
        } else {
            propFurnish.getSelectionModel().select("Furnished");
        }

        propPSF.textProperty().addListener((observable, oldValue, newValue) -> {
            propArea.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                try {
                    propRent.setText(String.valueOf(Float.parseFloat(newValue2) * Float.parseFloat(newValue)));
                } catch (Exception e) {}
            });
        });

        propArea.textProperty().addListener((observable, oldValue, newValue) -> {
            propPSF.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                try {
                    propRent.setText(String.valueOf(Float.parseFloat(newValue2) * Float.parseFloat(newValue)));
                } catch (Exception e) {}
            });
        });

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAgentHomepage
     *     Purpose         : to back to homepage
     */

    public void toAgentHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentBackHomeBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : toConfirm
     *     Purpose         : to save updated property details
     */


    public void toConfirm() throws IOException {

        int stat = 0;
        ArrayList<String> facList = new ArrayList<>();

        // handle empty address line error
        if (firstAdd.getText().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Enter address");
            errorAlert.showAndWait();
        } else {
            if (secondAdd.getText().isEmpty()) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Enter address");
                errorAlert.showAndWait();
            } else {
                // handle empty area error
                if (propArea.getText().isEmpty()) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Error");
                    errorAlert.setContentText("Enter area");
                    errorAlert.showAndWait();
                } else {
                    // handle empty psf error
                    if (propPSF.getText().isEmpty()) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Error");
                        errorAlert.setContentText("Enter psf rate");
                        errorAlert.showAndWait();
                    } else {
                        // handle empty rent error
                        if (propRent.getText().isEmpty()) {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Error");
                            errorAlert.setContentText("Enter rent price");
                            errorAlert.showAndWait();
                        } else {
                            // choose facility
                            if (fac1.isSelected()) {
                                facList.add("Air-conditioning");
                            }
                            if (fac2.isSelected()) {
                                facList.add("Car park");
                            }
                            if (fac3.isSelected()) {
                                facList.add("Swimming Pool");
                            }
                            if (fac4.isSelected()) {
                                facList.add("Gym");
                            }
                            if (fac5.isSelected()) {
                                facList.add("Playground");
                            }
                            if (fac6.isSelected()) {
                                facList.add("Security");
                            }

                            if (propFurnish.getSelectionModel().getSelectedItem().equals("Unfurnished")) {
                                int furnish = 0;

                                updatePropertyType(propID.getText(), propType.getSelectionModel().getSelectedItem());
                                updatePropertyAddress(propID.getText(), firstAdd.getText(), secondAdd.getText());

                                if (propStat.getSelectionModel().getSelectedItem().equals("Available")) {
                                    stat = 1;
                                    updatePropertyStatus(propID.getText(), stat, propStat.getSelectionModel().getSelectedItem());
                                } else {
                                    updatePropertyStatus(propID.getText(), stat, propStat.getSelectionModel().getSelectedItem());
                                }

                                updatePropertyFacilities(propID.getText(), facList);
                                updatePropertyDetails(propID.getText(),
                                        Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                        Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                        Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                        Float.parseFloat(propRent.getText()));

                            } else if (propFurnish.getSelectionModel().getSelectedItem().equals("Partially furnished")) {
                                int furnish = 1;

                                updatePropertyType(propID.getText(), propType.getSelectionModel().getSelectedItem());
                                updatePropertyAddress(propID.getText(), firstAdd.getText(), secondAdd.getText());

                                if (propStat.getSelectionModel().getSelectedItem().equals("Available")) {
                                    stat = 1;
                                    updatePropertyStatus(propID.getText(), stat, propStat.getSelectionModel().getSelectedItem());
                                } else {
                                    updatePropertyStatus(propID.getText(), stat, propStat.getSelectionModel().getSelectedItem());
                                }

                                updatePropertyFacilities(propID.getText(), facList);
                                updatePropertyDetails(propID.getText(),
                                        Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                        Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                        Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                        Float.parseFloat(propRent.getText()));

                            } else if (propFurnish.getSelectionModel().getSelectedItem().equals("Furnished")) {
                                int furnish = 2;

                                updatePropertyType(propID.getText(), propType.getSelectionModel().getSelectedItem());
                                updatePropertyAddress(propID.getText(), firstAdd.getText(), secondAdd.getText());

                                if (propStat.getSelectionModel().getSelectedItem().equals("Available")) {
                                    stat = 1;
                                    updatePropertyStatus(propID.getText(), stat, propStat.getSelectionModel().getSelectedItem());
                                } else {
                                    updatePropertyStatus(propID.getText(), stat, propStat.getSelectionModel().getSelectedItem());
                                }

                                updatePropertyFacilities(propID.getText(), facList);
                                updatePropertyDetails(propID.getText(),
                                        Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                        Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                        Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                        Float.parseFloat(propRent.getText()));

                            }

                            Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

                            Stage window = (Stage) agentConfirmBtn.getScene().getWindow();
                            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
                            window.setScene(new Scene(root,1182,665));

                        }
                    }
                }
            }
        }
    }

}