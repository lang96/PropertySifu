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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.kuthingalas.propertysifu.system.Property.PropertyList;
import static com.kuthingalas.propertysifu.usertype.User.*;
import static com.kuthingalas.propertysifu.MainApplication.*;



public class AgentOwnerHomepageController implements Initializable {

    @FXML
    private Button profile;

    @FXML
    private Button editPropBtn;

    @FXML
    private Button agentAddPropBtn;

    @FXML private TableView<AgentOwnerListing> tbl;

    @FXML
    private TableColumn<AgentOwnerListing, String> col_id;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_name;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_address;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_bed;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_bath;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_furnish;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_area;
    // @FXML private TableColumn<Listing, String> col_psf;
    @FXML
    private TableColumn<AgentOwnerListing, String> col_rent;

    ObservableList list =  FXCollections.observableArrayList();
    ArrayList<AgentOwnerListing> filterList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
            if (PropertyList.get(i).getRepresentativeID().equals(currentUserID)) {
                list.add(new AgentOwnerListing(i));
            }
        }

        tbl.setItems(list);

    }


    public void toProfile() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("agentOwnerViewProfile.fxml"));

        Stage window = (Stage) profile.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,597,338));
    }

    public void toEditProperty(ActionEvent event) throws IOException {

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
            loader.setLocation(getClass().getResource("editProperty.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            //access the controller and call a method
            EditPropertyController controller = loader.getController();
            controller.initData(selectedProperty);

            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
            window.show();

        }

    }

    public void toAddProp() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("addProperty.fxml"));

        Stage window = (Stage) agentAddPropBtn.getScene().getWindow();
        window.getIcons().add(new Image(this.getClass().getResource("/raw/house2.jpg").toString()));
        window.setScene(new Scene(root,370,665));
    }

    public class AgentOwnerListing{

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

        public AgentOwnerListing(int index){
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
