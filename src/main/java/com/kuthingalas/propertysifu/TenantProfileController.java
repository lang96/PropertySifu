package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.usertype.User;
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

import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.MainApplication.*;

public class TenantProfileController implements Initializable {

    @FXML
    private Button profile, confirmBtn;

    @FXML
    public TextField profID, profFName, profLName, profPhone, profPass;

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

}
