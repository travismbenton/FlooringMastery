/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author travi
 */
public class FlooringMasteryPersistenceExpection extends Exception {
    
    public FlooringMasteryPersistenceExpection(String message){
        super(message);
    }
    
    public FlooringMasteryPersistenceExpection(String message, Throwable cause){
        super(message, cause);
    }
}
