/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.products.dao;

import com.baked.products.models.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.Recipe;
import java.util.ArrayList;

/**
 *
 * @author khwezi
 */
public interface ProductsDAOInterface {
    ArrayList<Recipe> getRecipes(String productName);
    boolean addProduct(Product product);
    ArrayList<Product> getAllProducts();
    ArrayList<Product> getProductsByCategory();
    boolean editProduct(Product product);
    boolean addCategory(Category cat);
    ArrayList<Category> getCategories();
    boolean editCategory(Category category);
    boolean addIngredients(Ingredient ingred);
    boolean deleteProduct(String productName);
    boolean addProductRecipe(ArrayList<Recipe>recipes,String productID);
    boolean editIngredient(Ingredient ingred);
    ArrayList<Ingredient> getIngredients();
    boolean deleteIngredient(String ingredientName);
    Product getProduct(String productName);
    Ingredient getIngredient(String ingredientName);
    Recipe getRecipeItem(String productName,String ingredientName);
    
    
    
}
