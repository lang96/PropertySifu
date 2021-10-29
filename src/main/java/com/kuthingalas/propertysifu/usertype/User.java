package com.kuthingalas.propertysifu.usertype;

import com.kuthingalas.propertysifu.system.Property;

import java.util.ArrayList;



public abstract class User {

    // protected members
    protected String userID, userPass, userType, fName, lName, phoneNum;
    protected int verified;

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
    public int getVerified() {
        return verified;
    }
    // Override methods
    public String getIdNum() {
        String idNum = "";
        return idNum;
    }
    public String getOrganization() {
        String organization = "";
        return organization;
    }
    public ArrayList<Property> getProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        return properties;
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
    public void setVerified(int verified) {
        this.verified = verified;
    }
    // Override methods
    public void setIdNum(String idNum) {
        String idNumber = idNum;
    }
    public void setOrganization(String org) {
        String organization = org;
    }

    /*
    public void setProperties(ArrayList<Property> properties) {
        ArrayList<Property> propertyList = properties;
    } // to be overridden
    */

        // toString
    /*
    public String toString() {

        return String.format("%-15s  %-11s  %-6s \n", adminID, adminPass, String.valueOf(accessLvl));

    }
    */

        // list and JSON methods

        // accompanying methods

}