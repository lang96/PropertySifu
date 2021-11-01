package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.*;

import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.system.Property.Comment.*;
import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.usertype.Agent.*;
import static com.kuthingalas.propertysifu.usertype.Owner.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.MainApplication.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;



public class LoginController {

    @FXML
    private CheckBox checkPass;

    @FXML
    private Hyperlink regBtn, signupBtn;

    @FXML
    public TextField userID, phone, org, fName, lName;
    public PasswordField userPass;

    @FXML
    Button loginBtn, resetBtn, backBtn;

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

                    currentUserID = username;
                    currentUserType = "Admin";

                    for (int i = 0; i < AdminList.size(); i++) {
                        if (AdminList.get(i).getAdminID().equals(currentUserID)) {
                            adminAccessLvl = AdminList.get(i).getAdminAccessLvl();
                        }
                    }

                    // go to admin homepage
                    try {
                        root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (type.equals("Agent") || type.equals("Owner")) {

                    currentUserID = username;

                    for (int i = 0; i < UserList.size(); i++) {
                        if (UserList.get(i).getUserID().equals(currentUserID)) {
                            currentUserType = UserList.get(i).getUserType();
                        }
                    }

                    // go to agent/owner homepage
                    try {
                        root = FXMLLoader.load(getClass().getResource("agentHomepage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (type.equals("Tenant")) {

                    currentUserID = username;
                    currentUserType = "Tenant";

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
    public void toAgentRegister() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentRegister.fxml"));

        Stage window = (Stage) signupBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    @FXML
    public void toHomepage() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));

        Stage window = (Stage) backBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
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

}