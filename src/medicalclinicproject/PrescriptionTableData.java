/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HL30407
 */
public class PrescriptionTableData {
    
    private final StringProperty medicineNameTxt;
    private final StringProperty dosageTxt;
    private final DoubleProperty numberOfDayTxt;
    private final DoubleProperty totalTxt;

    public PrescriptionTableData(String medicineNameTxt, 
            String dosageTxt, Double numberOfDayTxt, Double totalTxt) {
        this.medicineNameTxt = new SimpleStringProperty(medicineNameTxt);
        this.dosageTxt = new SimpleStringProperty(dosageTxt);
        this.numberOfDayTxt =  new SimpleDoubleProperty(numberOfDayTxt);
        this.totalTxt =  new SimpleDoubleProperty(totalTxt);
    }

    public StringProperty getMedicineNameTxt() {
        return medicineNameTxt;
    }

    public StringProperty getDosageTxt() {
        return dosageTxt;
    }

    public DoubleProperty getNumberOfDayTxt() {
        return numberOfDayTxt;
    }

    public DoubleProperty getTotalTxt() {
        return totalTxt;
    }
    
}
