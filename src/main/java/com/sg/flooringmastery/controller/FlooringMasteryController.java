/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.service.FlooringMasteryDataValidationException;
import com.sg.flooringmastery.service.FlooringMasteryDuplicateIdException;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.util.List;

/**
 *
 * @author travi
 */
public class FlooringMasteryController {    
     
    FlooringMasteryServiceLayer service; 
    FlooringMasteryView view; 
    
    // --  CONSTRUCTOR -- 
    public FlooringMasteryController (FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;        
    }
    // -- "END" CONSTRUCTOR -- 
    
    public void run() throws FlooringMasteryPersistenceException, 
                             FlooringMasteryDuplicateIdException, 
                             FlooringMasteryDataValidationException{
        
        int menuSelection = 0;
        boolean keepGoing = true;
        try {
        while (keepGoing){            
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection){
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveCurrentWork();
                    break;
                case 6:
                    listAllOrders();
                    break;    
                case 7:
                    keepGoing = false;
                    break;
                case 8:
                    unknownCommand();
                    break;
     
            } // -- "END" SWITCH --            
           
            
        } // -- "END" WHILE LOOP --
        exitMessage();
        
        } catch (FlooringMasteryPersistenceException | NumberFormatException 
                | NullPointerException e){
            run();
            //view.displayErrorMessage(e.getMessage());
        }        
                   
            
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
    
    // -- DISPLAY ORDERS  SECTION --    
    private void displayOrders() throws FlooringMasteryPersistenceException {
        view.displayOrdersListBanner();
        String newDate = view.getOrderDateChoice();
        List<Orders> orderList = service.getAllOrders();
       // view.displayOrderList(orderList);
    }           
    // -- "END" DISPLAY ORDERS  SECTION --
    
    //---------------------------------------------------------|      
        
    // -- ADD ORDER  SECTION --    
    private void addOrder() throws FlooringMasteryPersistenceException, 
                                   FlooringMasteryDuplicateIdException,
                                   FlooringMasteryDataValidationException {
        view.displayCreateOrderBanner();
        boolean hasErrors = false;
        do {
            Orders newOrder = view.getNewOrderInfo();
            try {                  
                service.createOrder(newOrder);
                view.displayCreateOrderSuccessBanner();  
                hasErrors = false;
            } catch (FlooringMasteryDataValidationException | 
                     FlooringMasteryDuplicateIdException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
                
        } while(hasErrors);    
    }         
    // -- "END" ADD ORDER  SECTION --
    
    //---------------------------------------------------------|    
        
    // -- EDIT ORDER  SECTION --    
    private void editOrder() throws FlooringMasteryPersistenceException, 
                                    FlooringMasteryDuplicateIdException,
                                    FlooringMasteryDataValidationException {
        view.displayEditOrderBanner();
        boolean hasErrors = false;
        do {
            String existingOrderNumber = view.getEditOrderNumberChoice();
            Orders order = service.getOrder(existingOrderNumber);
            Orders newOrder = view.editExistingOrderInfo(order);
        try {
            service.editOrder(newOrder);
            view.displayEditOrderSuccessBanner();
                hasErrors = false;
        } catch (FlooringMasteryDataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
        }   
        
        } while (hasErrors);
        
    }
    // -- "END" EDIT ORDER  SECTION --
    
    //---------------------------------------------------------|
    
    // -- REMOVE ORDER  SECTION --    
    private void removeOrder() throws FlooringMasteryPersistenceException {
        view.displayRemoveOrderBanner();
        String existingOrderNumber = view.getOrderNumberChoice();
        Orders order = service.getOrder(existingOrderNumber);
        
        view.displayOrderSummary(order);
        if (order != null){
        service.removeOrder(existingOrderNumber);
        view.displayRemoveOrderSuccessfulBanner();
        } else {
            // intentionally left blank
        }
    }        
    // -- "END" REMOVE ORDER  SECTION --
    
    //---------------------------------------------------------| 
        
    // -- SAVE CURRENT WORK  SECTION --    
    private void saveCurrentWork() throws FlooringMasteryPersistenceException, 
                                          FlooringMasteryDuplicateIdException,
                                          FlooringMasteryDataValidationException {
        view.displaySaveCurrentWorkBanner();
        run();
        view.displaySaveCurrentWorkSuccessBanner();
    }    
    // -- "END" SAVE CURRENT WORK  SECTION --
    
    //---------------------------------------------------------|   
    
    // -- LIST ALL ORDERS  SECTION --    
    private void listAllOrders() throws FlooringMasteryPersistenceException {
        view.displayListAllOrdersBanner();
        List<Orders> orderList = service.getAllOrders();
        view.displayListAllOrders(orderList);       
    }           
    // -- "END" LIST ALL ORDERS  SECTION --
    
    //---------------------------------------------------------|
        
   
    
}
