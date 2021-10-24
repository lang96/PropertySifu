package com.kuthingalas.propertysifu.usertype;

/* Agent has elevated access (additional features) */
public class Agent extends User {

    // private members
    //private String; // change accordingly

    // public members


    // constructors
    public Agent(String id, String pass, String fName, String lName, String num) {

        //this.userID = id; // make function setID() to determine agent account userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = num;
        this.userType = "Agent";

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