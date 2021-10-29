package com.kuthingalas.propertysifu.usertype;

import java.util.ArrayList;



/* Admin has the highest access (all features)

- ensure there is only one control admin (holds control over all admins & cannot be removed)

feature list

- special menu
- reject or delete user registration
-

- login



*/

public class Admin {

    // private members
    private String adminID, adminPass;
    private int accessLvl;

    // public members
    public static ArrayList<Admin> AdminList = new ArrayList<>();

    // constructors
    public Admin(String id, String pass, boolean control) { // control admin - def id = melon, def pass = juicy123

        this.adminID = id;
        this.adminPass = pass;

        if (control) {
            this.accessLvl = 1;
        } else {
            this.accessLvl = 0;
        }

    }

    public Admin(String id, String pass) {

        this.adminID = id;
        this.adminPass = pass;
        this.accessLvl = 0;

    }

    // methods

        // getters
    public String getAdminID() {
        return this.adminID;
    }
    public String getAdminPass() {
        return this.adminPass;
    }
    public int getAdminAccessLvl() {
        return this.accessLvl;
    }

        // setters
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
    public void setAccessLvl(int accessLvl) {
        this.accessLvl = accessLvl;
    }

        // toString
    public String toString() {

        return String.format("%-15s  %-11s  %-6s \n", adminID, adminPass, String.valueOf(accessLvl));

    }

        // list and JSON methods

        // accompanying methods

}