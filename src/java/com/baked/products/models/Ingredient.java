
package com.baked.products.models;

/**
 *
 * @author UnicornBrendan
 */
public class Ingredient {

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    private String name;
    private Double quantity;
    private Boolean deleted;
    
    public Ingredient(String name,double quantity){
        setName(name);
        setQuantity(quantity);
    }

    public Ingredient() {
    }

    public Ingredient(String name, Double quantity, Boolean deleted) {
        this.name = name;
        this.quantity = quantity;
        this.deleted = deleted;
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
        this.setQuantity((Double) quantity);
    }
    
    
}
