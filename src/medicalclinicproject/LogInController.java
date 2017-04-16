/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hansa
 */
public class LogInController implements Initializable {

    @FXML
    private TextField txtusrNm;
    @FXML
    private PasswordField txtPsWrd;
    @FXML
    private Text lblMessage;
    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void LogInAction(){
        Logger.writeLog("log in started");
        String txtUsrNm = txtusrNm.getText();
        String txtPass  = txtPsWrd.getText();
        DbUtilClass dbutil = new DbUtilClass();
        ResultSet rs =DbUtilClass.readData("select * from medicalClinc.userLogin where uName ='"+txtUsrNm+"'");
        try{
        while(rs.next()){
         //Retrieve by column name
         String first = rs.getString("uName");
         String last = rs.getString("pasWrd");
         String userType = rs.getString("type");
         if(txtUsrNm.equals(first) && txtPass.equals(last)){
            closeLogin();
            if(userType.equalsIgnoreCase("admin")){
                loadMainMenu();
            }else if(userType.equalsIgnoreCase("stock")){
                loadStockMenu();
            }else if(userType.equalsIgnoreCase("physician")){
                loadPhysicianMenu();
            }            
         }else{
            lblMessage.setText("Incorrect User Name Password");
         }
        }
        }catch(Exception e){e.printStackTrace();}
        finally{
            try {
                rs.close();
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void loadMainMenu(){
        try{ 
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("MainPage.fxml"));
                BorderPane rootLayout = (BorderPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Main Menu");
                dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("dr_icon.png")));
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainApp.getPrimaryStage());
                Scene scene = new Scene(rootLayout);
                dialogStage.setMaximized(true);
                dialogStage.setScene(scene);
                MainPageController contro = loader.getController();
                contro.setMainApp(mainApp);
                contro.setRootLayout(rootLayout);
                dialogStage.showAndWait();
            }catch(Exception e){
            
            }
    }
    
    public void loadStockMenu(){
        try{ 
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("StockMainPage.fxml"));
                BorderPane rootLayout = (BorderPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Main Menu");
                dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("dr_icon.png")));
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainApp.getPrimaryStage());
                Scene scene = new Scene(rootLayout);
                dialogStage.setMaximized(true);
                dialogStage.setScene(scene);
                MainPageController contro = loader.getController();
                contro.setMainApp(mainApp);
                contro.setRootLayout(rootLayout);
                dialogStage.showAndWait();
            }catch(Exception e){
            
            }
    }
    
    public void loadPhysicianMenu(){
        try{ 
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("PhysicanMain.fxml"));
                BorderPane rootLayout = (BorderPane) loader.load();
                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Main Menu");
                dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("dr_icon.png")));
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainApp.getPrimaryStage());
                Scene scene = new Scene(rootLayout);
                dialogStage.setMaximized(true);
                dialogStage.setScene(scene);
                MainPageController contro = loader.getController();
                contro.setMainApp(mainApp);
                contro.setRootLayout(rootLayout);
                dialogStage.showAndWait();
            }catch(Exception e){
            
            }
    }
       
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void closeLogin() {
       Stage dialogStage = mainApp.getPrimaryStage();
       dialogStage.close();
    }
}
