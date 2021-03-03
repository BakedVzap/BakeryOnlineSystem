/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.ordering.models;

import java.util.ArrayList;

/**
 *
 * @author khwezi
 */
public class Order {
    private Integer id;
    private ArrayList<OrderLineItem>items;
    private Double totalPrice;
    
    public Order(int id,ArrayList<OrderLineItem>items,double totalPrice){
        setId(id);
        setItems(items);
        setTotalPrice(totalPrice);
    }
    
    public Order(){
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the items
     */
    public ArrayList<OrderLineItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<OrderLineItem> items) {
        this.items = items;
    }
   

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
    
    
}
