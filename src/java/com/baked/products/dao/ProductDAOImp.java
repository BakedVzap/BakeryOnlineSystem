
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UnicornBrendan
 */
public class ProductDAOImp implements ProductsDAOInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement myStmt;
    
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
        String url = "jdbc:mysql://localhost:3306/";
        try 
        {
            con = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException ex) 
        {
            System.out.println("Connection not established");
        }
    }

    @Override
    public ArrayList<Recipe> getRecipes(String productName) 
    {
         ArrayList<Recipe> tempRecipes= new ArrayList<Recipe>();
        Recipe tempRecipe;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT Product , Ingredient, Unit FROM productingredient WHERE Product LIKE '"+productName+"'");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
                        tempRecipe = new Recipe();
                        //Assigning all fields from the result set to new template
                        tempRecipe.setProduct(rs.getString("Product"));
                        tempRecipe.setIngredient(rs.getString("Ingredient"));
                        tempRecipe.setMeasurement(rs.getDouble("Unit"));
                     
                        
                        // returning now that new user object to the List created at start of method
                        tempRecipes.add(tempRecipe);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll getUserAddresses: "+ex.getMessage());
        }
        return tempRecipes; // giving back the ArrayList here  
    }

    @Override
    public boolean addProduct(Product product) 
    {
         try
            {
                // Populating both the product and the productingredient tables
                 ps = con.prepareStatement("INSERT INTO product( Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
                 ps.setString(1, product.getName());
                 ps.setDouble(2, product.getPrice());
                 ps.setString(3, product.getDescription());
                 ps.setString(4, product.getWarnings());
                 ps.setString(5, product.getNutritionalInformation());
                 ps.setString(6, product.getPictureOfCreation());
                 ps.setInt(7, product.getQuantity());
                 ps.setDouble(8, product.getDiscountMargin());
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll products : "+ex.getMessage());
                    return false;
                }
        
        try
            {
                // Populating both the payment and the paymentusertable tables
                 ps = con.prepareStatement("INSERT INTO productingredient( Product, Ingredient , Unit) VALUES (?, ?, ?)");
                 ps.setString(1, product.getName());
                 ps.setString(2, product.getDescription());
                 ps.setDouble(3, 0.0);
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users userPaymenttable : "+ex.getMessage());
                    return false;
                }
        return true;
    }

    @Override
    public ArrayList<Product> getAllProducts() 
    {
        ArrayList<Product> tempProducts = new ArrayList<Product>();
        Product tempProduct;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin FROM product");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new Product aka template
                        tempProduct= new Product();
                        //Assigning all fields from the result set to new template
                        tempProduct.setName(rs.getString("Name"));
                        tempProduct.setPrice(rs.getDouble("Price"));
                        tempProduct.setDescription(rs.getString("Description"));
                        tempProduct.setWarnings(rs.getString("Warning"));
                        tempProduct.setNutritionalInformation(rs.getString("Information"));
                        tempProduct.setPictureOfCreation(rs.getString("Image"));
                        tempProduct.setQuantity(rs.getInt("Quantity"));
                        tempProduct.setDiscountMargin(rs.getDouble("DiscountMargin"));
                        
                        // returning now that new user object to the List created at start of method
                        tempProducts.add(tempProduct);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll payments: "+ex.getMessage());
        }
        return tempProducts; // giving back the ArrayList here   
    }

    @Override
    public ArrayList<Product> getProductsByCategory() 
    {
         ArrayList<Product> tempProducts = new ArrayList<Product>();
        Product tempProduct;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin FROM product");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new Product aka template
                        tempProduct= new Product(rs.getString("Product"), rs.getInt("Category"));
                        //Assigning all fields from the result set to new template
                       
                        // returning now that new user object to the List created at start of method
                        tempProducts.add(tempProduct);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll payments: "+ex.getMessage());
        }
        return tempProducts; // giving back the ArrayList here   
    }

    @Override
    public boolean editProduct(Product product) 
    {
        String id = product.getName();
        try
            {
                ps = con.prepareStatement("UPDATE product SET Name = '"+product.getName()+"'"
                        + ", Price = '"+product.getPrice()+"'"
                        + ", Description = '"+product.getDescription()+"'"
                        + ", Warning = '"+product.getWarnings()+"'"
                        + ", Information = "+product.getNutritionalInformation()+""
                        + ", Image = '"+product.getPictureOfCreation()+"'"
                        + ", Quantity = '"+product.getQuantity()+"'"
                        + ", DiscountMargin = '"+product.getDiscountMargin()+"'"
                      
                                + " WHERE Name LIKE '"+id+"'");
                ps.executeUpdate();
                
            }
        catch (SQLException ex) 
         {
             System.out.println("Error at Edit Product : "+ex.getMessage());
             return false;
         }
        return true;
    }

    @Override
    public boolean addCategory(Category cat) 
    {
        try
            {
                ps = con.prepareStatement("INSERT INTO category(ID, Name) VALUES (?, ?)");
                ps.setInt(1, cat.getCateId());
                ps.setString(2, cat.getName());
                ps.executeUpdate();
            }catch(SQLException ex)
            {
                System.out.println("Error at category add :"+ex.getMessage());
            }
        return true;
    }

    @Override
    public ArrayList<Category> getCategories() 
    {
        //Creating a new List of Categories
        ArrayList<Category> tempCats = new ArrayList<Category>();
        // Creating a User reference here
        Category tempCat;
        try 
         {
             // creating a statement to retrieve a result set of users
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT ID, Name FROM category");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
                        tempCat = new Category();
                        //Assigning all fields from the result set to new template
                        tempCat.setCateId(rs.getInt("ID"));
                        tempCat.setName(rs.getString("Name"));
                        
                        
                        // returning now that new user object to the List created at start of method
                        tempCats.add(tempCat);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        return tempCats; // giving back the ArrayList here
    }

    @Override
    public boolean editCategory(Category category) 
    {
        int id = category.getCateId();
        try
            {
                ps = con.prepareStatement("UPDATE category SET Name = '"+category.getName()+"'"
                        
                        + ", ID = '"+category.getCateId()+"'"
                      
                                + " WHERE Name LIKE '"+id+"'");
                ps.executeUpdate();
                
            }
        catch (SQLException ex) 
         {
             System.out.println("Error at Edit Product : "+ex.getMessage());
             return false;
         }
        return true;
    }

    @Override
    public boolean addIngredients(Ingredient ingred) 
    {
         try
            {
                ps = con.prepareStatement("INSERT INTO ingredient(Name, Quantity) VALUES (?, ?)");
                ps.setString(1, ingred.getName());
                ps.setDouble(2, ingred.getQuantity());
                ps.executeUpdate();
            }catch(SQLException ex)
            {
                System.out.println("Error at category add :"+ex.getMessage());
            }
        return true;
    }

    @Override
    public boolean deleteProduct(String productName) 
    {
         try 
         {
             //must pass no whitespaces due to equals sign
             ps = con.prepareStatement("DELETE * FROM product WHERE Name = '"+productName+"'");
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
        return true;
    }

    @Override
    public boolean addProductRecipe(ArrayList<Recipe> recipes, String productID) 
    {
        Recipe r1 ;
        
        for(Recipe r : recipes)
            {
            try {
                ps = con.prepareStatement("INSERT INTO productingredient(Product, Ingredient, Unit) Values(?, ?, ? )");
                ps.setString(1, productID);
                ps.setString(2, r.getIngredient());
                ps.setDouble(3, r.getMeasurement());
                  
                  ps.executeUpdate();
            } catch (SQLException ex) 
            {
                System.out.println("");
            }
                
            }
        return true;
    }

    @Override
    public boolean editIngredient(Ingredient ingred) 
    {
        String id = ingred.getName();
        try
            {
                ps = con.prepareStatement("UPDATE ingredient SET Quantity = '"+ingred.getQuantity()+"'"
                        + " WHERE Name LIKE '"+id+"'");
                ps.executeUpdate();
                
            }
        catch (SQLException ex) 
         {
             System.out.println("Error at Edit Product : "+ex.getMessage());
             return false;
         }
        return true;
    }

    @Override
    public ArrayList<Ingredient> getIngredients() 
    {
        //Creating a new List of Ingredients
        ArrayList<Ingredient> tempIngreds = new ArrayList<Ingredient>();
        // Creating an Ingredient reference here
        Ingredient tempIngred;
        try 
         {
             // creating a statement to retrieve a result set of uIngredients
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT Name, Quantity FROM ingredient");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new Ingredient aka template
                        tempIngred = new Ingredient(rs.getString("Name"),rs.getDouble("Quantity"));
                        // returning now that new user object to the List created at start of method
                        tempIngreds.add(tempIngred);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        return tempIngreds; // giving back the ArrayList here
    }

    @Override
    public boolean deleteIngredient(String ingredientName) 
    {
        try 
         {
             //must pass no whitespaces due to equals sign
             ps = con.prepareStatement("DELETE * FROM ingredient WHERE Name = '"+ingredientName+"'");
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
        return true;
    }

    @Override
    public Product getProduct(String productName) 
    {
        Product product= null;
        
        try {
            ps=con.prepareStatement("SELECT Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin FROM product WHERE Name = '"+productName+"'");
            rs = ps.executeQuery();
            
            while(rs.next())
                {
                    product = new Product();
                    product.setName(rs.getString("Name"));
                    product.setPrice(rs.getDouble("Price"));
                    product.setDescription(rs.getString("Description"));
                    product.setWarnings(rs.getString("Warning"));
                    product.setNutritionalInformation(rs.getString("Information"));
                    product.setPictureOfCreation(rs.getString("Image"));
                    product.setQuantity(rs.getInt("Quantity"));
                    product.setDiscountMargin(rs.getDouble("DiscountMargin"));
                    
                }
        } catch (SQLException ex) 
                {
                    System.out.println("Error at Edit User Profile returning blank: "+ex.getMessage());
                    return new Product();
                }
        return product;
    }

    @Override
    public Ingredient getIngredient(String ingredientName) 
    {
        Ingredient tempIngred = new Ingredient();
         try 
         {
             ps = con.prepareStatement("SELECT Name, Quantity FROM ingredient WHERE Name ='"+ingredientName+"'");
             rs = ps.executeQuery();
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        
        try
            {
                while(rs.next())
                {
                    tempIngred.setName(rs.getString("Name"));
                    tempIngred.setQuantity(rs.getDouble("Quantity"));
                }
                
        }catch(SQLException ex)
        {
            System.out.println("Error at get Ingredient x: "+ex.getMessage());
        }
        
              
        
        return tempIngred;
    }

    @Override
    public Recipe getRecipeItem(String productName, String ingredientName) 
    {
        Recipe tempRecipe = new Recipe();
         try 
         {
             ps = con.prepareStatement("SELECT Product, Ingredient, Unit FROM productingredient WHERE Product ='"+productName+"' AND Ingredient = '"+ingredientName+"'");
             rs = ps.executeQuery();
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        
        try
            {
                while(rs.next())
                {
                    tempRecipe.setProduct(rs.getString("Product"));
                    tempRecipe.setIngredient(rs.getString("Ingredient"));
                    tempRecipe.setMeasurement(rs.getDouble("Unit"));
                }
                
        }catch(SQLException ex)
        {
            System.out.println("Error at get Recipe Item x: "+ex.getMessage());
        }
        
              
        
        return tempRecipe;
        
    }
    
}
