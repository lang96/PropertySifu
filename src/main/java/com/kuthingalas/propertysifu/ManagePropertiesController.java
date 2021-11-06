package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;
import com.kuthingalas.propertysifu.usertype.Admin;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

import static com.kuthingalas.propertysifu.system.Property.PropertyList;
import static com.kuthingalas.propertysifu.data.DataOperation.*;
import static com.kuthingalas.propertysifu.usertype.User.UserList;



public class ManagePropertiesController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment","Bungalow","Condominium","Semi-Detached","Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially furnished","Fully furnished");
    ObservableList<String> statList = FXCollections.observableArrayList("Active","Inactive");
    ObservableList<String> psfList = FXCollections.observableArrayList("<RM1/psf","RM1-2/psf",">RM2/psf");

    @FXML
    private Button createNewPropertyBtn, back4, refreshBtn, filterBtn;

    @FXML
    public ComboBox<String> propType;
    @FXML
    private ComboBox<String> bedroom;
    @FXML
    private ComboBox<String> bathroom;
    @FXML
    private ComboBox<String> furnishList;
    @FXML
    private ComboBox<String> status;
    @FXML
    private ComboBox<String> psf;

    @FXML private TableView<AdminProperty> tblAdminProp;

    @FXML
    private TableColumn<AdminProperty, String> col_id;
    @FXML
    private TableColumn<AdminProperty, String> col_type;
    @FXML
    private TableColumn<AdminProperty, String> col_address;
    @FXML
    private TableColumn<AdminProperty, String> col_bed;
    @FXML
    private TableColumn<AdminProperty, String> col_bath;
    @FXML
    private TableColumn<AdminProperty, String> col_area;
    @FXML
    private TableColumn<AdminProperty, String> col_repID;
    @FXML
    private TableColumn<AdminProperty, Float> col_psf;
    @FXML
    private TableColumn<AdminProperty, Float> col_rent;
    @FXML
    private TableColumn<AdminProperty, String> col_stat;
    @FXML
    private TableColumn<AdminProperty, String> col_furnish;

    ObservableList list =  FXCollections.observableArrayList();
    ArrayList<AdminProperty> filterList;

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : initialize
     *     Purpose         : to set initial value of the table
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        propType.setItems(propertyList);
        bedroom.setItems(numBedrooms);
        bathroom.setItems(numBathrooms);
        status.setItems(statList);
        psf.setItems(psfList);

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //col_fac.setCellValueFactory(new PropertyValueFactory<>("facilities"));
        col_bed.setCellValueFactory(new PropertyValueFactory<>("bed"));
        col_bath.setCellValueFactory(new PropertyValueFactory<>("bath"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        col_furnish.setCellValueFactory(new PropertyValueFactory<>("furnish"));
        col_psf.setCellValueFactory(new PropertyValueFactory<>("psf"));
        col_rent.setCellValueFactory(new PropertyValueFactory<>("rent"));
        col_repID.setCellValueFactory(new PropertyValueFactory<>("repID"));
        col_stat.setCellValueFactory(new PropertyValueFactory<>("stat"));

        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new AdminProperty(i));
        }

        if(list.isEmpty()) {
            tblAdminProp.setPlaceholder(new Label("No properties found."));
        } else {
            tblAdminProp.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : adminRemoveProperty
     *     Purpose         : to admin remove property
     */

    @FXML
    public void adminRemoveProperty() {

        String removeID = tblAdminProp.getSelectionModel().getSelectedItem().getID();

        if (removeID == null && removeID.isEmpty()) { // not working

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("No user selected!");
            errorAlert.showAndWait();

        } else {

            removeProperty(removeID);

            list.clear();

            for (int i = 0; i < PropertyList.size(); i++) {
                list.add(new AdminProperty(i));
            }

            if(list.isEmpty()) {
                tblAdminProp.setPlaceholder(new Label("No properties found."));
            } else {
                tblAdminProp.setItems(list);
            }

        }

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : refreshList
     *     Purpose         : to refresh list of the table
     */

    @FXML
    private void refreshList() {

        list.clear();
        filterList = new ArrayList<>();

        propType.valueProperty().set(null);
        bedroom.valueProperty().set(null);
        bathroom.valueProperty().set(null);
        psf.valueProperty().set(null);
        status.valueProperty().set(null);

        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new AdminProperty(i));
        }

        if(list.isEmpty()) {
            tblAdminProp.setPlaceholder(new Label("No properties found."));
        } else {
            tblAdminProp.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : filterList
     *     Purpose         : to filter list of the table
     */

    @FXML
    private void filterList() {

        list.clear();
        filterList = new ArrayList<>();
        AdminProperty prop = new AdminProperty();

        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new AdminProperty(i));
        }

        String rawStatus = "";

        if (!propType.getSelectionModel().isEmpty()) {
            String selectedType = propType.getSelectionModel().getSelectedItem();
            for (int i = 0; i < list.size(); i++) {
                prop = (AdminProperty) list.get(i);
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
                prop = (AdminProperty) list.get(i);
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
                prop = (AdminProperty) list.get(i);
                if (prop.getBath().equals(selectedBath)) {
                    filterList.add(prop);
                }
            }
        } else {
            // skip bath filter
        }

        if (!psf.getSelectionModel().isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                prop = (AdminProperty) list.get(i);
                if (psf.getSelectionModel().getSelectedItem().equals("<RM1/psf")) {
                    if (prop.getPsf() < 1) {
                        filterList.add(prop);
                    }
                } else if (psf.getSelectionModel().getSelectedItem().equals("RM1-2/psf")) {
                    if (prop.getPsf() >= 1 && prop.getPsf() <= 2) {
                        filterList.add(prop);
                    }
                } else {
                    if (prop.getPsf() > 2) {
                        filterList.add(prop);
                    }
                }
            }
        } else {
            // skip psf filter
        }

        if (!status.getSelectionModel().isEmpty()) {
            if (status.getSelectionModel().getSelectedItem().equals("Active")) {
                rawStatus = "1";
            } else {
                rawStatus = "0";
            }

            for (int i = 0; i < list.size(); i++) {
                prop = (AdminProperty) list.get(i);
                if (prop.getStat().equals(rawStatus)) {
                    filterList.add(prop);
                }
            }

        } else {
            // skip status filter
        }

        list.clear();

        if (!filterList.isEmpty()) {
            for (int i = 0; i < filterList.size(); i++) {
                list.add(filterList.get(i));
            }
        }

        if(list.isEmpty()) {
            tblAdminProp.setPlaceholder(new Label("No properties found for the selected filters."));
        } else {
            tblAdminProp.setItems(list);
        }

    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toCreateNewProperty
     *     Purpose         : to go to toCreateNewProperty page
     */

    public void toCreateNewProperty() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("createNewProperty.fxml"));

        Stage window = (Stage) createNewPropertyBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 370, 800));
    }

    /**
     *     Programmer's Name: Shuhail
     *     Method's Name    : toAdminHomepage3
     *     Purpose         : to go to adminHomepage page
     */

    public void toAdminHomepage3() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) back4.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root, 700, 400));
    }

    /**
     *     Programmer's Name: Arif
     *     Method's Name    : AdminProperty
     *     Purpose         : to hold object fits to tableview
     */

    public class AdminProperty {

        SimpleStringProperty ID;
        SimpleStringProperty type;
        SimpleStringProperty address;
        SimpleStringProperty projType;
        //SimpleStringProperty facilities;
        SimpleStringProperty bed;
        SimpleStringProperty bath;
        SimpleStringProperty area;
        SimpleStringProperty furnish;
        SimpleFloatProperty psf;
        SimpleFloatProperty rent;
        SimpleStringProperty repID;
        SimpleStringProperty stat;

        public AdminProperty(int index) {
            this.ID = new SimpleStringProperty(PropertyList.get(index).getPropertyID());
            this.type = new SimpleStringProperty(PropertyList.get(index).getPropertyType());
            this.address = new SimpleStringProperty(PropertyList.get(index).getFirstAddress() + ", " + PropertyList.get(index).getSecondAddress());
            this.projType = new SimpleStringProperty(PropertyList.get(index).getSecondAddress());
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

            this.psf = new SimpleFloatProperty(PropertyList.get(index).getPsfRate());
            this.rent = new SimpleFloatProperty(PropertyList.get(index).getRentalRate());
            this.repID = new SimpleStringProperty(PropertyList.get(index).getRepresentativeID());
            this.stat = new SimpleStringProperty(String.valueOf(PropertyList.get(index).getStatus()));
        }

        public AdminProperty() {}

        public String getID() {
            return ID.get();
        }

        public String getType() {
            return type.get();
        }

        public String getProjType() {
            return projType.get();
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

        public Float getPsf() {
            return psf.get();
        }

        public Float getRent() {
            return rent.get();
        }

        public String getRepID() {
            return repID.get();
        }

        public String getStat() {
            return stat.get();
        }

    }

}
