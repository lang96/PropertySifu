package com.kuthingalas.propertysifu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class propDetailsController implements Initializable {

    private HomepageController selectedProperty;

    @FXML private Label agentLbl;
    @FXML private Label psfLbl;
    @FXML private Label commentLbl;
    @FXML private Label facLbl;


    public void initData(HomepageController homepageController)
    {
        agentLbl.setText("Test");
    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
