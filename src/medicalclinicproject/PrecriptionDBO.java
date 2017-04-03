/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hansa
 */
public class PrecriptionDBO {
    
    private  LocalDate prescribeDate;
    private  int paientId;
    private  String symptoms;
    private  String diffrentialD;
    private  String labsTxt;
    private  String phamacyTxt;
    private  String prescriptionTxt;
    private  String amount;
    private  Examinations examination;
    private  List<PrescriptionTableData> dataFile;
    private  int examinationId;

    @Override
    public String toString() {
        return "PrecriptionDBO Values{" + "prescribeDate=" + prescribeDate + ", paientId=" + paientId + ", symptoms=" + symptoms + ", diffrentialD=" + diffrentialD + ", labsTxt=" + labsTxt + ", phamacyTxt=" + phamacyTxt + ", prescriptionTxt=" + prescriptionTxt + ", amount=" + amount + ", examination=" + examination + '}';
    }

    public List<PrescriptionTableData> getDataFile() {
        return dataFile;
    }

    public int getExaminationId() {
        return examinationId;
    }
    
    
    public void addPrescrptionTableData(PrescriptionTableData temp){
        dataFile.add(temp);
    }

    public PrecriptionDBO() {
       dataFile = new ArrayList<PrescriptionTableData>();
    }

    public LocalDate getPrescribeDate() {
        return prescribeDate;
    }

    public void setPrescribeDate(LocalDate prescribeDate) {
        this.prescribeDate = prescribeDate;
    }

    public int getPaientId() {
        return paientId;
    }

    public void setPaientId(int paientId) {
        this.paientId = paientId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiffrentialD() {
        return diffrentialD;
    }

    public void setDiffrentialD(String diffrentialD) {
        this.diffrentialD = diffrentialD;
    }

    public String getLabsTxt() {
        return labsTxt;
    }

    public void setLabsTxt(String labsTxt) {
        this.labsTxt = labsTxt;
    }

    public String getPhamacyTxt() {
        return phamacyTxt;
    }

    public void setPhamacyTxt(String phamacyTxt) {
        this.phamacyTxt = phamacyTxt;
    }

    public String getPrescriptionTxt() {
        return prescriptionTxt;
    }

    public void setPrescriptionTxt(String prescriptionTxt) {
        this.prescriptionTxt = prescriptionTxt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Examinations getExamination() {
        return examination;
    }

    public void setExamination(Examinations examination) {
        this.examination = examination;
    }

    public void setDataFile(List<PrescriptionTableData> dataFile) {
        this.dataFile = dataFile;
    }
      
}
