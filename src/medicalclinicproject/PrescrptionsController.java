/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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
    
    private PrecriptionDBO prescription = null;
    
    private Examinations examinations = null;
    
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
    @FXML
    private TextArea lblPrevPrescriptions;
    @FXML
    private ComboBox<String> cmbLastVisistCount;
    @FXML
    private Button btnRemove;
    @FXML
    private TableView<ExaminationTable> examninationTable;
    @FXML
    private TableColumn<ExaminationTable, String> examName;
    @FXML
    private TableColumn<ExaminationTable, String> examinationValue;

    ObservableList<ExaminationTable> listOfExaminationItems = FXCollections.observableArrayList();
    
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
            prescription = new PrecriptionDBO();
            examinations = new Examinations();
            prescription.setPaientId(person.getUserId().getValue());
            LocalDate dbDate =  LocalDate.now();
            prescription.setPrescribeDate(dbDate);
            loadCountOfPrescription();
            loadPreviousPrescriptions(3);
    }
    
    
    public void loadCountOfPrescription(){
         ResultSet rs = null;
        try {
            String prescriptionsLoadSql = "SELECT count(*)as total FROM prescriptions where paientId ="+prescription.getPaientId()+" ORDER BY prescribeDate DESC;";
            String PreviousPrescriptions = null;
            rs = DbUtilClass.readData(prescriptionsLoadSql);
            while(rs.next()){
                PreviousPrescriptions =  rs.getString("total");
            }
            ArrayList<String> list = new ArrayList<String>();
            int count= Integer.parseInt(PreviousPrescriptions);
            for(int i=0;i<=count;i++){
                list.add(i+"");
            }
            ObservableList<String> value = FXCollections.observableArrayList(list);
            cmbLastVisistCount.setItems(value);
            cmbLastVisistCount.valueProperty().addListener(new ChangeListener<String>() {
                @Override 
                public void changed(ObservableValue ov, String t, String t1) {
                    loadPreviousPrescriptions(Integer.parseInt(t1));
                }    
            });
        } catch (SQLException ex) {
            Logger.getLogger(PrescrptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void loadPreviousPrescriptions(int count){
        ResultSet rs = null;
        try {
            String prescriptionsLoadSql = "SELECT prescribeDate,paientId,symptoms,"
                    + "diffrentialD,prescriptionTxt FROM prescriptions where paientId ="+prescription.getPaientId()+" ORDER BY prescribeDate DESC limit "+count+";";
            
            String PreviousPrescriptions = null;
            rs = DbUtilClass.readData(prescriptionsLoadSql);
            while(rs.next()){
                PreviousPrescriptions +=  rs.getString("prescriptionTxt");
            }
            lblPrevPrescriptions.setText(PreviousPrescriptions);
        } catch (SQLException ex) {
            Logger.getLogger(PrescrptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        prescription.setSymptoms(txtSymp.getText());
        prescription.setDiffrentialD(tfDeffD.getText());
        prescription.setPrescriptionTxt(txfPreciption.getText());
        prescription.setLabsTxt(tfLabTest.getText());
        prescription.setPhamacyTxt(txtParmacy.getText());
        prescription.setAmount(txtAmount.getText());
        saveExaminations();
        savetoDataBase();
        printPrescription();
    }
    
    public void saveExaminations(){
        
       //save examinations and get the last inserted id
       if(!examinations.isSomeThingSet()){
           return;
       }
       LocalDate dbDate = LocalDate.now();
       examinations.setExaminationDate(dbDate.toString());
       DbUtilClass.insertion(examinations.insertQuery());
       ResultSet rs = DbUtilClass.readData("select LAST_INSERT_ID() as examinationId from examinationdetail");
       try{
            while(rs.next()){
             //Retrieve by column name
             Integer lastInsertId = rs.getInt("examinationId");
             examinations.setExaminationID(lastInsertId);
            }
         }catch(Exception e){
             examinations.setExaminationID(0);
             e.printStackTrace();}
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        prescription.setExamination(examinations);
        prescription.setExaminationId(examinations.getExaminationID());
    }
    
    public void savetoDataBase(){
        String prescriptionValuesSql = "INSERT INTO  prescriptions\n" +
                                        "(\n" +
                                        "prescribeDate,\n" +
                                        "paientId,\n" +
                                        "symptoms,\n" +
                                        "diffrentialD,\n" +
                                        "labsTxt,\n" +
                                        "phamacyTxt,\n" +
                                        "prescriptionTxt,\n" +
                                        "amount,\n" +
                                        "examinationId)\n" +
                                        "VALUES\n" +
                                        "(\n" +
                                        "'"+prescription.getPrescribeDate()+"',\n" +
                                        ""+prescription.getPaientId()+",\n" +
                                        "'"+prescription.getSymptoms()+"',\n" +
                                        "'"+prescription.getDiffrentialD()+"',\n" +
                                        "'"+prescription.getLabsTxt()+"',\n" +
                                        "'"+prescription.getPhamacyTxt()+"',\n" +
                                        "'"+prescription.getPrescriptionTxt()+"',\n" +
                                        "'"+prescription.getAmount()+"',\n" +
                                        ""+prescription.getExaminationId()+"); ";
        System.out.println("Query Before Save"+prescriptionValuesSql);
        DbUtilClass.insertion(prescriptionValuesSql);
    }
    
    
    String prescriptionValues ="Medicine Name\t| Dosage \t| NumberofDays \t| Total Duration \t| \n"
                             + "___________________________________________________________________________\n";
    @FXML
    public void insertPrecriptionItems(){
        PrescriptionTableData temp = new PrescriptionTableData(medicineNameTxt.getText(), 
                dosageTxt.getText(), Double.parseDouble(numberOfDayTxt.getText()),
                Double.parseDouble(totalTxt.getText()));
        prescriptionValues += 
        medicineNameTxt.getText() +" "+
        dosageTxt.getText()+" "+
        numberOfDayTxt.getText() +" "+
        totalTxt.getText()+" \n";
        txfPreciption.setText(prescriptionValues);
        prescription.addPrescrptionTableData(temp);
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
            System.out.println("Labs Print:="+prescription.getLabsTxt());
        }
        if(!txtParmacy.getText().isEmpty()){
            //print pharmacy
            System.out.println("Pharmacy Print:="+prescription.getPhamacyTxt());
        }
        if(!txfPreciption.getText().isEmpty()){
            //print prescription
            System.out.println("Prescrption Print:="+prescription.getPrescriptionTxt());
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
        Label Height = new Label("Height(KG)");  TextField txtHeig = new TextField();
        Label Weight = new Label("Weight (M)");  TextField txtWeig = new TextField();
        Label Pallor = new Label("Pallor");      ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);group.selectToggle(rb2);
        Label Jaundice = new Label("Jaundice");  ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); group1.selectToggle(rb21);
        Label Thin = new Label("Thin");          ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);group2.selectToggle(rb22);
        Label Obese = new Label("Obese");        ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);group3.selectToggle(rb23);
        Label oedema = new Label("oedema-ankle");ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); group4.selectToggle(rb24);
        Label peri = new Label("peri-orbital");  ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);group5.selectToggle(rb25);
        Label dehydration = new Label("dehydration"); ToggleGroup group6 = new ToggleGroup(); RadioButton rb16 = new RadioButton("Yes"); RadioButton rb26 = new RadioButton("No"); rb16.setToggleGroup(group6); rb26.setToggleGroup(group6);group6.selectToggle(rb26);
        Label sob = new Label("sob");                ToggleGroup group7 = new ToggleGroup(); RadioButton rb17 = new RadioButton("Yes"); RadioButton rb27 = new RadioButton("No"); rb17.setToggleGroup(group7); rb27.setToggleGroup(group7);group7.selectToggle(rb27);
        panelExamination.add(Height, 0, 1);     panelExamination.add(txtHeig, 1, 1);
        panelExamination.add(Weight, 0, 2);     panelExamination.add(txtWeig, 1, 2);
        panelExamination.add(Pallor, 0, 3);     panelExamination.add(rb1, 1, 3); panelExamination.add(rb2, 2, 3);
        panelExamination.add(Jaundice, 0,4);    panelExamination.add(rb11, 1, 4); panelExamination.add(rb21, 2, 4);
        panelExamination.add(Thin, 0, 5);       panelExamination.add(rb12, 1, 5); panelExamination.add(rb22, 2, 5);
        panelExamination.add(Obese, 0, 6);      panelExamination.add(rb13, 1, 6); panelExamination.add(rb23, 2, 6);
        panelExamination.add(oedema, 0, 7);     panelExamination.add(rb14, 1, 7); panelExamination.add(rb24, 2, 7);
        panelExamination.add(peri, 0, 8);       panelExamination.add(rb15, 1, 8); panelExamination.add(rb25, 2, 8);
        panelExamination.add(dehydration, 0, 9);panelExamination.add(rb16, 1, 9); panelExamination.add(rb26, 2, 9);
        panelExamination.add(sob, 0, 10);       panelExamination.add(rb17, 1, 10); panelExamination.add(rb27, 2, 10);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setGeneral_height(txtHeig.getText());
               examinations.setGeneral_weight(txtWeig.getText());
               examinations.setGeneral_Pallor(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setGeneral_Jaundice(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setGeneral_Thin(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setGeneral_Obese(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setGeneral_oedemaankle(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setGeneral_periorbital(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setGeneral_dehydration(((RadioButton)group6.getSelectedToggle()).getText());
               examinations.setGeneral_sob(((RadioButton)group7.getSelectedToggle()).getText());
               ExaminationTable examitemp = new ExaminationTable("General", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void CVS(){
        Label pulse = new Label("pulse(Bpm)");                  TextField txtpulse = new TextField();
        Label regular = new Label("regular");      		ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label irregular = new Label("irregular");  		ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label bp = new Label("Bp");                             TextField txtbp = new TextField();
        Label hert = new Label("Heart-   dual rhythm");         ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label trpl = new Label("triple rhythm");        	ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label murmurs = new Label("murmurs");                   ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); 
        Label systolic = new Label("systolic");  		ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        Label diastolic = new Label("diastolic");               ToggleGroup group6 = new ToggleGroup(); RadioButton rb16 = new RadioButton("Yes"); RadioButton rb26 = new RadioButton("No"); rb16.setToggleGroup(group6); rb26.setToggleGroup(group6);
        panelExamination.add(pulse, 0, 1);     			panelExamination.add(txtpulse, 1, 1);
        panelExamination.add(regular, 0, 2);     		panelExamination.add(rb1, 1, 2); panelExamination.add(rb2, 2, 2);
        panelExamination.add(irregular, 0,3);    		panelExamination.add(rb11, 1, 3); panelExamination.add(rb21, 2, 3);
        panelExamination.add(bp, 0, 4);                         panelExamination.add(txtbp, 1, 4);
        panelExamination.add(hert, 0, 5);       		panelExamination.add(rb12, 1, 5); panelExamination.add(rb22, 2, 5);
        panelExamination.add(trpl, 0, 6);                       panelExamination.add(rb13, 1, 6); panelExamination.add(rb23, 2, 6);
        panelExamination.add(murmurs, 0, 7);     		panelExamination.add(rb14, 1, 7); panelExamination.add(rb24, 2, 7);
        panelExamination.add(systolic, 0, 8);       		panelExamination.add(rb15, 1, 8); panelExamination.add(rb25, 2, 8);
        panelExamination.add(diastolic, 0, 9);		panelExamination.add(rb16, 1, 9); panelExamination.add(rb26, 2, 9);
        
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setCvs_pulseBpm(txtpulse.getText());
               examinations.setCvs_regular(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setCvs_irregular(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setCvs_Bp(txtbp.getText());
               examinations.setCvs_Heartdualrhythm(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setCvs_triplerhythm(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setCvs_murmurs(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setCvs_systolic(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setCvs_diastolic(((RadioButton)group6.getSelectedToggle()).getText());
               ExaminationTable examitemp = new ExaminationTable("CVS", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void RS(){
        Label Pallor = new Label("equal air entry");        ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label Jaundice = new Label("reduced air entry ");   ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label Thin = new Label("vbs");                      ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label Obese = new Label("bbs");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label oedema = new Label("creps");                  ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); 
        Label peri = new Label("rhonchi");                  ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        panelExamination.add(Pallor, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(Jaundice, 0,2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(Thin, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);
        panelExamination.add(Obese, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);
        panelExamination.add(oedema, 0, 5);     panelExamination.add(rb14, 1, 5); panelExamination.add(rb24, 2,5);
        panelExamination.add(peri, 0, 6);       panelExamination.add(rb15, 1, 6); panelExamination.add(rb25, 2, 6);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setRs_equalairentry(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setRs_reducedairentry(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setRs_vbs(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setRs_bbs(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setRs_creps(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setRs_rhonchi(((RadioButton)group5.getSelectedToggle()).getText());
               ExaminationTable examitemp = new ExaminationTable("RS", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void Ear(){
        Label l1 = new Label("Normal");                 ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label l2 = new Label("Normal hearing");         ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label l3 = new Label("Wax");                    ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label l4 = new Label("OE");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label l5 = new Label("traumatic perforation");  ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); 
        Label l6 = new Label("AOM");                    ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        Label l7 = new Label("OME");                    ToggleGroup group6 = new ToggleGroup(); RadioButton rb16 = new RadioButton("Yes"); RadioButton rb26 = new RadioButton("No"); rb16.setToggleGroup(group6); rb26.setToggleGroup(group6);
        Label l8 = new Label("CSOM");                   ToggleGroup group7 = new ToggleGroup(); RadioButton rb17 = new RadioButton("Yes"); RadioButton rb27 = new RadioButton("No"); rb17.setToggleGroup(group7); rb27.setToggleGroup(group7); 
        Label l9 = new Label("CP");                     ToggleGroup group8 = new ToggleGroup(); RadioButton rb18 = new RadioButton("Yes"); RadioButton rb28 = new RadioButton("No"); rb18.setToggleGroup(group8); rb28.setToggleGroup(group8);
        Label ll10 = new Label("bleeding");             ToggleGroup group9 = new ToggleGroup(); RadioButton rb19 = new RadioButton("Yes"); RadioButton rb29 = new RadioButton("No"); rb19.setToggleGroup(group9); rb29.setToggleGroup(group9);
        Label ll11 = new Label("Ear discharge");        ToggleGroup group10 = new ToggleGroup(); RadioButton rb100 = new RadioButton("Yes"); RadioButton rb201 = new RadioButton("No"); rb100.setToggleGroup(group10); rb201.setToggleGroup(group10); 
        Label ll12 = new Label("Granulation tissue");   ToggleGroup group11 = new ToggleGroup(); RadioButton rb101 = new RadioButton("Yes"); RadioButton rb202 = new RadioButton("No"); rb101.setToggleGroup(group11); rb202.setToggleGroup(group11);
        Label ll13 = new Label("Polyp");                ToggleGroup group12 = new ToggleGroup(); RadioButton rb102 = new RadioButton("Yes"); RadioButton rb203 = new RadioButton("No"); rb102.setToggleGroup(group12); rb203.setToggleGroup(group12);
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(l3, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);
        panelExamination.add(l4, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);
        panelExamination.add(l5, 0, 5);     panelExamination.add(rb14, 1, 5); panelExamination.add(rb24, 2,5);
        panelExamination.add(l6, 0, 6);       panelExamination.add(rb15, 1, 6); panelExamination.add(rb25, 2, 6);
        panelExamination.add(l7, 0, 7);     panelExamination.add(rb16, 1, 7); panelExamination.add(rb26, 2, 7);
        panelExamination.add(l8, 0, 8);    panelExamination.add(rb17, 1, 8); panelExamination.add(rb27, 2, 8);
        panelExamination.add(l9, 0, 9);       panelExamination.add(rb18, 1, 9); panelExamination.add(rb28, 2, 9);
        panelExamination.add(ll10, 0, 10);      panelExamination.add(rb19, 1, 10); panelExamination.add(rb29, 2, 10);
        panelExamination.add(ll11, 0, 11);     panelExamination.add(rb100, 1, 11); panelExamination.add(rb201, 2,11);
        panelExamination.add(ll12, 0, 12);       panelExamination.add(rb101, 1, 12); panelExamination.add(rb202, 2, 12);
        panelExamination.add(ll13, 0, 13);       panelExamination.add(rb102, 1, 13); panelExamination.add(rb203, 2, 13);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setEar_EarNormal(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setEar_Normalhearing(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setEar_Wax(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setEar_OE(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setEar_traumaticperforation(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setEar_AOM(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setEar_OME(((RadioButton)group6.getSelectedToggle()).getText());
               examinations.setEar_CSOM(((RadioButton)group7.getSelectedToggle()).getText());
               examinations.setEar_CP(((RadioButton)group8.getSelectedToggle()).getText());
               examinations.setEar_bleedingEar(((RadioButton)group9.getSelectedToggle()).getText());
               examinations.setEar_Eardischarge(((RadioButton)group10.getSelectedToggle()).getText());
               examinations.setEar_Granulationtissue(((RadioButton)group11.getSelectedToggle()).getText());
               examinations.setEar_EarPolyp(((RadioButton)group12.getSelectedToggle()).getText());
               ExaminationTable examitemp = new ExaminationTable("Ear", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void Nose(){
        Label l1 = new Label("Patent");                 ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label l2 = new Label("blocked");                ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label l3 = new Label("Sense of smell");         ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label l4 = new Label("Fb");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label l5 = new Label("Deviated Septum");        ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); 
        Label l6 = new Label("Bleeding");               ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        Label l7 = new Label("Secretions");             ToggleGroup group6 = new ToggleGroup(); RadioButton rb16 = new RadioButton("Yes"); RadioButton rb26 = new RadioButton("No"); rb16.setToggleGroup(group6); rb26.setToggleGroup(group6);
        Label l8 = new Label("Polyp");                  ToggleGroup group7 = new ToggleGroup(); RadioButton rb17 = new RadioButton("Yes"); RadioButton rb27 = new RadioButton("No"); rb17.setToggleGroup(group7); rb27.setToggleGroup(group7); 
        Label l9 = new Label("PND");                    ToggleGroup group8 = new ToggleGroup(); RadioButton rb18 = new RadioButton("Yes"); RadioButton rb28 = new RadioButton("No"); rb18.setToggleGroup(group8); rb28.setToggleGroup(group8);
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(l3, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);
        panelExamination.add(l4, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);
        panelExamination.add(l5, 0, 5);     panelExamination.add(rb14, 1, 5); panelExamination.add(rb24, 2,5);
        panelExamination.add(l6, 0, 6);       panelExamination.add(rb15, 1, 6); panelExamination.add(rb25, 2, 6);
        panelExamination.add(l7, 0, 7);     panelExamination.add(rb16, 1, 7); panelExamination.add(rb26, 2, 7);
        panelExamination.add(l8, 0, 8);    panelExamination.add(rb17, 1, 8); panelExamination.add(rb27, 2, 8);
        panelExamination.add(l9, 0, 9);       panelExamination.add(rb18, 1, 9); panelExamination.add(rb28, 2, 9);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setNose_Patent(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setNose_blocked(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setNose_Senseofsmell(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setNose_Fb(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setNose_DeviatedSeptum(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setNose_BleedingS(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setNose_Secretions(((RadioButton)group6.getSelectedToggle()).getText());
               examinations.setNose_Polyp(((RadioButton)group7.getSelectedToggle()).getText());
               examinations.setNose_PND(((RadioButton)group8.getSelectedToggle()).getText());
               ExaminationTable examitemp = new ExaminationTable("Nose", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void Throat(){
        Label l1 = new Label("Normal");                 ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label l2 = new Label("Pharyngitis");                ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label l3 = new Label("Tonsillitis");         ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label l4 = new Label("Normal VC");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label l5 = new Label("Other");        TextArea tx1 = new TextArea();
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(l3, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);
        panelExamination.add(l4, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);
        panelExamination.add(l5, 0, 5);     panelExamination.add(tx1, 1, 5); 
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setThoat_Normal(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setThoat_Pharyngitis(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setThoat_Tonsillitis(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setThoat_NormalVC(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setThoat_Other(tx1.getText());
               ExaminationTable examitemp = new ExaminationTable("Throat", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void ABDOMEN(){
        Label l1 = new Label("Distension");                 ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group); group.selectToggle(rb2);
        Label l2 = new Label("Tenderness");                ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); group1.selectToggle(rb21);
        ObservableList items = FXCollections.observableArrayList("not palpable","palpable");
        Label l3 = new Label("Liver"); ComboBox cb1 = new ComboBox(items);
        Label l4 = new Label("Spleen"); ComboBox cb2 = new ComboBox(items);
        Label l5 = new Label("Kidney"); ComboBox cb3 = new ComboBox(items);
        Label l6 = new Label("Other");        TextArea tx1 = new TextArea();
        
        CheckBox cb4 = new CheckBox("site-epigastric");
        CheckBox cb8 = new CheckBox("umbilical");
        CheckBox cb6 = new CheckBox("hypochondriac");
        
        CheckBox cb12 = new CheckBox("hypogastric L"); 
        CheckBox cb10 = new CheckBox("lumbar L"); 
        CheckBox cb14 = new CheckBox("inguinal L");
        
        CheckBox cb13= new CheckBox("hypogastric R");
        CheckBox cb11= new CheckBox("lumbar R");
        CheckBox cb15= new CheckBox("inguinal R");
        
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(l3, 0, 3); panelExamination.add(cb1, 1, 3);
        panelExamination.add(l4, 0, 4); panelExamination.add(cb2, 1, 4);
        panelExamination.add(l5, 0, 5); panelExamination.add(cb3, 1, 5);
        panelExamination.add(l6, 0, 6);     panelExamination.add(tx1, 1, 6);
        
        //right side
        panelExamination.add(cb13, 0, 8,1,1);
        panelExamination.add(cb11, 0, 9,1,1);
        panelExamination.add(cb15, 0, 10,1,1);
        //center parts
        panelExamination.add(cb4, 1, 8,1,1);
        panelExamination.add(cb8, 1, 9,1,1);
        panelExamination.add(cb6, 1, 10,1,1); 
        //leftSide
        panelExamination.add(cb12, 3, 8,1,1);
        panelExamination.add(cb10, 3, 9,1,1);
        panelExamination.add(cb14, 3, 10,1,1);
        
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setAmodoment_Distension(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setAmodoment_Tenderness(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setAmodoment_Liver(!cb1.getSelectionModel().isEmpty()?cb1.getSelectionModel().getSelectedItem().toString():"N/A");
               examinations.setAmodoment_Spleen(!cb2.getSelectionModel().isEmpty()?cb2.getSelectionModel().getSelectedItem().toString():"N/A");
               examinations.setAmodoment_Kidney(!cb3.getSelectionModel().isEmpty()?cb3.getSelectionModel().getSelectedItem().toString():"N/A");
               examinations.setAmodoment_AbodometOther(tx1.getText());
               //left side
               examinations.setAmodoment_hypogastricL(cb12.isSelected()?"Yes":"N/A");
               examinations.setAmodoment_lumbarL(cb10.isSelected()?"Yes":"N/A");
               examinations.setAmodoment_inguinalL(cb14.isSelected()?"Yes":"N/A");
               //right side
               examinations.setAmodoment_hypogastricR(cb13.isSelected()?"Yes":"N/A");
               examinations.setAmodoment_lumbarR(cb11.isSelected()?"Yes":"N/A");
               examinations.setAmodoment_inguinalR(cb15.isSelected()?"Yes":"N/A");
               //center part
               examinations.setAmodoment_siteepigastricC(cb4.isSelected()?"Yes":"N/A");
               examinations.setAmodoment_umbilicalC(cb8.isSelected()?"Yes":"N/A");
               examinations.setAmodoment_hypochondriacC(cb6.isSelected()?"Yes":"N/A");
               ExaminationTable examitemp = new ExaminationTable("ABDOMEN", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void Malegenitalia(){
        Label l1 = new Label("Swelling");                 ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label l2 = new Label("Tenderness");                ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label l3 = new Label("Hydrocele");         ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label l4 = new Label("Balanitis");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label l5 = new Label("Urethral discharge");        ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); 
        Label l6 = new Label("fungal rash");               ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        Label l7 = new Label("Other");        TextArea tx1 = new TextArea();
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(l3, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);
        panelExamination.add(l4, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);
        panelExamination.add(l5, 0, 5);     panelExamination.add(rb14, 1, 5); panelExamination.add(rb24, 2,5);
        panelExamination.add(l6, 0, 6);       panelExamination.add(rb15, 1, 6); panelExamination.add(rb25, 2, 6);
        panelExamination.add(l7, 0, 7);     panelExamination.add(tx1, 1, 7); 
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setMale_Swelling(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setMale_MaleTenderness(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setMale_Hydrocele(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setMale_Balanitis(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setMale_Urethraldischarge(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setMale_fungalrash(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setMale_MaleOther(tx1.getText());
               ExaminationTable examitemp = new ExaminationTable("Male-genitalia", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
        
    }
    private void FeMalegenitalia(){
        Label l6 = new Label("LMP");TextField tx5 = new TextField(); TextField tx6 = new TextField();TextField tx7 = new TextField();
        Label l1 = new Label("Breasts- L-"); CheckBox cb1 = new CheckBox("normal"); TextField tx1 = new TextField();
        Label l2 = new Label("Breasts- R-"); CheckBox cb2 = new CheckBox("normal"); TextField tx2 = new TextField();
        Label l3 = new Label("Nipples- L-"); CheckBox cb3 = new CheckBox("normal"); TextField tx3 = new TextField();
        Label l4 = new Label("Nipples- R-"); CheckBox cb4 = new CheckBox("normal"); TextField tx4 = new TextField();
        ObservableList items = FXCollections.observableArrayList("not palpable","palpable");
        Label l5 = new Label("Uterus"); ComboBox cb5 = new ComboBox(items);
        Label l9 = new Label("fungal rash"); ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        Label l10 = new Label("Vaginal discharge"); ToggleGroup group6 = new ToggleGroup(); RadioButton rb16 = new RadioButton("Yes"); RadioButton rb26 = new RadioButton("No"); rb16.setToggleGroup(group6); rb26.setToggleGroup(group6);
        Label l7 = new Label("Perineum");TextField tx8 = new TextField();
        Label l8 = new Label("Other");TextField tx9 = new TextField();
        panelExamination.add(l6, 0, 1); panelExamination.add(tx5, 1, 1); panelExamination.add(tx6, 2, 1);panelExamination.add(tx7, 3, 1);
        panelExamination.add(l1, 0, 2); panelExamination.add(cb1, 1, 2); panelExamination.add(tx1, 2, 2);
        panelExamination.add(l2, 0, 3); panelExamination.add(cb2, 1, 3); panelExamination.add(tx2, 2, 3);
        panelExamination.add(l3, 0, 4); panelExamination.add(cb3, 1, 4); panelExamination.add(tx3, 2, 4);
        panelExamination.add(l4, 0, 5); panelExamination.add(cb4, 1, 5); panelExamination.add(tx4, 2, 5);
        panelExamination.add(l5, 0, 6); panelExamination.add(cb5, 1, 6);
        panelExamination.add(l7, 0, 7); panelExamination.add(tx8, 1, 7);
        panelExamination.add(l8, 0, 8); panelExamination.add(tx9, 1, 8);
        panelExamination.add(l9, 0, 9); panelExamination.add(rb15, 1, 9); panelExamination.add(rb25, 2, 9);
        panelExamination.add(l10, 0, 10);panelExamination.add(rb16, 1, 10); panelExamination.add(rb26, 2, 10);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                examinations.setSomeThingSet(true);
                examinations.setFemale_LMP1(tx5.getText());
                examinations.setFemale_LMP2(tx6.getText());
                examinations.setFemale_LMP3(tx7.getText());
                examinations.setFemale_BreastsL(cb1.isSelected()?"Yes":"N/A");
                examinations.setFemale_BreastsTL(tx1.getText());
                examinations.setFemale_BreastsR(cb2.isSelected()?"Yes":"N/A");
                examinations.setFemale_BreastsTR(tx2.getText());
                examinations.setFemale_NipplesL(cb3.isSelected()?"Yes":"N/A");
                examinations.setFemale_NipplesTL(tx3.getText());
                examinations.setFemale_NipplesR(cb4.isSelected()?"Yes":"N/A");
                examinations.setFemale_NipplesTR(tx4.getText());
                examinations.setFemale_Uterus(!cb5.getSelectionModel().isEmpty()?cb5.getSelectionModel().getSelectedItem().toString():"N/A");
                examinations.setFemale_fungalRash(((RadioButton)group5.getSelectedToggle()).getText());
                examinations.setFemale_viginalDischagrg(((RadioButton)group6.getSelectedToggle()).getText());
                examinations.setFemale_Perineum(tx8.getText());
                examinations.setFemale_FemaleOther(tx9.getText());
                ExaminationTable examitemp = new ExaminationTable("Female-genitalia", examinations.getGeneral_height());
                loadToTable(examitemp);
                
            }
        });
    }
    private void CNS(){
        Label l1 = new Label("Normal cranial nerves");                 ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group); TextField t1 = new TextField();
        Label l2 = new Label("Gait-normal");                ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); TextField t2 = new TextField();
        Label l3 = new Label("speech-normal");         ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);TextField t3 = new TextField();
        Label l4 = new Label("Coordination-normal");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3); TextField t4 = new TextField();
        Label l5 = new Label("Power-normal");        ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4);  TextField t5 = new TextField();
        Label l6 = new Label("Sensory-normal");               ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5); TextField t6 = new TextField();
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1); panelExamination.add(t1, 3, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);panelExamination.add(t2, 3, 2);
        panelExamination.add(l3, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);panelExamination.add(t3, 3, 3);
        panelExamination.add(l4, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);panelExamination.add(t4, 3, 4);
        panelExamination.add(l5, 0, 5);     panelExamination.add(rb14, 1, 5); panelExamination.add(rb24, 2,5);panelExamination.add(t5, 3, 5);
        panelExamination.add(l6, 0, 6);       panelExamination.add(rb15, 1, 6); panelExamination.add(rb25, 2, 6);panelExamination.add(t6, 3, 6);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setCns_Normalcranialnerves(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setCns_Normalcranialnerves(t1.getText());
               examinations.setCns_Gaitnormal(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setCns_Gaitnormaltxt(t2.getText());
               examinations.setCns_speechnormal(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setCns_speechnormaltxt(t3.getText());
               examinations.setCns_Coordinationnormal(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setCns_Coordinationnormaltxt(t4.getText());
               examinations.setCns_Powernormal(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setCns_Powernormaltxt(t5.getText());
               examinations.setCns_Sensorynormal(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setCns_Sensorynormaltxt(t6.getText());
               ExaminationTable examitemp = new ExaminationTable("CNS", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    private void MusculoSkeletal(){
        Label l1 = new Label("cervical spine tenderness");          ToggleGroup group = new ToggleGroup(); RadioButton rb1 = new RadioButton("Yes"); RadioButton rb2 = new RadioButton("No"); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
        Label l2 = new Label("thoracic spine tenderness");          ToggleGroup group1 = new ToggleGroup(); RadioButton rb11 = new RadioButton("Yes"); RadioButton rb21 = new RadioButton("No"); rb11.setToggleGroup(group1); rb21.setToggleGroup(group1); 
        Label l3 = new Label("lumbo-sacral spine tenderness");      ToggleGroup group2 = new ToggleGroup(); RadioButton rb12 = new RadioButton("Yes"); RadioButton rb22 = new RadioButton("No"); rb12.setToggleGroup(group2); rb22.setToggleGroup(group2);
        Label l4 = new Label("SIJ tenderness");                     ToggleGroup group3 = new ToggleGroup(); RadioButton rb13 = new RadioButton("Yes"); RadioButton rb23 = new RadioButton("No"); rb13.setToggleGroup(group3); rb23.setToggleGroup(group3);
        Label l5 = new Label("Swollen large joints");               ToggleGroup group4 = new ToggleGroup(); RadioButton rb14 = new RadioButton("Yes"); RadioButton rb24 = new RadioButton("No"); rb14.setToggleGroup(group4); rb24.setToggleGroup(group4); 
        Label l6 = new Label("Tender large joints");                ToggleGroup group5 = new ToggleGroup(); RadioButton rb15 = new RadioButton("Yes"); RadioButton rb25 = new RadioButton("No"); rb15.setToggleGroup(group5); rb25.setToggleGroup(group5);
        Label l7 = new Label("Swollen small joints");               ToggleGroup group6 = new ToggleGroup(); RadioButton rb16 = new RadioButton("Yes"); RadioButton rb26 = new RadioButton("No"); rb16.setToggleGroup(group6); rb26.setToggleGroup(group6);
        Label l8 = new Label("Tender small joints");                ToggleGroup group7 = new ToggleGroup(); RadioButton rb17 = new RadioButton("Yes"); RadioButton rb27 = new RadioButton("No"); rb17.setToggleGroup(group7); rb27.setToggleGroup(group7); 
        Label l9 = new Label("Chest wall/rib tenderness");          ToggleGroup group8 = new ToggleGroup(); RadioButton rb18 = new RadioButton("Yes"); RadioButton rb28 = new RadioButton("No"); rb18.setToggleGroup(group8); rb28.setToggleGroup(group8);
        Label ll10 = new Label("Upper limb tenderness");            ToggleGroup group9 = new ToggleGroup(); RadioButton rb19 = new RadioButton("Yes"); RadioButton rb29 = new RadioButton("No"); rb19.setToggleGroup(group9); rb29.setToggleGroup(group9);
        Label ll11 = new Label("Lower limb tenderness");            ToggleGroup group10 = new ToggleGroup(); RadioButton rb100 = new RadioButton("Yes"); RadioButton rb201 = new RadioButton("No"); rb100.setToggleGroup(group10); rb201.setToggleGroup(group10); 
        panelExamination.add(l1, 0, 1);     panelExamination.add(rb1, 1, 1); panelExamination.add(rb2, 2, 1);
        panelExamination.add(l2, 0, 2);    panelExamination.add(rb11, 1, 2); panelExamination.add(rb21, 2, 2);
        panelExamination.add(l3, 0, 3);       panelExamination.add(rb12, 1, 3); panelExamination.add(rb22, 2, 3);
        panelExamination.add(l4, 0, 4);      panelExamination.add(rb13, 1, 4); panelExamination.add(rb23, 2, 4);
        panelExamination.add(l5, 0, 5);     panelExamination.add(rb14, 1, 5); panelExamination.add(rb24, 2,5);
        panelExamination.add(l6, 0, 6);       panelExamination.add(rb15, 1, 6); panelExamination.add(rb25, 2, 6);
        panelExamination.add(l7, 0, 7);     panelExamination.add(rb16, 1, 7); panelExamination.add(rb26, 2, 7);
        panelExamination.add(l8, 0, 8);    panelExamination.add(rb17, 1, 8); panelExamination.add(rb27, 2, 8);
        panelExamination.add(l9, 0, 9);       panelExamination.add(rb18, 1, 9); panelExamination.add(rb28, 2, 9);
        panelExamination.add(ll10, 0, 10);      panelExamination.add(rb19, 1, 10); panelExamination.add(rb29, 2, 10);
        panelExamination.add(ll11, 0, 11);     panelExamination.add(rb100, 1, 11); panelExamination.add(rb201, 2,11);
        btnAdd.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               examinations.setSomeThingSet(true);
               examinations.setMuskoskel_cervicalspinetenderness(((RadioButton)group.getSelectedToggle()).getText());
               examinations.setMuskoskel_thoracicspinetenderness(((RadioButton)group1.getSelectedToggle()).getText());
               examinations.setMuskoskel_lumbosacralspinetenderness(((RadioButton)group2.getSelectedToggle()).getText());
               examinations.setMuskoskel_SIJtenderness(((RadioButton)group3.getSelectedToggle()).getText());
               examinations.setMuskoskel_Swollenlargejoints(((RadioButton)group4.getSelectedToggle()).getText());
               examinations.setMuskoskel_Tenderlargejoints(((RadioButton)group5.getSelectedToggle()).getText());
               examinations.setMuskoskel_Swollensmalljoints(((RadioButton)group6.getSelectedToggle()).getText());
               examinations.setMuskoskel_Tendersmalljoints(((RadioButton)group7.getSelectedToggle()).getText());
               examinations.setMuskoskel_Chestwallribtenderness(((RadioButton)group8.getSelectedToggle()).getText());
               examinations.setMuskoskel_Upperlimbtenderness(((RadioButton)group9.getSelectedToggle()).getText());
               examinations.setMuskoskel_Lowerlimbtenderness(((RadioButton)group10.getSelectedToggle()).getText());
               ExaminationTable examitemp = new ExaminationTable("Musculo-skeletal", examinations.getGeneral_height());
               loadToTable(examitemp);
            }
        });
    }
    
    
    public void loadToTable(ExaminationTable examitemp){
        try{
            listOfExaminationItems.add(examitemp);
            examName.setCellValueFactory(celldata->celldata.getValue().getExamName());
            examinationValue.setCellValueFactory(celldata->celldata.getValue().getExaminationValue());
            examninationTable.setItems(listOfExaminationItems);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    @FXML
    public void removeRow(){
        ExaminationTable examitemp = examninationTable.getSelectionModel().getSelectedItem();
        removeExaminationData(examitemp.getExamName().getValue());
        listOfExaminationItems.remove(examitemp);
        examninationTable.getItems().remove(examitemp);
        if(listOfExaminationItems.size()==0){
            examinations.setSomeThingSet(false);
        }
    }
    
    
    private void removeExaminationData(String selectedItem){
        clearExaminatioPanel();
        if(selectedItem.equals("General")){
            GeneralRemove();
        }else if(selectedItem.equals("CVS")){
            CVSRemove();
        }else if(selectedItem.equals("RS")){
            RSRemove();
        }else if(selectedItem.equals("Ear")){
            EarRemove();
        }else if(selectedItem.equals("Nose")){
            NoseRemove();
        }else if(selectedItem.equals("Throat")){
            ThroatRemove();
        }else if(selectedItem.equals("ABDOMEN")){
            ABDOMENRemove();
        }else if(selectedItem.equals("Male-genitalia")){
            MalegenitaliaRemove();
        }else if(selectedItem.equals("Female-genitalia")){
            FeMalegenitaliaRemove();
        }else if(selectedItem.equals("CNS")){
            CNSRemove();
        }else if(selectedItem.equals("Musculo-skeletal")){
           MusculoSkeletalRemove();
        }
    }

    private void GeneralRemove() {
        examinations.setGeneral_height("");
        examinations.setGeneral_weight("");
        examinations.setGeneral_Pallor("");
        examinations.setGeneral_Jaundice("");
        examinations.setGeneral_Thin("");
        examinations.setGeneral_Obese("");
        examinations.setGeneral_oedemaankle("");
        examinations.setGeneral_periorbital("");
        examinations.setGeneral_dehydration("");
        examinations.setGeneral_sob("");
    }

    private void CVSRemove() {
        examinations.setCvs_pulseBpm("");
        examinations.setCvs_regular("");
        examinations.setCvs_irregular("");
        examinations.setCvs_Bp("");
        examinations.setCvs_Heartdualrhythm("");
        examinations.setCvs_triplerhythm("");
        examinations.setCvs_murmurs("");
        examinations.setCvs_systolic("");
        examinations.setCvs_diastolic("");
    }

    private void RSRemove() {
        examinations.setRs_equalairentry("");
        examinations.setRs_reducedairentry("");
        examinations.setRs_vbs("");
        examinations.setRs_bbs("");
        examinations.setRs_creps("");
        examinations.setRs_rhonchi("");
    }

    private void EarRemove() {
        examinations.setEar_EarNormal("");
        examinations.setEar_Normalhearing("");
        examinations.setEar_Wax("");
        examinations.setEar_OE("");
        examinations.setEar_traumaticperforation("");
        examinations.setEar_AOM("");
        examinations.setEar_OME("");
        examinations.setEar_CSOM("");
        examinations.setEar_CP("");
        examinations.setEar_bleedingEar("");
        examinations.setEar_Eardischarge("");
        examinations.setEar_Granulationtissue("");
        examinations.setEar_EarPolyp("");
    }

    private void NoseRemove() {
        examinations.setNose_Patent("");
        examinations.setNose_blocked("");
        examinations.setNose_Senseofsmell("");
        examinations.setNose_Fb("");
        examinations.setNose_DeviatedSeptum("");
        examinations.setNose_BleedingS("");
        examinations.setNose_Secretions("");
        examinations.setNose_Polyp("");
        examinations.setNose_PND("");
    }

    private void ThroatRemove() {
        examinations.setThoat_Normal("");
        examinations.setThoat_Pharyngitis("");
        examinations.setThoat_Tonsillitis("");
        examinations.setThoat_NormalVC("");
        examinations.setThoat_Other("");
    }

    private void ABDOMENRemove() {
        examinations.setAmodoment_Distension("");
        examinations.setAmodoment_Tenderness("");
        examinations.setAmodoment_Liver("");
        examinations.setAmodoment_Spleen("");
        examinations.setAmodoment_Kidney("");
        examinations.setAmodoment_AbodometOther("");
        examinations.setAmodoment_hypogastricL("");
        examinations.setAmodoment_lumbarL("");
        examinations.setAmodoment_inguinalL("");
        examinations.setAmodoment_hypogastricR("");
        examinations.setAmodoment_lumbarR("");
        examinations.setAmodoment_inguinalR("");
        examinations.setAmodoment_siteepigastricC("");
        examinations.setAmodoment_umbilicalC("");
        examinations.setAmodoment_hypochondriacC("");
    }

    private void MalegenitaliaRemove() {
        examinations.setMale_Swelling("");
        examinations.setMale_MaleTenderness("");
        examinations.setMale_Hydrocele("");
        examinations.setMale_Balanitis("");
        examinations.setMale_Urethraldischarge("");
        examinations.setMale_fungalrash("");
        examinations.setMale_MaleOther("");
    }

    private void FeMalegenitaliaRemove() {
        examinations.setFemale_LMP1("");
        examinations.setFemale_LMP2("");
        examinations.setFemale_LMP3("");
        examinations.setFemale_BreastsL("");
        examinations.setFemale_BreastsTL("");
        examinations.setFemale_BreastsR("");
        examinations.setFemale_BreastsTR("");
        examinations.setFemale_NipplesL("");
        examinations.setFemale_NipplesTL("");
        examinations.setFemale_NipplesR("");
        examinations.setFemale_NipplesTR("");
        examinations.setFemale_Uterus("");
        examinations.setFemale_fungalRash("");
        examinations.setFemale_viginalDischagrg("");
        examinations.setFemale_Perineum("");
        examinations.setFemale_FemaleOther("");
    }

    private void CNSRemove() {
        examinations.setCns_Normalcranialnerves("");
        examinations.setCns_Normalcranialnerves("");
        examinations.setCns_Gaitnormal("");
        examinations.setCns_Gaitnormaltxt("");
        examinations.setCns_speechnormal("");
        examinations.setCns_speechnormaltxt("");
        examinations.setCns_Coordinationnormal("");
        examinations.setCns_Coordinationnormaltxt("");
        examinations.setCns_Powernormal("");
        examinations.setCns_Powernormaltxt("");
        examinations.setCns_Sensorynormal("");
        examinations.setCns_Sensorynormaltxt("");
    }

    private void MusculoSkeletalRemove() {
        examinations.setMuskoskel_cervicalspinetenderness("");
        examinations.setMuskoskel_thoracicspinetenderness("");
        examinations.setMuskoskel_lumbosacralspinetenderness("");
        examinations.setMuskoskel_SIJtenderness("");
        examinations.setMuskoskel_Swollenlargejoints("");
        examinations.setMuskoskel_Tenderlargejoints("");
        examinations.setMuskoskel_Swollensmalljoints("");
        examinations.setMuskoskel_Tendersmalljoints("");
        examinations.setMuskoskel_Chestwallribtenderness("");
        examinations.setMuskoskel_Upperlimbtenderness("");
        examinations.setMuskoskel_Lowerlimbtenderness("");
    }
}
