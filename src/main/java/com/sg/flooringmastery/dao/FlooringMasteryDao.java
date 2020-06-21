/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import java.util.List;

/**
 *
 * @author travi
 */
public interface FlooringMasteryDao {
    
    Orders addOrder(String orderNumber, Orders order)
            throws FlooringMasteryPersistenceException;
    
    List<Orders> getAllOrders()
            throws FlooringMasteryPersistenceException; 
    
    Orders getOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;       
    
    Orders removeOrder(String orderNumber)
            throws FlooringMasteryPersistenceException;
    
    
}
