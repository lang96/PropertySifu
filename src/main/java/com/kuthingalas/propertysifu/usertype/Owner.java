package com.kuthingalas.propertysifu.usertype;

/* agent has elevated access (additional features) */
public class Owner extends User {

    // private members
    //private String; // change accordingly

    // public members


    // constructors
    public Owner(String id, String pass, String fName, String lName, String num) {

        //this.userID = id; // make function setID() to determine owner account userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = num;
        this.userType = "Owner";

    }

    // methods

        // getters
    //public String getUserName() {return this.userName}

        // setters


        // toString
    /*
    public String toString() {

        return String.format("%-15s  %-11s  %-6s \n", adminID, adminPass, String.valueOf(accessLvl));

    }
    */

        // list and JSON methods

        // accompanying methods

}