package com.kuthingalas.propertysifu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.kuthingalas.propertysifu.HomepageController.*;
import static com.kuthingalas.propertysifu.MainApplication.currentUserID;
import static com.kuthingalas.propertysifu.data.DataOperation.addProperty;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



/**
 *     Programmer's Name: Shuhail & Arif
 *     Method's Name    : AdminAddPropertyController
 *     Purpose         : For other details further processing
 */

public class AdminAddPropertyController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment", "Bungalow", "Condominium", "Semi-Detached", "Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished", "Partially furnished", "Furnished");

    @FXML
    private TextField firstAdd, secondAdd, propArea, propPSF, propRent;

    @FXML
    public ComboBox<String> propType;
    @FXML
    private ComboBox<String> propBed;
    @FXML
    private ComboBox<String> propBath;
    @FXML
    private ComboBox<String> propFurnish;

    @FXML
    private CheckBox fac1, fac2, fac3, fac4, fac5, fac6;

    @FXML
    private Button saveBtn, back5;

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toManageProperties2
     *     Purpose         : To go back manage property page
     */

    public void toManageProperties2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) back5.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 967, 544));
    }


    /**
     *     Programmer's Name: Shuhail & Arif
     *     Method's Name    : initialize
     *     Purpose         : To set an initial value for admin add property page
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        propType.setItems(propertyList);
        propBed.setItems(numBedrooms);
        propBath.setItems(numBathrooms);
        propFurnish.setItems(furniture);

        propPSF.textProperty().addListener((observable, oldValue, newValue) -> {
            propArea.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                try {
                    propRent.setText(String.valueOf(Float.parseFloat(newValue2) * Float.parseFloat(newValue)));
                } catch (Exception e) {
                }
            });
        });

        propArea.textProperty().addListener((observable, oldValue, newValue) -> {
            propPSF.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                try {
                    propRent.setText(String.valueOf(Float.parseFloat(newValue2) * Float.parseFloat(newValue)));
                } catch (Exception e) {
                }
            });
        });
    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : toConfirm
     *     Purpose         : To confirm and save property details
     */

    public void toConfirm() throws IOException {

        ArrayList<String> facList = new ArrayList<>();

        // handle empty type error
        if (propType.getSelectionModel().getSelectedItem() == null) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Select property type");
            errorAlert.showAndWait();
        } else {
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
                    // handle empty bed error
                    if (propBed.getSelectionModel().getSelectedItem() == null) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Error");
                        errorAlert.setContentText("Select bedrooms");
                        errorAlert.showAndWait();
                    } else {
                        // handle empty bath error
                        if (propBath.getSelectionModel().getSelectedItem() == null) {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setHeaderText("Error");
                            errorAlert.setContentText("Select bathrooms");
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
                                            addProperty(propType.getSelectionModel().getSelectedItem(), firstAdd.getText(), secondAdd.getText(),
                                                    facList, Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                                    Float.parseFloat(propRent.getText()), currentUserID);

                                            Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

                                            Stage window = (Stage) saveBtn.getScene().getWindow();
                                            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
                                            window.setScene(new Scene(root, 1182, 665));

                                        } else if (propFurnish.getSelectionModel().getSelectedItem().equals("Partially furnished")) {
                                            int furnish = 1;
                                            addProperty(propType.getSelectionModel().getSelectedItem(), firstAdd.getText(), secondAdd.getText(),
                                                    facList, Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                                    Float.parseFloat(propRent.getText()), currentUserID);

                                            Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

                                            Stage window = (Stage) saveBtn.getScene().getWindow();
                                            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
                                            window.setScene(new Scene(root, 1182, 665));

                                        } else if (propFurnish.getSelectionModel().getSelectedItem().equals("Furnished")) {
                                            int furnish = 2;
                                            addProperty(propType.getSelectionModel().getSelectedItem(), firstAdd.getText(), secondAdd.getText(),
                                                    facList, Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                                    Float.parseFloat(propRent.getText()), currentUserID);

                                            Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

                                            Stage window = (Stage) saveBtn.getScene().getWindow();
                                            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
                                            window.setScene(new Scene(root, 1182, 665));

                                        } else {
                                            if (propFurnish.getSelectionModel().getSelectedItem() == null) {
                                                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                                                errorAlert.setHeaderText("Error");
                                                errorAlert.setContentText("Choose furnishing");
                                                errorAlert.showAndWait();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
