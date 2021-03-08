
package com.baked.products.dao;

import com.baked.products.models.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.Recipe;
// import com.mysql.jdbc.PreparedStatement; //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public class ProductDAOImp implements ProductsDAOInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ProductDAOImp()
    {
         try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) 
        {
            System.out.println("Driver not found");
            System.exit(0);
        }
        String url = "jdbc:mysql://192.168.20.241:3306/TheDoughKnot";
        try 
        {
            con = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException ex) 
        {
            System.out.println("Connection not established");
        }
    }

    @Override
    public ArrayList<Recipe> getRecipes(String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> getProductsByCategory(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addCategory(Category cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Category> getCategories() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editCategory(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addIngredients(Ingredient ingred) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteProduct(String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProductRecipe(Recipe recipe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editIngredient(Ingredient ingred) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteIngredient(String ingredientName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient getIngredient(String ingredientName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe getRecipeItem(String productName, String ingredientName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Category getCategory(Category category) {
        return null;
    }
    public boolean addToCategoryProduct(){
        return false;
    }
    public  boolean addProuctCategory(Category category, Product prod){
        return false;
    }
    public ArrayList<Category> getAllCategoryOfProduct(Product prod) {
        return null;
    }
    public ArrayList<Product> getProductsByName(String prodName) {
        return null;
    }
}
