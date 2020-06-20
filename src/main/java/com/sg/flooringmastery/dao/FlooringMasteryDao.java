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
    
    Orders addOrder(int orderNumber, Orders order)
            throws FlooringMasteryPersistenceExpection;
    
    List<Orders> getAllOrders()
            throws FlooringMasteryPersistenceExpection; 
    
    Orders getOrder(int orderNumber)
            throws FlooringMasteryPersistenceExpection;       
    
    Orders removeOrder(int orderNumber)
            throws FlooringMasteryPersistenceExpection;
    
    
}
