/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Orders;
import java.util.List;

/**
 *
 * @author travi
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer{

    private FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao; 
    
    
    // -- Constructor --
    public FlooringMasteryServiceLayerImpl(FlooringMasteryDao dao, FlooringMasteryAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    // -- "END" Constructor --

    @Override
    public void createOrder(Orders order) throws FlooringMasteryPersistenceException,
                                                 FlooringMasteryDataValidationException,
                                                 FlooringMasteryDuplicateIdException {
        if(dao.getOrder(order.getOrderNumber()) != null) {
            throw new FlooringMasteryDuplicateIdException(
                    "Error: Could not create order. Order "
                            +order.getOrderNumber()
                            +" already exists");
        }
        validateOrderData(order);
        dao.addOrder(order.getOrderNumber(), order);
        auditDao.writeAuditEntry("New Order: "+order.getOrderNumber()+" CREATED.");
    }

    @Override
    public void editOrder(Orders order) throws FlooringMasteryPersistenceException,
                                               FlooringMasteryDataValidationException,
                                               FlooringMasteryDuplicateIdException {
        validateOrderData(order);
        dao.addOrder(order.getOrderNumber(), order);
        auditDao.writeAuditEntry("Existing Order: "+order.getOrderNumber()+" EDITED.");
    }

    @Override
    public List<Orders> getAllOrders() throws FlooringMasteryPersistenceException {
        return dao.getAllOrders();
    }

    @Override
    public Orders getOrder(String orderNumber) throws FlooringMasteryPersistenceException {
        return dao.getOrder(orderNumber);
    }

    @Override
    public Orders removeOrder(String orderNumber) throws FlooringMasteryPersistenceException {
        Orders removedOrder = dao.removeOrder(orderNumber);
        auditDao.writeAuditEntry("Existing Order: "+orderNumber+" REMOVED.");
        return removedOrder;
    }
    
    
    public void validateOrderData(Orders order) throws 
            FlooringMasteryDataValidationException {            
        
        if (order.getCustomerName() == null || order.getCustomerName().trim().length() == 0
            || order.getState() == null || order.getState().trim().length() == 0    
            || order.getProductType() == null || order.getProductType().trim().length() == 0
            || order.getArea() == null || order.getArea().trim().length() == 0) {
            
            throw new FlooringMasteryDataValidationException (
                    "ERROR: All fields [Order Number, Customer Name, State, "
                            + " Product Type and Area] are required.");            
        }                
    }
    
    
}
