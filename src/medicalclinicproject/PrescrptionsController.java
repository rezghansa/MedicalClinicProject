/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class PrescrptionsController implements Initializable {

    @FXML
    private TextArea txtSymp;
    
    private MainPageController mainPageController;
    
    private PatientDBO selcedtPatien = null;
    
    private boolean okClicked = false;
    @FXML
    private TextField medicineNameTxt;
    @FXML
    private TextField dosageTxt;
    @FXML
    private TextField numberOfDayTxt;
    @FXML
    private TextArea tfDeffD;
    @FXML
    private TextArea tfLabTest;
    @FXML
    private TextArea txtParmacy;
    @FXML
    private Button btnSave;
    @FXML
    private TextField totalTxt;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextArea txfPreciption;
    
    private String[]  possibleSuggestions;
    
    private HashMap<String,Medicines> medicineList;
    private ArrayList<PrecriptionDBO> pricsrption;
    
    @FXML
    private Label lblAlrt;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblPatientName;

    public TextArea getTxtSymp() {
        return txtSymp;
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public PatientDBO getSelcedtPatien() {
        return selcedtPatien;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void setSelectedPatient(PatientDBO obj){
        this.selcedtPatien = obj;
    }
            
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public void setTxtSymp(TextArea txtSymp) {
        this.txtSymp = txtSymp;
    }

    public void setSelcedtPatien(PatientDBO selcedtPatien) {
        this.selcedtPatien = selcedtPatien;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          if(selcedtPatien != null){
              showPersonDetails(selcedtPatien);
          }
    }    
    
    public void showPersonDetails(PatientDBO person) {
            // Fill the labels with info from the person object.
            medicineNameinitializer();
            TextFields.bindAutoCompletion(medicineNameTxt, possibleSuggestions);
            lblPatientName.setText("Name:-"+person.getFirstName().getValue() +" Age :- "+person.getAge().getValue() +" Gender:-"+person.getGender().getValue());
            medicineNameTxt.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
                focusState(newValue);
            });
            
    }
    
    public void medicineNameinitializer(){
        ObservableList<Medicines> datatemp =  DbUtilClass.loadMedicinesList(DbUtilClass.readData("select * from medicines"));
        possibleSuggestions = new String[datatemp.size()];
        medicineList = new HashMap<>(datatemp.size());
        int i = 0;
        for(Medicines medi: datatemp){
            possibleSuggestions[i] = medi.getMedicineName().getValue();
            medicineList.put(medi.getMedicineName().getValue(), medi);
            i++;
        }
    }
    
    public void medicineChanges(String medicineID){
        Medicines medi = medicineList.get(medicineID);
        int stockCount = medi.getInStockCount().getValue();
        int urgentCount= medi.getUgentCount().getValue();
        int leasureCount = medi.getLeasulyCount().getValue();
        int earlyCount = medi.getEarlyCount().getValue();
        if(stockCount<=urgentCount){
            lblAlrt.setText("Urgent Purchase");lblAlrt.setTextFill(Color.RED);
        }
        else if((stockCount>=urgentCount)&&(stockCount<=leasureCount)){
            lblAlrt.setText("Lesure Purchase");lblAlrt.setTextFill(Color.DARKBLUE);
        }
        else if((stockCount>=leasureCount)&& (stockCount<=earlyCount)){
            lblAlrt.setText("Early Purchase");lblAlrt.setTextFill(Color.BLUEVIOLET);
        }
        else if(stockCount>=earlyCount){
            lblAlrt.setText("");
        }
    }
    
    private void focusState(boolean value) {
        if (value) {
            //System.out.println("Focus Gained");
        }
        else {
            medicineChanges(medicineNameTxt.getText());
        }
    }
    
    @FXML
    public void savePrescriptionDetails(){
        
        printPrescription();
    }
    
    String prescriptionValues ="Medicine Name\t| Dosage \t| NumberofDays \t| Total Duration \t| \n"
                             + "___________________________________________________________________________\n";
    @FXML
    public void insertPrecriptionItems(){
        prescriptionValues += 
        medicineNameTxt.getText() +" "+
        dosageTxt.getText()+" "+
        numberOfDayTxt.getText() +" "+
        totalTxt.getText()+" \n";
        txfPreciption.setText(prescriptionValues);
        clearFields();
    }
    
    private void clearFields(){
        medicineNameTxt.clear();
        dosageTxt.clear();
        numberOfDayTxt.clear();
        totalTxt.clear();
    }
    
    //this will print as per the requirements to be printed
    public void printPrescription(){
        if(!tfLabTest.getText().isEmpty()){
            //print labtest
        }
        if(!txtParmacy.getText().isEmpty()){
            //print pharmacy
        }
        if(!txfPreciption.getText().isEmpty()){
            //print prescription
        }
    }
}
