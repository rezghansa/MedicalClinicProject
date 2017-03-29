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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    
    @FXML
    private Label lblAlrt;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblPatientName;
    @FXML
    private ComboBox<String> cmbExaminations;
    @FXML
    private GridPane panelExamination;

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
            loadExaminations();
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
        txtSymp.getText();
        tfDeffD.getText();
        txfPreciption.getText();
        tfLabTest.getText();
        txtParmacy.getText();
        txtAmount.getText();
        System.out.println(" -->"+txtSymp.getText()+tfDeffD.getText()+txfPreciption.getText()+tfLabTest.getText()+txtParmacy.getText()+"<<-----");
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
            System.out.println(""+tfLabTest.getText());
        }
        if(!txtParmacy.getText().isEmpty()){
            //print pharmacy
            System.out.println(""+txtParmacy.getText());
        }
        if(!txfPreciption.getText().isEmpty()){
            //print prescription
            System.out.println(""+txfPreciption.getText());
        }
    }
    
    private void loadExaminations(){
       
       ObservableList<String> dataList = FXCollections.observableArrayList("General","CVS","RS","Ear","Nose","Throat","ABDOMEN","Male genitalia","Female genitalia","CNS","Musculo-skeletal");
       cmbExaminations.setItems(dataList);
       cmbExaminations.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
             displayExaminationUI(cmbExaminations.getSelectionModel().getSelectedItem());
           }
       });
    }
    
    private void displayExaminationUI(Object selectedItem){
        clearExaminatioPanel();
        if(selectedItem.equals("General")){
            General();
        }else if(selectedItem.equals("CVS")){
            CVS();
        }else if(selectedItem.equals("RS")){
            RS();
        }else if(selectedItem.equals("Ear")){
            Ear();
        }else if(selectedItem.equals("Nose")){
            Nose();
        }else if(selectedItem.equals("Throat")){
            Throat();
        }else if(selectedItem.equals("ABDOMEN")){
            ABDOMEN();
        }else if(selectedItem.equals("Male genitalia")){
            Malegenitalia();
        }else if(selectedItem.equals("Female genitalia")){
            FeMalegenitalia();
        }else if(selectedItem.equals("CNS")){
            CNS();
        }else if(selectedItem.equals("Musculo-skeletal")){
           MusculoSkeletal();
        }
    }
    
    private void clearExaminatioPanel(){
        panelExamination.getChildren().clear();
    }
    
    private void General(){
        Label newLabel = new Label("Ge");
        panelExamination.add(newLabel, 1, 2);
    
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                panelExamination.getChildren().remove(newLabel);
            }
        });
    }
    private void CVS(){
        Label newLabeSl = new Label("CV");
        panelExamination.add(newLabeSl, 1, 2);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                panelExamination.getChildren().remove(newLabeSl);
            }
        });
    }
    private void RS(){
        
    }
    private void Ear(){
        
    }
    private void Nose(){
        
    }
    private void Throat(){
        
    }
    private void ABDOMEN(){
        
    }
    private void Malegenitalia(){
        
    }
    private void FeMalegenitalia(){
        
    }
    private void CNS(){
        
    }
    private void MusculoSkeletal(){
        
    }
    
}
