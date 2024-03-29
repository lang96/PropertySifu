package com.kuthingalas.propertysifu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.data.DataOperation.*;

/**
 *     Programmer's Name: Shuhail & Arif
 *     Method's Name    : CreateNewAdminController
 *     Purpose         : To hold object
 */

public class CreateNewAdminController implements Initializable {

    ObservableList<String> accessLvl = FXCollections.observableArrayList("0","1");

    @FXML
    private ComboBox<String> adminAccess;

    @FXML
    private TextField adminID;

    @FXML
    private PasswordField adminPass;

    @FXML
    private Button back2, addAdmin;

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : initialize
     *     Purpose         : Set initial value of admin
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        adminAccess.setItems(accessLvl);

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : createAdmin
     *     Purpose         : create new admin
     */

    @FXML
    public void createAdmin() throws IOException {

        String id = adminID.getText();
        String pass = adminPass.getText();
        int access = Integer.parseInt(adminAccess.getSelectionModel().getSelectedItem());

        addAdmin(id, pass, access);
        addLogin(id, pass, "Admin");

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) back2.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toManageAdmins2
     *     Purpose         : to go back to manage admin page
     */


    public void toManageAdmins2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) back2.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }
}
