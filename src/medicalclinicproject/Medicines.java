/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class Medicines {
    
    private final IntegerProperty medicineId;
    private final StringProperty medicineName;
    private final StringProperty medicinesGenericName;
    private final StringProperty medicinesCategory;
    private final StringProperty medicinesStrengthperUnit;
    private final IntegerProperty UgentCount;
    private final IntegerProperty LeasulyCount;
    private final IntegerProperty EarlyCount;
    private final IntegerProperty inStockCount;
    private final IntegerProperty eachTypeId;

    public Medicines(Integer medicineId, String medicineName, 
            String medicinesGenericName, String medicinesCategory, 
            String medicinesStrengthperUnit, Integer UgentCount, 
            Integer LeasulyCount, Integer EarlyCount, 
            Integer inStockCount, Integer eachTypeId) {
        this.medicineId = new SimpleIntegerProperty(medicineId);
        this.medicineName = new SimpleStringProperty(medicineName);
        this.medicinesGenericName = new SimpleStringProperty(medicinesGenericName);
        this.medicinesCategory = new SimpleStringProperty(medicinesCategory);
        this.medicinesStrengthperUnit = new SimpleStringProperty(medicinesStrengthperUnit);
        this.UgentCount = new SimpleIntegerProperty(UgentCount);
        this.LeasulyCount = new SimpleIntegerProperty(LeasulyCount);
        this.EarlyCount = new SimpleIntegerProperty(EarlyCount);
        this.inStockCount = new SimpleIntegerProperty(inStockCount);
        this.eachTypeId = new SimpleIntegerProperty(eachTypeId);
    }

    public IntegerProperty getMedicineId() {
        return medicineId;
    }

    public StringProperty getMedicineName() {
        return medicineName;
    }

    public StringProperty getMedicinesGenericName() {
        return medicinesGenericName;
    }

    public StringProperty getMedicinesCategory() {
        return medicinesCategory;
    }

    public StringProperty getMedicinesStrengthperUnit() {
        return medicinesStrengthperUnit;
    }

    public IntegerProperty getUgentCount() {
        return UgentCount;
    }

    public IntegerProperty getLeasulyCount() {
        return LeasulyCount;
    }

    public IntegerProperty getEarlyCount() {
        return EarlyCount;
    }

    public IntegerProperty getInStockCount() {
        return inStockCount;
    }

    public IntegerProperty getEachTypeId() {
        return eachTypeId;
    }
    
    
    
    
}
