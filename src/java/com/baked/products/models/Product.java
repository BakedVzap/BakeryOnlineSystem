/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.products.models;

import java.util.ArrayList;

/**
 *
 * @author khwezi
 */
public class Product {
    private String name;
    private String description;
    private String warnings;
    private String nutritionalInformation;
    private String pictureOfCreation;
    private Integer quantity;
    private ArrayList<Recipe> recipes;
    private ArrayList<Category> categories;
    private Double price;
    
    public Product(String name,String description,String warnings,String nutritionalInformation, String pictureOfCreation,Integer quantity){
        setName(name);
        setDescription(description);
        setWarnings(warnings);
        setNutritionalInformation(nutritionalInformation);
        setPictureOfCreation(pictureOfCreation);
        setQuantity(quantity);
    }
    public Product(String name, String description, String warnings, String nutrition, String picture, Double price, ArrayList<Category> categories,
    				ArrayList<Recipe> recipes) {
    	setName(name);
    	setDescription(description);
    	setWarnings(warnings);
    	setNutritionalInformation(nutrition);
    	setPictureOfCreation(picture);
    	this.price=price;
    	setCategories(categories);
        setRecipe(recipes);
    }
    public Product(){}
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
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setRecipe(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public Double getPrice() {
    	return price;
    }
    public void setPrice(Double price) {
    	this.price=price;
    }
    
    public ArrayList<Category> getCategories(){
    	return categories;
    }
    public void setCategories(ArrayList<Category> categories) {
    	this.categories=categories;
    }
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", warnings=" + warnings
				+ ", nutritionalInformation=" + nutritionalInformation + ", pictureOfCreation=" + pictureOfCreation
				+ ", quantity=" + quantity + ", ingredients=" + recipes + ", price=" + price + "]";
	}
}
