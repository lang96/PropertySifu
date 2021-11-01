package com.kuthingalas.propertysifu;

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
import java.util.Optional;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.MainApplication.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;

public class AdminProfileController implements Initializable {

    @FXML
    private TextField adminID;

    @FXML
    private PasswordField adminPass;

    @FXML
    private Button back, logoutBtn3, confirmBtn;

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : initialize
     *     Purpose         : To set admin profile
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String pass = "";

        for (int i = 0; i < AdminList.size(); i++) {

            if (AdminList.get(i).getAdminID().equals(currentUserID)) {
                pass = AdminList.get(i).getAdminPass();
            }

        }

        adminID.setText(currentUserID);
        adminPass.setText(pass);

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAdminHomepage
     *     Purpose         : To go back to admin homepage
     */


    public void toAdminHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toConfirm
     *     Purpose         : To update admin info
     */

    public void toConfirm() throws IOException {

        int access = 0;
        String newID = adminID.getText();
        String newPass = adminPass.getText();

        for (int i = 0; i < AdminList.size(); i++) {

            if (AdminList.get(i).getAdminID().equals(currentUserID)) {
                access = AdminList.get(i).getAdminAccessLvl();
            }

        }

        updateAdmin(currentUserID, newID, access);
        updateAdminPass(newID, newPass);
        updateLogin(currentUserID, newID, newPass);
        currentUserID = newID;

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : logout3
     *     Purpose         : To log out of the system
     */

    public void logout3() throws IOException {

        currentUserID = "";
        currentUserType = "";
        adminAccessLvl = 0;

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

}
