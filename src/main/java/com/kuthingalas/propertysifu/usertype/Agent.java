package com.kuthingalas.propertysifu.usertype;

import com.kuthingalas.propertysifu.system.Property;

import java.util.ArrayList;



/**
 *     Programmer's Name : Arif
 *     Class's name    : Agent
 *     Purpose         : Agent object
 */

/* Agent has elevated access (additional features) */
public class Agent extends User {

    // private members
    private String idNum, organization;
    private ArrayList<Property> properties;

    // public members


    // constructors

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Agent
     *     Purpose         : Agent object constructor used for initialize purposes.
     */

    public Agent(String userID, String pass, String fName, String lName, String phone, String id, String org, ArrayList<Property> propertyList, int verified) {

        this.userID = userID;
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.organization = org;
        this.properties = propertyList;
        this.userType = "Agent";
        this.verified = verified;

    }

    // new agents added from add() has empty properties list and has ID auto-generated

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Agent
     *     Purpose         : Agent object constructor used for creating new agents.
     */

    public Agent(String userID, String pass, String fName, String lName, String phone, String id, String org) {

        this.userID = userID;
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.idNum = id;
        this.organization = org;
        this.properties = new ArrayList<>();
        this.userType = "Agent";
        this.verified = 0;

    }

    // methods

        // getters
    @Override
    public String getIdNum() {
        return idNum;
    }
    @Override
    public String getOrganization() {
        return organization;
    }
    @Override
    public ArrayList<Property> getProperties() {
        return properties;
    }

        // setters
    @Override
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
    @Override
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /* might change out with proper method below
    @Override
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

    /**
     *     Programmer's Name : Arif
     *     Method's name    : setNewAgentID
     *     Purpose         : Generates new agent ID for subsequently created agents.
     */

    public static String setNewAgentID() { // ID structure designed for system with a maximum of 999 agents

        String newID = "";
        int totalAgents = 0;

        for (int i = 0; i < UserList.size(); i++) {

            if (UserList.get(i).getUserType().equals("Agent")) {
                totalAgents++;
            }

        }

        if (totalAgents < 9) {
            newID = "AG00" + (totalAgents + 1);
        } else if (totalAgents < 99) {
            newID = "AG0" + (totalAgents + 1);
        } else {
            newID = "AG" + (totalAgents + 1);
        }

        return newID;

    }

        // accompanying methods

}