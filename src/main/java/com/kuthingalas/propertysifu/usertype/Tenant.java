package com.kuthingalas.propertysifu.usertype;

public class Tenant extends User {

    // private members
    //private String; // change accordingly

    // public members


    // constructors
    public Tenant(String username, String pass, String fName, String lName, String phone) {

        this.userID = username; // user can set their own custom username/userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.userType = "Tenant";

    }

    // methods

        // getters


        // setters


        // toString
    /*
    public String toString() {

        return String.format("%-15s  %-11s  %-6s \n", adminID, adminPass, String.valueOf(accessLvl));

    }
    */

        // list and JSON methods

        // accompanying methods

    // void addTenant()
    // void updateProfile()
    // void removeTenant()

}