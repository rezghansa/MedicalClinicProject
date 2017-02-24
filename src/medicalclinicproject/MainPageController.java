/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class MainPageController implements Initializable {

    @FXML
    private Button btnUserPage;
    @FXML
    private Button btnPrescrptionPage;
    @FXML
    private Button btnReportPage;
    @FXML
    private Button btnstockPage;
    @FXML
    private Button btnvasPage;
    @FXML
    private AnchorPane middleAnch;
    
    private BorderPane rootLayout;
    
    // Reference to the main application.
    private MainApp mainApp;
    
    private PatientDBO selectedPatient;
    
    public void setSelectedPatent(PatientDBO select){
        this.selectedPatient = select;
    }
    
    public PatientDBO getSelectedPatent(){
        if(selectedPatient != null)
            return selectedPatient;
        else
            return null;
    }
    
    public void setRootLayout(BorderPane rootLayout){
        this.rootLayout = rootLayout;
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initRootLayout();
    }    
    
    
    public void initRootLayout(){
        try{                 
                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                this.mainApp.getPrimaryStage().setScene(scene);
                this.mainApp.getPrimaryStage().setMaximized(true);
                this.mainApp.getPrimaryStage().show();
        }catch(Exception e){
        }
    }
    
    @FXML
    public void loadUserPage(){
       loadUsers();
    }
    
    @FXML
    public void loadPrescrtionPage(){
        pationSearch();        
    }
    
    @FXML
    public void loadReportPage(){
        loadReports();
    }
    
    @FXML
    public void loadStockPage(){
        loadStocksSection();
    }
    
    @FXML
    public void loadVASPage(){
        loadLetters();
    }
    
    public void startPresceiption(PatientDBO selectedPatien){
        loadPrescrtion();
    }
    
    public void editPatient(PatientDBO selectedPatien){
            this.selectedPatient = selectedPatien;
            loadCenterFXML("UserSave.fxml");
    }
    
    private void loadStocksSection(){
        loadCenterFXML("StocksManager.fxml");
    }
    
    private void loadUsers(){
        loadCenterFXML("UserSave.fxml");  
    }
    
    private void loadLetters(){
       loadCenterFXML("Letters.fxml");           
    }    
    private void pationSearch(){
        loadCenterFXML("PatianSearch.fxml");
        PatianSearchController p = loader.getController();
        p.setMainPageController(this);
    }
    
    private void loadPrescrtion(){
        loadCenterFXML("Prescrptions.fxml");
    }
    
    private void loadReports(){
        loadCenterFXML("Reports.fxml");
    }
    
    private FXMLLoader loader;
    private void loadCenterFXML(String fxmlName){
        try{ 
            loader = new FXMLLoader();
            loader.setLocation(MainPageController.class.getResource(fxmlName));
            middleAnch = (AnchorPane) loader.load();  
            rootLayout.setCenter(middleAnch);
        }catch(IOException e){
        }  
    } 
    
    public boolean showPersonEditDialog() {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("editPatent.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.mainApp.getPrimaryStage());
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        UserSaveController controller = loader.getController();
        controller.setSelectedPatient(this.selectedPatient);
        controller.showPersonDetails(selectedPatient);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
    
    
    public boolean showPersonPrescriptionDialog() {
        loadPrescrtion();
        return false;
    }
    
}
