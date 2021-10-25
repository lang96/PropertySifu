package com.kuthingalas.propertysifu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class LoginController {

    @FXML
    private CheckBox checkpass ;

    @FXML
    private Hyperlink regisbtn,signupbtn;

    @FXML
    public TextField userid;
    public PasswordField userpass;

    @FXML
    Button loginbtn , resetbtn  ,confirmregisbtn ,profile , confirmBtn ;

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
            String username = userid.getText();
            String pass = userpass.getText();


            while (scan.hasNext()) {
                usertxt = scan.nextLine();
                passtxt = scan.nextLine();

            }

            if (username.equals(usertxt) && pass.equals(passtxt)) {
                Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));

                Stage window = (Stage) confirmregisbtn.getScene().getWindow();
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
                userid.setText("");
                userpass.setText("");
            }
        } catch (IOException d) {
            d.printStackTrace();
        }
    }

    @FXML
    public void toregis() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("registerpage.fxml"));

        Stage window = (Stage) regisbtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));

    }

    @FXML
    public void toregis2() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("registerpage.fxml"));

        Stage window = (Stage) signupbtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    @FXML
    public void actionPerformed(ActionEvent e) {

        if (checkpass.isSelected()){
            userpass.setPromptText(userpass.getText());
            userpass.setText("");

        }else {
            userpass.setText(userpass.getPromptText());
            userpass.setPromptText("");
        }
    }

    @FXML
    public void reset(ActionEvent event){

        userid.setText("");
        userpass.setText("");

    }

    public void tohome() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Details Confirmation");
        alert.setHeaderText("Please confirm the details");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("listingpage.fxml"));

            Stage window = (Stage) confirmregisbtn.getScene().getWindow();
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.setScene(new Scene(root,1182,665));
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    public void toprof() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("viewprofile.fxml"));

        Stage window = (Stage) profile.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void backhome() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("listingpage.fxml"));

        Stage window = (Stage) confirmBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,1182,665));
    }




}
