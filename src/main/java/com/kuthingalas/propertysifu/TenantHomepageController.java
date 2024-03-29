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
import java.util.ArrayList;
import java.util.ResourceBundle;



public class TenantHomepageController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment","Bungalow","Condominium","Semi-Detached","Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially furnished","Fully furnished");

    @FXML
    private Button profile, refreshBtn, filterBtn;

    @FXML
    public ComboBox<String> propType;
    @FXML
    public ComboBox<String> projType;
    @FXML
    private ComboBox<String> bedroom;
    @FXML
    private ComboBox<String> bathroom;
    @FXML
    private ComboBox<String> facList;
    @FXML
    private ComboBox<String> furnishList;
    @FXML
    private ComboBox<String> psf;
    @FXML
    private ComboBox<String> price;

    @FXML private TableView<TenantListing> tbl;

    @FXML
    private TableColumn<TenantListing, String> col_id;
    @FXML
    private TableColumn<TenantListing, String> col_name;
    @FXML
    private TableColumn<TenantListing, String> col_address;
    @FXML
    private TableColumn<TenantListing, String> col_bed;
    @FXML
    private TableColumn<TenantListing, String> col_bath;
    @FXML
    private TableColumn<TenantListing, String> col_furnish;
    @FXML
    private TableColumn<TenantListing, String> col_area;
    @FXML
    private TableColumn<TenantListing, String> col_rent;

    ObservableList list =  FXCollections.observableArrayList();
    ArrayList<TenantListing> filterList = new ArrayList<>();

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : viewProperty
     *     Purpose         : This method is to view selected property.
     */

    @FXML
    public void viewProperty(ActionEvent event) throws IOException
    {
        String compareID = tbl.getSelectionModel().getSelectedItem().getID();

        if (compareID == null && compareID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No property selected!");
            errorAlert.showAndWait();

        } else {

            Property selectedProperty = new Property();
            for (int i = 0; i < PropertyList.size(); i++) {

                if (PropertyList.get(i).getPropertyID().equals(compareID)) {
                    selectedProperty = PropertyList.get(i);
                    break;
                }

            }

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("propDetails.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            //access the controller and call a method
            ViewPropertyController controller = loader.getController();
            controller.initData(selectedProperty);

            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();

        }

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : refreshList
     *     Purpose         : This method is to clear the
     */

    @FXML
    private void refreshList() {

        list.clear();
        filterList.clear();

        propType.valueProperty().set(null);
        bedroom.valueProperty().set(null);
        bathroom.valueProperty().set(null);
        furnishList.valueProperty().set(null);

        for (int i = 0; i < PropertyList.size(); i++) {
            if (PropertyList.get(i).getStatus() == 1) {
                list.add(new TenantListing(i));
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
     *     Purpose         : This method is filter list
     */

    @FXML
    private void filterList() {

        list.clear();
        filterList.clear();
        TenantListing prop = new TenantListing();

        for (int i = 0; i < PropertyList.size(); i++) {
            if (PropertyList.get(i).getStatus() == 1) {
                list.add(new TenantListing(i));
            }
        }

        if (!propType.getSelectionModel().isEmpty()) {
            String selectedType = propType.getSelectionModel().getSelectedItem();
            for (int i = 0; i < list.size(); i++) {
                prop = (TenantListing) list.get(i);
                if (prop.getType().equals(selectedType)) {
                    filterList.add(prop);
                }
            }
        } else {
            // skip type filter
        }

        if (!bedroom.getSelectionModel().isEmpty()) {
            String selectedBed = bedroom.getSelectionModel().getSelectedItem();
            for (int i = 0; i < list.size(); i++) {
                prop = (TenantListing) list.get(i);
                if (prop.getBed().equals(selectedBed)) {
                    filterList.add(prop);
                }
            }
        } else {
            // skip bed filter
        }

        if (!bathroom.getSelectionModel().isEmpty()) {
            String selectedBath = bathroom.getSelectionModel().getSelectedItem();
            for (int i = 0; i < list.size(); i++) {
                prop = (TenantListing) list.get(i);
                if (prop.getBath().equals(selectedBath)) {
                    filterList.add(prop);
                }
            }
        } else {
            // skip bath filter
        }

        if (!furnishList.getSelectionModel().isEmpty()) {
            String selectedFurnish = furnishList.getSelectionModel().getSelectedItem();
            for (int i = 0; i < list.size(); i++) {
                prop = (TenantListing) list.get(i);
                if (prop.getFurnish().equals(selectedFurnish)) {
                    filterList.add(prop);
                }
            }
        } else {
            // skip furnish filter
        }

        list.clear();

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
     *     Programmer's Name: Arif & Shuhail
     *     Method's Name    : initialize
     *     Purpose         : To set an initial value of table
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
        col_bed.setCellValueFactory(new PropertyValueFactory<>("bed"));
        col_bath.setCellValueFactory(new PropertyValueFactory<>("bath"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_furnish.setCellValueFactory(new PropertyValueFactory<>("furnish"));
        col_rent.setCellValueFactory(new PropertyValueFactory<>("rent"));


        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new TenantListing(i));
        }

        if(list.isEmpty()) {
            tbl.setPlaceholder(new Label("No properties found."));
        } else {
            tbl.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Arif & Shuhail
     *     Method's Name    : TenantListing
     *     Purpose         : To hold object to display to tableview
     */

    public class TenantListing{

        SimpleStringProperty ID;
        SimpleStringProperty type;
        SimpleStringProperty address;
        SimpleStringProperty bed;
        SimpleStringProperty bath;
        SimpleStringProperty area;
        SimpleStringProperty furnish;
        SimpleStringProperty rent;
        SimpleStringProperty repID;

        public TenantListing(int index){
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

        public TenantListing() {}

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

        public String getRent() {
            return rent.get();
        }

        public String getFurnish() {
            return furnish.get();
        }

        public String getRepID() {
            return repID.get();
        }
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toProfile
     *     Purpose         : This method is to go to view profile
     */

    public void toProfile() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("viewProfile.fxml"));

        Stage window = (Stage) profile.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

}