/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.universalclasses;

import upac.Apparitora;
import upac.helper.DatabaseManager;
import upac.helper.Login;
import upac.helper.UniversalMethod;

/**
 *
 * @author user
 */
public class ShowMessagesThread extends Thread implements Runnable{
    //DatabaseManager db;
    public ShowMessagesThread(){
       // db = new DatabaseManager();
    }
    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        try {
            UniversalMethod.alertNumber = Login.db.getNewAlert();
            if ( Apparitora.frame != null) {
                Apparitora.frame.getButton().setText( Apparitora.frame.getButton().getText() +" ("+ UniversalMethod.alertNumber +")");
            }
            System.out.println("New Messages : "+ UniversalMethod.alertNumber);
            Thread.sleep(5000);
            new ShowMessagesThread().start();
            
        } catch (Exception e) {
        }
       
    }
    
      
    
    
}
