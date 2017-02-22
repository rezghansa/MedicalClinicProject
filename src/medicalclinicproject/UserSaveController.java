/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class UserSaveController implements Initializable {

    @FXML
    private TextField txtFristName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtSecondNameTwo;
    @FXML
    private RadioButton rdMale;
    @FXML
    private RadioButton rdFemale;
    @FXML
    private TextField txtTel;
    @FXML
    private TextArea txBAllergy;
    @FXML
    private TextArea txBMajorSick;
    @FXML
    private TextArea txbTreatment;
    @FXML
    private ToggleGroup radio;
    @FXML
    private DatePicker bDate;
    @FXML
    private Label lblmsgSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
    } 

    public UserSaveController(PatientDBO patinDbo) {
        showPersonDetails(patinDbo);
    }
    
        
    @FXML
    public void saveUser(){
        
        String firstName = this.txtFristName.getText();
        String lastName  = this.txtLastName.getText();
        String seconNO   = this.txtSecondName.getText();
        String seconNT   = this.txtSecondNameTwo.getText();
        String telphone  = this.txtTel.getText(); 
        String gender    = this.rdMale.isSelected()?"M":"F";
        String MajSic    = this.txBMajorSick.getText();
        String Tretm     = this.txbTreatment.getText();
        String Aler      = this.txBAllergy.getText();
        LocalDate datebo = this.bDate.getValue();
        
        System.out.println(datebo+"----"+gender);
        
         String sqlInsertNew = "insert into patient(firstName,lastName,telephone,secondName,secondNameMidle,gender,dateofBirth,allergy,majourSicknes,tretments)\n" +
        "values('"+firstName+"','"+lastName+"','"+telphone+"','"+seconNO+"','"+seconNT+"',"
                + "'"+gender+"','"+datebo+"','"+Aler+"','"+MajSic+"','"+Tretm+"')";
        DbUtilClass dbUtil = new DbUtilClass();
        System.out.println("sql query :="+sqlInsertNew);
        if(DbUtilClass.insertion(sqlInsertNew))
            lblmsgSave.setText("User Sucesfully Saved");
        else
            lblmsgSave.setText("User Error Not Saved");
        
    }

    private void showPersonDetails(PatientDBO person) {
         if (person != null) {
            // Fill the labels with info from the person object.
            txtFristName.setText(person.getFirstName().getValue());
            txtLastName.setText(person.getLastName().getValue());
            txtSecondName.setText(person.getSecondName().getValue());
            txtSecondNameTwo.setText(person.getSecondOName().getValue());
        }        
    }
    
}
