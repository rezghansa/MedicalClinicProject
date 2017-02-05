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
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void searchPation(){
        String searchFactor = "select * from patient where ";
        if(!txtFname.getText().isEmpty()){
            searchFactor = "FirstName like (%"+txtFname.getText()+"%) or ";
        }else if(!txtLastName.getText().isEmpty()){
            searchFactor += "LastName like (%"+txtLastName.getText()+"%) or ";
        }else if(!txtTelphone.getText().isEmpty()){
            searchFactor += "Telephone = '"+txtTelphone.getText()+"' ";
        }
        ArrayList<PatientDBO> listOfPatients = 
                DbUtilClass.convertoPatientList(DbUtilClass.readData(searchFactor));
        initializeTable(listOfPatients);
        
    }
    
    public void initializeTable(ArrayList<PatientDBO> listOfPatients){
        //for (PatientDBO Patient : listOfPatients) {
            cloumnFName.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
            cloumnLName.setCellValueFactory(cellData -> cellData.getValue().getLastName());
            cloumnSName.setCellValueFactory(cellData -> cellData.getValue().getSecondName());
            cloumnSSName.setCellValueFactory(cellData -> cellData.getValue().getSecondOName());
            cloumnGender.setCellValueFactory(cellData -> cellData.getValue().getGender());
            cloumnDbo.setCellValueFactory(cellData -> cellData.getValue().getBirthday());
            //cloumnTel.setCellValueFactory(cellData -> cellData.getValue().getTelephone());
            //cloumnAge.setCellValueFactory(cellData -> cellData.getValue().getAge());
            
        //}
    }
}
