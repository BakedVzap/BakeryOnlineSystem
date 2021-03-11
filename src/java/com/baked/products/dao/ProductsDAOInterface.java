
package com.baked.products.dao;

import com.baked.products.models.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.Recipe;
import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public interface ProductsDAOInterface 
{
    ArrayList<Recipe> getRecipes(String productName);
    boolean addProduct(Product product);
    ArrayList<Product> getAllProducts();
    ArrayList<Product> getProductsByCategory(Category category);
    boolean editProduct(Product product);
    boolean addCategory(Category cat);
    ArrayList<Category> getCategories();
    boolean editCategory(Category category);
    boolean addIngredients(Ingredient ingred);
    boolean deleteProduct(String productName);
    //boolean addProductRecipe(Recipe recipe);
    boolean editIngredient(Ingredient ingred);
    ArrayList<Ingredient> getIngredients();
    boolean deleteIngredient(String ingredientName);
    Product getProduct(String productName);
    Ingredient getIngredient(String ingredientName);
    Category getCategory(Integer category);
    //Recipe getRecipeItem(String productName,String ingredientName);
    Category getCategory(Category category);
    //boolean addProductCategory(Category category, Product prod);
    //ArrayList<Category> getAllCategoryOfProduct(Product prod);
    ArrayList<Product> getProductsByName(String name);
}
