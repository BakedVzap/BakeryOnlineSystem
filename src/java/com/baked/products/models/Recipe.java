
package com.baked.products.models;

/**
 *
 * @author UnicornBrendan
 */
public class Recipe {
    private String product;
    private String ingredient;
    private double measurement;
    
    public Recipe(String product,String ingredient,double measurement){
        setProduct(product);
        setIngredient(ingredient);
        setMeasurement(measurement);
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
     * @return the ingredient
     */
    public String getIngredient() {
        return ingredient;
    }

    /**
     * @param ingredient the ingredient to set
     */
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * @return the measurement
     */
    public double getMeasurement() {
        return measurement;
    }

    /**
     * @param measurement the measurement to set
     */
    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }
    
}
