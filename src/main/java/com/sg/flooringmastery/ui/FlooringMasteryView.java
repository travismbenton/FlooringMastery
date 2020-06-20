/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.dto.ProductLaborCost;
import com.sg.flooringmastery.dto.ProductPrice;
import com.sg.flooringmastery.dto.TaxRates;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author travi
 */
public class FlooringMasteryView {
    
    private UserIO io; 
    TaxRates taxRate;
    ProductPrice productPrice;
    ProductLaborCost productLaborCost; 
    
    // -- CONSTRUCTOR -- 
    public FlooringMasteryView (UserIO io, TaxRates taxRate, 
            ProductPrice productPrice, ProductLaborCost productLaborCost){
        this.io = io;
        this.taxRate = taxRate;
        this.productPrice = productPrice;
        this.productLaborCost = productLaborCost;        
    }
    // -- "END" CONSTRUCTOR -- 
    
    public int printMenuGetUserSelection(){
        
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders ");
        io.print("2. Add an Order ");
        io.print("3. Edit an Order ");
        io.print("4. Remove an Order ");
        io.print("5. Save Current Work ");
        io.print("6. List all Orders ");
        io.print("7. Quit");
            
        return io.readInt("Please select from the menu above. ", 1, 7);
    }   
    
    //---------------------------------------------------------|
    
    // -- ERROR MESSAGE SECTION --
    public void displayErrorMessage(String errorMsg) {
	io.print("=== ERROR ===");
	io.print(errorMsg);
    }    
    // -- "END" ERROR MESSAGE SECTION --
    
    //---------------------------------------------------------|
    
    // -- Menu Section --
    public void displayUnknownMenuBanner(){
        io.print("Please select from the menu above.");
    }        
    public void displayExitMenuBanner(){
        io.print("Thank you!");
    }
        
    // -- "END" Menu Section --
    
    // ---------------------------------------------| 
 
    // -- DISPLAY ORDERS  SECTION --         
    public void displayOrdersListBanner() {
	io.print("=== Customer's Orders ===");
    }   
    public String getOrderDateChoice(){
        return io.readString("Please enter Order Date.");
    }
    public void displayOrderList(List<Orders> orderList){        
        for (Orders existingOrders : orderList){
            io.print("Order Number: "+existingOrders.getOrderNumber());
	    io.print("Customer Name: "+existingOrders.getCustomerName());
	    io.print("Customer State: "+existingOrders.getState());
	    io.print("Product Type: "+existingOrders.getProductType());
            io.print("Projected Area: "+existingOrders.getArea());          
              
        }      
    }
    // -- "END" DISPLAY ORDERS  SECTION --
    
    //---------------------------------------------------------|      
        
    // -- ADD ORDER  SECTION -- 
    public void displayCreateOrderBanner() {
	io.print("=== Create New Order ===");        
    }
    public Orders getNewOrderInfo(){
        
        int orderNumber = io.readInt("Enter Order Number. ");
        String customerName = io.readString("Enter Customer Name. ");
        String state = io.readString("Enter Customer State. ");
        String productType = io.readString("Enter Product Type. ");
        double area = io.readDouble("Enter Projected Area. ");
        // -- Create a new "Order" object --
        Orders currentOrder = new Orders(orderNumber);// -- OrderNumber of the "New Order"
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        // -- TAXE RATES --
        switch (state.toUpperCase()){
            case "OH":
                currentOrder.setTaxRate(taxRate.getOH());
                break;
            case "PA":
                currentOrder.setTaxRate(taxRate.getPA());                
                break;
            case "MI":
                currentOrder.setTaxRate(taxRate.getMI());                
                break;
            case "IN":
                currentOrder.setTaxRate(taxRate.getIN());                
                break;
            default:
                break;
        }        
        currentOrder.setProductType(productType);
        
        currentOrder.setArea(area);
        // -- COST PER SQUARE FOOT --
        switch (productType.toLowerCase()){
            case "carpet":
                currentOrder.setCostPerSquareFoot(productPrice.getCarpet());
                break;
            case "laminate":
                currentOrder.setCostPerSquareFoot(productPrice.getLaminate());                
                break;
            case "tile":
                currentOrder.setCostPerSquareFoot(productPrice.getTile());               
                break;
            case "wood":    
                currentOrder.setCostPerSquareFoot(productPrice.getWood());
            default:
                break;     
        }
        // -- LABOR COST PER SQUARE FOOT --
        switch (productType.toLowerCase()){
            case "carpet":
                currentOrder.setLaborCostPerSquareFoot(productLaborCost.getCarpet());
                break;
            case "laminate":
                currentOrder.setLaborCostPerSquareFoot(productLaborCost.getLaminate());                
                break;
            case "tile":
                currentOrder.setLaborCostPerSquareFoot(productLaborCost.getTile());               
                break;
            case "wood":
                currentOrder.setLaborCostPerSquareFoot(productLaborCost.getWood());                
                break;
            default:
                break;    
        }       
        //materialCost = area * costPerSquareFoot
        double materialCost=0;       
        BigDecimal decimalMaterialCost = new BigDecimal(Double.toString(materialCost));
        BigDecimal decimalArea = new BigDecimal(Double.toString(area));  
        BigDecimal decimalCarpetCostPerSquareFoot = new BigDecimal(Double.toString(productPrice.getCarpet()));
        BigDecimal decimalLaminateCostPerSquareFoot = new BigDecimal(Double.toString(productPrice.getLaminate()));
        BigDecimal decimalTileCostPerSquareFoot = new BigDecimal(Double.toString(productPrice.getTile()));
        BigDecimal decimalWoodCostPerSquareFoot = new BigDecimal(Double.toString(productPrice.getWood()));
        //laborCost = (area * laborCostPerSquareFoot)
        double laborCost=0;
        BigDecimal decimalLaborCost = new BigDecimal(Double.toString(laborCost));
        BigDecimal decimalCarpetLaborCostPerSquareFoot = new BigDecimal(Double.toString(productLaborCost.getCarpet()));
        //System.out.println("Carpet Labor Cost Test: "+decimalCarpetLaborCostPerSquareFoot);
        BigDecimal decimalLaminateLaborCostPerSquareFoot = new BigDecimal(Double.toString(productLaborCost.getLaminate()));
        BigDecimal decimalTileLaborCostPerSquareFoot = new BigDecimal(Double.toString(productLaborCost.getTile()));
        BigDecimal decimalWoodLaborCostPerSquareFoot = new BigDecimal(Double.toString(productLaborCost.getWood()));
        double tax=0;
        BigDecimal decimalTax = new BigDecimal(Double.toString(tax));
        BigDecimal decimalOHTaxRate = new BigDecimal(Double.toString(taxRate.getOH()));
        //System.out.println("Tax Test: "+decimalOHTaxRate);
        BigDecimal decimalPATaxRate = new BigDecimal(Double.toString(taxRate.getPA()));
        BigDecimal decimalMITaxRate = new BigDecimal(Double.toString(taxRate.getMI()));
        BigDecimal decimalINTaxRate = new BigDecimal(Double.toString(taxRate.getIN()));
        //Total = (MaterialCost + LaborCost + Tax)
        double total=0;
        BigDecimal decimalTotal = new BigDecimal(Double.toString(total));        
        
        // -- BIG DECIMAL  COST PER SQUARE FOOT --
        switch (productType.toLowerCase()){
            case "carpet":
                decimalMaterialCost = decimalArea.multiply(decimalCarpetCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
                decimalLaborCost = decimalArea.multiply(decimalCarpetLaborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
                break;
            case "laminate":
                decimalMaterialCost = decimalArea.multiply(decimalLaminateCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP); 
                decimalLaborCost = decimalArea.multiply(decimalLaminateLaborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP); 
                break;
            case "tile":
                decimalMaterialCost = decimalArea.multiply(decimalTileCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);   
                decimalLaborCost = decimalArea.multiply(decimalTileLaborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
                break;
            case "wood":
                decimalMaterialCost = decimalArea.multiply(decimalWoodCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);  
                decimalLaborCost = decimalArea.multiply(decimalWoodLaborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
                break; 
            default:
                break;     
        }        
        
        // -- BIG DECIMAL  TAX RATES --
        switch (state.toUpperCase()){
            case "OH":
                decimalTax = decimalMaterialCost.add(decimalLaborCost).multiply(decimalOHTaxRate).setScale(2, RoundingMode.HALF_UP);
                break;
            case "PA":
                 decimalTax = decimalMaterialCost.add(decimalLaborCost).multiply(decimalPATaxRate).setScale(2, RoundingMode.HALF_UP);               
                break;
            case "MI":
                 decimalTax = decimalMaterialCost.add(decimalLaborCost).multiply(decimalMITaxRate).setScale(2, RoundingMode.HALF_UP);              
                break;
            case "IN":
                 decimalTax = decimalMaterialCost.add(decimalLaborCost).multiply(decimalINTaxRate).setScale(2, RoundingMode.HALF_UP);               
                break;
            default:
                break;
        } 
        
        //Total = (MaterialCost + LaborCost + Tax)
        decimalTotal = decimalMaterialCost.add(decimalLaborCost).add(decimalTax).setScale(2, RoundingMode.HALF_UP);
        
        // -- Big Decimal  Conversion back to Double --
        materialCost = decimalMaterialCost.doubleValue();        
        currentOrder.setMaterialCost(materialCost);
        laborCost = decimalLaborCost.doubleValue();
        currentOrder.setLaborCost(laborCost);        
        tax = decimalTax.doubleValue();
        currentOrder.setTax(tax);
        total = decimalTotal.doubleValue();        
        currentOrder.setTotal(total);       
                        
       
        return currentOrder;         
               
    }    
    
    public Orders displayOrderSummary(Orders newOrder){
        
        boolean submit = true;
        do {
            io.print("  ===  Order Summary  ===  ");
            io.print("");
            io.print("Order Number: "+newOrder.getOrderNumber());
	    io.print("Customer Name: "+newOrder.getCustomerName());
	    io.print("Customer State: "+newOrder.getState());
            io.print("Tax Rate: $"+newOrder.getTaxRate());
	    io.print("Product Type: "+newOrder.getProductType());
            io.print("Projected Area: "+newOrder.getArea());            
	    io.print("Product Cost Per Square Foot: $"+newOrder.getCostPerSquareFoot());
	    io.print("Labor Cost Per Square Foot: $"+newOrder.getLaborCostPerSquareFoot());
	    io.print("Material Cost: $"+newOrder.getMaterialCost());
            io.print("Labor Cost: $"+newOrder.getLaborCost());
            io.print("Tax: $"+newOrder.getTax());
            io.print("Total: $"+newOrder.getTotal());    
            io.print("");
            io.print("  ===  END:  Order Summary  ===  ");        
        
        String submitOrder = io.readString("Would you like to submit this order \"Y/N\" ").toUpperCase();
        if (submitOrder.equals("Y") || submitOrder.equals("YES")){
            submit = false;
            break;           
            
        }
        
        }while(submit);
        
        return null;
   }
    
    public void displayCreateOrderSuccessBanner(){
        io.readString("New Order successfully "
                + "Created.  Please hit enter to continue. ");
    }
             
    // -- "END" ADD ORDER  SECTION --
    
    //---------------------------------------------------------|    
        
    // -- EDIT ORDER  SECTION -- 
    public void displayEditOrderBanner() {
	io.print("=== Edit Order ===");
    } 
    public void displayEditOrderSuccessBanner(){
        io.readString("Edit successful. "
                + "Please hit enter to continue. ");
    }    
    // -- "END" EDIT ORDER  SECTION --
    
    //---------------------------------------------------------|
    
    // -- REMOVE ORDER  SECTION --    
    public void displayRemoveOrderBanner() {
	io.print("=== Remove Order ===");
    } 
     public int getOrderNumberChoice(){
        return io.readInt("Please enter Order Number.");
    }
    public void displayRemoveOrderSuccessfulBanner() {
	io.readString("Order successfully removed. "
                + "Press enter to continue.");
    }
    // -- "END" REMOVE ORDER  SECTION --
    
    //---------------------------------------------------------| 
        
    // -- SAVE CURRENT WORK  SECTION --         
    public String displaySaveCurrentWorkBanner(){
        return io.readString("Saving your selections. "
                + "Please hit the enter key to continue.");
    }
    public void displaySaveCurrentWorkSuccessBanner() {
	io.print("=== Selections Saved Successful. ===");        
    }
    // -- "END" SAVE CURRENT WORK  SECTION --
    
    //---------------------------------------------------------|

    // -- LIST ALL ORDERS  SECTION -- 
    public void displayListAllOrdersBanner() {
	io.print("=== List All Orders ===");
    }
    public void displayListAllOrders(List<Orders> orderList){        
        for (Orders existingOrders : orderList){
            io.print("Order Number: "+existingOrders.getOrderNumber());
	    io.print("Customer Name: "+existingOrders.getCustomerName());
	    io.print("Customer State: "+existingOrders.getState());
            io.print("Tax Rate: $"+existingOrders.getTaxRate());
	    io.print("Product Type: "+existingOrders.getProductType());
            io.print("Projected Area: "+existingOrders.getArea());            
	    io.print("Product Cost Per Square Foot: $"+existingOrders.getCostPerSquareFoot());
	    io.print("Labor Cost Per Square Foot: $"+existingOrders.getLaborCostPerSquareFoot());
	    io.print("Material Cost: $"+existingOrders.getMaterialCost());
            io.print("Labor Cost: $"+existingOrders.getLaborCost());
            io.print("Tax: $"+existingOrders.getTax());
            io.print("Total: $"+existingOrders.getTotal());
            io.print("");
            io.print("  -- End Of Order --  ");            
        }      
    }
         
    // -- "END" LIST ALL ORDERS  SECTION --
    
    //---------------------------------------------------------|    
        
    

}
