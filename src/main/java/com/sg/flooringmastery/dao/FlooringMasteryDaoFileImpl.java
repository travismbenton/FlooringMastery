/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
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
    
    private Map<Integer, Orders> myOrders = new HashMap<>();
    
    public static final String ORDERS_FILE = "flooringmastery.txt";
    public static final String DELIMITER = "::"; 
    
    
    @Override
    public Orders addOrder(int orderNumber, Orders order) 
            throws FlooringMasteryPersistenceExpection {
        Orders newOrder = myOrders.put(orderNumber, order);
        writeOrder();
        return newOrder;                
    }
    
    
    @Override
    public List<Orders> getAllOrders() 
            throws FlooringMasteryPersistenceExpection {
        loadOrder();
        return new ArrayList<>(myOrders.values());        
    }
    

    @Override
    public Orders getOrder(int orderNumber) 
            throws FlooringMasteryPersistenceExpection {
        loadOrder();
        return myOrders.get(orderNumber);        
    }
    

    @Override
    public Orders removeOrder(int orderNumber) 
            throws FlooringMasteryPersistenceExpection {
        Orders removedOrder = myOrders.remove(orderNumber);
        writeOrder();
        return removedOrder;        
    }
    
    
    
    
    
    
    
    
    
    // -- LOAD - ROSTER --
    private void loadOrder() throws FlooringMasteryPersistenceExpection {
        Scanner scanner;
        
        try {   
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                        new FileReader(ORDERS_FILE)));
        } catch (FileNotFoundException e){
            throw new FlooringMasteryPersistenceExpection(
                    "-_- Could not load order data into memory", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        String[] currentTokens;
        double[] currentTokens2 = null;
        int [] currentTokens3 = null;
        
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Orders currentOrder = new Orders(currentTokens3[0]);
            
            
            currentOrder.setCustomerName(currentTokens[1]);
	    currentOrder.setState(currentTokens[2]);
	    currentOrder.setTaxRate(currentTokens2[3]);
            currentOrder.setProductType(currentTokens[4]);
	    currentOrder.setArea(currentTokens2[5]);
	    currentOrder.setCostPerSquareFoot(currentTokens2[6]);
            currentOrder.setLaborCostPerSquareFoot(currentTokens2[7]);
	    currentOrder.setMaterialCost(currentTokens2[8]);
	    currentOrder.setLaborCost(currentTokens2[9]);
            currentOrder.setTax(currentTokens2[10]);
	    currentOrder.setTotal(currentTokens2[11]);	    
            
            myOrders.put(currentOrder.getOrderNumber(), currentOrder);           
        }
        scanner.close(); 
        
    } // -- loadRoster() --


    // -- WRITE - ROSTER --
    private void writeOrder() throws FlooringMasteryPersistenceExpection {
	    
	    PrintWriter out;
	    
	    try {                
	        out = new PrintWriter(new FileWriter(ORDERS_FILE));
	    } catch (IOException e) {
	        throw new FlooringMasteryPersistenceExpection(
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
