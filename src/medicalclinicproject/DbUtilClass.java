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
import java.util.ArrayList;
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
   
   public static void insertion(String sql){
       try {
        stmt.executeUpdate(sql);
        } catch (Exception ex) {
           ex.printStackTrace();
       }

   }
   
   
   public static ArrayList<PatientDBO> convertoPatientList(ResultSet rs){
       ArrayList<PatientDBO> listOfPatients = null;
       try{
          listOfPatients = new ArrayList<PatientDBO>(rs.getFetchSize());
        while(rs.next()){
            PatientDBO tempPatient = new PatientDBO(
                    rs.getString("fName"), 
                    rs.getString("lName"),
                    rs.getString("secondName"), 
                    rs.getString("secondOnName"),
                    rs.getInt("age"), 
                    rs.getInt("telephone"), 
                    rs.getString("gender"), 
                    rs.getString("dateofBirth"));
            listOfPatients.add(tempPatient);     
        }
       }catch(Exception e){  
       }
       return listOfPatients;
   }
    
}
