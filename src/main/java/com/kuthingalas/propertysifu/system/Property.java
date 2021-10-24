package com.kuthingalas.propertysifu.system;

import java.util.ArrayList;

public class Property {

    // private members

        // firstAdd - location | secondAdd - project location (projectType)
    private String propertyID, propertyType, firstAddress, secondAddress, statusDesc, projectType, agentID;

        // status - 0 = inactive, 1 = active | visibility - 0 = hidden, 1 = visible
        // bedroom - 0 = studio | furnishing - 0 = unfurnished, 1 = partial, 2 = full
    private int status, bedroom, bathroom, area, furnishing, visibility;

    private float psfRate, rentalRate;

    private ArrayList<String> facilityList, commentID;

    // public members
    public static ArrayList<Property> PropertyList = new ArrayList<>();

    // constructors
    public Property(String id, String type, String firstAdd, String secondAdd, int stat, String statDesc,
                    String projType, ArrayList<String> facList, int bed, int bath, int area, int furnish,
                    float psf, float rent, String agent, int vis, ArrayList<String> comment)
    {

        this.propertyID = id;
        this.propertyType = type;
        this.firstAddress = firstAdd;
        this.secondAddress = secondAdd;
        this.status = stat;
        this.statusDesc = statDesc;
        this.projectType = projType;
        this.facilityList = facList;
        this.bedroom = bed;
        this.bathroom = bath;
        this.area = area;
        this.furnishing = furnish;
        this.psfRate = psf;
        this.rentalRate = rent;
        this.agentID = agent;
        this.visibility = vis;
        this.commentID = comment;

    }

    // new properties added from add() is visible by default
    public Property(String id, String type, String firstAdd, String secondAdd, int stat, String statDesc,
                    String projType, ArrayList<String> facList, int bed, int bath, int area, int furnish,
                    float psf, float rent, String agent, ArrayList<String> comment)
    {

        this.propertyID = id;
        this.propertyType = type;
        this.firstAddress = firstAdd;
        this.secondAddress = secondAdd;
        this.status = stat;
        this.statusDesc = statDesc;
        this.projectType = projType;
        this.facilityList = facList;
        this.bedroom = bed;
        this.bathroom = bath;
        this.area = area;
        this.furnishing = furnish;
        this.psfRate = psf;
        this.rentalRate = rent;
        this.agentID = agent;
        this.visibility = 1;
        this.commentID = comment;

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
    public String getProjectType() {
        return projectType;
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
    public String getAgentID() {
        return agentID;
    }
    public int getVisibility() {
        return visibility;
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
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    // might need to fine tune to modify individual facilities
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
    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    // might need to fine tune to modify individual comments
    public void setCommentID(ArrayList<String> commentID) {
        this.commentID = commentID;
    }

        // toString
    public String toString() {

        return String.format("| %-5s | %-10s | %-30s |\n", propertyID, propertyType, firstAddress);

    }

    public String toStringExtend() {

        return String.format("| %-30s | %-2s | %-2s | %-5s | %-5s |\n",
                secondAddress, bedroom, bathroom, area, rentalRate);

    }

        // list and JSON methods

        // accompanying methods

}
