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

import static com.kuthingalas.propertysifu.MainApplication.currentUserID;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.system.Property.PropertyList;
import static com.kuthingalas.propertysifu.usertype.User.UserList;




public class ManageUsersController implements Initializable {

    @FXML
    private Button pendingUsersBtn, removeUsersBtn, back7, back8;

    @FXML
    private TableView<ManageUser> tblUser;

    @FXML
    private TableColumn<ManageUser, String> col_id, col_type, col_FName, col_LName, col_phone, col_idNum, col_org;

    ObservableList list =  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_FName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_idNum.setCellValueFactory(new PropertyValueFactory<>("idNum"));
        col_org.setCellValueFactory(new PropertyValueFactory<>("org"));

        for (int i = 0; i < UserList.size(); i++) {
            if (UserList.get(i).getVerified() == 1) {
                list.add(new ManageUser(i));
            }
        }

        if(list.isEmpty()) {
            tblUser.setPlaceholder(new Label("No verified users found."));
        } else {
            tblUser.setItems(list);
        }

    }

    public void revokeUser() {

        String removeID = tblUser.getSelectionModel().getSelectedItem().getID();

        if (removeID == null && removeID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No user selected!");
            errorAlert.showAndWait();

        } else {

            removeUser(removeID);
            removeLogin(removeID);

            list.clear();

            for (int i = 0; i < UserList.size(); i++) {
                if (UserList.get(i).getVerified() == 1) {
                    list.add(new ManageUser(i));
                }
            }

            if(list.isEmpty()) {
                tblUser.setPlaceholder(new Label("No users found."));
            } else {
                tblUser.setItems(list);
            }

        }

    }

    public void toPendingUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("pendingUsers.fxml"));

        Stage window = (Stage) pendingUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 716, 403));
    }

    public void toAdminHomepage4() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back7.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    public class ManageUser{

        SimpleStringProperty ID;
        SimpleStringProperty type;
        SimpleStringProperty fName;
        SimpleStringProperty lName;
        SimpleStringProperty phone;
        SimpleStringProperty idNum;
        SimpleStringProperty org;
        SimpleStringProperty verified;

        public ManageUser(int index){
            this.ID = new SimpleStringProperty(UserList.get(index).getUserID());
            this.type = new SimpleStringProperty(UserList.get(index).getUserType());
            this.fName = new SimpleStringProperty(UserList.get(index).getFName());
            this.lName = new SimpleStringProperty(UserList.get(index).getLName());
            this.phone = new SimpleStringProperty(UserList.get(index).getPhoneNum());
            this.idNum = new SimpleStringProperty(UserList.get(index).getIdNum());
            this.org = new SimpleStringProperty(UserList.get(index).getOrganization());
            this.verified = new SimpleStringProperty(String.valueOf(UserList.get(index).getVerified()));
        }

        public String getID() {
            return ID.get();
        }

        public String getType() {
            return type.get();
        }

        public String getFName() {
            return fName.get();
        }

        public String getLName() {
            return lName.get();
        }

        public String getPhone() {
            return phone.get();
        }

        public String getIdNum() {
            return idNum.get();
        }

        public String getOrg() {
            return org.get();
        }

        public String getVerified() {
            return verified.get();
        }

    }

}
