/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.service.FlooringMasteryDuplicateIdException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author travi
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    
    private Map<String, Orders> myOrders = new HashMap<>();
    
    public static final String ORDERS_FILE = "flooringmastery.txt";
    public static final String DELIMITER = "::"; 
    
    
    @Override
    public Orders addOrder(String orderNumber, Orders order) 
            throws FlooringMasteryPersistenceException, FlooringMasteryDuplicateIdException {
        Orders newOrder = myOrders.put(orderNumber, order);
        writeOrder();
        return newOrder;                
    }
    
    
    @Override
    public List<Orders> getAllOrders() 
            throws FlooringMasteryPersistenceException {
        loadOrder();
        return new ArrayList<>(myOrders.values());        
    }
    

    @Override
    public Orders getOrder(String orderNumber) 
            throws FlooringMasteryPersistenceException {
        loadOrder();
        return myOrders.get(orderNumber);        
    }
    

    @Override
    public Orders removeOrder(String orderNumber) 
            throws FlooringMasteryPersistenceException {
        Orders removedOrder = myOrders.remove(orderNumber);
        writeOrder();
        return removedOrder;        
    }
    
    
    
    
    
    
    
    
    
    // -- LOAD - ROSTER --
    private void loadOrder() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {   
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                        new FileReader(ORDERS_FILE)));
        } catch (FileNotFoundException e){
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load order data into memory", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;        
        String[] currentTokens;
        
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Orders currentOrder = new Orders(currentTokens[0]);            
            
            currentOrder.setCustomerName(currentTokens[1]);
	    currentOrder.setState(currentTokens[2]);
	    currentOrder.setTaxRate(currentTokens[3]);
            currentOrder.setProductType(currentTokens[4]);
	    currentOrder.setArea(currentTokens[5]);
	    currentOrder.setCostPerSquareFoot(currentTokens[6]);
            currentOrder.setLaborCostPerSquareFoot(currentTokens[7]);
	    currentOrder.setMaterialCost(currentTokens[8]);
	    currentOrder.setLaborCost(currentTokens[9]);
            currentOrder.setTax(currentTokens[10]);
	    currentOrder.setTotal(currentTokens[11]);	    
            
            myOrders.put(currentOrder.getOrderNumber(), currentOrder);           
        }
        scanner.close(); 
        
    } // -- loadRoster() --


    // -- WRITE - ROSTER --
    private void writeOrder() throws FlooringMasteryPersistenceException {
	    
	    PrintWriter out;
	    
	    try {                
	        out = new PrintWriter(new FileWriter(ORDERS_FILE));
	    } catch (IOException e) {
	        throw new FlooringMasteryPersistenceException(
	                "Could not save order data.", e);
	    }	    
	    
	    List<Orders> orderList = this.getAllOrders();
	    for (Orders currentOrder : orderList) {
	        // write the Student object to the file
	        out.println(currentOrder.getOrderNumber()+ DELIMITER	                
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
	               
	        // force PrintWriter to write line to the file
	        out.flush();
	    }
	    // Clean up
	    out.close();
            
    } // -- writeRoster() --    
    
    
    
    
}
