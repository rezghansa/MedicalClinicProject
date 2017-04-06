/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Invoice {
    
     private final ObjectProperty<LocalDate> invoiceDate;
     private final StringProperty mediceName;
     private final DoubleProperty getQuty;
     private final DoubleProperty eachPrice;
     private final DoubleProperty pricePaid;
     private final StringProperty eachType;
     private final DoubleProperty discountedPrice;
     private final DoubleProperty disountedTotal;
     private final DoubleProperty stkQty;
     private final DoubleProperty inStkQty;
     private final IntegerProperty medicineId;
     private final IntegerProperty eachTypeId;

    public Invoice(String mediceName, 
            Double getQuty, Double eachPrice, 
            Double pricePaid, String eachType) {
        LocalDate dbDate = LocalDate.now();
        this.invoiceDate = new SimpleObjectProperty<LocalDate>(dbDate);
        this.mediceName = new SimpleStringProperty(mediceName);
        this.getQuty = new SimpleDoubleProperty(getQuty);
        this.eachPrice = new SimpleDoubleProperty(eachPrice);
        this.pricePaid = new SimpleDoubleProperty(pricePaid);
        this.eachType = new SimpleStringProperty(eachType);
        this.discountedPrice = new SimpleDoubleProperty((pricePaid / getQuty));
        this.disountedTotal = new SimpleDoubleProperty(((getQuty*eachPrice)-pricePaid));
        this.medicineId = new SimpleIntegerProperty(getMediceineIdByName());
        this.eachTypeId = new SimpleIntegerProperty(0);
        this.stkQty = new SimpleDoubleProperty(getStockQty());
        this.inStkQty = new SimpleDoubleProperty((getStockQty()+getQuty));
    }
    
    public Invoice(String invoiceDate, Integer medicineId, Double Qty, Double eachPrice, 
            Double pricePaid, Double discountedPrice, Double discountedTotal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dbDate = LocalDate.parse(invoiceDate, formatter);
        this.invoiceDate = new SimpleObjectProperty<LocalDate>(dbDate);
        this.getQuty = new SimpleDoubleProperty(Qty);
        this.eachPrice = new SimpleDoubleProperty(eachPrice);
        this.pricePaid = new SimpleDoubleProperty(pricePaid);
        this.discountedPrice = new SimpleDoubleProperty(discountedPrice);
        this.disountedTotal = new SimpleDoubleProperty(discountedTotal);
        this.medicineId = new SimpleIntegerProperty(medicineId);
        this.eachTypeId = new SimpleIntegerProperty(0);
        this.mediceName = new SimpleStringProperty("");
        this.eachType = new SimpleStringProperty("");
        this.stkQty = new SimpleDoubleProperty(0.0);
        this.inStkQty = new SimpleDoubleProperty(0.0);
    }
    
    
    public Invoice(String invoiceDate, Integer medicineId, Double Qty, Double eachPrice, 
            Double pricePaid, Double discountedPrice, Double discountedTotal,String medicineName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dbDate = LocalDate.parse(invoiceDate, formatter);
        this.invoiceDate = new SimpleObjectProperty<LocalDate>(dbDate);
        this.getQuty = new SimpleDoubleProperty(Qty);
        this.eachPrice = new SimpleDoubleProperty(eachPrice);
        this.pricePaid = new SimpleDoubleProperty(pricePaid);
        this.discountedPrice = new SimpleDoubleProperty(discountedPrice);
        this.disountedTotal = new SimpleDoubleProperty(discountedTotal);
        this.medicineId = new SimpleIntegerProperty(medicineId);
        this.eachTypeId = new SimpleIntegerProperty(0);
        this.mediceName = new SimpleStringProperty(medicineName);
        this.eachType = new SimpleStringProperty("");
        this.stkQty = new SimpleDoubleProperty(0.0);
        this.inStkQty = new SimpleDoubleProperty(0.0);
    }
    
    public void setMedicineName(){
        this.mediceName.set(getMediceinName());
    }
    
    private String getMediceinName(){
        String medicineName = null;
        ResultSet rs = DbUtilClass.readData("select medicineName from medicines where id ="+this.medicineId.getValue().intValue());
        try {
             while(rs.next()){
                 medicineName = rs.getString("medicineName");
             }
        } catch (SQLException ex) {
             Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return medicineName;
    }
    
    private String getEachTypefromId(){
        String eachName = null;
        ResultSet rs = DbUtilClass.readData("select typeName from typeofmedines where typeId ="+this.eachTypeId.getValue().intValue());
        try {
             while(rs.next()){
                 eachName = rs.getString("typeName");
             }
        } catch (SQLException ex) {
             Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return eachName;
    }
    
    private Integer getMediceineIdByName(){
        Integer medicineId = null;
        ResultSet rs = DbUtilClass.readData("select id from medicines where medicineName = '"+this.mediceName.getValue() +"'");
        try {
             while(rs.next()){
                 medicineId = rs.getInt("id");
             }
        } catch (SQLException ex) {
             Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return medicineId;
    }
    
    private Integer getEachTypeIdByName(){
        Integer eachTypeId = null;
        ResultSet rs = DbUtilClass.readData("select typeId from medicines where typeName = '"+this.eachType.getValue() +"'");
        try {
             while(rs.next()){
                 eachTypeId = rs.getInt("typeId");
             }
        } catch (SQLException ex) {
             Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return eachTypeId;
    }
    
    private Double getStockQty(){
        Double eachTypeId = null;
        ResultSet rs = null;
        try {
             rs = DbUtilClass.readData("select inStockCount from medicines where id ="+this.medicineId.getValue().intValue());;
             while(rs.next()){
                 eachTypeId = rs.getDouble("inStockCount");
             }
        } catch (SQLException ex) {
             Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return eachTypeId; 
    }
    
    public IntegerProperty getMedicineId() {
        return medicineId;
    }

    public IntegerProperty getEachTypeId() {
        return eachTypeId;
    }

    
    public ObjectProperty<LocalDate> getInvoiceDate() {
        return invoiceDate;
    }

    public StringProperty getMediceName() {
        return mediceName;
    }

    public DoubleProperty getGetQuty() {
        return getQuty;
    }

    public DoubleProperty getEachPrice() {
        return eachPrice;
    }

    public DoubleProperty getPricePaid() {
        return pricePaid;
    }

    public StringProperty getEachType() {
        return eachType;
    }

    public DoubleProperty getDiscountedPrice() {
        return discountedPrice;
    }

    public DoubleProperty getDisountedTotal() {
        return disountedTotal;
    }

    public DoubleProperty getStkQty() {
        return stkQty;
    }

    public DoubleProperty getInStkQty() {
        return inStkQty;
    }
     
    
}
