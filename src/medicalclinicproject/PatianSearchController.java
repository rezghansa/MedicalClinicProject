/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class PatianSearchController implements Initializable {

    @FXML
    private TextField txtFname;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtTelphone;
    @FXML
    private TableColumn<PatientDBO, String> cloumnFName;
    @FXML
    private TableColumn<PatientDBO, String> cloumnLName;
    @FXML
    private TableColumn<PatientDBO, String> cloumnSName;
    @FXML
    private TableColumn<PatientDBO, String> cloumnSSName;
    @FXML
    private TableColumn<PatientDBO, String> cloumnTel;
    @FXML
    private TableColumn<PatientDBO, Integer> cloumnAge;
    @FXML
    private TableColumn<PatientDBO, LocalDate> cloumnDbo;
    @FXML
    private TableColumn<PatientDBO, String> cloumnGender;
    @FXML
    private TableView<PatientDBO> tableSearch;
    
    private ObservableList<PatientDBO> personData = FXCollections.observableArrayList();
    @FXML
    private Button btnSelect;

    private MainPageController mainPageController;
    @FXML
    private Button btnEdit;

    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void searchPation(){
        String conditions = "";
        String searchFactor = "select * from patient where  ";
        if(!txtFname.getText().isEmpty()){
            conditions += "firstName like ('%"+txtFname.getText()+"%') ";
        }if(!txtLastName.getText().isEmpty()){
            if(!conditions.isEmpty())
                conditions +="or ";
            conditions += "lastName like ('%"+txtLastName.getText()+"%')";
        }if(!txtTelphone.getText().isEmpty()){
               if(!conditions.isEmpty())
                conditions +="or ";
            conditions += "telephone like '%"+txtTelphone.getText()+"%' ";
        }
        searchFactor+=conditions;
        System.out.println("Search Query :="+searchFactor);
        ObservableList<PatientDBO> listOfPatients = 
                DbUtilClass.convertoPatientList(DbUtilClass.readData(searchFactor));
        cloumnFName.setCellValueFactory(celldata->celldata.getValue().getFirstName());
        cloumnLName.setCellValueFactory(celldata->celldata.getValue().getLastName());
        cloumnSName.setCellValueFactory(celldata->celldata.getValue().getSecondName());
        cloumnSSName.setCellValueFactory(celldata->celldata.getValue().getSecondOName());
        cloumnGender.setCellValueFactory(celldata->celldata.getValue().getGender());
        cloumnAge.setCellValueFactory(celldata->celldata.getValue().getAge().asObject());
        cloumnTel.setCellValueFactory(celldata->celldata.getValue().getTelephone());
        cloumnDbo.setCellValueFactory(celldata->celldata.getValue().getBirthday());
        tableSearch.setItems(listOfPatients);
           
    }

    @FXML
    private void onSelectPatien(ActionEvent event) {
        PatientDBO selectedPation = tableSearch.getSelectionModel().getSelectedItem();
        if(selectedPation !=null){
           mainPageController.startPresceiption(selectedPation);
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void editPatient(ActionEvent event) {
        PatientDBO selectedPation = tableSearch.getSelectionModel().getSelectedItem();
        if(selectedPation !=null){
           mainPageController.editPatient(selectedPation);
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
 }
