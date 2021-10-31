package com.kuthingalas.propertysifu;

import com.kuthingalas.propertysifu.system.Property;

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

import static com.kuthingalas.propertysifu.system.Property.*;



public class HomepageController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Apartment","Bungalow","Condominium","Semi-Detached","Terrace/Link");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("Studio","1","2","3","4","5+");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5+");
    ObservableList<String> facilitiesList = FXCollections.observableArrayList("Air-conditioning","Water heater","Gym","Swimming Pool","Car park","Playground","Security","Sports court");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially","Furnished");
    ObservableList<String> sortPSF = FXCollections.observableArrayList("Low to High","High to Low");
    ObservableList<String> sortPrice = FXCollections.observableArrayList("Low to High","High to Low");

    @FXML
    private Button adminPageBtn, refreshBtn, viewPropertyBtn, filterBtn;

    @FXML
    private Hyperlink toLogBtn;

    @FXML
    private Label lbl;

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
    // @FXML private TableColumn<Listing, String> col_psf;
    @FXML
    private TableColumn<Listing, String> col_rent;

    ObservableList list =  FXCollections.observableArrayList();
    ArrayList<Listing> filterList = new ArrayList<>();

    @FXML
    public void toLogin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Stage window = (Stage) toLogBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toTestAdmin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("adminHomepage.fxml"));

        Stage window = (Stage) adminPageBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,700,400));
    }

    //tak jadi lagi, nak initialize data tak lepas - rename
    public void viewProperty(ActionEvent event) throws IOException
    {
        String compareID = tbl.getSelectionModel().getSelectedItem().getID();



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

    @FXML
    private void refreshList() {

        list.clear();
        filterList.clear();

        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new Listing(i));
        }

        tbl.setItems(list);

    }

    @FXML
    private void filterList() {

        list.clear();
        filterList.clear();

        String selectedType = propType.getSelectionModel().getSelectedItem();
        //String selectedProj = projType.getSelectionModel().getSelectedItem();
        String selectedBed = bedroom.getSelectionModel().getSelectedItem();
        //String selectedBath = bathroom.getSelectionModel().getSelectedItem();
        //String selectedFac = facList.getSelectionModel().getSelectedItem();
        //String selectedFurnish = furnishList.getSelectionModel().getSelectedItem();
        //String selectedPSF = psf.getSelectionModel().getSelectedItem();
        //String selectedRent = price.getSelectionModel().getSelectedItem();

        if (selectedType != null) { // type /

            for (int i = 0; i < PropertyList.size(); i++) {
                if (PropertyList.get(i).getPropertyType().equals(selectedType)) {
                    filterList.add(new Listing(i));
                }
            }

            if (selectedBed != null) { // type /, bed /

                for (int j = 0; j < filterList.size(); j++) {
                    if (filterList.get(j).getBed().equals(selectedBed)) {
                        System.out.println(filterList.get(j).getBed());
                        continue;
                    } else {
                        filterList.remove(j);
                    }
                }

            } else {

                // filter list skips bedroom

            }

        } else { // type not selected

            if (selectedBed != null) { // type X, bed /

                for (int i = 0; i < PropertyList.size(); i++) {
                    if (PropertyList.get(i).getBedroom() == Integer.parseInt(selectedBed)) {
                        filterList.add(new Listing(i));
                    }
                }

            } else {

                // filter list empty

            }

        }

        if (!filterList.isEmpty()) {
            for (int z = 0; z < filterList.size(); z++) {
                list.add(filterList.get(z));
            }
        }

        if(list.isEmpty()) {
            tbl.setPlaceholder(new Label("No properties found for the selected filters."));
        } else {
            tbl.setItems(list);
        }

    }

    @FXML
    private void propTypeList(){

    }

    @FXML
    private void projTypeList(){

    }

    @FXML
    private void bedList(){

    }

    @FXML
    private void bathList(){

    }

    @FXML
    private void facList(){

    }

    @FXML
    private void furnishList(){

    }

    @FXML
    private void psfList(){

    }

    @FXML
    private void priceList(){

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        propType.setItems(propertyList);
        facList.setItems(facilitiesList);
        price.setItems(sortPrice);
        bedroom.setItems(numBedrooms);
        bathroom.setItems(numBathrooms);
        furnishList.setItems(furniture);

        col_id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        //col_projType.setCellValueFactory(new PropertyValueFactory<>("projType"));
        //col_fac.setCellValueFactory(new PropertyValueFactory<>("facilities"));
        col_bed.setCellValueFactory(new PropertyValueFactory<>("bed"));
        col_bath.setCellValueFactory(new PropertyValueFactory<>("bath"));
        col_area.setCellValueFactory(new PropertyValueFactory<>("area"));
        //col_furnish.setCellValueFactory(new PropertyValueFactory<>("furnish"));
        //col_psf.setCellValueFactory(new PropertyValueFactory<>("psf"));
        col_rent.setCellValueFactory(new PropertyValueFactory<>("rent"));

        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new Listing(i));
        }

        tbl.setItems(list);

    }

    public class Listing{

        SimpleStringProperty ID;
        SimpleStringProperty type;
        SimpleStringProperty address;
        //SimpleStringProperty projType;
        //SimpleStringProperty facilities;
        SimpleStringProperty bed;
        SimpleStringProperty bath;
        SimpleStringProperty area;
        //SimpleStringProperty furnish;
        //SimpleStringProperty psf;
        SimpleStringProperty rent;

        public Listing(int index){
            this.ID = new SimpleStringProperty(PropertyList.get(index).getPropertyID());
            this.type = new SimpleStringProperty(PropertyList.get(index).getPropertyType());
            this.address = new SimpleStringProperty(PropertyList.get(index).getFirstAddress() + ", " + PropertyList.get(index).getSecondAddress());
            this.bed = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getBedroom()));
            this.bath = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getBathroom()));
            this.area = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getArea()));
            this.rent = new SimpleStringProperty(Float.toString(PropertyList.get(index).getRentalRate()));
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

        public String getRent() {
            return rent.get();
        }

    }

}