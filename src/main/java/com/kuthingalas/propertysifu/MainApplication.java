package com.kuthingalas.propertysifu;

import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class MainApplication extends Application  {

    public static String currentUserID; // determine logged in user
    public static String currentUserType; // determine logged in userType

    public static int adminAccessLvl; // if admin logged in, determine accessLvl

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        primaryStage.setTitle("Property Sifu");
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        primaryStage.setScene(new Scene(root,1182,665));
        primaryStage.show();
    }

    public static void main(String[] args) {

        // startup setup
        initializeAdmin();
        initializeProperty();
        initializeUser();

        launch();

    }
}