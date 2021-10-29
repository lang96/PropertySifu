package com.kuthingalas.propertysifu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kuthingalas.propertysifu.system.Property;
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

    @FXML private Label agentLabel;
    @FXML private Label psqfLabel;
    @FXML private Label commentLabel;
    @FXML private Label facilitiesLabel;


//    public void initData(Property property)
//    {
//        agentLabel.setText(property.getPropertyType());
//    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("tenantHomepage.fxml"));
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
