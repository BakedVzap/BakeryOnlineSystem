/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.products.service;

import com.baked.products.models.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.Recipe;
import java.util.ArrayList;

/**
 *
 * @author Rt Netwa
 */
public interface ProductsServiceInterface {
    boolean addProduct(Product product);
    ArrayList<Product> getAllProducts();
    ArrayList<Product> getProductsByCategory(Category category);
    boolean editProduct(Product product);
    boolean addCategory(Category cat);
    ArrayList<Category> getCategories();
    boolean editCategory(Category category);
    boolean addIngredients(Ingredient ingred);
    boolean deleteProduct(String productName);
    boolean addProductRecipe(Recipe recipe);
    boolean editIngredient(Ingredient ingred);
    ArrayList<Ingredient> getIngredients();
    boolean deleteIngredient(String ingredientName);
    Product getProduct(String productName);
    Ingredient getIngredient(String ingredientName);
    Category getCategory(Category category); //Added new methods 
    ArrayList<Product> getProductsByName(String prodName);
}
