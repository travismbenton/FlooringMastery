/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

/**
 *
 * @author travi
 */
public class TaxRates {
    
    private double OH = .0625;
    private double PA = .0675;
    private double MI = .0575;
    private double IN = .06;

    public double getOH() {
        return OH;
    }

    public void setOH(double OH) {
        this.OH = OH;
    }

    public double getPA() {
        return PA;
    }

    public void setPA(double PA) {
        this.PA = PA;
    }

    public double getMI() {
        return MI;
    }

    public void setMI(double MI) {
        this.MI = MI;
    }

    public double getIN() {
        return IN;
    }

    public void setIN(double IN) {
        this.IN = IN;
    }

    
    
}
