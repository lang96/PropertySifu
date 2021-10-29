package com.kuthingalas.propertysifu;

import static com.kuthingalas.propertysifu.usertype.Admin.*;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.system.Property.*;
import static com.kuthingalas.propertysifu.data.DataOperation.*;

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



public class HomepageController implements Initializable {

    ObservableList<String> propertyList = FXCollections.observableArrayList("Bungalow","Apartment","Condominium");
    ObservableList<String> facilitiesList = FXCollections.observableArrayList("Swimming Pool","Park");
    ObservableList<String> sortPrice = FXCollections.observableArrayList("Low to High","High to Low");
    ObservableList<String> numBedrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> numBathrooms = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> furniture = FXCollections.observableArrayList("Unfurnished","Partially","Furnished");


//    @FXML
//    private CheckBox sial;

    @FXML
    private Button profile , confirmBtn ,adminPageBtn;

    @FXML
    private Hyperlink toLogBtn;

    @FXML
    private Label lbl;

    @FXML
    public ComboBox <String> propType;
    @FXML
    private ComboBox<String> facList;
    @FXML
    private ComboBox<String> price;
    @FXML
    private ComboBox<String> bedroom;
    @FXML
    private ComboBox<String> bathroom;
    @FXML
    private ComboBox<String> furnishList;

    @FXML private TableView<Property> tbl;

    @FXML
    private TableColumn<Std, String> col_id;
    @FXML
    private TableColumn<Std, String> col_name;
    @FXML
    private TableColumn<Std, String> col_address;
    @FXML
    private TableColumn<Std, String> col_bed;
    @FXML
    private TableColumn<Std, String> col_bath;
    @FXML
    private TableColumn<Std, String> col_area;
    @FXML
    private TableColumn<Std, Button> col_btn;
    /*
    @FXML
    private TableColumn<Std, String> col_psf;
     */
    @FXML
    private TableColumn<Std, String> col_rent;

    ObservableList list =  FXCollections.observableArrayList();

    @FXML
    public void toLogin() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));

        Stage window = (Stage) toLogBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toProfile() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("viewProfile.fxml"));

        Stage window = (Stage) profile.getScene().getWindow();
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
    public void inforr(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("propDetails.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        propDetailsController controller = loader.getController();
        //controller.initData(tbl.getSelectionModel().getSelectedItem());

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }



    @FXML
    private void doList(ActionEvent event){

        String s = propType.getSelectionModel().getSelectedItem().toString();
        //textareaname.setText( "" + s);
    }

    @FXML
    private void doList2(){

    }

    @FXML
    private void priceList(){

    }

    @FXML
    private void bedList(){

    }

    @FXML
    private void bathList(){

    }

    @FXML
    private void furnishList(){

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
//        col_btn.setCellValueFactory(new PropertyValueFactory<>("button"));

        for (int i = 0; i < PropertyList.size(); i++) {
            list.add(new Std(i));
        }

        tbl.setItems(list);

    }

    public class Std{

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
//        Button info;

        public Std(int index){
            this.ID = new SimpleStringProperty(PropertyList.get(index).getPropertyID());
            this.type = new SimpleStringProperty(PropertyList.get(index).getPropertyType());
            this.address = new SimpleStringProperty(PropertyList.get(index).getFirstAddress());
            this.bed = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getBedroom()));
            this.bath = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getBathroom()));
            this.area = new SimpleStringProperty(Integer.toString(PropertyList.get(index).getArea()));
            this.rent = new SimpleStringProperty(Float.toString(PropertyList.get(index).getRentalRate()));
//            this.info = new Button("Info");

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

//        public void setButton(Button info){
//            this.info = info;
//        }
//
//        public Button getInfo() {
//            return info;
//        }
    }


}

