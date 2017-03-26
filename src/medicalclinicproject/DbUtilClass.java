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
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
   
   
   public static ObservableList<PatientDBO> convertoPatientList(ResultSet rs){
      
       ObservableList<PatientDBO> listOfPatients = null;
       try{
          listOfPatients = FXCollections.observableArrayList();
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
              String bloodGroup = rs.getString("bloodGroupType");
            PatientDBO tempPatient = new PatientDBO
            (userId,firstName,lastName,secondName,secondMidName,age,tele,gender,dbo,alle,sick,ter,bloodGroup);
            listOfPatients.add(tempPatient);     
        }
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return listOfPatients;
   }
   
   private static int calculateAge(String bdo){
   
       LocalDate today = LocalDate.now();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
       LocalDate dbDate = LocalDate.parse(bdo, formatter);
       Period p = Period.between(dbDate, today);
       return p.getYears();
   }
 
   public static ObservableList<Invoice> convertoInvoiceList(ResultSet rs){
      
       ObservableList<Invoice> listOfPatients = null;
       try{
          listOfPatients = FXCollections.observableArrayList();
        while(rs.next()){
              Integer idInvoice      = rs.getInt("idInvoice");
              String invoiceDate     = rs.getString("invoiceDate");
              Integer medicineId     = rs.getInt("medicineId");
              Double Qty             = rs.getDouble("Qty");
              Double eachPrice       = rs.getDouble("eachPrice");
              Double pricePaid       = rs.getDouble("pricePaid");
              Double discountedPrice = rs.getDouble("discountedPrice"); 
              Double discountedTotal = rs.getDouble("discountedTotal");
              Invoice invoiceTemp = new Invoice(invoiceDate,medicineId, Qty, 
                      eachPrice, pricePaid, discountedPrice,discountedTotal);
            listOfPatients.add(invoiceTemp);     
        }
       }catch(Exception e){
           e.printStackTrace();
       }
       return listOfPatients;
   }
   
   public static ObservableList<Medicines> loadMedicinesList(ResultSet rs){
      
       ObservableList<Medicines> listOfPatients = null;
       try{
          listOfPatients = FXCollections.observableArrayList();
        while(rs.next()){
              Integer id                      = rs.getInt("id");
              String medicineName             = rs.getString("medicineName");
              String medicinesGenericName     = rs.getString("medicinesGenericName");
              String medicinesCategory        = rs.getString("medicinesCategory");
              String medicinesStrengthperUnit = rs.getString("medicinesStrengthperUnit");
              Integer UgentCount              = rs.getInt("UgentCount");
              Integer LeasulyCount            = rs.getInt("LeasulyCount"); 
              Integer EarlyCount              = rs.getInt("EarlyCount");
              Integer inStockCount            = rs.getInt("inStockCount");
              Integer eachTypeId              = rs.getInt("eachTypeId");
              Medicines tempMedicine = new Medicines(id, medicineName, medicinesGenericName,
                      medicinesCategory, medicinesStrengthperUnit, UgentCount, LeasulyCount, 
                      EarlyCount, inStockCount, eachTypeId);
            listOfPatients.add(tempMedicine);     
        }
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return listOfPatients;
   }
   
   public static ObservableList<String> loadTypesofMedicines(){
         ObservableList<String> listOfPatients = null;
       try{
          listOfPatients = FXCollections.observableArrayList();
          ResultSet rs = DbUtilClass.readData("select typeName from typeOfMedines");
        while(rs.next()){
              String typeName     = rs.getString("typeName");
              listOfPatients.add(typeName);     
        }
       }catch(Exception e){
           e.printStackTrace();
       }
       return listOfPatients;
   }
}
