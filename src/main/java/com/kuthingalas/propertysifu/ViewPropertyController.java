package com.kuthingalas.propertysifu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static com.kuthingalas.propertysifu.usertype.User.UserList;

public class ViewPropertyController implements Initializable {

    @FXML private Label propTypeLabel;
    @FXML private Label firstAddLabel;
    @FXML private Label secondAddLabel;
    @FXML private Label bedLabel;
    @FXML private Label bathLabel;
    @FXML private Label furnishingLabel;
    @FXML private Label facilitiesLabel;
    @FXML private Label areaLabel;
    @FXML private Label psfLabel;
    @FXML private Label rentLabel;
    @FXML private Label agentLabel;
    @FXML private Label agentContactLabel;
    @FXML private Label commentLabel;


    public void initData(Property property) {

        // determine furnishing
        String furnishing = "";

        switch (property.getFurnishing()) {

            case (0):
                furnishing = "Unfurnished";
                break;
            case (1):
                furnishing = "Partially furnished";
                break;
            case (2):
                furnishing = "Fully furnished";
                break;

        }

        // determine agent name
        String agentName = "";

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserID().equals(property.getRepresentativeID())) {
                agentName = UserList.get(i).getFName() + " " + UserList.get(i).getLName();
                break;
            } else {
                agentName = "Error, not found!";
            }

        }

        propTypeLabel.setText(property.getPropertyType());
        firstAddLabel.setText(property.getFirstAddress());
        secondAddLabel.setText(property.getSecondAddress());
        bedLabel.setText(String.valueOf(property.getBedroom()));
        bathLabel.setText(String.valueOf(property.getBathroom()));
        furnishingLabel.setText(furnishing);
        //facilitiesLabel.setText(property.getFirstAddress());
        areaLabel.setText(String.valueOf(property.getArea()));
        psfLabel.setText(String.valueOf(property.getPsfRate()));
        rentLabel.setText(String.valueOf(property.getRentalRate()));
        agentLabel.setText(agentName);
        //agentContactLabel.setText(property.getFirstAddress());
        // comment label

    }

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
