package com.kuthingalas.propertysifu.usertype;



/**
 *     Programmer's Name : Arif
 *     Class's name    : Tenant
 *     Purpose         : Tenant object
 */

public class Tenant extends User {

    // private members

    // public members

    // constructors

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Tenant
     *     Purpose         : Tenant object constructor for initialize purposes.
     */

    public Tenant(String username, String pass, String fName, String lName, String phone, int verified) {

        this.userID = username; // user can set their own custom username/userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.userType = "Tenant";
        this.verified = verified;

    }

    // new tenants added from add() is yet to be verified by admin

    /**
     *     Programmer's Name : Arif
     *     Method's name    : Tenant
     *     Purpose         : Tenant object constructor used for creating new owners.
     */

    public Tenant(String username, String pass, String fName, String lName, String phone) {

        this.userID = username; // user can set their own custom username/userID
        this.userPass = pass;
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phone;
        this.userType = "Tenant";
        this.verified = 0;

    }

    // methods

        // getters

        // setters

        // toString

        // list and JSON methods

        // accompanying methods

}