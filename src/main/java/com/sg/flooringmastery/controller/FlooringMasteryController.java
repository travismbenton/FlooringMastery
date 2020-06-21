/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.util.List;

/**
 *
 * @author travi
 */
public class FlooringMasteryController {    
     
    FlooringMasteryDao dao; 
    FlooringMasteryView view; 
    
    // --  CONSTRUCTOR -- 
    public FlooringMasteryController (FlooringMasteryDao dao, FlooringMasteryView view) {
        this.dao = dao;
        this.view = view;        
    }
    // -- "END" CONSTRUCTOR -- 
    
    public void run() throws FlooringMasteryPersistenceException{
        
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
        List<Orders> orderList = dao.getAllOrders();
       // view.displayOrderList(orderList);
    }           
    // -- "END" DISPLAY ORDERS  SECTION --
    
    //---------------------------------------------------------|      
        
    // -- ADD ORDER  SECTION --    
    private void addOrder() throws FlooringMasteryPersistenceException {
        view.displayCreateOrderBanner();
        Orders newOrder = view.getNewOrderInfo();                
        //view.displayOrderSummary(newOrder);        
        dao.addOrder(newOrder.getOrderNumber(), newOrder);
        view.displayCreateOrderSuccessBanner();                 
    }         
    // -- "END" ADD ORDER  SECTION --
    
    //---------------------------------------------------------|    
        
    // -- EDIT ORDER  SECTION --    
    private void editOrder() throws FlooringMasteryPersistenceException {
        view.displayEditOrderBanner();
        String existingOrderNumber = view.getOrderNumberChoice();
        Orders order = dao.getOrder(existingOrderNumber);
        view.displayFindOrder(order);
        Orders newOrder = view.editExistingOrderInfo(order); 
        //view.displayOrderSummary(newOrder);
        dao.addOrder(newOrder.getOrderNumber(), newOrder);
        view.displayEditOrderSuccessBanner();
    }
    // -- "END" EDIT ORDER  SECTION --
    
    //---------------------------------------------------------|
    
    // -- REMOVE ORDER  SECTION --    
    private void removeOrder() throws FlooringMasteryPersistenceException {
        view.displayRemoveOrderBanner();
        String existingOrderNumber = view.getOrderNumberChoice();
        //String orderDate = view.getOrderDateChoice();        
        dao.removeOrder(existingOrderNumber);
        view.displayRemoveOrderSuccessfulBanner();
    }        
    // -- "END" REMOVE ORDER  SECTION --
    
    //---------------------------------------------------------| 
        
    // -- SAVE CURRENT WORK  SECTION --    
    private void saveCurrentWork() throws FlooringMasteryPersistenceException {
        view.displaySaveCurrentWorkBanner();
        run();
        view.displaySaveCurrentWorkSuccessBanner();
    }    
    // -- "END" SAVE CURRENT WORK  SECTION --
    
    //---------------------------------------------------------|   
    
    // -- LIST ALL ORDERS  SECTION --    
    private void listAllOrders() throws FlooringMasteryPersistenceException {
        view.displayListAllOrdersBanner();
        List<Orders> orderList = dao.getAllOrders();
        view.displayListAllOrders(orderList);       
    }           
    // -- "END" LIST ALL ORDERS  SECTION --
    
    //---------------------------------------------------------|
        
   
    
}
