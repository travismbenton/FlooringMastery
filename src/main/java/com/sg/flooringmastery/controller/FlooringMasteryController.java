/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceExpection;
import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import com.sg.flooringmastery.ui.UserIO;
import com.sg.flooringmastery.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author travi
 */
public class FlooringMasteryController {
    
    private UserIO io = new UserIOConsoleImpl();
    FlooringMasteryDao dao = new FlooringMasteryDaoFileImpl();
    FlooringMasteryView view = new FlooringMasteryView();
    
    public void run() throws FlooringMasteryPersistenceExpection{
        
        int menuSelection = 0;
        boolean keepGoing = true;
        //try {
        while (keepGoing){            
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection){
                case 1:
                    listOrders();
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
    
    // -- LIST ORDERS  SECTION --    
    private void listOrders() throws FlooringMasteryPersistenceExpection {
        view.displayOrdersListBanner();
        String newDate = view.getOrderDateChoice();
        List<Orders> orderList = dao.getAllOrders();
       // view.displayOrderList(orderList);
    }           
    // -- "END" LIST ORDERS  SECTION --
    
    //---------------------------------------------------------|      
        
    // -- ADD ORDER  SECTION --    
    private void addOrder() throws FlooringMasteryPersistenceExpection {
        view.displayCreateOrderBanner();
        Orders newOrder = view.getNewOrderInfo();
        dao.addOrder(newOrder.getOrderNumber(), newOrder);
        view.displayCreateOrderSuccessBanner();
    }         
    // -- "END" ADD ORDER  SECTION --
    
    //---------------------------------------------------------|    
        
    // -- EDIT ORDER  SECTION --    
    private void editOrder() throws FlooringMasteryPersistenceExpection {
        
    }
    // -- "END" EDIT ORDER  SECTION --
    
    //---------------------------------------------------------|
    
    // -- REMOVE ORDER  SECTION --    
    private void removeOrder() throws FlooringMasteryPersistenceExpection {
        view.displayRemoveOrderBanner();
        String existingOrderNumber = view.getOrderNumberChoice();
        String orderDate = view.getOrderDateChoice();        
        dao.removeOrder(existingOrderNumber);
        view.displayRemoveOrderSuccessfulBanner();
    }        
    // -- "END" REMOVE ORDER  SECTION --
    
    //---------------------------------------------------------| 
        
    // -- SAVE CURRENT WORK  SECTION --    
    private void saveCurrentWork() throws FlooringMasteryPersistenceExpection {
        view.displaySaveCurrentWorkBanner();
        run();
        view.displaySaveCurrentWorkSuccessBanner();
    }    
    // -- "END" SAVE CURRENT WORK  SECTION --
    
    //---------------------------------------------------------|   
    
    // -- LIST ALL ORDERS  SECTION --    
    private void listAllOrders() throws FlooringMasteryPersistenceExpection {
        view.displayListAllOrdersBanner();
        List<Orders> orderList = dao.getAllOrders();
        view.displayListAllOrders(orderList);       
    }           
    // -- "END" LIST ALL ORDERS  SECTION --
    
    //---------------------------------------------------------|
        
   
    
}
