package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.usertype.User;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.system.Property.Comment.*;
import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.usertype.Agent.*;
import static com.kuthingalas.propertysifu.usertype.Owner.*;

public class adminHomepageController implements Initializable {



    @FXML
    private Button manageAdminBtn, managePropertiesBtn, manageUsersBtn, adminInfoBtn, editAdminBtn, createAdminBtn, createNewPropertyBtn, adminEditPropertyBtn, pendingUsersBtn, addUsersBtn;

    @FXML
    private TextField collectUserID, collectUserPass, collectUserFName, collectUserLName, collectUserPhone, collectUserIDNum, collectUserOrg;


    //@FXML private option collectUserType;

    @FXML
    private TableView<ManageUser> tblUser, tblPending;
    @FXML
    private TableColumn<ManageUser, String> col_id, col_type, col_FName, col_LName, col_phone, col_idNum, col_org, col_verified;

    ObservableList list =  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*
        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_FName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_idNum.setCellValueFactory(new PropertyValueFactory<>("idNum"));
        col_org.setCellValueFactory(new PropertyValueFactory<>("org"));
        col_verified.setCellValueFactory(new PropertyValueFactory<>("verified"));

        for (int i = 0; i < UserList.size(); i++) {
            list.add(new ManageUser(i));
        }

        if(list.isEmpty()) {
            tblUser.setPlaceholder(new Label("No unverified users found."));
        } else {
            tblUser.setItems(list);
        }
        */

    }

    public void addUser() {



    }

    public void rejectUserReg() {

       //tblPending.getSelectionModel().getSelectedItem();

    }

    public void acceptUserReg() {



    }

    // navigation
    public void toAdminInfo() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminInfo.fxml"));

        Stage window = (Stage) adminInfoBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 597, 338));
    }


    public void toManageAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) manageAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }


    public void toManageProperties() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) managePropertiesBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 809, 456));
    }

    public void toManageUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) manageUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    public void toEditAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("editAdmin.fxml"));

        Stage window = (Stage) editAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 597, 338));
    }

    public void toCreateAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewAdmin.fxml"));

        Stage window = (Stage) createAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 278, 380));
    }

    public void toCreateNewProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewProperty.fxml"));

        Stage window = (Stage) createNewPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 370, 800));
    }

    //Add comment
    public void toAdminEditProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminAddComment.fxml"));

        Stage window = (Stage) adminEditPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 716, 403));
    }

    public void toPendingUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("pendingUsers.fxml"));

        Stage window = (Stage) pendingUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 716, 403));
    }

    public void toAddUsers() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addUsers.fxml"));

        Stage window = (Stage) addUsersBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 276, 597));
    }


    ///////////////////Back Button Functions///////////////////////////
    @FXML
    private Button back, back1, back2, back3, back4, back5, back6, back7, back8, back9;

    public void toAdminHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }


    public void toAdminHomepage2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back1.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    public void toManageAdmins3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageAdmin.fxml"));

        Stage window = (Stage) back3.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 741, 417));
    }

    public void toAdminHomepage3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back4.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    public void toManageProperties3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageProperties.fxml"));

        Stage window = (Stage) back6.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 809, 456));
    }

    public void toAdminHomepage4() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back7.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    public void toManageUsers2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) back8.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    public void toManageUsers3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageUsers.fxml"));

        Stage window = (Stage) back9.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 762, 431));
    }

    //////////////////////////////////////////////////////////////////

    ////////////////////////Combobox/////////////////////

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
