package com.kuthingalas.propertysifu;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.usertype.User.UserList;

public class PendingUsersController implements Initializable {

    @FXML
    private Button back8;

    @FXML
    private TableView<PendingUser> tblPending;

    @FXML
    private TableColumn<PendingUser, String> col_showID, col_showType;

    ObservableList pendingList =  FXCollections.observableArrayList();

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

    public void rejectUserReg() {

        //tblPending.getSelectionModel().getSelectedItem();

    }

    public void acceptUserReg() {



    }

    public void toManageUsers2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) back8.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    public class PendingUser{

        SimpleStringProperty ID;
        SimpleStringProperty type;
        SimpleStringProperty verified;

        public PendingUser(int index){
            this.ID = new SimpleStringProperty(UserList.get(index).getUserID());
            this.type = new SimpleStringProperty(UserList.get(index).getUserType());
            this.verified = new SimpleStringProperty(String.valueOf(UserList.get(index).getVerified()));
        }

        public String getID() {
            return ID.get();
        }

        public String getType() {
            return type.get();
        }

        public String getVerified() {
            return verified.get();
        }

    }

}
