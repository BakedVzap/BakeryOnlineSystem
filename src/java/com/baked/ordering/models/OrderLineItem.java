/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.ordering.models;

/**
 *
 * @author khwezi
 */
public class OrderLineItem {
    private String product;
    private Integer quantity;
    private Integer order;
    private Integer id;
    
    public OrderLineItem(String product,int quantity){
        setProduct(product);
        setQuantity(quantity);
    }

    public OrderLineItem(String product, Integer quantity, Integer order, Integer id) {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
        this.id = id;
    }

    /**
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
