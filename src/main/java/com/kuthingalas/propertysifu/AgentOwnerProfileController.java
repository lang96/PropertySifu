package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.usertype.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.MainApplication.currentUserID;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.usertype.User.UserList;

public class AgentOwnerProfileController implements Initializable {

    @FXML
    private Button agentConfirmBtn;

    @FXML
    private Label orgLbl;

    @FXML
    public TextField profIDNum, profFName, profLName, profPhone, profPass, profOrg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserID().equals(currentUserID)) {

                if (UserList.get(i).getUserType().equals("Agent")){
                    profOrg.setVisible(true);
                    orgLbl.setVisible(true);
                    profIDNum.setText(UserList.get(i).getIdNum());
                    profFName.setText(UserList.get(i).getFName());
                    profLName.setText(UserList.get(i).getLName());
                    profPhone.setText(UserList.get(i).getPhoneNum());
                    profPass.setText(UserList.get(i).getUserPass());
                    profOrg.setText(UserList.get(i).getOrganization());
                }
                else {
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

    public void toAgentHomepage() throws IOException {

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserID().equals(currentUserID)) {
                if (UserList.get(i).getUserType().equals("Agent")){
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
        window.setScene(new Scene(root,1182,665));
    }

}
