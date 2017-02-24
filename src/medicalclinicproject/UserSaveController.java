/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    
    private MainPageController mainPageController;
    
    private PatientDBO selcedtPatien = null;
    
    private boolean okClicked = false;
    @FXML
    private ComboBox<String> cmBG;
    
    public void setSelectedPatient(PatientDBO obj){
        this.selcedtPatien = obj;
    }
            
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        ObservableList<String> data = FXCollections.observableArrayList("A+", "A-", "B+","B-", "AB+", "AB-","O+","O-");
        cmBG.setItems(data);
    } 

    
            
    public UserSaveController() {
        if(this.selcedtPatien!=null){
            showPersonDetails(selcedtPatien);
        }
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
        String blodGroup = this.cmBG.getSelectionModel().getSelectedItem();
        
        System.out.println(datebo+"----"+gender);
        
         String sqlInsertNew = "insert into patient(firstName,lastName,telephone,secondName,secondNameMidle,gender,dateofBirth,allergy,majourSicknes,tretments,bloodGroupType)\n" +
        "values('"+firstName+"','"+lastName+"','"+telphone+"','"+seconNO+"','"+seconNT+"',"
                + "'"+gender+"','"+datebo+"','"+Aler+"','"+MajSic+"','"+Tretm+"','"+blodGroup+"')";
        DbUtilClass dbUtil = new DbUtilClass();
        System.out.println("sql query :="+sqlInsertNew);
        if(DbUtilClass.insertion(sqlInsertNew))
            lblmsgSave.setText("User Sucesfully Saved");
        else
            lblmsgSave.setText("User Error Not Saved");
        
    }

    public void showPersonDetails(PatientDBO person) {
            // Fill the labels with info from the person object.
            txtFristName.setText(person.getFirstName().getValue());
            txtLastName.setText(person.getLastName().getValue());
            txtSecondName.setText(person.getSecondName().getValue());
            txtSecondNameTwo.setText(person.getSecondOName().getValue());
            txtTel.setText(person.getTelephone().getValue());
            txBAllergy.setText(person.getAllegy().getValue());
            txbTreatment.setText(person.getTretmnet().getValue());
            txBMajorSick.setText(person.getSick().getValue());
            bDate.setValue(person.getBirthday().getValue());
            if(person.getGender().getValue().equalsIgnoreCase("M")){
                rdFemale.setSelected(false);
                rdMale.setSelected(true);
                rdFemale.setDisable(true);
            }else{
                rdFemale.setSelected(true);
                rdMale.setSelected(false);
                rdMale.setDisable(true);
            }
            cmBG.getItems().clear();
            cmBG.setValue(person.getBloodGroupType().getValue());
            bDate.setDisable(true);
            
    }
 
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void EditUser(ActionEvent event) {
        
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
        
         String sqlInsertNew = "update patient "+
         "set telephone = '"+telphone+"' , "+
         "allergy= '"+Aler+"' , "+
         "majourSicknes= '"+MajSic+"' , "+
         "tretments= '"+Tretm+"' "+
         "where userId = "+selcedtPatien.getUserId().getValue();
         
        DbUtilClass dbUtil = new DbUtilClass();
        System.out.println("sql query :="+sqlInsertNew);
        if(DbUtilClass.insertion(sqlInsertNew))
            lblmsgSave.setText("User Sucesfully Updated");
        else
            lblmsgSave.setText("User Error Not Saved");
    }
}
