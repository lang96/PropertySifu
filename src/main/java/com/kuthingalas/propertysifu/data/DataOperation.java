package com.kuthingalas.propertysifu.data;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.kuthingalas.propertysifu.system.Property.PropertyList;
import static com.kuthingalas.propertysifu.usertype.Admin.AdminList;
import static com.kuthingalas.propertysifu.usertype.User.UserList;



public class DataOperation {

    /* void initialize users from json
    - for admin, if there are no control admins - create one and initialize | else - initialize
    */

    public static void initializeAdmin() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject adminData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) adminData.get("ID");
            JSONArray passArr = (JSONArray) adminData.get("pass");
            JSONArray accessLvlArr = (JSONArray) adminData.get("accessLvl");

            if (IDArr.size() != 0) { // there is existing control admin

                for (int i = 0; i < IDArr.size(); i++) {

                    String ID = "" + IDArr.get(i);
                    String pass = "" + passArr.get(i);
                    int accessLvl = Integer.parseInt("" + accessLvlArr.get(i));

                    if (accessLvl == 1) {
                        AdminList.add(new Admin(ID, pass, true));
                    } else {
                        AdminList.add(new Admin(ID, pass));
                    }

                }

            } else { // no control admin found - create one with default login details

                String defID = "melon";
                String defPass = "juicy123";

                IDArr.add(defID);
                passArr.add(defPass);
                accessLvlArr.add(1);

                adminData.put("accessLvl", accessLvlArr);
                adminData.put("pass", passArr);
                adminData.put("ID", IDArr);

                AdminList.add(new Admin(defID, defPass, true));

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

                    fileWrite.write(adminData.toJSONString());
                    fileWrite.flush();

                } catch (IOException ef) {
                    ef.printStackTrace();
                }

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void initializeUser() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray passArr = (JSONArray) userData.get("pass");
            JSONArray typeArr = (JSONArray) userData.get("type");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");
            JSONArray orgArr = (JSONArray) userData.get("org");

            if (IDArr.size() != 0) { // only proceed with array if there are users registered - else start with empty user list

                for (int i = 0; i < IDArr.size(); i++) {

                    String ID = "" + IDArr.get(i);
                    String pass = "" + passArr.get(i);
                    String type = "" + typeArr.get(i);
                    String fName = "" + fNameArr.get(i);
                    String lName = "" + lNameArr.get(i);
                    String phone = "" + phoneArr.get(i);
                    String idNum = "" + idNumArr.get(i);
                    String org = "" + orgArr.get(i);
                    ArrayList<Property> properties = new ArrayList<>();

                    if (type.equals("Agent") || type.equals("Owner")) { // get properties registered under this agent/owner

                        if (!PropertyList.isEmpty()) { // only proceed after PropertyList is initialized and not empty

                            for (int j = 0; j < PropertyList.size(); j++) {

                                if (PropertyList.get(j).getRepresentativeID().equals(ID)) {

                                    properties.add(PropertyList.get(j));

                                }

                            }

                        }

                    } else {

                        switch(type) {

                            case "Tenant":
                                UserList.add(new Tenant(ID, pass, fName, lName, phone));
                                break;
                            case "Agent":
                                UserList.add(new Agent(ID, pass, fName, lName, phone, idNum, org, properties));
                                break;
                            case "Owner":
                                UserList.add(new Owner(ID, pass, fName, lName, phone, idNum, properties));
                                break;
                            default:
                                continue;

                        }

                    }

                }

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    // void initialize properties from json

        // PropertyList is initialized before UserList
    public static void initializeProperty() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");
            JSONArray typeArr = (JSONArray) propertyData.get("type");

            Object addressObj = propertyData.get("address");
            JSONObject addressArr = (JSONObject) addressObj;
            JSONArray firstAddressArr = (JSONArray) addressArr.get("firstAdd");
            JSONArray secondAddressArr = (JSONArray) addressArr.get("secondAdd");

            JSONArray statusArr = (JSONArray) propertyData.get("status"); // getting int from String
            JSONArray statDescArr = (JSONArray) propertyData.get("statDesc");

            // keep in mind this is an array of arrays - do add, remove and update accordingly
            JSONArray facArr = (JSONArray) propertyData.get("fac");

            JSONArray bedArr = (JSONArray) propertyData.get("bed"); // getting int from String
            JSONArray bathArr = (JSONArray) propertyData.get("bath"); // getting int from String
            JSONArray areaArr = (JSONArray) propertyData.get("area"); // getting int from String
            JSONArray furnishArr = (JSONArray) propertyData.get("furnish"); // getting int from String
            JSONArray psfArr = (JSONArray) propertyData.get("psf"); // getting int from String
            JSONArray rentArr = (JSONArray) propertyData.get("rent"); // getting int from String
            JSONArray repIDArr = (JSONArray) propertyData.get("repID");

            // keep in mind this is an array of arrays - do add, remove and update accordingly
            JSONArray commentIDArr = (JSONArray) propertyData.get("commentID");

            if (IDArr.size() != 0) { // only proceed with array if there are properties listed - else start with empty property list

                for (int i = 0; i < IDArr.size(); i++) {

                    String ID = "" + IDArr.get(i);
                    String type = "" + typeArr.get(i);
                    String firstAdd = "" + firstAddressArr.get(i);
                    String secondAdd = "" + secondAddressArr.get(i);
                    int status = Integer.parseInt("" + statusArr.get(i));
                    String statDesc = "" + statDescArr.get(i);

                    ArrayList<String> facList = new ArrayList<>();
                    JSONArray fac = (JSONArray) facArr.get(i);
                    for (int j = 0; j < fac.size(); j++) {
                        facList.add("" + fac.get(j));
                    }

                    int bed = Integer.parseInt("" + bedArr.get(i));
                    int bath = Integer.parseInt("" + bathArr.get(i));
                    int area = Integer.parseInt("" + areaArr.get(i));
                    int furnish = Integer.parseInt("" + furnishArr.get(i));
                    float psf = Float.parseFloat("" + psfArr.get(i));
                    float rent = Float.parseFloat("" + rentArr.get(i));
                    String repID = "" + repIDArr.get(i);

                    ArrayList<String> commentIDList = new ArrayList<>();
                    JSONArray commentID = (JSONArray) commentIDArr.get(i);
                    for (int k = 0; k < commentID.size(); k++) {
                        commentIDList.add("" + commentID.get(k));
                    }

                    PropertyList.add(new Property(ID, type, firstAdd, secondAdd, status, statDesc, facList, bed,
                            bath, area, furnish, psf, rent, repID, commentIDList));

                }

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    // void add users to json

    public static void addAdmin(String id, String pass, boolean control) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject adminData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) adminData.get("ID");
            JSONArray passArr = (JSONArray) adminData.get("pass");
            JSONArray accessLvlArr = (JSONArray) adminData.get("accessLvl");

            int accessLvl;

            if (control) {
                accessLvl = 1;
            } else {
                accessLvl = 0;
            }

            IDArr.add(id);
            passArr.add(pass);
            accessLvlArr.add(accessLvl);

            adminData.put("accessLvl", accessLvlArr);
            adminData.put("pass", passArr);
            adminData.put("ID", IDArr);

            AdminList.add(new Admin(id, pass, control));

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

                fileWrite.write(adminData.toJSONString());
                fileWrite.flush();

            } catch (IOException ef) {
                ef.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void addTenant(String id, String pass, String type, String fName, String lName, String phone) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray passArr = (JSONArray) userData.get("pass");
            JSONArray typeArr = (JSONArray) userData.get("type");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");
            JSONArray orgArr = (JSONArray) userData.get("org");

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add(type);
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneArr.add(phone);
            idNumArr.add("-");
            orgArr.add("-");

            userData.put("org", orgArr);
            userData.put("idNum", idNumArr);
            userData.put("phone", phoneArr);
            userData.put("lName", lNameArr);
            userData.put("fName", fNameArr);
            userData.put("type", typeArr);
            userData.put("pass", passArr);
            userData.put("userID", IDArr);

            UserList.add(new Tenant(id, pass, fName, lName, phone));

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

                fileWrite.write(userData.toJSONString());
                fileWrite.flush();

            } catch (IOException ef) {
                ef.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void addAgent(String id, String pass, String type, String fName, String lName, String phone, String idNum, String org) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray passArr = (JSONArray) userData.get("pass");
            JSONArray typeArr = (JSONArray) userData.get("type");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");
            JSONArray orgArr = (JSONArray) userData.get("org");

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add(type);
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneArr.add(phone);
            idNumArr.add(idNum);
            orgArr.add(org);

            userData.put("org", orgArr);
            userData.put("idNum", idNumArr);
            userData.put("phone", phoneArr);
            userData.put("lName", lNameArr);
            userData.put("fName", fNameArr);
            userData.put("type", typeArr);
            userData.put("pass", passArr);
            userData.put("userID", IDArr);

            UserList.add(new Agent(id, pass, fName, lName, phone, idNum, org));

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

                fileWrite.write(userData.toJSONString());
                fileWrite.flush();

            } catch (IOException ef) {
                ef.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void addOwner(String id, String pass, String type, String fName, String lName, String phone, String idNum) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray passArr = (JSONArray) userData.get("pass");
            JSONArray typeArr = (JSONArray) userData.get("type");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");
            JSONArray orgArr = (JSONArray) userData.get("org");

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add(type);
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneArr.add(phone);
            idNumArr.add(idNum);
            orgArr.add("-");

            userData.put("org", orgArr);
            userData.put("idNum", idNumArr);
            userData.put("phone", phoneArr);
            userData.put("lName", lNameArr);
            userData.put("fName", fNameArr);
            userData.put("type", typeArr);
            userData.put("pass", passArr);
            userData.put("userID", IDArr);

            UserList.add(new Owner(id, pass, fName, lName, phone, idNum));

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

                fileWrite.write(userData.toJSONString());
                fileWrite.flush();

            } catch (IOException ef) {
                ef.printStackTrace();
            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    // void add properties from json

    public static void addProperty() {

        // add property id by indexing idArr then parseInt to String of next int
        // add property to the propertyList of the listed agent/owner

    }

    // void delete users from json

    public static void removeAdmin(String id) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject adminData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) adminData.get("ID");
            JSONArray passArr = (JSONArray) adminData.get("pass");
            JSONArray accessLvlArr = (JSONArray) adminData.get("accessLvl");

            String idCompare = "";
            int jsonRemoveIndex = 0;
            int listRemoveIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonRemoveIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < AdminList.size(); j++) {

                    if (idCompare.equals(AdminList.get(j).getAdminID())) {
                        listRemoveIndex = j;
                        break;
                    }

                }

                AdminList.remove(listRemoveIndex);

                IDArr.remove(jsonRemoveIndex);
                passArr.remove(jsonRemoveIndex);
                accessLvlArr.remove(jsonRemoveIndex);

                adminData.put("accessLvl", accessLvlArr);
                adminData.put("pass", passArr);
                adminData.put("ID", IDArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

                    fileWrite.write(adminData.toJSONString());
                    fileWrite.flush();

                } catch (IOException ef) {
                    ef.printStackTrace();
                }

            } else { // cancel remove operation and notify user

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    public static void removeUser(String id) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("ID");
            JSONArray passArr = (JSONArray) userData.get("pass");
            JSONArray typeArr = (JSONArray) userData.get("type");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");
            JSONArray orgArr = (JSONArray) userData.get("org");

            String idCompare = "";
            int jsonRemoveIndex = 0;
            int listRemoveIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonRemoveIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < UserList.size(); j++) {

                    if (idCompare.equals(UserList.get(j).getUserID())) {
                        listRemoveIndex = j;
                        break;
                    }

                }

                UserList.remove(listRemoveIndex);

                IDArr.remove(jsonRemoveIndex);
                passArr.remove(jsonRemoveIndex);
                typeArr.remove(jsonRemoveIndex);
                fNameArr.remove(jsonRemoveIndex);
                lNameArr.remove(jsonRemoveIndex);
                phoneArr.remove(jsonRemoveIndex);
                idNumArr.remove(jsonRemoveIndex);
                orgArr.remove(jsonRemoveIndex);

                userData.put("org", orgArr);
                userData.put("idNum", idNumArr);
                userData.put("phone", phoneArr);
                userData.put("lName", lNameArr);
                userData.put("fName", fNameArr);
                userData.put("type", typeArr);
                userData.put("pass", passArr);
                userData.put("ID", IDArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

                    fileWrite.write(userData.toJSONString());
                    fileWrite.flush();

                } catch (IOException ef) {
                    ef.printStackTrace();
                }

            } else { // cancel remove operation and notify user

            }

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ParseException f) {
            f.printStackTrace();
        }

    }

    // void delete properties from json

    public static void removeProperty(String id) {



    }

    // void update user list after changes (update details / removal)

    public static void updateAdmin() {



    }

    public static void updateUser() {



    }

    // void update property list after changes (update details / removal)

    public static void updateProperty() {



    }

    public static void main(String[] args) {



    }

}
