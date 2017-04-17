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
    
    private static ObservableList<String> listOfMedicines;
    
    public static ObservableList<String> getMedicinesNames(String queryName){
        
        String query = "select medicineName from medicines where medicineName like('%"+queryName+"%')";
        listOfMedicines = DbUtilClass.loadMedicineName(DbUtilClass.readData(query));
        return listOfMedicines;
    }
    
}
