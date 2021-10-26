package com.kuthingalas.propertysifu.usertype;

import java.util.ArrayList;



public abstract class User {

    // protected members
    protected String userID, userPass, userType, fName, lName, phoneNum;

    // public members
    public static ArrayList<User> UserList = new ArrayList<>();

    // methods

        // getters
    public String getUserID() {
        return userID;
    }
    public String getUserPass() {
        return this.userPass;
    }
    public String getUserType() {
        return this.userType;
    }
    public String getFName() {
        return this.fName;
    }
    public String getLName() {
        return this.lName;
    }
    public String getPhoneNum() {
        return this.phoneNum;
    }

        // setters
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    public void setFName(String fName) {
        this.fName = fName;
    }
    public void setLName(String lName) {
        this.lName = lName;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

        // toString
    /*
    public String toString() {

        return String.format("%-15s  %-11s  %-6s \n", adminID, adminPass, String.valueOf(accessLvl));

    }
    */

        // list and JSON methods

        // accompanying methods

    // static void setUserID()


}