/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalclinicproject;

import javafx.print.PrinterJob;
import javafx.scene.Node;

/**
 *
 * @author HL30407
 */
public class PrintUtil {

    public PrintUtil() {
    }
    
    public void printPage(Node node){
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
           boolean success = job.printPage(node);
           if (success) {
               job.endJob();
           }
        }
    }
}
