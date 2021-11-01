package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.*;

import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.system.Property.Comment.*;
import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.usertype.Agent.*;
import static com.kuthingalas.propertysifu.usertype.Owner.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.MainApplication.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;



public class HomepageController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment","Bungalow","Condominium","Semi-Detached","Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> facilitiesList = FXCollections.observableArrayList("Air-conditioning","Gym","Swimming Pool","Car park","Playground","Security");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially","Furnished");
    ObservableList<String> sortPSF = FXCollections.observableArrayList("Low to High","High to Low");
    ObservableList<String> sortPrice = FXCollections.observableArrayList("Low to High","High to Low");

    @FXML
    private Button adminPageBtn, refreshBtn, filterBtn;

    @FXML
    private Hyperlink toLogBtn;

    @FXML
    public ComboBox<String> propType;
    @FXML
    private ComboBox<String> bedroom;
    @FXML
    private ComboBox<String> bathroom;
    @FXML
    private ComboBox<String> furnishList;

    @FXML private TableView<Listing> tbl;

    @FXML
    private TableColumn<Listing, String> col_id;
    @FXML
    private TableColumn<Listing, String> col_name;
    @FXML
    private TableColumn<Listing, String> col_address;
    @FXML
    private TableColumn<Listing, String> col_bed;
    @FXML
    private TableColumn<Listing, String> col_bath;
    @FXML
    private TableColumn<Listing, String> col_area;
    @FXML
    private TableColumn<Listing, String> col_furnish;

    @FXML
    private TableColumn<Listing, String> col_rent;

    ObservableList list =  FXCollections.observableArrayList();
    ArrayList<Listing> filterList = new ArrayList<>();

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toLogin
     *     Purpose         : to go to loginpage
     */

    @FXML
    public void toLogin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Stage window = (Stage) toLogBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toTestAdmin
     *     Purpose         : to go to adminHomepage
     */

    @FXML
    public void toTestAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) adminPageBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,700,400));
    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : refreshList
     *     Purpose         : to clear sorting data list
     */

    @FXML
    private void refreshList() {

        list.clear();
        filterList.clear();

        for (int i = 0; i < PropertyList.size(); i++) {
            if (PropertyList.get(i).getStatus() == 1) {
                list.add(new Listing(i));
            }
        }

        if(list.isEmpty()) {
            tbl.setPlaceholder(new Label("No properties found."));
        } else {
            tbl.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : filterList
     *     Purpose         : to filter properties
     */

    @FXML
    private void filterList() {

        list.clear();
        filterList.clear();

        for (int i = 0; i < PropertyList.size(); i++) {
            if (PropertyList.get(i).getStatus() == 1) {
                filterList.add(new Listing(i));
            }
        }

        String selectedType = propType.getSelectionModel().getSelectedItem();
        String selectedBed = bedroom.getSelectionModel().getSelectedItem();
        String selectedBath = bathroom.getSelectionModel().getSelectedItem();
        String selectedFurnish = furnishList.getSelectionModel().getSelectedItem();

        if (selectedType == null) {
            // skip type filter
        } else {
            for (int i = 0; i < filterList.size(); i++) {
                if (!filterList.get(i).getType().equals(selectedType)) {
                    filterList.remove(i);
                }
            }
        }

        if (selectedBed == null) {
            // skip bed filter
        } else {
            for (int i = 0; i < filterList.size(); i++) {
                if (!filterList.get(i).getBed().equals(selectedBed)) {
                    filterList.remove(i);
                }
            }
        }

        if (selectedBath == null) {
            // skip bath filter
        } else {
            for (int i = 0; i < filterList.size(); i++) {
                if (!filterList.get(i).getBath().equals(selectedBath)) {
                    filterList.remove(i);
                }
            }
        }

        if (selectedFurnish == null) {
            // skip furnish filter
        } else {
            // slightly different filter format
        }

        if (!filterList.isEmpty()) {
            for (int i = 0; i < filterList.size(); i++) {
                list.add(filterList.get(i));
            }
        }

        if(list.isEmpty()) {
            tbl.setPlaceholder(new Label("No properties found for the selected filters."));
        } else {
            tbl.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Shuhail & Arif
     *     Method's Name    : initialize
     *     Purpose         : to set initial value of table's data
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        propType.setItems(propertyList);
        bedroom.setItems(numBedrooms);
        bathroom.setItems(numBathrooms);
        furnishList.setItems(furniture);

        col_id.setCellValueFactory(new PropertyValueFactory<>("repID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //col_projType.setCellValueFactory(new PropertyValueFactory<>("projType"));
        //col_fac.setCellValueFactory(new PropertyValueFactory<>("facilities"));
        col_bed.setCellValueFactory(new PropertyValueFactory<>("bed"));
        col_bath.setCellValueFactory(new PropertyValueFactory<>("bath"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_furnish.setCellValueFactory(new PropertyValueFactory<>("furnish"));
        //col_psf.setCellValueFactory(new PropertyValueFactory<>("psf"));
        col_rent.setCellValueFactory(new PropertyValueFactory<>("rent"));

        for (int i = 0; i < PropertyList.size(); i++) {
            if (PropertyList.get(i).getStatus() == 1) {
                list.add(new Listing(i));
            }
        }

        if(list.isEmpty()) {
            tbl.setPlaceholder(new Label("No properties found."));
        } else {
            tbl.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : Listing
     *     Purpose         : hold Listing object
     */

    public class Listing{

        SimpleStringProperty ID;
        SimpleStringProperty type;
        SimpleStringProperty address;
        SimpleStringProperty bed;
        SimpleStringProperty bath;
        SimpleStringProperty area;
        SimpleStringProperty furnish;
        SimpleStringProperty rent;
        SimpleStringProperty repID;

        public Listing(int index){
            this.ID = new SimpleStringProperty(PropertyList.get(index).getPropertyID());
            this.type = new SimpleStringProperty(PropertyList.get(index).getPropertyType());
            this.address = new SimpleStringProperty(PropertyList.get(index).getFirstAddress() + ", " + PropertyList.get(index).getSecondAddress());
            this.bed = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getBedroom()));
            this.bath = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getBathroom()));
            this.area = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getArea()));

            if (PropertyList.get(index).getFurnishing() == 0) {
                this.furnish = new SimpleStringProperty("Unfurnished");
            } else if (PropertyList.get(index).getFurnishing() == 1) {
                this.furnish = new SimpleStringProperty("Partially furnished");
            } else {
                this.furnish = new SimpleStringProperty("Fully furnished");
            }

            this.rent = new SimpleStringProperty(Float.toString(PropertyList.get(index).getRentalRate()));

            for (int i = 0; i < UserList.size(); i++) {
                if (PropertyList.get(index).getRepresentativeID().equals(UserList.get(i).getUserID())) {
                    this.repID = new SimpleStringProperty(UserList.get(i).getLName());
                }
            }

        }

        public String getID() {
            return ID.get();
        }

        public String getType() {
            return type.get();
        }

        public String getAddress() {
            return address.get();
        }

        public String getBed() {
            return bed.get();
        }

        public String getBath() {
            return bath.get();
        }

        public String getArea() {
            return area.get();
        }

        public String getFurnish() {
            return furnish.get();
        }

        public String getRent() {
            return rent.get();
        }

        public String getRepID() {
            return repID.get();
        }
    }

}