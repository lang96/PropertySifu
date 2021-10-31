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

public class AdminHomepageController {



    @FXML
    private Button manageAdminBtn, managePropertiesBtn, manageUsersBtn, adminInfoBtn, editAdminBtn, createAdminBtn, createNewPropertyBtn, adminEditPropertyBtn,logoutBtn3;

    @FXML
    private TextField collectUserID, collectUserPass, collectUserFName, collectUserLName, collectUserPhone, collectUserIDNum, collectUserOrg;


    //@FXML private option collectUserType;

    // navigation
    public void toAdminInfo() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminInfo.fxml"));

        Stage window = (Stage) adminInfoBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 717, 350));
    }


    public void toManageAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) manageAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }


    public void toManageProperties() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) managePropertiesBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 967, 544));
    }

    public void toManageUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) manageUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    public void toEditAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("editAdmin.fxml"));

        Stage window = (Stage) editAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 597, 338));
    }

    public void toCreateAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewAdmin.fxml"));

        Stage window = (Stage) createAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 278, 380));
    }

    public void toCreateNewProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewProperty.fxml"));

        Stage window = (Stage) createNewPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 370, 800));
    }

    //Add comment
    public void toAdminEditProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminAddComment.fxml"));

        Stage window = (Stage) adminEditPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 716, 403));
    }




    ///////////////////Back Button Functions///////////////////////////
    @FXML
    private Button back, back1, back2, back3, back4, back5, back6, back7, back8, back9;

    public void toAdminHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }


    public void toAdminHomepage2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back1.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    public void toManageAdmins3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) back3.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }

    public void toAdminHomepage3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back4.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
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

    public void logout3() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("Log Out");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));

            Stage window = (Stage) logoutBtn3.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root, 1182, 665));
        } else {
            //  user chose CANCEL or closed the dialog
        }
    }

    //////////////////////////////////////////////////////////////////

    ////////////////////////Combobox/////////////////////

}
