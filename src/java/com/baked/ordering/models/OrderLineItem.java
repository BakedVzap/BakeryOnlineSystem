
package com.baked.ordering.models;

/**
 *
 * @author Brendan
 */
public class OrderLineItem {
    private String product;
    private Integer quantity;
    private Integer order;
   // private Integer id; this was removed 
    
    public OrderLineItem(String product,int quantity){
        setProduct(product);
        setQuantity(quantity);
    }

    public OrderLineItem(String product, Integer quantity, Integer order) 
    {
        this.product = product;
        this.quantity = quantity;
        this.order = order;
        //this.id = id;
    }

    public OrderLineItem(){}
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

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
    
}
