/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upac.universalclasses;

import java.lang.reflect.Method;
import upac.helper.DatabaseManager;

/**
 *
 * @author user
 */
public class UniversalThread extends Thread implements Runnable{
    public static DatabaseManager db;
    private String methodName;
    private Object[] objects;
    public UniversalThread() {
        this.db = new DatabaseManager();
        
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        //db.methodName();
//        try {
//            Method[] method = db.getClass().getDeclaredMethods();
//            for (int i = 0; i < method.length; i++) {
//                if (method[i].getName().equals(methodName)) {   
//                    
//                    System.out.println("Return Type is : "+ method[i].getReturnType());
//                    method[i].invoke(db, objects);
//                     //= method[i].get;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error Message : "+ e.getMessage());
//        }        
        
    }
    
    
}
