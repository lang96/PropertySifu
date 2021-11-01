package com.kuthingalas.propertysifu.usertype;

import java.util.ArrayList;


/**
 *     Programmer's Name : Arif
 *     Class's name    : Admin
 *     Purpose         : Admin object
 */



public class Admin {

    // private members
    private String adminID, adminPass;
    private int accessLvl;

    // public members
    public static ArrayList<Admin> AdminList = new ArrayList<>();

    // constructors

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Admin
     *     Purpose         : Admin object constructor for control admin.
     */

    public Admin(String id, String pass, boolean control) { // control admin - def id = melon, def pass = juicy123

        this.adminID = id;
        this.adminPass = pass;

        if (control) {
            this.accessLvl = 1;
        } else {
            this.accessLvl = 0;
        }

    }

    /**
     *     Programmer's Name : Arif
     *     Method's name    : initializeAdmin
     *     Purpose         : Admin object constructor for regular admin.
     */

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