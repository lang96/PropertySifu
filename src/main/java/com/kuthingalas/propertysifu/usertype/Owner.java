package com.kuthingalas.propertysifu.usertype;

import com.kuthingalas.propertysifu.system.Property;

import java.util.ArrayList;



/**
 *     Programmer's Name : Arif
 *     Class's name    : Owner
 *     Purpose         : Owner object
 */

/* agent has elevated access (additional features) */
public class Owner extends User {

    // private members
    private String idNum;
    private ArrayList<Property> properties;

    // public members


    // constructors

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Owner
     *     Purpose         : Owner object constructor used for initialize purposes.
     */

    public Owner(String userID, String pass, String fName, String lName, String phone, String id, ArrayList<Property> propertyList, int verified) {

        this.userID = userID;
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.properties = propertyList;
        this.userType = "Owner";
        this.verified = verified;

    }

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Owner
     *     Purpose         : Owner object constructor used for initialize purposes.
     */

    public Owner(String userID, String pass, String fName, String lName, String phone, String id, int verified) {

        this.userID = userID;
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.properties = new ArrayList<>();
        this.userType = "Owner";
        this.verified = verified;

    }

    // new owners added from add() has empty properties list and has ID auto-generated

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Owner
     *     Purpose         : Owner object constructor used for creating new owners.
     */

    public Owner(String userID, String pass, String fName, String lName, String phone, String id) {

        this.userID = userID;
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.properties = new ArrayList<>();
        this.userType = "Owner";
        this.verified = 0;

    }

    public Owner() {}

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

    /**
     *     Programmer's Name : Arif
     *     Method's name    : setNewID
     *     Purpose         : Generates new owner ID for subsequently created owner.
     */

    public static String setNewID() { // ID structure designed for system with a maximum of 999 owners

        String newID = "";
        int totalOwners = 0;

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserType().equals("Owner")) {
                totalOwners++;
            }

        }

        if (totalOwners < 9) {
            newID = "OR00" + (totalOwners + 1);
        } else if (totalOwners < 99) {
            newID = "OR0" + (totalOwners + 1);
        } else {
            newID = "OR" + (totalOwners + 1);
        }

        return newID;

    }

        // accompanying methods

}