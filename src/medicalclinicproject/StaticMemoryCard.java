/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import javafx.collections.ObservableList;

/**
 *
 * @author hansa
 */
public class StaticMemoryCard {
       
    public static ObservableList<String> getMedicinesNames(String queryName){
        ObservableList<String> listOfMedicines;
        String query = "select medicineName from medicines where medicineName like('%"+queryName+"%')";
        listOfMedicines = DbUtilClass.loadMedicineName(DbUtilClass.readData(query));
        return listOfMedicines;
    }
    
    public static Medicines getMedicines(String queryName){
        Medicines medicine;
        String query = "select * from medicines where medicineName ='"+queryName+"'";
        medicine = DbUtilClass.loadMedicine(DbUtilClass.readData(query));
        return medicine;
    }
}
