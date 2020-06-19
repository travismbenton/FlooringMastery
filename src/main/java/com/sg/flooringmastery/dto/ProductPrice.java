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
public class ProductPrice {
    
    private double carpet = 2.25;    
    private double laminate = 1.75;   
    private double tile = 3.50;    
    private double wood = 5.15;
    

    public double getCarpet() {
        return carpet;
    }

    public void setCarpet(double carpet) {
        this.carpet = carpet;
    }

    public double getLaminate() {
        return laminate;
    }

    public void setLaminate(double laminate) {
        this.laminate = laminate;
    }

    public double getTile() {
        return tile;
    }

    public void setTile(double tile) {
        this.tile = tile;
    }

    public double getWood() {
        return wood;
    }

    public void setWood(double wood) {
        this.wood = wood;
    }

    
    

}
