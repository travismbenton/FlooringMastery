/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

/**
 *
 * @author travi
 */
public class FlooringMasteryView {
    
    private UserIO io = new UserIOConsoleImpl();
    
    public int printMenuGetUserSelection(){
        
        io.print("Welcome - MAIN MENU");
        io.print("1. Display Orders ");
        io.print("2. Add an Order ");
        io.print("3. Edit an Order ");
        io.print("4. Remove an Order ");
        io.print("5. Save Current Work ");
        io.print("6. Quit");
            
        return io.readInt("Please select from the menue above. ", 1, 6);
    }   
    
    //---------------------------------------------------------|
    
    // -- ERROR MESSAGE SECTION --
    public void displayErrorMessage(String errorMsg) {
	io.print("=== ERROR ===");
	io.print(errorMsg);
    }    
    // -- "END" ERROR MESSAGE SECTION --
    
    //---------------------------------------------------------|
    
    // -- Menu Section --
    public void displayUnknownMenuBanner(){
        io.print("Please select from the menu above.");
    }        
    public void displayExitMenuBanner(){
        io.print("Thank you!");
    }
        
    // -- "END" Menu Section --
    
    // ---------------------------------------------| 
 
    // -- DISPLAY ALL ORDERS  SECTION --         
            
    // -- "END" DISPLAY ALL ORDERS  SECTION --
    
    //---------------------------------------------------------|      
        
    // -- ADD AN ORDER  SECTION --    
             
    // -- "END" ADD AN ORDER  SECTION --
    
    //---------------------------------------------------------|    
        
    // -- EDIT AN ORDER  SECTION --    
    
    // -- "END" EDIT AN ORDER  SECTION --
    
    //---------------------------------------------------------|
    
    // -- REMOVE AN ORDER  SECTION --    
          
    // -- "END" REMOVE AN ORDER  SECTION --
    
    //---------------------------------------------------------| 
        
    // -- SAVE CURRENT WORK  SECTION --         
      
    // -- "END" SAVE CURRENT WORK  SECTION --
    
    //---------------------------------------------------------|    
        
    

}
