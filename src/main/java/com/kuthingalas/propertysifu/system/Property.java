package com.kuthingalas.propertysifu.system;

import java.util.ArrayList;



public class Property {

    // private members

        // firstAdd - location | secondAdd - project location (projectType)
    private String propertyID, propertyType, firstAddress, secondAddress, statusDesc, representativeID;

        // status - 0 = inactive, 1 = active
        // bedroom - 0 = studio | furnishing - 0 = unfurnished, 1 = partial, 2 = full
    private int status, bedroom, bathroom, area, furnishing;

    private float psfRate, rentalRate;
    private ArrayList<String> facilityList, commentID;

    // public members
    public static ArrayList<Property> PropertyList = new ArrayList<>();

    public static class Comment {

        // private members
        private String commentID, userID, commentDesc;

        // public members
        public static ArrayList<Comment> CommentList = new ArrayList<>();

        // constructor
        public Comment(String id, String user, String desc) {

            this.commentID = id;
            this.userID = user;
            this.commentDesc = desc;

        }

        // methods

        // getters
        public String getCommentID() {
            return commentID;
        }
        public String getUserID() {
            return userID;
        }
        public String getCommentDesc() {
            return commentDesc;
        }

        // setters
        public void setCommentID(String commentID) {
            this.commentID = commentID;
        }
        public void setUserID(String userID) {
            this.userID = userID;
        }
        public void setCommentDesc(String commentDesc) {
            this.commentDesc = commentDesc;
        }

        // toString

        // list and JSON methods

        public static String setNewCommentID() { // ID structure designed for a maximum of 999 comments throughout system

            String newID = "";

            if (CommentList.size() < 9) {
                newID = "CM00" + (CommentList.size() + 1);
            } else if (CommentList.size() < 99) {
                newID = "CM0" + (CommentList.size() + 1);
            } else {
                newID = "CM" + (CommentList.size() + 1);
            }

            return newID;

        }

        // accompanying methods

    }

    // constructors
    public Property(String id, String type, String firstAdd, String secondAdd, int stat, String statDesc,
                    ArrayList<String> facList, int bed, int bath, int area, int furnish,
                    float psf, float rent, String rep, ArrayList<String> comment)
    {

        this.propertyID = id;
        this.propertyType = type;
        this.firstAddress = firstAdd;
        this.secondAddress = secondAdd;
        this.status = stat;
        this.statusDesc = statDesc;
        this.facilityList = facList;
        this.bedroom = bed;
        this.bathroom = bath;
        this.area = area;
        this.furnishing = furnish;
        this.psfRate = psf;
        this.rentalRate = rent;
        this.representativeID = rep;
        this.commentID = comment;

    }

    // new properties added from add() is visible by default, has empty comments list and has ID auto-generated
    public Property(String id, String type, String firstAdd, String secondAdd,
                    ArrayList<String> facList, int bed, int bath, int area,
                    int furnish, float psf, float rent, String rep)
    {

        this.propertyID = id;
        this.propertyType = type;
        this.firstAddress = firstAdd;
        this.secondAddress = secondAdd;
        this.status = 1;
        this.statusDesc = "Available";
        this.facilityList = facList;
        this.bedroom = bed;
        this.bathroom = bath;
        this.area = area;
        this.furnishing = furnish;
        this.psfRate = psf;
        this.rentalRate = rent;
        this.representativeID = rep;
        this.commentID = new ArrayList<>();

    }

    // methods

        // getters
    public String getPropertyID() {
        return propertyID;
    }
    public String getPropertyType() {
        return propertyType;
    }
    public String getFirstAddress() {
        return firstAddress;
    }
    public String getSecondAddress() {
        return secondAddress;
    }
    public int getStatus() {
        return status;
    }
    public String getStatusDesc() {
        return statusDesc;
    }
    public ArrayList<String> getFacilityList() {
        return facilityList;
    }
    public int getBedroom() {
        return bedroom;
    }
    public int getBathroom() {
        return bathroom;
    }
    public int getArea() {
        return area;
    }
    public int getFurnishing() {
        return furnishing;
    }
    public float getPsfRate() {
        return psfRate;
    }
    public float getRentalRate() {
        return rentalRate;
    }
    public String getRepresentativeID() {
        return representativeID;
    }
    public ArrayList<String> getCommentID() {
        return commentID;
    }

        // setters
    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public void setFirstAddress(String firstAddress) {
        this.firstAddress = firstAddress;
    }
    public void setSecondAddress(String secondAddress) {
        this.secondAddress = secondAddress;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
    public void setFacilityList(ArrayList<String> facilityList) {
        this.facilityList = facilityList;
    }
    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }
    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }
    public void setArea(int area) {
        this.area = area;
    }
    public void setFurnishing(int furnishing) {
        this.furnishing = furnishing;
    }
    public void setPsfRate(float psfRate) {
        this.psfRate = psfRate;
    }
    public void setRentalRate(float rentalRate) {
        this.rentalRate = rentalRate;
    }
    public void setRepresentativeID(String representativeID) {
        this.representativeID = representativeID;
    }
    public void setCommentID(ArrayList<String> commentID) {
        this.commentID = commentID;
    }

        // toString
    public String toString() {

        return String.format("| %-5s | %-10s | %-30s |\n", propertyID, propertyType, firstAddress);

    }

    public String toStringExtend() { // temp

        return String.format("| %-30s | %-2s | %-2s | %-5s | %-5s |\n",
                secondAddress, bedroom, bathroom, area, rentalRate);

    }

        // list and JSON methods

    public static String setNewPropertyID() { // ID structure designed for system with a maximum of 999 properties

        String newID = "";

        if (PropertyList.size() < 9) {
            newID = "PR00" + (PropertyList.size() + 1);
        } else if (PropertyList.size() < 99) {
            newID = "PR0" + (PropertyList.size() + 1);
        } else {
            newID = "PR" + (PropertyList.size() + 1);
        }

        return newID;

    }

    public ArrayList<String> addToFacilityList(ArrayList<String> facList, String fac) {
        facList.add(fac);
        return facList;
    }

    public ArrayList<String> removeFromFacilityList(ArrayList<String> facList, String fac) {
        facList.remove(fac);
        return facList;
    }

    public ArrayList<String> addToCommentList(ArrayList<String> commentList, String commentID) {
        commentList.add(commentID);
        return commentList;
    }

    public ArrayList<String> removeFromCommentList(ArrayList<String> commentList, String commentID) {
        commentList.remove(commentID);
        return commentList;
    }

        // accompanying methods

}