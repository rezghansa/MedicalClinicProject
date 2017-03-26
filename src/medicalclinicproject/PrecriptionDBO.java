/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class PrecriptionDBO {
    
    private final ObjectProperty<LocalDate> prescribeDate;
    private final IntegerProperty paientId;
    private final StringProperty symptoms;
    private final StringProperty diffrentialD;
    private final StringProperty labsTxt;
    private final StringProperty phamacyTxt;
    private final StringProperty prescriptionTxt;
    private final DoubleProperty amount;
    private final ListProperty<Examinations> examination;
    

    public PrecriptionDBO() {
        this.prescribeDate = null;
        this.paientId = null;
        this.symptoms = null;
        this.diffrentialD = null;
        this.labsTxt = null;
        this.phamacyTxt = null;
        this.prescriptionTxt = null;
        this.amount = null;
        this.examination = null;
    }
    
    
    
}
