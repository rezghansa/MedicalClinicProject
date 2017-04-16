/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HL30407
 */
public class FinanceDataController implements Initializable {

    @FXML
    private ComboBox<String> cmbReportName;
    @FXML
    private Button btnView;
    @FXML
    private DatePicker expenseDate;
    @FXML
    private TextField expenseAmount;
    @FXML
    private ComboBox<String> cmbExpenseType;
    @FXML
    private Button btnAddExpense;
    @FXML
    private TableView<Expenses> tableExpense;
    @FXML
    private TableColumn<Expenses, String> expenseName;
    @FXML
    private TableColumn<Expenses, String> expenseDescription;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnRemoveExpense;
    @FXML
    private TextField txtExpenseName;
    @FXML
    private TextField txtexpenseDescription;
    @FXML
    private TableColumn<Expenses, Double> expenseTableAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> data = FXCollections.observableArrayList("ASSETS","LIABILITIES", "REVENUES", "EXPENSES");
        cmbExpenseType.setItems(data);
    }    
    
}
