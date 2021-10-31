package com.kuthingalas.propertysifu.usertype;



public class Tenant extends User {

    // private members
    //private String; // change accordingly

    // public members


    // constructors
    public Tenant(String username, String pass, String fName, String lName, String phone, int verified) {

        this.userID = username; // user can set their own custom username/userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.userType = "Tenant";
        this.verified = verified;

    }

    // new tenants added from add() is yet to be verified by admin
    public Tenant(String username, String pass, String fName, String lName, String phone) {

        this.userID = username; // user can set their own custom username/userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.userType = "Tenant";
        this.verified = 0;

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