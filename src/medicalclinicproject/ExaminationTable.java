/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author HL30407
 */
public class ExaminationTable {
   
    private final StringProperty examName;
    private final StringProperty examinationValue;

    public ExaminationTable(String examName, String examinationValue) {
        this.examName = new SimpleStringProperty(examName);
        this.examinationValue = new SimpleStringProperty(examinationValue);
    }

    public StringProperty getExamName() {
        return examName;
    }

    public StringProperty getExaminationValue() {
        return examinationValue;
    }
    
}
