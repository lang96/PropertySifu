package com.kuthingalas.propertysifu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.synedra.validatorfx.Check;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class LoginController {

    @FXML
    private CheckBox checkPass, ownerBox,agentBox;

    @FXML
    private Hyperlink regBtn, signupBtn;
    @FXML
    private Label orglbl;

    @FXML
    public TextField userID,phone ,org;
    public PasswordField userPass;

    @FXML
    Button loginBtn, resetBtn, confirmRegBtn, profile, confirmBtn, backBtn;

    public void proceed() {

        Writer writer = null;
        File check = new File("userPass.txt");

        if(check.exists()){

            //Checks if the file exists. will not add anything if the file does exist.
        }else{
            try{
                File texting = new File("userPass.txt");
                writer = new BufferedWriter(new FileWriter(texting));
                writer.write("message");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        try {
            File file = new File("userPass.txt");
            Scanner scan = new Scanner(file);
            String line = null;
            FileWriter filewrite = new FileWriter(file, true);
            String usertxt = " ";
            String passtxt = " ";
            String username = userID.getText();
            String pass = userPass.getText();


            while (scan.hasNext()) {
                usertxt = scan.nextLine();
                passtxt = scan.nextLine();

            }

            if (username.equals(usertxt) && pass.equals(passtxt)) {
                Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));

                Stage window = (Stage) confirmRegBtn.getScene().getWindow();
                window.setScene(new Scene(root,1182,665));
            } else if (username.equals("") && pass.equals("")) {

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
                userPass.setText("");
            }
        } catch (IOException d) {
            d.printStackTrace();
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
