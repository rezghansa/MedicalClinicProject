/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
        this.stkQty = new SimpleDoubleProperty(100);
        this.inStkQty = new SimpleDoubleProperty((100+getQuty));
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
