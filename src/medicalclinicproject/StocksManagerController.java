/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView<Invoice> tblViewMedicnesLoad;
    @FXML
    private TableColumn<Invoice, LocalDate> tblInvoiceDates;
    @FXML
    private TableColumn<Invoice, String> tblNameMedicine;
    @FXML
    private TableColumn<Invoice, Double> tblQtyofInvoice;
    @FXML
    private TextField txtItemName;
    @FXML
    private Button btnSearch;
    @FXML
    private PieChart pieChat;
    @FXML
    private BarChart<Invoice, Double> mediBar;
    @FXML
    private TableView<Invoice> sumaryTable;
    @FXML
    private TableColumn<Invoice, String> name;
    @FXML
    private TableColumn<Invoice, Double> availbleQty;
    @FXML
    private ComboBox<String> threhodLevel;
    @FXML
    private Button btnRemove;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeMedicines();
        TextFields.bindAutoCompletion(txtmedinceName, possibleSuggestions);
        TextFields.bindAutoCompletion(txtItemName, possibleSuggestions);
        ObservableList<String> data = FXCollections.observableArrayList("A+", "A-", "B+","B-", "AB+", "AB-","O+","O-");
        cmbQtryType.setItems(data);
        ObservableList<String> data2 = FXCollections.observableArrayList("All","High", "Medium", "Low");
        threhodLevel.setItems(data2);
        initSummaryTable();
    }    

    public StocksManagerController() {
        loadDataForSummary();
    }
 
    
    public void initSummaryTable(){
        name.setCellValueFactory(new PropertyValueFactory<Invoice, String>("medicineName"));
        availbleQty.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("stockQty"));
        sumaryTable.setItems(filteredData);
        threhodLevel.valueProperty().addListener(new ChangeListener<String>() {
        @Override 
            public void changed(ObservableValue ov, String t, String t1) {
                updateFilteredData();
            }    
        });
    }
    
    
    //kernal of adding Item Stock  Tab
    @FXML
    public void saveStock(){
        String mediceName= txtmedinceName.getText();
        double getQuty   = Double.parseDouble(txtQnty.getText());
        double eachPrice = Double.parseDouble(txteachPrice.getText());
        double pricePaid = Double.parseDouble(txtPaid.getText());
        String eachType  = (String)cmbQtryType.getSelectionModel().getSelectedItem();
        Invoice invoietemp =  new Invoice(mediceName, getQuty, eachPrice, pricePaid, eachType);
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
    
    @FXML
    public void removeRow(){
        Invoice invoice = tblStock.getSelectionModel().getSelectedItem();
        listOfItems.remove(invoice);
        totalValue = (totalValue-invoice.getPricePaid().doubleValue());
        lblBillTotalAmount.setText("LKR "+ totalValue);
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
    
    //kernal of Invoice Item Search Tabl
    @FXML
    public void searchMedinceInvoice(){
        loadMedicensToTable(txtItemName.getText());
    }
    
    private void loadMedicensToTable(String nameOfSearch){
        try{
            String nameSql = "";
            ObservableList<Invoice> listOfinvoices = 
                DbUtilClass.convertoInvoiceList(DbUtilClass.readData(nameSql));
            tblInvoiceDates.setCellValueFactory(celldata->celldata.getValue().getInvoiceDate());
            tblNameMedicine.setCellValueFactory(celldata->celldata.getValue().getMediceName());
            tblQtyofInvoice.setCellValueFactory(celldata->celldata.getValue().getStkQty().asObject());
            tblViewMedicnesLoad.setItems(listOfinvoices);
        }catch(Exception e){e.printStackTrace();}
    }
    
    private ObservableList<Invoice> masterData = FXCollections.observableArrayList();
    private ObservableList<Invoice> filteredData = FXCollections.observableArrayList();
    
    private void loadDataForSummary(){
        try{
            // Initially add all data to filtered data
            filteredData.addAll(masterData);
            // Listen for changes in master data.
            // Whenever the master data changes we must also update the filtered data.
            masterData.addListener(new ListChangeListener<Invoice>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Invoice> change) {
                updateFilteredData();
            }
        });
        }catch(Exception e){e.printStackTrace();}
    }
    
      /**
     * Updates the filteredData to contain all data from the masterData that
     * matches the current filter.
     */
    private void updateFilteredData() {
        filteredData.clear();

        for (Invoice p : masterData) {
            if (matchesFilter(p)) {
                filteredData.add(p);
            }
        }

        // Must re-sort table after items changed
        reapplyTableSortOrder();
    }
    
     private boolean matchesFilter(Invoice invoice) {
        String filterString = threhodLevel.getSelectionModel().getSelectedItem();
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();
        
        if (invoice.getMediceName().toString().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
           return true;
        }
        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Invoice, ?>> sortOrder = new ArrayList<>(sumaryTable.getSortOrder());
        sumaryTable.getSortOrder().clear();
        sumaryTable.getSortOrder().addAll(sortOrder);
    }
}
