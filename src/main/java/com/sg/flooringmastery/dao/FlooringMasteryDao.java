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
    
    Orders getOrder(String orderNumber)
            throws FlooringMasteryPersistenceExpection;
    
    Orders addOrder(String orderNumber, Orders order)
            throws FlooringMasteryPersistenceExpection;
    
    List<Orders> getAllOrders()
            throws FlooringMasteryPersistenceExpection;    
    
    Orders removeOrder(String orderNumber)
            throws FlooringMasteryPersistenceExpection;
    
    
}
