/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.ui.FlooringMasteryView;
import com.sg.flooringmastery.ui.UserIO;
import com.sg.flooringmastery.ui.UserIOConsoleImpl;

/**
 *
 * @author travi
 */
public class FlooringMasteryController {
    
    private UserIO io = new UserIOConsoleImpl();
    FlooringMasteryView view = new FlooringMasteryView();
    
    public void run(){
        
        int menuSelection = 0;
        boolean keepGoing = true;
        //try {
        while (keepGoing){            
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection){
                case 1:
                    io.print("Display");//displayAllOrders();
                    break;
                case 2:
                    io.print("Add");//addAnOrder();
                    break;
                case 3:
                    io.print("Edit");//editAnOrder();
                    break;
                case 4:
                    io.print("Remove");//removeAnOrder();
                    break;
                case 5:
                    io.print("Save");//saveCurrentWork();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                case 7:
                    unknownCommand();
                    break;
     
            } // -- "END" SWITCH --            
           
            
        } // -- "END" WHILE LOOP --
        exitMessage();
        /*
        } catch (SetUpDaoException e){
            run();
            //view.displayErrorMessage(e.getMessage());
        }        
        */           
            
    } // -- "END" RUN --
        
    
    
    // -- CONTROLLER --
    //---------------------------------------------------------|
    
    // -- Menu Section --    
    public int getMenuSelection(){
        return view.printMenuGetUserSelection();
    }    
    private void unknownCommand(){
        view.displayUnknownMenuBanner();
    }        
    private void exitMessage(){
        view.displayExitMenuBanner();
    }
    
    // -- "END" Menu Section --
    
    // ---------------------------------------------|  
    
    // -- DISPLAY ALL ORDERS  SECTION --    
    private void displayAllOrders(){
        
    }           
    // -- "END" DISPLAY ALL ORDERS  SECTION --
    
    //---------------------------------------------------------|      
        
    // -- ADD AN ORDER  SECTION --    
    private void addAnOrder(){
        
    }         
    // -- "END" ADD AN ORDER  SECTION --
    
    //---------------------------------------------------------|    
        
    // -- EDIT AN ORDER  SECTION --    
    private void editAnOrder(){
        
    }
    // -- "END" EDIT AN ORDER  SECTION --
    
    //---------------------------------------------------------|
    
    // -- REMOVE AN ORDER  SECTION --    
    private void removeAnOrder(){
        
    }        
    // -- "END" REMOVE AN ORDER  SECTION --
    
    //---------------------------------------------------------| 
        
    // -- SAVE CURRENT WORK  SECTION --         
    private void saveCurrentWork(){
        
    }    
    // -- "END" SAVE CURRENT WORK  SECTION --
    
    //---------------------------------------------------------|    
        
   
    
}
