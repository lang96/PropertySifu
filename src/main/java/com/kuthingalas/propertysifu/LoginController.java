package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.Agent;
import com.kuthingalas.propertysifu.usertype.Owner;
import com.kuthingalas.propertysifu.usertype.Tenant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import net.synedra.validatorfx.Check;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import static com.kuthingalas.propertysifu.system.Property.PropertyList;
import static com.kuthingalas.propertysifu.usertype.User.UserList;

public class LoginController {

    @FXML
    private CheckBox checkPass, ownerBox, agentBox;

    @FXML
    private Hyperlink regBtn, signupBtn;

    @FXML
    private Label orglbl;

    @FXML
    public TextField userID, phone, org;
    public PasswordField userPass;

    @FXML
    Button loginBtn, resetBtn, confirmRegBtn, profile, confirmBtn, backBtn;

    public void proceed() {

        ArrayList<String> IDArr = new ArrayList<>();
        ArrayList<String> passArr = new ArrayList<>();
        ArrayList<String> typeArr = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray JSONIDArr = (JSONArray) userData.get("userID");
            JSONArray JSONpassArr = (JSONArray) userData.get("pass");
            JSONArray JSONtypeArr = (JSONArray) userData.get("type");

            if (JSONIDArr.size() != 0) { // only proceed with array if there are users registered

                for (int i = 0; i < JSONIDArr.size(); i++) {

                    String ID = "" + JSONIDArr.get(i);
                    String pass = "" + JSONpassArr.get(i);
                    String type = "" + JSONtypeArr.get(i);

                    IDArr.add(ID);
                    passArr.add(pass);
                    typeArr.add(type);

                }

            } else {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("No users registered!");
                errorAlert.setContentText("Please register for a new account.");
                errorAlert.showAndWait();

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

        String username = userID.getText();
        String pass = userPass.getText();

        boolean found = false;
        int idIndex = 0;

        for (int i = 0; i < IDArr.size(); i++) {

            if (IDArr.get(i).equals(username)) {
                found = true;
                idIndex = i;
            }

        }

        if (found) {

            if (passArr.get(idIndex).equals(pass)) {

                String type = typeArr.get(idIndex);
                Parent root = null;

                if (type.equals("Admin")) {
                    // go to admin homepage
                } else if (type.equals("Agent") || type.equals("Owner")) {
                    // go to agent homepage
                    try {
                        root = FXMLLoader.load(getClass().getResource("tenantHomepage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (type.equals("Tenant")) {
                    // go to tenant homepage
                    try {
                        root = FXMLLoader.load(getClass().getResource("tenantHomepage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Stage window = (Stage) loginBtn.getScene().getWindow();
                window.setScene(new Scene(root,1182,665));

            } else {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Wrong Username or Password");
                errorAlert.showAndWait();
                userID.setText("");
                userPass.setText("");

            }

        } else {

            if (username.equals("") || pass.equals("")) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Please insert Username and Password");
                errorAlert.showAndWait();

            } else {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Wrong Username or Password");
                errorAlert.showAndWait();
                userID.setText("");
                userPass.setText("");}

        }

    }

    @FXML
    public void toRegister() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("userRegister.fxml"));

        Stage window = (Stage) regBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));

    }

    @FXML
    public void toRegister2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentRegister.fxml"));

        Stage window = (Stage) signupBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    @FXML
    public void actionPerformed(ActionEvent e) {

        if (checkPass.isSelected()){
            userPass.setPromptText(userPass.getText());
            userPass.setText("");

        }else {
            userPass.setText(userPass.getPromptText());
            userPass.setPromptText("");
        }
    }

    @FXML
    public void reset(ActionEvent event){

        userID.setText("");
        userPass.setText("");

    }

    @FXML
    public void toHome() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Details Confirmation");
        alert.setHeaderText("Please confirm the details");
        alert.setContentText("Are you fine with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("tenantHomepage.fxml"));

            Stage window = (Stage) confirmRegBtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root,1182,665));
        } else {
            //  user chose CANCEL or closed the dialog
        }

    }

    public void toHome2() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Details Confirmation");
        alert.setHeaderText("Please confirm the details");
        alert.setContentText("Are you fine with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));

            Stage window = (Stage) confirmRegBtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root,1182,665));
        } else {
            //  user chose CANCEL or closed the dialog
        }

    }

    public void backHome() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Details Confirmation");
        alert.setHeaderText("Please confirm the details");
        alert.setContentText("Are you fine with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("tenantHomepage.fxml"));

            Stage window = (Stage) confirmBtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root, 1182, 665));
        } else {
            //  user chose CANCEL or closed the dialog
        }
    }

    @FXML
    void Select(ActionEvent event) {

        if (agentBox.isSelected()){
            org.setVisible(true);
            orglbl.setVisible(true);
        }
        else {
            org.setVisible(false);
            orglbl.setVisible(false);
        }

    }

}