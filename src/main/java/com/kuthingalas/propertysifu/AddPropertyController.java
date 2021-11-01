package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.*;

import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.system.Property.Comment.*;
import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.usertype.Agent.*;
import static com.kuthingalas.propertysifu.usertype.Owner.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.MainApplication.*;

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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



/**
 *     Programmer's Name: Shuhail & Arif
 *     Method's Name    : AddPropertyController
 *     Purpose         : For other details further processing
 */

public class AddPropertyController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment", "Bungalow", "Condominium", "Semi-Detached", "Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    ObservableList<String> facilitiesList = FXCollections.observableArrayList("Air-conditioning", "Gym", "Swimming Pool", "Car park", "Playground", "Security");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished", "Partially furnished", "Furnished");

    @FXML
    private Button agentBackHomeBtn, agentConfirmBtn;

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


    /**
     *     Programmer's Name: Shuhail & Arif
     *     Method's Name    : initialize
     *     Purpose         : To set an initial value of this page's choicebox
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
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAgentHomepage
     *     Purpose         : to go back to homepage
     */

    public void toAgentHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentBackHomeBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 1182, 665));
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

                                            Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

                                            Stage window = (Stage) agentConfirmBtn.getScene().getWindow();
                                            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
                                            window.setScene(new Scene(root, 1182, 665));

                                        } else if (propFurnish.getSelectionModel().getSelectedItem().equals("Partially furnished")) {
                                            int furnish = 1;
                                            addProperty(propType.getSelectionModel().getSelectedItem(), firstAdd.getText(), secondAdd.getText(),
                                                    facList, Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                                    Float.parseFloat(propRent.getText()), currentUserID);

                                            Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

                                            Stage window = (Stage) agentConfirmBtn.getScene().getWindow();
                                            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
                                            window.setScene(new Scene(root, 1182, 665));

                                        } else if (propFurnish.getSelectionModel().getSelectedItem().equals("Furnished")) {
                                            int furnish = 2;
                                            addProperty(propType.getSelectionModel().getSelectedItem(), firstAdd.getText(), secondAdd.getText(),
                                                    facList, Integer.parseInt(propBed.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propBath.getSelectionModel().getSelectedItem()),
                                                    Integer.parseInt(propArea.getText()), furnish, Float.parseFloat(propPSF.getText()),
                                                    Float.parseFloat(propRent.getText()), currentUserID);

                                            Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

                                            Stage window = (Stage) agentConfirmBtn.getScene().getWindow();
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