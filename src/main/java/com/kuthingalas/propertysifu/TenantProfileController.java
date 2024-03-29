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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class TenantProfileController implements Initializable {

    @FXML
    private Button confirmBtn , logoutBtn;

    @FXML
    public TextField profID, profFName, profLName, profPhone, profPass;

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : initialize
     *     Purpose         : This method is to set initial value of user logged in
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserID().equals(currentUserID)) {
                profID.setText(UserList.get(i).getUserID());
                profFName.setText(UserList.get(i).getFName());
                profLName.setText(UserList.get(i).getLName());
                profPhone.setText(UserList.get(i).getPhoneNum());
                profPass.setText(UserList.get(i).getUserPass());
            }

        }

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : backHome
     *     Purpose         : This method is to update changes of tenant profile and back to homepage
     */

    public void backHome() throws IOException {

        updateTenant(currentUserID, profID.getText(), profFName.getText(), profLName.getText(), profPhone.getText());
        updateUserPass(profID.getText(), profPass.getText());
        updateLogin(currentUserID, profID.getText(), profPass.getText());
        currentUserID = profID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Details Confirmation");
        alert.setHeaderText("Please confirm the details");
        alert.setContentText("Are you fine with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("tenantHomepage.fxml"));

            Stage window = (Stage) confirmBtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root, 1182, 665));
        } else {
            //  user chose CANCEL or closed the dialog
        }
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : logout
     *     Purpose         : To logout
     */

    public void logout() throws IOException {

        currentUserID = "";
        currentUserType = "";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("Log Out");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));

            Stage window = (Stage) logoutBtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root, 1182, 665));
        } else {
            //  user chose CANCEL or closed the dialog
        }

    }

}