package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.usertype.Admin;
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

import static com.kuthingalas.propertysifu.MainApplication.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.usertype.User.UserList;
import static com.kuthingalas.propertysifu.usertype.Admin.AdminList;


public class ManageAdminsController implements Initializable {

    @FXML
    private Button createAdminBtn;

    @FXML
    private Button back1, changeAccessBtn, revokeAdminBtn;

    @FXML
    private TableView<ListAdmin> tblAdmin;

    @FXML
    private TableColumn<ListAdmin, String> col_showID, col_showAccess;

    ObservableList adminList =  FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_showID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_showAccess.setCellValueFactory(new PropertyValueFactory<>("accessLvl"));

        for (int i = 0; i < AdminList.size(); i++) {
            adminList.add(new ListAdmin(i));
        }

        if(adminList.isEmpty()) {
            tblAdmin.setPlaceholder(new Label("No admins found."));
        } else {
            tblAdmin.setItems(adminList);
        }

    }

    public void revokeAdmin() {

        String removeID = tblAdmin.getSelectionModel().getSelectedItem().getID();

        if (removeID == null && removeID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No user selected!");
            errorAlert.showAndWait();

        } else {

            removeAdmin(removeID);
            removeLogin(removeID);

            adminList.clear();

            for (int i = 0; i < AdminList.size(); i++) {
                adminList.add(new ListAdmin(i));
            }

            if(adminList.isEmpty()) {
                tblAdmin.setPlaceholder(new Label("No users found."));
            } else {
                tblAdmin.setItems(adminList);
            }

        }

    }

    public void changeAccess() {

        String changeID = tblAdmin.getSelectionModel().getSelectedItem().getID();
        int access = 0;

        if (changeID == null && changeID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No user selected!");
            errorAlert.showAndWait();

        } else {

            for (int i = 0; i < AdminList.size(); i++) {
                if (AdminList.get(i).getAdminID().equals(changeID)) {
                    if (AdminList.get(i).getAdminAccessLvl() == 0) {
                        access = 1;
                    }
                }
            }

            updateAdmin(changeID, changeID, access);

            adminList.clear();

            for (int i = 0; i < UserList.size(); i++) {
                adminList.add(new ListAdmin(i));
            }

            if(adminList.isEmpty()) {
                tblAdmin.setPlaceholder(new Label("No users found."));
            } else {
                tblAdmin.setItems(adminList);
            }

        }

    }

    public void toCreateAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewAdmin.fxml"));

        Stage window = (Stage) createAdminBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 278, 380));
    }

    public void toAdminHomepage2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back1.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    public class ListAdmin{

        SimpleStringProperty ID;
        SimpleStringProperty pass;
        SimpleStringProperty accessLvl;

        public ListAdmin(int index){
            this.ID = new SimpleStringProperty(AdminList.get(index).getAdminID());
            this.pass = new SimpleStringProperty(AdminList.get(index).getAdminPass());
            this.accessLvl = new SimpleStringProperty(String.valueOf(AdminList.get(index).getAdminAccessLvl()));
        }

        public String getID() {
            return ID.get();
        }

        public String getPass() {
            return pass.get();
        }

        public String getAccessLvl() {
            return accessLvl.get();
        }

    }


}
