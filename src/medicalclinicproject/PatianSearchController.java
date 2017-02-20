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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableColumn<PatientDBO, Integer> cloumnTel;
    @FXML
    private TableColumn<PatientDBO, Integer> cloumnAge;
    @FXML
    private TableColumn<PatientDBO, LocalDate> cloumnDbo;
    @FXML
    private TableColumn<PatientDBO, String> cloumnGender;
    @FXML
    private TableView<PatientDBO> tableSearch;
    
    private ObservableList<PatientDBO> data ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    public void searchPation(){
        String searchFactor = "select * from patient where  ";
        if(!txtFname.getText().isEmpty()){
            searchFactor += "firstName like ('%"+txtFname.getText()+"%') ";
        }if(!txtLastName.getText().isEmpty()){
            searchFactor += "or lastName like ('%"+txtLastName.getText()+"%')";
        }if(!txtTelphone.getText().isEmpty()){
            searchFactor += "or telephone = '"+txtTelphone.getText()+"' ";
        }
        System.out.println("Search Query :="+searchFactor);
        ArrayList<PatientDBO> listOfPatients = 
                DbUtilClass.convertoPatientList(DbUtilClass.readData(searchFactor));
        initializeTable(listOfPatients);
        
    }
    
    public void initializeTable(ArrayList<PatientDBO> listOfPatients){
        tableSearch = new TableView<PatientDBO>();
        data = FXCollections.observableArrayList(listOfPatients);
        tableSearch.setEditable(true);
        
        for (PatientDBO Patient : listOfPatients) {
            tableSearch.getColumns().clear();
            cloumnFName.setCellValueFactory(new PropertyValueFactory<PatientDBO, String>("firstName"));
            //cloumnLName.setCellValueFactory(cellData -> cellData.getValue().getLastName());
            //cloumnSName.setCellValueFactory(cellData -> cellData.getValue().getSecondName());
            //cloumnSSName.setCellValueFactory(cellData -> cellData.getValue().getSecondOName());
            //cloumnGender.setCellValueFactory(cellData -> cellData.getValue().getGender());
            //cloumnDbo.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
            //cloumnTel.setCellValueFactory(cellData -> cellData.getValue().getTelephone());
            //cloumnAge.setCellValueFactory(cellData -> cellData.getValue().getAge());  
           
           tableSearch.getColumns().addAll(cloumnFName);
        }
        tableSearch.setItems(data);
    }
}
