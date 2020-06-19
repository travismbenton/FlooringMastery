/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.util.Objects;

/**
 *
 * @author travi
 */
public class Orders {
    
    private String orderNumber;
    private String CustomerName;
    private String State;
    private double TaxRate;
    private String ProductType;
    private double Area;
    private double CostPerSquareFoot;
    private double LaborCostPerSquareFoot;
    private double MaterialCost;
    private double LaborCost;
    private double Tax;
    private double Total;
    
    
    public Orders (String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }    

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public double getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(double TaxRate) {
        this.TaxRate = TaxRate;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double Area) {
        this.Area = Area;
    }

    public double getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(double CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public double getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(double LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    public double getMaterialCost() {
        return MaterialCost;
    }

    public void setMaterialCost(double MaterialCost) {
        this.MaterialCost = MaterialCost;
    }

    public double getLaborCost() {
        return LaborCost;
    }

    public void setLaborCost(double LaborCost) {
        this.LaborCost = LaborCost;
    }

    public double getTax() {
        return Tax;
    }

    public void setTax(double Tax) {
        this.Tax = Tax;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    

   
    
    
    
    
    
    
    
    
}
