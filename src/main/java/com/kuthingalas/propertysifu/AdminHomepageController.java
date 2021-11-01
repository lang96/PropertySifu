package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.usertype.User;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.system.Property.Comment.*;
import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.usertype.Agent.*;
import static com.kuthingalas.propertysifu.usertype.Owner.*;
import static com.kuthingalas.propertysifu.MainApplication.*;



public class AdminHomepageController implements Initializable {

    @FXML
    private Button manageAdminBtn, managePropertiesBtn, manageUsersBtn, adminInfoBtn, createNewPropertyBtn, adminEditPropertyBtn,logoutBtn3;

    @FXML
    private Label collectAdminID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        collectAdminID.setText(currentUserID);

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAdminInfo
     *     Purpose         : To go to admin info page
     */
    // navigation
    public void toAdminInfo() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminInfo.fxml"));

        Stage window = (Stage) adminInfoBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 717, 350));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toManageAdmin
     *     Purpose         : To go to manage admin page
     */

    public void toManageAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) manageAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toManageProperties
     *     Purpose         : To go to manage properties  page
     */

    public void toManageProperties() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) managePropertiesBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 967, 544));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toManageUsers
     *     Purpose         : To go to manage users  page
     */

    public void toManageUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) manageUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAdminEditProperty
     *     Purpose         : To go to admin add comment page
     */

    //Add comment
    public void toAdminEditProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminAddComment.fxml"));

        Stage window = (Stage) adminEditPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 716, 403));
    }




    ///////////////////BACK BUTTON FUNCTIONS///////////////////////////
    @FXML
    private Button back2, back3, back4, back5, back6, back7, back8, back9;

    public void toManageAdmins3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) back3.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }

    public void toManageProperties3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) back6.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 967, 544));
    }

    public void toManageUsers3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) back9.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

}
