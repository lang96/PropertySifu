package com.kuthingalas.propertysifu;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.MainApplication.adminAccessLvl;
import static com.kuthingalas.propertysifu.usertype.User.UserList;
import static com.kuthingalas.propertysifu.data.DataOperation.*;



public class PendingUsersController implements Initializable {

    @FXML
    private Button acceptBtn, rejectBtn, back8;

    @FXML
    private TableView<PendingUser> tblPending;

    @FXML
    private TableColumn<PendingUser, String> col_showID, col_showType;

    ObservableList pendingList =  FXCollections.observableArrayList();



    /**
     *     Programmer's Name:
     *     Method's Name    : initialize
     *     Purpose         : This method is to set initial value of table column
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_showID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_showType.setCellValueFactory(new PropertyValueFactory<>("type"));

        for (int i = 0; i < UserList.size(); i++) {
            if (UserList.get(i).getVerified() == 0) {
                pendingList.add(new PendingUser(i));
            }
        }

        if(pendingList.isEmpty()) {
            tblPending.setPlaceholder(new Label("No unverified users found."));
        } else {
            tblPending.setItems(pendingList);
        }

    }

    /**
     *     Programmer's Name:
     *     Method's Name    : rejectUserReg
     *     Purpose         : This method is to set reject pending user registration
     */

    public void rejectUserReg() {

        String removeID = tblPending.getSelectionModel().getSelectedItem().getID();

        if (removeID == null && removeID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No user selected!");
            errorAlert.showAndWait();

        } else {

            if (adminAccessLvl == 0) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Sorry, you do not have clearance to do this!");
                errorAlert.showAndWait();

            } else {

                removeUser(removeID);

                pendingList.clear();

                for (int i = 0; i < UserList.size(); i++) {
                    if (UserList.get(i).getVerified() == 0) {
                        pendingList.add(new PendingUser(i));
                    }
                }

                if (pendingList.isEmpty()) {
                    tblPending.setPlaceholder(new Label("No unverified users found."));
                } else {
                    tblPending.setItems(pendingList);
                }

            }

        }

    }

    /**
     *     Programmer's Name:
     *     Method's Name    : acceptUserReg
     *     Purpose         : This method is to set accept pending user registration
     */

    public void acceptUserReg() {

        String verifyID = tblPending.getSelectionModel().getSelectedItem().getID();
        String verifyPass = tblPending.getSelectionModel().getSelectedItem().getPass();
        String verifyType = tblPending.getSelectionModel().getSelectedItem().getType();

        if (verifyID == null && verifyID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No user selected!");
            errorAlert.showAndWait();

        } else {

            if (adminAccessLvl == 0) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Sorry, you do not have clearance to do this!");
                errorAlert.showAndWait();

            } else {

                updateUserVerified(verifyID);
                addLogin(verifyID, verifyPass, verifyType);

                pendingList.clear();

                for (int i = 0; i < UserList.size(); i++) {
                    if (UserList.get(i).getVerified() == 0) {
                        pendingList.add(new PendingUser(i));
                    }
                }

                if (pendingList.isEmpty()) {
                    tblPending.setPlaceholder(new Label("No unverified users found."));
                } else {
                    tblPending.setItems(pendingList);
                }

            }

        }

    }

    /**
     *     Programmer's Name:
     *     Method's Name    : toManageUsers2
     *     Purpose         : This method is to change scene
     */

    public void toManageUsers2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) back8.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    /**
     *     Programmer's Name:
     *     Method's Name    : PendingUser
     *     Purpose         : This method is to get Pending User Variables
     */

    public class PendingUser{

        SimpleStringProperty ID;
        SimpleStringProperty pass;
        SimpleStringProperty type;
        SimpleStringProperty verified;

        public PendingUser(int index){
            this.ID = new SimpleStringProperty(UserList.get(index).getUserID());
            this.pass = new SimpleStringProperty(UserList.get(index).getUserPass());
            this.type = new SimpleStringProperty(UserList.get(index).getUserType());
            this.verified = new SimpleStringProperty(String.valueOf(UserList.get(index).getVerified()));
        }

        public String getID() {
            return ID.get();
        }

        public String getPass() {
            return pass.get();
        }

        public String getType() {
            return type.get();
        }

        public String getVerified() {
            return verified.get();
        }

    }

}
