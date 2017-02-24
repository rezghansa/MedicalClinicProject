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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class PrescrptionsController implements Initializable {

    @FXML
    private TextArea txtSymp;
    
    private MainPageController mainPageController;
    
    private PatientDBO selcedtPatien = null;
    
    private boolean okClicked = false;

    public TextArea getTxtSymp() {
        return txtSymp;
    }

    public MainPageController getMainPageController() {
        return mainPageController;
    }

    public PatientDBO getSelcedtPatien() {
        return selcedtPatien;
    }

    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void setSelectedPatient(PatientDBO obj){
        this.selcedtPatien = obj;
    }
            
    public void setMainPageController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    public void setTxtSymp(TextArea txtSymp) {
        this.txtSymp = txtSymp;
    }

    public void setSelcedtPatien(PatientDBO selcedtPatien) {
        this.selcedtPatien = selcedtPatien;
    }

    public void setOkClicked(boolean okClicked) {
        this.okClicked = okClicked;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          if(selcedtPatien != null){
              showPersonDetails(selcedtPatien);
          }
    }    
    
    public void showPersonDetails(PatientDBO person) {
            // Fill the labels with info from the person object.
            txtSymp.setText(person.getFirstName().getValue());
    }
}
