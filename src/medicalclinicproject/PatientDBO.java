/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class PatientDBO {
    
    private final IntegerProperty userId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty secondName;
    private final StringProperty secondOName;
    private final IntegerProperty age;
    private final StringProperty telephone;
    private final StringProperty gender;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty allegy;
    private final StringProperty sick;
    private final StringProperty tretmnet;
    
      public PatientDBO(Integer userId,String firstName, String lastName, 
            String secondName, String secondOName, 
            Integer age, String telephone, 
            String gender, String birthday,String allergy,
            String sick,String treatmen) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.secondName = new SimpleStringProperty(secondName);
        this.secondOName = new SimpleStringProperty(secondOName);
        this.age = new SimpleIntegerProperty(age);
        this.telephone = new SimpleStringProperty(telephone);
        this.gender = new SimpleStringProperty(gender);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dbDate = LocalDate.parse(birthday, formatter);
        this.birthday = new SimpleObjectProperty<LocalDate>(dbDate);
        this.userId = new SimpleIntegerProperty(userId);
        this.allegy = new SimpleStringProperty(allergy);
        this.sick = new SimpleStringProperty(sick);
        this.tretmnet = new SimpleStringProperty(treatmen);
    }

    public IntegerProperty getUserId() {
        return userId;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public StringProperty getLastName() {
        return lastName;
    }

    public StringProperty getSecondName() {
        return secondName;
    }

    public StringProperty getSecondOName() {
        return secondOName;
    }

    public IntegerProperty getAge() {
        return age;
    }

    public StringProperty getTelephone() {
        return telephone;
    }

    public StringProperty getGender() {
        return gender;
    }

    public ObjectProperty<LocalDate> getBirthday() {
        return birthday;
    }

    public StringProperty getAllegy() {
        return allegy;
    }

    public StringProperty getSick() {
        return sick;
    }

    public StringProperty getTretmnet() {
        return tretmnet;
    }
    
}
