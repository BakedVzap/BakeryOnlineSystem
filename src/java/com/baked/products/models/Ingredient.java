
package com.baked.products.models;

/**
 *
 * @author UnicornBrendan
 */
public class Ingredient {
    private String name;
    private Double quantity;
    
    public Ingredient(String name,double quantity){
        setName(name);
        setQuantity(quantity);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    
    
}
