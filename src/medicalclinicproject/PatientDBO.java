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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class PatientDBO {
    
    private IntegerProperty userId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty secondName;
    private StringProperty secondOName;
    private IntegerProperty age;
    private StringProperty telephone;
    private StringProperty gender;
    private ObjectProperty<LocalDate> birthday;
    private StringProperty allegy;
    private StringProperty sick;
    private StringProperty tretmnet;
    
      public PatientDBO(IntegerProperty userId,StringProperty firstName, StringProperty lastName, 
            StringProperty secondName, StringProperty secondOName, 
            IntegerProperty age, StringProperty telephone, 
            StringProperty gender, ObjectProperty<LocalDate> birthday,StringProperty allergy,
            StringProperty sick,StringProperty treatmen) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.secondOName = secondOName;
        this.age = age;
        this.telephone = telephone;
        this.gender = gender;
        this.birthday = birthday;
        this.userId = userId;
        this.allegy = allergy;
        this.sick = sick;
        this.tretmnet = treatmen;
    }
    
    public IntegerProperty getUserId() {
        return userId;
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
    
    public void setUserId(Integer ind){
         this.userId.set(ind);
     }
     public void setAllergy(String alergy){
         this.allegy.set(alergy);
     }
     public void setTretment(String tret){
         this.tretmnet.set(tret);
     }
     public void setSickness(String sick){
         this.sick.set(sick);
     }
     public void setFirstName(String firstName){
         this.firstName.set(firstName);
     }
     public void setLastName(String lastName){
         this.lastName.set(lastName);
     }
     public void setSecondName(String secondName){
         this.secondName.set(secondName);
     }
     public void setSecondOName(String secondNameO){
        this.secondOName.set(secondNameO);
     }
     public void setAge(Integer age){
         this.age.set(age);
     }
     public void setTelephone(String telephone){
         this.telephone.set(telephone);
     }
     public void setDateOfBirth(LocalDate dateOfBirth){
         this.birthday.set(dateOfBirth);
     }
     public void setGender(String gender){
         this.gender.set(gender);
     }
}
