/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.ImageIcon;

/**
 *
 * @author hansa
 */
public class MainApp extends Application {
    
    private AnchorPane rootLayout;
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try { 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("LogIn.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("dr_icon.png")));
            this.primaryStage.setMaximized(true);
            this.primaryStage.setTitle("Medical World");
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            
            LogInController loadConroler = loader.getController();
            loadConroler.setMainApp(this);
                    
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
}
