/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hansa
 */
public class DbUtilClass {
    
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/medicalClinc";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "1234";
   
   private static Connection conn = null;
   private static Statement stmt = null;

    public DbUtilClass() {
        try{
           //STEP 2: Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           //STEP 3: Open a connection
           System.out.println("Connecting to database...");
           conn = DriverManager.getConnection(DB_URL,USER,PASS);

           //STEP 4: Execute a query
           System.out.println("Creating statement...");
           stmt = conn.createStatement(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
   
   
   public static ResultSet readData(String sql){
       try {
           ResultSet rs = stmt.executeQuery(sql);
           return rs;
       } catch (Exception ex) {
           ex.printStackTrace();
       }
        return null;
   }
   
   public static boolean insertion(String sql){
       try {
        stmt.executeUpdate(sql);
        return true;
        } catch (Exception ex) {
           ex.printStackTrace();
           return false;
       }

   }
   
   
   public static ArrayList<PatientDBO> convertoPatientList(ResultSet rs){
       ArrayList<PatientDBO> listOfPatients = null;
       try{
          listOfPatients = new ArrayList<PatientDBO>(rs.getFetchSize());
        while(rs.next()){
              Integer userId =      rs.getInt("userId");
              String firstName =      rs.getString("firstName");
              String lastName =      rs.getString("lastName");
              String secondName =      rs.getString("secondName");
              String secondMidName =      rs.getString("secondNameMidle");
              Integer age =       calculateAge(rs.getString("dateofBirth"));
              String tele =      rs.getString("telephone"); 
              String gender =      rs.getString("gender");
              String dbo =      rs.getString("dateofBirth");
              String alle =      rs.getString("allergy");
              String sick =      rs.getString("majourSicknes");
              String ter =      rs.getString("tretments");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dbDate = LocalDate.parse(dbo, formatter);
            PatientDBO tempPatient = new PatientDBO(
            new SimpleIntegerProperty(userId),
            new SimpleStringProperty(firstName),
            new SimpleStringProperty(lastName),
            new SimpleStringProperty(secondName),
            new SimpleStringProperty(secondMidName),
            new SimpleIntegerProperty(age),
            new SimpleStringProperty(tele),
            new SimpleStringProperty(gender),
            new SimpleObjectProperty<LocalDate>(dbDate),
            new SimpleStringProperty(alle),
            new SimpleStringProperty(sick),
            new SimpleStringProperty(ter));
            listOfPatients.add(tempPatient);     
        }
       }catch(Exception e){
           e.printStackTrace();
       }
       return listOfPatients;
   }
   
   private static int calculateAge(String bdo){
   
       return 0;
   }
    
}
