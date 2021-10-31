package com.kuthingalas.propertysifu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static com.kuthingalas.propertysifu.data.DataOperation.*;



public class RegisterController {

    @FXML
    Button confirmRegBtn;

    @FXML
    public TextField userID, phone, fName, lName;
    public PasswordField userPass;

    @FXML
    public void toHome() throws IOException {

        String regID = userID.getText();
        String regFName = fName.getText();
        String regLName = lName.getText();
        String regPhone = phone.getText();
        String regPass = userPass.getText();

        addTenant(regID, regPass, regFName, regLName, regPhone);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Details Confirmation");
        alert.setHeaderText("Please confirm the details");
        alert.setContentText("Are you fine with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));

            Stage window = (Stage) confirmRegBtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root,1182,665));
        } else {
            //  user chose CANCEL or closed the dialog
        }

    }

}
