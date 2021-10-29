package com.kuthingalas.propertysifu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class adminHomepageController {

    @FXML
    private Button manageAdminBtn,managePropertiesBtn,manageUsersBtn,adminInfoBtn,editAdminBtn,createAdminBtn,createNewPropertyBtn,adminEditPropertyBtn,pendingUsersBtn,addUsersBtn;

    public void toAdminInfo() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) adminInfoBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }


    public void toManageAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) manageAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }


    public void toManageProperties() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) managePropertiesBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }



    public void toManageUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) manageUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toEditAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("editAdmin.fxml"));

        Stage window = (Stage) editAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toCreateAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewAdmin.fxml"));

        Stage window = (Stage) createAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,278,380));
    }

    public void toCreateNewProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewProperty.fxml"));

        Stage window = (Stage) createNewPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toAdminEditProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminAddComment.fxml"));

        Stage window = (Stage) adminEditPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toPendingUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("pendingUsers.fxml"));

        Stage window = (Stage) pendingUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toAddUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addUsers.fxml"));

        Stage window = (Stage) addUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }
}
