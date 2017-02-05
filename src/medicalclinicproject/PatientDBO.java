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
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class PatientDBO {
    
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty secondName;
    private final StringProperty secondOName;
    private final IntegerProperty age;
    private final IntegerProperty telephone;
    private final StringProperty gender;
    private final ObjectProperty<LocalDate> birthday;

    public PatientDBO(StringProperty firstName, StringProperty lastName, 
            StringProperty secondName, StringProperty secondOName, 
            IntegerProperty age, IntegerProperty telephone, 
            StringProperty gender, ObjectProperty<LocalDate> birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.secondOName = secondOName;
        this.age = age;
        this.telephone = telephone;
        this.gender = gender;
        this.birthday = birthday;
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

    public IntegerProperty getTelephone() {
        return telephone;
    }

    public StringProperty getGender() {
        return gender;
    }

    public ObjectProperty<LocalDate> getBirthday() {
        return birthday;
    }
    
     public PatientDBO(String firstName, String lastName, 
            String secondName, String secondOName, 
            Integer age, Integer telephone, 
            String gender, String birthday) {
        this.firstName = null;
        this.lastName = null;
        this.secondName = null;
        this.secondOName = null;
        this.age = null;
        this.telephone = null;
        this.gender = null;
        this.birthday = null;
        setFirstName(firstName);
        setLastName(lastName);
        setSecondName(secondName);
        setSecondOName(secondName);
        setAge(age);
        setGender(gender);
        setTelephone(telephone);
        LocalDate myDate = LocalDate.parse(birthday);
        setDateOfBirth(myDate);
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
     public void setTelephone(Integer telephone){
         this.telephone.set(telephone);
     }
     public void setDateOfBirth(LocalDate dateOfBirth){
         this.birthday.set(dateOfBirth);
     }
     public void setGender(String gender){
         this.gender.set(gender);
     }
}
