package com.kuthingalas.propertysifu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AgentHomepageController {

    @FXML
    private Button profile;

    @FXML
    private Button agentconfirmBtn;
    @FXML
    private Button editpropBtn;

    @FXML
    private Button agentbackhomeBtn;

    public void toProfile() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentViewProfile.fxml"));

        Stage window = (Stage) profile.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toeditProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("editproperty.fxml"));

        Stage window = (Stage) editpropBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toagentHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentconfirmBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }

    public void toagentHomepage2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

        Stage window = (Stage) agentbackhomeBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }


}
