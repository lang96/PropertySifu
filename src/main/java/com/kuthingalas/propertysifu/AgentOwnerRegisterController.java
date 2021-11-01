package com.kuthingalas.propertysifu;

import javafx.event.ActionEvent;
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

public class AgentOwnerRegisterController {

    @FXML
    private CheckBox checkPass, ownerBox, agentBox;

    @FXML
    private Hyperlink regBtn, signupBtn;

    @FXML
    private Label orglbl;

    @FXML
    public TextField idNum, phone, org, fName, lName;
    public PasswordField userPass;

    @FXML
    Button loginBtn, resetBtn, confirmRegBtn, profile, confirmBtn, backBtn;

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toHome
     *     Purpose         : For user to go homepage
     */

    public void toHome() throws IOException {

        if (agentBox.isSelected()) {

            String regID = idNum.getText();
            String regFName = fName.getText();
            String regLName = lName.getText();
            String regPhone = phone.getText();
            String regPass = userPass.getText();
            String regOrg = org.getText();

            addAgent(regPass, regFName, regLName, regPhone, regID, regOrg);

        } else {

            if (ownerBox.isSelected()) {

                String regID = idNum.getText();
                String regFName = fName.getText();
                String regLName = lName.getText();
                String regPhone = phone.getText();
                String regPass = userPass.getText();

                addOwner(regPass, regFName, regLName, regPhone, regID);

            }

        }

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

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toLoginPage2
     *     Purpose         : For user to go to Login Page
     */

    public void toLoginPage2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Stage window = (Stage) backBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 597, 338));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : Select
     *     Purpose         : To make a textfield appear for Agent
     */

    @FXML
    void Select(ActionEvent event) {

        if (agentBox.isSelected()){
            org.setVisible(true);
            orglbl.setVisible(true);
        }
        else {
            org.setVisible(false);
            orglbl.setVisible(false);
        }

    }

}
