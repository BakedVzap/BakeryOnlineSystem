
package com.baked.products.models;

import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public class Product {
    private String name;
    private String description;
    private String warnings;
    private String nutritionalInformation;
    private String pictureOfCreation;
    private Double price;
    private Integer quantity;
    private Double discountMargin;
    private ArrayList<Recipe>ingredients;
    private ArrayList<Category>categories;
    public Product(String name,Integer category){
        setName(name);
    }

    public Product(String name, String description, String warnings, String nutritionalInformation, String pictureOfCreation, Double price, Integer quantity, Double discountMargin, ArrayList<Recipe> ingredients, ArrayList<Category> categories) {
        this.name = name;
        this.description = description;
        this.warnings = warnings;
        this.nutritionalInformation = nutritionalInformation;
        this.pictureOfCreation = pictureOfCreation;
        this.price = price;
        this.quantity = quantity;
        this.discountMargin = discountMargin;
        this.ingredients = ingredients;
        this.categories = categories;
    }
    
    public Product(String name,String description,double price,String warnings,String nutritionalInformation, String pictureOfCreation,Integer quantity){
        setName(name);
        setDescription(description);
        setPrice(price);
        setWarnings(warnings);
        setNutritionalInformation(nutritionalInformation);
        setPictureOfCreation(pictureOfCreation);
        setQuantity(quantity);
    }
    public Product(String name,String description,double price,String warnings,String nutritionalInformation, String pictureOfCreation,Integer quantity, Integer category){
        setName(name);
        setDescription(description);
        setPrice(price);
        setWarnings(warnings);
        setNutritionalInformation(nutritionalInformation);
        setPictureOfCreation(pictureOfCreation);
        setQuantity(quantity);
    }

    /**
     * @return the name
     */
    public Product(){}
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the warnings
     */
    public String getWarnings() {
        return warnings;
    }

    /**
     * @param warnings the warnings to set
     */
    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    /**
     * @return the nutritionalInformation
     */
    public String getNutritionalInformation() {
        return nutritionalInformation;
    }

    /**
     * @param nutritionalInformation the nutritionalInformation to set
     */
    public void setNutritionalInformation(String nutritionalInformation) {
        this.nutritionalInformation = nutritionalInformation;
    }

    /**
     * @return the pictureOfCreation
     */
    public String getPictureOfCreation() {
        return pictureOfCreation;
    }

    /**
     * @param pictureOfCreation the pictureOfCreation to set
     */
    public void setPictureOfCreation(String pictureOfCreation) {
        this.pictureOfCreation = pictureOfCreation;
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

    /**
     * @return the ingredients
     */
    public ArrayList<Recipe> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(ArrayList<Recipe> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountMargin() {
        return discountMargin;
    }

    public void setDiscountMargin(Double discountMargin) {
        this.discountMargin = discountMargin;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
