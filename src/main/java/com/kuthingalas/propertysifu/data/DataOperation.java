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

import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.system.Property.Comment.*;
import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.usertype.Agent.*;
import static com.kuthingalas.propertysifu.usertype.Owner.*;

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
            JSONArray verifiedArr = (JSONArray) userData.get("verified");

            if (IDArr.size() != 0) { // only proceed with array if there are users registered - else start with empty user list

                for (int i = 0; i < IDArr.size(); i++) {

                    String ID = "" + IDArr.get(i);
                    String pass = "" + passArr.get(i);
                    String type = "" + typeArr.get(i);
                    String fName = "" + fNameArr.get(i);
                    String lName = "" + lNameArr.get(i);
                    String phone = "" + phoneArr.get(i);
                    int verified = Integer.parseInt("" + verifiedArr.get(i));

                    // only for agent and owner
                    String idNum = "" + idNumArr.get(i);
                    String org = "" + orgArr.get(i);
                    ArrayList<Property> properties = new ArrayList<>();

                    switch (type) {

                        case "Tenant":
                            UserList.add(new Tenant(ID, pass, fName, lName, phone, verified));
                            break;

                        case "Agent":

                            if (!PropertyList.isEmpty()) { // only proceed after PropertyList is initialized and not empty

                                for (int j = 0; j < PropertyList.size(); j++) {
                                    if (PropertyList.get(j).getRepresentativeID().equals(ID)) {
                                        properties.add(PropertyList.get(j));
                                    }
                                }

                                if (!properties.isEmpty()) {
                                    UserList.add(new Agent(ID, pass, fName, lName, phone, idNum, org, properties, verified));
                                    break;
                                } else {
                                    UserList.add(new Agent(ID, pass, fName, lName, phone, idNum, org));
                                    break;
                                }

                            } else {
                                UserList.add(new Agent(ID, pass, fName, lName, phone, idNum, org));
                                break;
                            }

                        case "Owner":

                            if (!PropertyList.isEmpty()) { // only proceed after PropertyList is initialized and not empty

                                for (int j = 0; j < PropertyList.size(); j++) {
                                    if (PropertyList.get(j).getRepresentativeID().equals(ID)) {
                                        properties.add(PropertyList.get(j));
                                    }
                                }

                                if (!properties.isEmpty()) {
                                    UserList.add(new Owner(ID, pass, fName, lName, phone, idNum, properties, verified));
                                    break;
                                } else {
                                    UserList.add(new Owner(ID, pass, fName, lName, phone, idNum));
                                    break;
                                }

                            } else {
                                UserList.add(new Owner(ID, pass, fName, lName, phone, idNum));
                                break;
                            }

                        default:
                            continue;

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
            JSONArray psfArr = (JSONArray) propertyData.get("psf"); // getting float from String
            JSONArray rentArr = (JSONArray) propertyData.get("rent"); // getting float from String
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

    // void initialize comments from json

    public static void initializeComments() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject commentData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) commentData.get("commentID");
            JSONArray userIDArr = (JSONArray) commentData.get("userID");
            JSONArray descArr = (JSONArray) commentData.get("commentDesc");

            if (IDArr.size() != 0) { // only proceed with array if there are comments listed - else start with empty comment list

                for (int i = 0; i < IDArr.size(); i++) {

                    String ID = "" + IDArr.get(i);
                    String userID = "" + userIDArr.get(i);
                    String desc = "" + descArr.get(i);

                    CommentList.add(new Comment(ID, userID, desc));

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

    public static void addTenant(String id, String pass, String fName, String lName, String phone) {

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
            JSONArray verifiedArr = (JSONArray) userData.get("verified");

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add("Tenant");
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneArr.add(phone);
            idNumArr.add("-");
            orgArr.add("-");
            verifiedArr.add("0");

            userData.put("org", orgArr);
            userData.put("idNum", idNumArr);
            userData.put("phone", phoneArr);
            userData.put("lName", lNameArr);
            userData.put("fName", fNameArr);
            userData.put("type", typeArr);
            userData.put("pass", passArr);
            userData.put("userID", IDArr);
            userData.put("verified", verifiedArr);

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

    public static void addAgent(String pass, String fName, String lName, String phone, String idNum, String org) {

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
            JSONArray verifiedArr = (JSONArray) userData.get("verified");

            // generating agent ID based on existing number of agent
            String id = setNewAgentID();

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add("Agent");
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneArr.add(phone);
            idNumArr.add(idNum);
            orgArr.add(org);
            verifiedArr.add("0");

            userData.put("org", orgArr);
            userData.put("idNum", idNumArr);
            userData.put("phone", phoneArr);
            userData.put("lName", lNameArr);
            userData.put("fName", fNameArr);
            userData.put("type", typeArr);
            userData.put("pass", passArr);
            userData.put("userID", IDArr);
            userData.put("verified", verifiedArr);

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

    public static void addOwner(String pass, String fName, String lName, String phone, String idNum) {

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
            JSONArray verifiedArr = (JSONArray) userData.get("verified");

            // generating owner ID based on existing number of owners
            String id = setNewOwnerID();

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add("Owner");
            fNameArr.add(fName);
            lNameArr.add(lName);
            phoneArr.add(phone);
            idNumArr.add(idNum);
            orgArr.add("-");
            verifiedArr.add("0");

            userData.put("org", orgArr);
            userData.put("idNum", idNumArr);
            userData.put("phone", phoneArr);
            userData.put("lName", lNameArr);
            userData.put("fName", fNameArr);
            userData.put("type", typeArr);
            userData.put("pass", passArr);
            userData.put("userID", IDArr);
            userData.put("verified", verifiedArr);

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

    // void add properties to json

    public static void addProperty(String type, String firstAdd, String secondAdd,
                                   ArrayList<String> facList, int bed, int bath, int area, int furnish,
                                   float psf, float rent, String rep) {

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
            JSONArray psfArr = (JSONArray) propertyData.get("psf"); // getting float from String
            JSONArray rentArr = (JSONArray) propertyData.get("rent"); // getting float from String
            JSONArray repIDArr = (JSONArray) propertyData.get("repID");

            // keep in mind this is an array of arrays - do add, remove and update accordingly
            JSONArray commentIDArr = (JSONArray) propertyData.get("commentID");

            // generating property ID based on existing number of properties
            String id = setNewPropertyID();

            IDArr.add(id);
            typeArr.add(type);
            firstAddressArr.add(firstAdd);
            secondAddressArr.add(secondAdd);
            statusArr.add("1");
            statDescArr.add("Available");
            facArr.add(facList);
            bedArr.add(bed);
            bathArr.add(bath);
            areaArr.add(area);
            furnishArr.add(furnish);
            psfArr.add(psf);
            rentArr.add(rent);
            repIDArr.add(rep);
            commentIDArr.add(new JSONArray());

            propertyData.put("ID", IDArr);
            propertyData.put("type", typeArr);

            addressArr.put("firstAdd", firstAddressArr);
            addressArr.put("secondAdd", secondAddressArr);
            propertyData.put("address", addressArr);

            propertyData.put("status", statusArr);
            propertyData.put("statDesc", statDescArr);
            propertyData.put("fac", facArr);
            propertyData.put("bed", bedArr);
            propertyData.put("bath", bathArr);
            propertyData.put("area", areaArr);
            propertyData.put("furnish", furnishArr);
            propertyData.put("psf", psfArr);
            propertyData.put("rent", rentArr);
            propertyData.put("repID", repIDArr);
            propertyData.put("commentID", commentIDArr);

            PropertyList.add(new Property(id, type, firstAdd, secondAdd, facList, bed, bath, area, furnish, psf, rent, rep));

            // once a new property listing is created, it must be added to the property list of its respective representative

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                fileWrite.write(propertyData.toJSONString());
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

    public static void addComment(String user, String desc) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject commentData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) commentData.get("commentID");
            JSONArray userIDArr = (JSONArray) commentData.get("userID");
            JSONArray descArr = (JSONArray) commentData.get("commentDesc");

            // generating comment ID based on existing number of comments
            String id = setNewCommentID();

            IDArr.add(id);
            userIDArr.add(user);
            descArr.add(desc);

            commentData.put("commentID", IDArr);
            commentData.put("userID", userIDArr);
            commentData.put("commentDesc", descArr);

            CommentList.add(new Comment(id, user, desc));

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

                fileWrite.write(commentData.toJSONString());
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

    // void add login data to json

    public static void addLogin(String id, String pass, String type) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject loginData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) loginData.get("userID");
            JSONArray passArr = (JSONArray) loginData.get("pass");
            JSONArray typeArr = (JSONArray) loginData.get("type");

            IDArr.add(id);
            passArr.add(pass);
            typeArr.add(type);

            loginData.put("userID", IDArr);
            loginData.put("pass", passArr);
            loginData.put("type", typeArr);

            try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                    "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

                fileWrite.write(loginData.toJSONString());
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

    // void delete property from json

    public static void removeProperty(String id) {

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
            JSONArray psfArr = (JSONArray) propertyData.get("psf"); // getting float from String
            JSONArray rentArr = (JSONArray) propertyData.get("rent"); // getting float from String
            JSONArray repIDArr = (JSONArray) propertyData.get("repID");

            // keep in mind this is an array of arrays - do add, remove and update accordingly
            JSONArray commentIDArr = (JSONArray) propertyData.get("commentID");

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

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listRemoveIndex = j;
                        break;
                    }

                }

                PropertyList.remove(listRemoveIndex);

                IDArr.remove(jsonRemoveIndex);
                typeArr.remove(jsonRemoveIndex);
                firstAddressArr.remove(jsonRemoveIndex);
                secondAddressArr.remove(jsonRemoveIndex);
                statusArr.remove(jsonRemoveIndex);
                statDescArr.remove(jsonRemoveIndex);
                facArr.remove(jsonRemoveIndex);
                bedArr.remove(jsonRemoveIndex);
                bathArr.remove(jsonRemoveIndex);
                areaArr.remove(jsonRemoveIndex);
                furnishArr.remove(jsonRemoveIndex);
                psfArr.remove(jsonRemoveIndex);
                rentArr.remove(jsonRemoveIndex);
                repIDArr.remove(jsonRemoveIndex);
                commentIDArr.remove(jsonRemoveIndex);

                propertyData.put("ID", IDArr);
                propertyData.put("type", typeArr);

                addressArr.put("firstAdd", firstAddressArr);
                addressArr.put("secondAdd", secondAddressArr);
                propertyData.put("address", addressArr);

                propertyData.put("status", statusArr);
                propertyData.put("statDesc", statDescArr);
                propertyData.put("fac", facArr);
                propertyData.put("bed", bedArr);
                propertyData.put("bath", bathArr);
                propertyData.put("area", areaArr);
                propertyData.put("furnish", furnishArr);
                propertyData.put("psf", psfArr);
                propertyData.put("rent", rentArr);
                propertyData.put("repID", repIDArr);
                propertyData.put("commentID", commentIDArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    // void remove comment from json

    public static void removeComment(String id) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject commentData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) commentData.get("commentID");
            JSONArray userIDArr = (JSONArray) commentData.get("userID");
            JSONArray descArr = (JSONArray) commentData.get("commentDesc");

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

                for (int j = 0; j < CommentList.size(); j++) {

                    if (idCompare.equals(CommentList.get(j).getCommentID())) {
                        listRemoveIndex = j;
                        break;
                    }

                }

                CommentList.remove(listRemoveIndex);

                IDArr.remove(jsonRemoveIndex);
                userIDArr.remove(jsonRemoveIndex);
                descArr.remove(jsonRemoveIndex);

                commentData.put("commentID", IDArr);
                commentData.put("userID", userIDArr);
                commentData.put("commentDesc", descArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

                    fileWrite.write(commentData.toJSONString());
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

    // void remove from login data (when revoked by admin)

    public static void removeLogin(String id) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject loginData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) loginData.get("userID");
            JSONArray passArr = (JSONArray) loginData.get("pass");
            JSONArray typeArr = (JSONArray) loginData.get("type");

            String idCompare = "";
            int jsonRemoveIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonRemoveIndex = i;
                        break;
                    }

                }

                IDArr.remove(jsonRemoveIndex);
                passArr.remove(jsonRemoveIndex);
                typeArr.remove(jsonRemoveIndex);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

                    fileWrite.write(loginData.toJSONString());
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

    // void update user list after changes

    public static void updateAdmin(String oldID, String newID, int access) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject adminData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) adminData.get("ID");
            JSONArray accessLvlArr = (JSONArray) adminData.get("accessLvl");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(oldID)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < AdminList.size(); j++) {

                    if (idCompare.equals(AdminList.get(j).getAdminID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                AdminList.get(listUpdateIndex).setAdminID(newID);
                AdminList.get(listUpdateIndex).setAccessLvl(access);

                IDArr.set(jsonUpdateIndex, newID);
                accessLvlArr.set(jsonUpdateIndex, String.valueOf(access));

                adminData.put("ID", IDArr);
                adminData.put("accessLvl", accessLvlArr);

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

    public static void updateAdminPass(String id, String pass) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "adminData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject adminData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) adminData.get("ID");
            JSONArray passArr = (JSONArray) adminData.get("pass");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < AdminList.size(); j++) {

                    if (idCompare.equals(AdminList.get(j).getAdminID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                AdminList.get(listUpdateIndex).setAdminPass(pass);

                passArr.set(jsonUpdateIndex, pass);

                adminData.put("pass", passArr);

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

    public static void updateTenant(String oldID, String newID, String fName, String lName, String phone) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(oldID)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < UserList.size(); j++) {

                    if (idCompare.equals(UserList.get(j).getUserID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                UserList.get(listUpdateIndex).setUserID(newID);
                UserList.get(listUpdateIndex).setFName(fName);
                UserList.get(listUpdateIndex).setLName(lName);
                UserList.get(listUpdateIndex).setPhoneNum(phone);

                IDArr.set(jsonUpdateIndex, newID);
                fNameArr.set(jsonUpdateIndex, fName);
                lNameArr.set(jsonUpdateIndex, lName);
                phoneArr.set(jsonUpdateIndex, phone);

                userData.put("userID", IDArr);
                userData.put("fName", fNameArr);
                userData.put("lName", lNameArr);
                userData.put("phone", phoneArr);

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

    public static void updateAgent(String id, String fName, String lName, String phone, String idNum, String org) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");
            JSONArray orgArr = (JSONArray) userData.get("org");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < UserList.size(); j++) {

                    if (idCompare.equals(UserList.get(j).getUserID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                UserList.get(listUpdateIndex).setFName(fName);
                UserList.get(listUpdateIndex).setLName(lName);
                UserList.get(listUpdateIndex).setPhoneNum(phone);
                UserList.get(listUpdateIndex).setIdNum(idNum);
                UserList.get(listUpdateIndex).setOrganization(org);

                fNameArr.set(jsonUpdateIndex, fName);
                lNameArr.set(jsonUpdateIndex, lName);
                phoneArr.set(jsonUpdateIndex, phone);
                idNumArr.set(jsonUpdateIndex, idNum);
                orgArr.set(jsonUpdateIndex, org);

                userData.put("fName", fNameArr);
                userData.put("lName", lNameArr);
                userData.put("phone", phoneArr);
                userData.put("idNum", idNumArr);
                userData.put("org", orgArr);

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

    public static void updateOwner(String id, String fName, String lName, String phone, String idNum) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray fNameArr = (JSONArray) userData.get("fName");
            JSONArray lNameArr = (JSONArray) userData.get("lName");
            JSONArray phoneArr = (JSONArray) userData.get("phone");
            JSONArray idNumArr = (JSONArray) userData.get("idNum");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < UserList.size(); j++) {

                    if (idCompare.equals(UserList.get(j).getUserID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                UserList.get(listUpdateIndex).setFName(fName);
                UserList.get(listUpdateIndex).setLName(lName);
                UserList.get(listUpdateIndex).setPhoneNum(phone);
                UserList.get(listUpdateIndex).setIdNum(idNum);

                fNameArr.set(jsonUpdateIndex, fName);
                lNameArr.set(jsonUpdateIndex, lName);
                phoneArr.set(jsonUpdateIndex, phone);
                idNumArr.set(jsonUpdateIndex, idNum);

                userData.put("fName", fNameArr);
                userData.put("lName", lNameArr);
                userData.put("phone", phoneArr);
                userData.put("idNum", idNumArr);

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

    public static void updateUserPass(String id, String pass) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray passArr = (JSONArray) userData.get("pass");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < UserList.size(); j++) {

                    if (idCompare.equals(UserList.get(j).getUserID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                UserList.get(listUpdateIndex).setUserPass(pass);

                passArr.set(jsonUpdateIndex, pass);

                userData.put("pass", passArr);

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

    public static void updateUserVerified(String id) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "userData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject userData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) userData.get("userID");
            JSONArray verifiedArr = (JSONArray) userData.get("verified");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < UserList.size(); j++) {

                    if (idCompare.equals(UserList.get(j).getUserID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                UserList.get(listUpdateIndex).setVerified(1);

                verifiedArr.set(jsonUpdateIndex, "1");

                userData.put("verified", verifiedArr);

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

    // void update property list after changes

    public static void updatePropertyType(String id, String type) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");
            JSONArray typeArr = (JSONArray) propertyData.get("type");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                PropertyList.get(listUpdateIndex).setPropertyType(type);

                typeArr.set(jsonUpdateIndex, type);

                propertyData.put("type", typeArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    public static void updatePropertyAddress(String id, String firstAdd, String secondAdd) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");

            Object addressObj = propertyData.get("address");
            JSONObject addressArr = (JSONObject) addressObj;
            JSONArray firstAddressArr = (JSONArray) addressArr.get("firstAdd");
            JSONArray secondAddressArr = (JSONArray) addressArr.get("secondAdd");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                PropertyList.get(listUpdateIndex).setFirstAddress(firstAdd);
                PropertyList.get(listUpdateIndex).setSecondAddress(secondAdd);

                firstAddressArr.set(jsonUpdateIndex, firstAdd);
                secondAddressArr.set(jsonUpdateIndex, secondAdd);

                addressArr.put("firstAdd", firstAddressArr);
                addressArr.put("secondAdd", secondAddressArr);
                propertyData.put("address", addressArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    public static void updatePropertyStatus(String id, int stat, String statDesc) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");

            JSONArray statusArr = (JSONArray) propertyData.get("status"); // convert int to String
            JSONArray statDescArr = (JSONArray) propertyData.get("statDesc");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                PropertyList.get(listUpdateIndex).setStatus(stat);
                PropertyList.get(listUpdateIndex).setStatusDesc(statDesc);

                statusArr.set(jsonUpdateIndex, String.valueOf(stat));
                statDescArr.set(jsonUpdateIndex, statDesc);

                propertyData.put("status", statusArr);
                propertyData.put("statDesc", statDescArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    public static void updatePropertyFacilities(String id, ArrayList<String> facList) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");

            // keep in mind this is an array of arrays - do add, remove and update accordingly
            JSONArray facArr = (JSONArray) propertyData.get("fac");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                PropertyList.get(listUpdateIndex).setFacilityList(facList);

                facArr.set(jsonUpdateIndex, facList);

                propertyData.put("fac", facArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    public static void updatePropertyDetails(String id, int bed, int bath, int area, int furnish, float psf, float rent) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");

            JSONArray bedArr = (JSONArray) propertyData.get("bed"); // convert int to String
            JSONArray bathArr = (JSONArray) propertyData.get("bath"); // convert int to String
            JSONArray areaArr = (JSONArray) propertyData.get("area"); // convert int to String
            JSONArray furnishArr = (JSONArray) propertyData.get("furnish"); // convert int to String
            JSONArray psfArr = (JSONArray) propertyData.get("psf"); // convert float to String
            JSONArray rentArr = (JSONArray) propertyData.get("rent"); // convert float to String

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                PropertyList.get(listUpdateIndex).setBedroom(bed);
                PropertyList.get(listUpdateIndex).setBathroom(bath);
                PropertyList.get(listUpdateIndex).setArea(area);
                PropertyList.get(listUpdateIndex).setFurnishing(furnish);
                PropertyList.get(listUpdateIndex).setPsfRate(psf);
                PropertyList.get(listUpdateIndex).setRentalRate(rent);

                bedArr.set(jsonUpdateIndex, String.valueOf(bed));
                bathArr.set(jsonUpdateIndex, String.valueOf(bath));
                areaArr.set(jsonUpdateIndex, String.valueOf(area));
                furnishArr.set(jsonUpdateIndex, String.valueOf(furnish));
                psfArr.set(jsonUpdateIndex, String.valueOf(psf));
                rentArr.set(jsonUpdateIndex, String.valueOf(rent));

                propertyData.put("bed", bedArr);
                propertyData.put("bath", bathArr);
                propertyData.put("area", areaArr);
                propertyData.put("furnish", furnishArr);
                propertyData.put("psf", psfArr);
                propertyData.put("rent", rentArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    public static void updatePropertyComments(String id, ArrayList<String> commentList) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject propertyData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) propertyData.get("ID");

            // keep in mind this is an array of arrays - do add, remove and update accordingly
            JSONArray commentIDArr = (JSONArray) propertyData.get("commentID");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < PropertyList.size(); j++) {

                    if (idCompare.equals(PropertyList.get(j).getPropertyID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                PropertyList.get(listUpdateIndex).setCommentID(commentList);

                commentIDArr.set(jsonUpdateIndex, commentList);

                propertyData.put("commentID", commentIDArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "propertyData.json")) {

                    fileWrite.write(propertyData.toJSONString());
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

    // void update comment

    public static void updateComment(String id, String desc) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject commentData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) commentData.get("commentID");
            JSONArray descArr = (JSONArray) commentData.get("commentDesc");

            String idCompare = "";
            int jsonUpdateIndex = 0;
            int listUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(id)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                for (int j = 0; j < CommentList.size(); j++) {

                    if (idCompare.equals(CommentList.get(j).getCommentID())) {
                        listUpdateIndex = j;
                        break;
                    }

                }

                CommentList.get(listUpdateIndex).setCommentDesc(desc);

                descArr.set(jsonUpdateIndex, desc);

                commentData.put("commentDesc", descArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "commentData.json")) {

                    fileWrite.write(commentData.toJSONString());
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

    // void update login

    public static void updateLogin(String oldID, String newID, String pass) {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

            Object obj = jsonParser.parse(reader);
            JSONObject loginData = (JSONObject) obj;

            JSONArray IDArr = (JSONArray) loginData.get("userID");
            JSONArray passArr = (JSONArray) loginData.get("pass");

            String idCompare = "";
            int jsonUpdateIndex = 0;

            if (IDArr.size() != 0) { // only proceed if list is not empty

                for (int i = 0; i < IDArr.size(); i++) {

                    idCompare = "" + IDArr.get(i);
                    if (idCompare.equals(oldID)) {
                        jsonUpdateIndex = i;
                        break;
                    }

                }

                IDArr.set(jsonUpdateIndex, newID);
                passArr.set(jsonUpdateIndex, pass);

                loginData.put("userID", IDArr);
                loginData.put("pass", passArr);

                try (FileWriter fileWrite = new FileWriter(System.getProperty("user.dir") +
                        "\\src\\main\\java\\com\\kuthingalas\\propertysifu\\data\\" + "loginData.json")) {

                    fileWrite.write(loginData.toJSONString());
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

    public static void main(String[] args) {

    }

}