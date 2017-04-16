/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class Expenses {
    
    private final IntegerProperty expenseId;
    private final ObjectProperty<LocalDate> expenseDate;
    private final StringProperty expenseName;
    private final DoubleProperty expenseAmount;
    private final StringProperty expenseDescription;

    public Expenses(Integer expenseId, String expenseDate,
            String expenseName, Double expenseAmount, 
            String expenseDescription) {
        this.expenseId = new SimpleIntegerProperty(expenseId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dbDate = LocalDate.parse(expenseDate, formatter);
        this.expenseDate =  new SimpleObjectProperty<LocalDate>(dbDate);
        this.expenseName = new SimpleStringProperty(expenseName);
        this.expenseAmount = new SimpleDoubleProperty(expenseAmount);
        this.expenseDescription = new SimpleStringProperty(expenseDescription);
    }

    public IntegerProperty getExpenseId() {
        return expenseId;
    }

    public ObjectProperty<LocalDate> getExpenseDate() {
        return expenseDate;
    }

    public StringProperty getExpenseName() {
        return expenseName;
    }

    public DoubleProperty getExpenseAmount() {
        return expenseAmount;
    }

    public StringProperty getExpenseDescription() {
        return expenseDescription;
    }
     
        
}
