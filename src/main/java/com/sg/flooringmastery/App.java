/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.ProductLaborCost;
import com.sg.flooringmastery.dto.ProductPrice;
import com.sg.flooringmastery.dto.TaxRates;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import com.sg.flooringmastery.ui.UserIO;
import com.sg.flooringmastery.ui.UserIOConsoleImpl;

/**
 *
 * @author travi
 */
public class App {
    public static void main(String[] args) throws FlooringMasteryPersistenceException {
        
        UserIO myIO = new UserIOConsoleImpl();
        TaxRates myTaxRate = new TaxRates();
        ProductPrice myProductPrice = new ProductPrice();
        ProductLaborCost myProductLaborCost = new ProductLaborCost();        
        FlooringMasteryView myView = new FlooringMasteryView(myIO, myTaxRate, 
                                            myProductPrice, myProductLaborCost);
        FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
        FlooringMasteryController controller = new 
            FlooringMasteryController(myDao, myView);
        controller.run();
        
    }
}
