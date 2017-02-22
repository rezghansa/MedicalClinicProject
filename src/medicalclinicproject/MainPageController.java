/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

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
        loadUsers();
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
}
