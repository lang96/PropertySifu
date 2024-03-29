package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.usertype.User;
import javafx.event.ActionEvent;
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

import static com.kuthingalas.propertysifu.MainApplication.currentUserID;
import static com.kuthingalas.propertysifu.MainApplication.currentUserType;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.usertype.User.UserList;

public class AgentOwnerProfileController implements Initializable {

    @FXML
    private Button agentConfirmBtn, logoutBtn2;

    @FXML
    private Label orgLbl;

    @FXML
    public TextField profIDNum, profFName, profLName, profPhone, profPass, profOrg;

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : initialize
     *     Purpose         : To set initial value of logged in user
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserID().equals(currentUserID)) {

                if (UserList.get(i).getUserType().equals("Agent")) {
                    profOrg.setVisible(true);
                    orgLbl.setVisible(true);
                    profIDNum.setText(UserList.get(i).getIdNum());
                    profFName.setText(UserList.get(i).getFName());
                    profLName.setText(UserList.get(i).getLName());
                    profPhone.setText(UserList.get(i).getPhoneNum());
                    profPass.setText(UserList.get(i).getUserPass());
                    profOrg.setText(UserList.get(i).getOrganization());
                } else {
                    profOrg.setVisible(false);
                    orgLbl.setVisible(false);
                    profIDNum.setText(UserList.get(i).getIdNum());
                    profFName.setText(UserList.get(i).getFName());
                    profLName.setText(UserList.get(i).getLName());
                    profPhone.setText(UserList.get(i).getPhoneNum());
                    profPass.setText(UserList.get(i).getUserPass());
                }

            }

        }

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAgentHomepage
     *     Purpose         : To go to agent homepage and update the profile
     */

    public void toAgentHomepage() throws IOException {

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserID().equals(currentUserID)) {
                if (UserList.get(i).getUserType().equals("Agent")) {
                    updateAgent(currentUserID, profFName.getText(), profLName.getText(), profPhone.getText(), profIDNum.getText(), profOrg.getText());
                    updateUserPass(currentUserID, profPass.getText());
                    updateLogin(currentUserID, currentUserID, profPass.getText());
                }
            } else {
                updateOwner(currentUserID, profFName.getText(), profLName.getText(), profPhone.getText(), profIDNum.getText());
                updateUserPass(currentUserID, profPass.getText());
                updateLogin(currentUserID, currentUserID, profPass.getText());
            }
        }

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentConfirmBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 1182, 665));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : logout2
     *     Purpose         : To log out of the system
     */

    public void logout2() throws IOException {

        currentUserID = "";
        currentUserType = "";

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Log Out");
        alert.setHeaderText("Log Out");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));

            Stage window = (Stage) logoutBtn2.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root, 1182, 665));
        } else {
            //  user chose CANCEL or closed the dialog
        }

    }
}
