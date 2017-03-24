/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class StocksManagerController implements Initializable {

    @FXML
    private Tab tabOne;
    @FXML
    private Tab tabThree;
    @FXML
    private Tab tabFour;
    @FXML
    private AnchorPane sumryAnch;
    @FXML
    private AnchorPane invoiceAnch;
    @FXML
    private AnchorPane stockAnchor;
    @FXML
    private TextField txtmedinceName;
    @FXML
    private TextField txtQnty;
    @FXML
    private TextField txteachPrice;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnsave;
    @FXML
    private ComboBox<String> cmbQtryType;
    @FXML
    private TextField txtPaid;
    
    private String[]  possibleSuggestions;
    @FXML
    private TableView<Invoice> tblStock;
    @FXML
    private TableColumn<Invoice, LocalDate> tblDate;
    @FXML
    private TableColumn<Invoice, String> tblMedName;
    @FXML
    private TableColumn<Invoice, Double> tblPurchQty;
    @FXML
    private TableColumn<Invoice, Double> tblPrice;
    @FXML
    private TableColumn<Invoice, Double> tblDiscountedPrice;
    @FXML
    private TableColumn<Invoice, Double> tblDiscountedTotal;
    @FXML
    private TableColumn<Invoice, Double> tblTotal;
    
    ObservableList<Invoice> listOfItems = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Invoice, Double> tblStockQty;
    @FXML
    private TableColumn<Invoice, Double> tblInStk;
    @FXML
    private Label lblBillTotalAmount;

    private double totalValue = 0.0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeMedicines();
        TextFields.bindAutoCompletion(txtmedinceName, possibleSuggestions);
    }    
 
    //kernal of adding Item Stock  Tab
    @FXML
    public void saveStock(){
        String mediceName= txtmedinceName.getText();
        double getQuty   = Double.parseDouble(txtQnty.getText());
        double eachPrice = Double.parseDouble(txteachPrice.getText());
        double pricePaid = Double.parseDouble(txtPaid.getText());
        String eachType  = (String)cmbQtryType.getSelectionModel().getSelectedItem();
        Invoice invoietemp =  new Invoice(mediceName, getQuty, eachPrice, pricePaid, eachPrice);
        loadToTable(invoietemp);
        resetComponent();
        totalValue +=pricePaid;
        lblBillTotalAmount.setText("LKR "+totalValue);
    }
    
    
    @FXML
    public void saveInvoice(){
        for(Invoice invoice:listOfItems){
            String sql = "";
            DbUtilClass.insertion(sql);
        }
    }
    
    public void removeRow(){
        Invoice invoice = tblStock.getSelectionModel().getSelectedItem();
        listOfItems.remove(invoice);
        tblStock.getItems().remove(invoice);
    }
    
    private void resetComponent(){
        txtmedinceName.clear();
        txtPaid.clear();
        txtQnty.clear();
        txteachPrice.clear();
    }
    
    
    
    public void loadToTable(Invoice invoice){
        try{
            listOfItems.add(invoice);
            tblDate.setCellValueFactory(celldata->celldata.getValue().getInvoiceDate());
            tblMedName.setCellValueFactory(celldata->celldata.getValue().getMediceName());
            tblPurchQty.setCellValueFactory(celldata->celldata.getValue().getGetQuty().asObject());
            tblStockQty.setCellValueFactory(celldata->celldata.getValue().getStkQty().asObject());
            tblPrice.setCellValueFactory(celldata->celldata.getValue().getEachPrice().asObject());
            tblDiscountedPrice.setCellValueFactory(celldata->celldata.getValue().getDiscountedPrice().asObject());
            tblDiscountedTotal.setCellValueFactory(celldata->celldata.getValue().getDisountedTotal().asObject());
            tblTotal.setCellValueFactory(celldata->celldata.getValue().getPricePaid().asObject());
            tblInStk.setCellValueFactory(celldata->celldata.getValue().getInStkQty().asObject());
            tblStock.setItems(listOfItems);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void initializeMedicines(){
        String []p = {"paracetamol","panadol","alfa"};
        possibleSuggestions = p;
    }
    
}
