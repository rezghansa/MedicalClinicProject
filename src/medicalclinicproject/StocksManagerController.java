/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class StocksManagerController implements Initializable {

    @FXML
    private Tab tabOne;
    @FXML
    private Tab tabTwo;
    @FXML
    private Tab tabThree;
    @FXML
    private Tab tabFour;
    @FXML
    private AnchorPane sumryAnch;
    @FXML
    private AnchorPane grnAnchr;
    @FXML
    private AnchorPane invoiceAnch;
    @FXML
    private AnchorPane stockAnchor;
    @FXML
    private TextField txrPurchaseOrderID;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 

    
}
