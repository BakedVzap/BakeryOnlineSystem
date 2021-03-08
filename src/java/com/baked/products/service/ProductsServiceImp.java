
package com.baked.products.service;

import com.baked.products.dao.ProductDAOImp;
import com.baked.products.dao.ProductsDAOInterface;
import com.baked.products.models.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.Recipe;
import java.util.ArrayList;


public class ProductsServiceImp implements ProductsServiceInterface {

    private ProductsDAOInterface prodImp;
    private ArrayList<Category> categories;
    private ArrayList<Ingredient> allIng;
    //********************************************************************************************************************************************
    public ProductsServiceImp() {
        prodImp = new ProductDAOImp();
    }
    @Override
    public ArrayList<Recipe> getRecipes(String productName) {
        return prodImp.getRecipes(productName);
    }
    //**************************************************************************
    @Override
    public boolean addProduct(Product product) {
        if((prodImp.getProduct(product.getName())!=null)) {
            System.out.println("Product name already exist!!");
            return false;
        }else{
            return prodImp.addProduct(product);           
        }
    }
    //**************************************************************************
    @Override
    public ArrayList<Product> getAllProducts() {
        return prodImp.getAllProducts();
    }
    //**************************************************************************
    @Override
    public ArrayList<Product> getProductsByCategory(Category category) {
        return prodImp.getProductsByCategory(category);
    }
    //**************************************************************************
    @Override
    public boolean editProduct(Product product) {
        return prodImp.editProduct(product);
    }
    //**************************************************************************
    @Override
    public boolean addCategory(Category cat) {
        return prodImp.addCategory(cat);
    }
    //**************************************************************************
    @Override
    public ArrayList<Category> getCategories() {
        return prodImp.getCategories();
    }
    //**************************************************************************
    @Override
    public boolean editCategory(Category category) {
        return prodImp.editCategory(category);
    }
    //**************************************************************************
    @Override
    public boolean addIngredients(Ingredient ingred) {
        return prodImp.addIngredients(ingred);
    }
    //**************************************************************************
    @Override
    public boolean deleteProduct(String productName) {
        return prodImp.deleteProduct(productName);
    }
    //**************************************************************************
    @Override
    public boolean addProductRecipe(Recipe recipe) {
        return prodImp.addProductRecipe(recipe);
    }   
    //**************************************************************************
    @Override
    public boolean editIngredient(Ingredient ingred) {
        return prodImp.editIngredient(ingred);
    }
    //**************************************************************************
    @Override
    public ArrayList<Ingredient> getIngredients() {
        return prodImp.getIngredients();
    }
    //**************************************************************************
    @Override
    public boolean deleteIngredient(String ingredientName) {
        return prodImp.deleteIngredient(ingredientName);
    }
    //**************************************************************************
    @Override
    public Product getProduct(String productName) {
       // return prodImp.getProduct(productName);
       return this.getProd();
    }
    //**************************************************************************
    @Override
    public Ingredient getIngredient(String ingredientName) {
        return prodImp.getIngredient(ingredientName);
    }
    //**************************************************************************
    @Override
    public Recipe getRecipeItem(String productName, String ingredientName) {
        return prodImp.getRecipeItem(productName, ingredientName);
    }
    //**************************************************************************
    public Category getCategory(Category category) {
        return prodImp.getCategory(category);
    }
    //**************************************************************************
    public ArrayList<Category> getAllCategoryOfProduct(Product prod){
        return prodImp.getAllCategoryOfProduct(prod);
    }

    @Override
    public boolean addProuctCategory(Category category, Product prod) {
        return prodImp.addProuctCategory(category, prod);
    }

    @Override
    public ArrayList<Product> getProductsByName(String prodName) {
        return prodImp.getProductsByName(prodName);
    }
    public ArrayList<Category> allCategories() {
		categories = new ArrayList<>();
		categories.add(new Category("Cakes"));
		categories.add(new Category("Pastries"));
		categories.add(new Category("Cookies"));
		categories.add(new Category("Fresh Breads"));
		categories.add(new Category("Pies"));
		categories.add(new Category("Brownies"));
		categories.add(new Category("CupCakes"));
		return categories;
	}
	public ArrayList<Ingredient> allIngred(){
		allIng=new ArrayList<>();
		allIng.add(new Ingredient("eggs", 60));
		allIng.add(new Ingredient("flour", 10000));
		allIng.add(new Ingredient("baking powder", 10000));
		allIng.add(new Ingredient("Milk", 700));
		allIng.add(new Ingredient("Sugar", 2000));
		allIng.add(new Ingredient("Icing", 500));
		return allIng;
	}
        
        public ArrayList<Recipe> allRecipesss(){
            ArrayList<Recipe> recips=new ArrayList<Recipe>();
            recips.add(new Recipe("BlackForest", "eggs", 5));
            recips.add(new Recipe("BlackForest", "flour", 500));
            return recips;
        }
        public Product getProd() {
            ArrayList<Category> cats=new ArrayList<>();
            ArrayList<Recipe> recipee=new ArrayList<>();
            Product prod=new Product("BlackForest", "Sweet and nice", "contains sugar","Provides with vitaminC and B", "pic",
                                        120.00, cats, recipee);
            return prod;
        }
}
