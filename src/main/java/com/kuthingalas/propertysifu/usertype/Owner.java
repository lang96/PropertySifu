package com.kuthingalas.propertysifu.usertype;

import com.kuthingalas.propertysifu.system.Property;

import java.util.ArrayList;



/* agent has elevated access (additional features) */
public class Owner extends User {

    // private members
    private String idNum;
    private ArrayList<Property> properties;

    // public members


    // constructors
    public Owner(String userID, String pass, String fName, String lName, String phone, String id) {

        //this.userID = userID; // make function setID() to determine owner account userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.properties = new ArrayList<>();
        this.userType = "Owner";

    }

    public Owner(String userID, String pass, String fName, String lName, String phone, String id, ArrayList<Property> propertyList) {

        //this.userID = userID; // make function setID() to determine owner account userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.properties = propertyList;
        this.userType = "Owner";

    }

    // methods

        // getters
    public String getIdNum() {
            return idNum;
    }
    public ArrayList<Property> getProperties() {
        return properties;
    }

    // setters
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /* might change out with proper method below
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
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

    // void addOwner()
    // void updateProfile()
    // void removeOwner()
    // void addProperty()
    // void updateProperty()
    // void deactivateProperty()
    // void removeProperty()

}