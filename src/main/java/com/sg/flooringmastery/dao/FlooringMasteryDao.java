/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.service.FlooringMasteryDuplicateIdException;
import java.util.List;

/**
 *
 * @author travi
 */
public interface FlooringMasteryDao {
    
    Orders addOrder(String orderNumber, Orders order)
            throws FlooringMasteryPersistenceException, FlooringMasteryDuplicateIdException;
    
    List<Orders> getAllOrders()
            throws FlooringMasteryPersistenceException; 
    
    Orders getOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;       
    
    Orders removeOrder(String orderDate, String orderNumber)
            throws FlooringMasteryPersistenceException;
    
    public String generateNextOrderNumber(String orderNumber) 
            throws FlooringMasteryPersistenceException;
    
}
