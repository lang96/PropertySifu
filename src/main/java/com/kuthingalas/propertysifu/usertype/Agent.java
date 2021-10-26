package com.kuthingalas.propertysifu.usertype;

import com.kuthingalas.propertysifu.system.Property;

import java.util.ArrayList;



/* Agent has elevated access (additional features) */
public class Agent extends User {

    // private members
    private String idNum, organization;
    private ArrayList<Property> properties;

    // public members


    // constructors
    public Agent(String userID, String pass, String fName, String lName, String phone, String id, String org) {

        //this.userID = userID; // make function setID() to determine agent account userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.organization = org;
        this.properties = new ArrayList<>();
        this.userType = "Agent";

    }

    public Agent(String userID, String pass, String fName, String lName, String phone, String id, String org, ArrayList<Property> propertyList) {

        //this.userID = userID; // make function setID() to determine agent account userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.organization = org;
        this.properties = propertyList;
        this.userType = "Agent";

    }

    // methods

        // getters
    public String getIdNum() {
        return idNum;
    }
    public String getOrganization() {
        return organization;
    }
    public ArrayList<Property> getProperties() {
        return properties;
    }

        // setters
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /* might change out with proper method below
    public void setProperties(ArrayList<Property> properties) {
        this.properties = new ArrayList<>(properties);
    }
    */

        // toString
    /*
    public String toString() {

        return String.format("%-15s  %-11s  %-6s \n", adminID, adminPass, String.valueOf(accessLvl));

    }
    */

        // list and JSON methods

        // accompanying methods

    // void addAgent()
    // void updateProfile()
    // void removeAgent()
    // void addProperty()
    // void updateProperty()
    // void deactivateProperty()
    // void removeProperty()

}