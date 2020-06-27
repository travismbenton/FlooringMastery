/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.service.FlooringMasteryDuplicateIdException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author travi
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    private Map<String, Orders> myOrders = new HashMap<>();
    
    public static final String DELIMITER = "::"; 
    
    

    @Override
    public Orders addOrder(String orderNumber, Orders order) 
            throws FlooringMasteryPersistenceException, FlooringMasteryDuplicateIdException {
        Orders newOrder = myOrders.put(orderNumber, order);        
        try {        
            loadOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newOrder;                
    }
    
    @Override
    public String generateNextOrderNumber(String orderNumber) 
            throws FlooringMasteryPersistenceException{
        orderNumber = "0";
        Integer newOrderNumber = 0;
        //if(dao.getOrder(order.getOrderNumber()) == null) {              
            newOrderNumber = Integer.valueOf(orderNumber);            
            newOrderNumber = myOrders.size() + 1;            
            orderNumber = String.valueOf(newOrderNumber);            
            //order.setOrderNumber(orderNumber);
        System.out.println("Next Avail OrderNumber: "+orderNumber);
        try {        
            loadOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderNumber;
        //String orderNumber = "";                  
   }
    
    
    @Override
    public List<Orders> getAllOrders() 
            throws FlooringMasteryPersistenceException {
        try {        
            loadOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(myOrders.values());        
    }
    

    @Override
    public Orders getOrder(String orderNumber) 
            throws FlooringMasteryPersistenceException {
        try {        
            loadOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myOrders.get(orderNumber);        
    }
    

    @Override
    public Orders removeOrder(String orderDate, String orderNumber) 
            throws FlooringMasteryPersistenceException {
        Orders removedOrder = myOrders.remove(orderNumber);
        try {        
            loadOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writeOrder();
        } catch (IOException e) {
            e.printStackTrace();
        }        
        return removedOrder;        
    }
    
   
    
    
    
    /*public void createNewFile() {    
    
// -- CREATE THE FILE --
    try {    
        
      File ORDERS_FILE = new File("Order_"+LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))+".txt");
      ORDERS_FILE.createNewFile();
      if (ORDERS_FILE.createNewFile()) {
        System.out.println("CREATE-FILE MESSAGE:  File created: " + ORDERS_FILE.getName());
      } else {
        System.out.println("CREATE-FILE MESSAGE:  File already exists. ");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }*/
      
    
    
    
    // -- LOAD - ROSTER --
    private void loadOrder() throws FlooringMasteryPersistenceException, IOException {
        //Scanner scanner;
        
        try {    
            File ORDERS_FILE = new File("Order_"+LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))+".txt");
            ORDERS_FILE.createNewFile(); // -- Create New File DAILY --
            if (ORDERS_FILE.createNewFile()) {
            System.out.println("CREATE-FILE MESSAGE:  File created: " + ORDERS_FILE.getName());
        //} else {
            //System.out.println("CREATE-FILE MESSAGE:  File already exists. ");
        }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        Scanner scanner;
        
        
        try {   
            // Create Scanner for "READING" the file            
            File ORDERS_FILE = new File("Order_"+LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))+".txt");
            //scanner = new Scanner(ORDERS_FILE);
              scanner = new Scanner(
                        new BufferedReader(
                        new FileReader(ORDERS_FILE)));
        } catch (FileNotFoundException e){
            System.out.println("-_- Could not load order data into memory");
            e.printStackTrace();
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load order data into memory");            
        }       
        
              
        // currentLine holds the most recent line read from the file
        String currentLine;        
        String[] currentTokens;

        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);            
            
            Orders currentOrder = new Orders(currentTokens[0]);            
            
            currentOrder.setOrderDate(currentTokens[1]);
            currentOrder.setCustomerName(currentTokens[2]);
	    currentOrder.setState(currentTokens[3]);
	    currentOrder.setTaxRate(currentTokens[4]);
            currentOrder.setProductType(currentTokens[5]);
	    currentOrder.setArea(currentTokens[6]);
	    currentOrder.setCostPerSquareFoot(currentTokens[7]);
            currentOrder.setLaborCostPerSquareFoot(currentTokens[8]);
	    currentOrder.setMaterialCost(currentTokens[9]);
	    currentOrder.setLaborCost(currentTokens[10]);
            currentOrder.setTax(currentTokens[11]);
	    currentOrder.setTotal(currentTokens[12]);
           
            myOrders.put(currentOrder.getOrderNumber(), currentOrder);            
        
        }            
        
        scanner.close();    
        
    }  // -- loadRoster() --
        
             
    
    


    // -- WRITE - ROSTER --
    private void writeOrder() throws FlooringMasteryPersistenceException, 
            FileNotFoundException, IOException {
	    
	    PrintWriter out;
	    
	    try {                
	        out = new PrintWriter(new FileWriter("Order_"+LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"))+".txt"));
	    } catch (IOException e) {
	        throw new FlooringMasteryPersistenceException(
	                "Could not save order data.", e);
	    }            
            
            
	    List<Orders> orderList = this.getAllOrders();
	    for (Orders currentOrder : orderList) {
	        // write the Orders object to the file
                
                 
	        out.println(currentOrder.getOrderNumber()+ DELIMITER
                        + currentOrder.getOrderDate()+ DELIMITER
	                + currentOrder.getCustomerName()+ DELIMITER
	                + currentOrder.getState()+ DELIMITER
                        + currentOrder.getTaxRate()+ DELIMITER 
	                + currentOrder.getProductType()+ DELIMITER
	                + currentOrder.getArea()+ DELIMITER
                        + currentOrder.getCostPerSquareFoot()+ DELIMITER 
	                + currentOrder.getLaborCostPerSquareFoot()+ DELIMITER
	                + currentOrder.getMaterialCost()+ DELIMITER
                        + currentOrder.getLaborCost()+ DELIMITER 
	                + currentOrder.getTax()+ DELIMITER 
                        + currentOrder.getTotal());         
            
	    out.flush();
                
	    } 
            
	    // Clean up
	    out.close();
            System.out.println("WRITE-FILE MESSAGE:  Successfully wrote to the file.");
            
    } // -- writeRoster() --    
    
    
    
    
}
