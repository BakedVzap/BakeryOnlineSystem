
package com.baked.products.models;

import com.baked.products.models.Category;
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
    private Integer quantity;
    private ArrayList<Recipe> recipes;
    private ArrayList<ProductCategory> categories;
    private Double price;
    private Double discountMargin;
    private Boolean deleted;

    public Product(String name, String description, String warnings, String nutritionalInformation, String pictureOfCreation, Integer quantity, ArrayList<Recipe> recipes, ArrayList<ProductCategory> categories, Double price, Double discountMargin, Boolean deleted) {
        this.name = name;
        this.description = description;
        this.warnings = warnings;
        this.nutritionalInformation = nutritionalInformation;
        this.pictureOfCreation = pictureOfCreation;
        this.quantity = quantity;
        this.recipes = recipes;
        this.categories = categories;
        this.price = price;
        this.discountMargin = discountMargin;
        this.deleted = deleted;
    }
    
    public Product(String name,String description,String warnings,String nutritionalInformation, String pictureOfCreation,Integer quantity,
            ArrayList<Recipe>recipes,ArrayList<ProductCategory>categories,double price,double discountMargin){
        setName(name);
        setDescription(description);
        setWarnings(warnings);
        setNutritionalInformation(nutritionalInformation);
        setPictureOfCreation(pictureOfCreation);
        setQuantity(quantity);
        setRecipe(recipes);
        setCategories(categories);
        setPrice(price);
        setDiscountMargin(discountMargin);
    }
    
    public Product(String name,String description,String warnings,String nutritionalInformation, String pictureOfCreation,Integer quantity,
            double price,double discountMargin){
        setName(name);
        setDescription(description);
        setWarnings(warnings);
        setNutritionalInformation(nutritionalInformation);
        setPictureOfCreation(pictureOfCreation);
        setQuantity(quantity);
        setPrice(price);
        setDiscountMargin(discountMargin);
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
    
    public ArrayList<ProductCategory> getCategories(){
    	return categories;
    }
    public void setCategories(ArrayList<ProductCategory> categories) {
    	this.categories=categories;
    }
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", warnings=" + warnings
				+ ", nutritionalInformation=" + nutritionalInformation + ", pictureOfCreation=" + pictureOfCreation
				+ ", quantity=" + quantity + ", ingredients=" + recipes + ", price=" + price + "]";
	}

    /**
     * @param recipes the recipes to set
     */
   

    /**
     * @return the discountMargin
     */
    public Double getDiscountMargin() {
        return discountMargin;
    }

    /**
     * @param discountMargin the discountMargin to set
     */
    public void setDiscountMargin(Double discountMargin) {
        this.discountMargin = discountMargin;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
