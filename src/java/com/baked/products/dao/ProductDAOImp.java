
package com.baked.products.dao;

import com.baked.databaseManager.DBManager;
import com.baked.products.models.Category;
//import testproductdao.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.ProductCategory;
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
//import testproductdao.ProductCategory;

/**
 *
 * @author UnicornBrendan
 */
public class ProductDAOImp implements ProductsDAOInterface {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement myStmt;
    public ProductDAOImp() throws SQLException
    {
            con = DBManager.getConnection();
    }

    @Override
    public ArrayList<Recipe> getRecipes(String productName) 
    {
         ArrayList<Recipe> tempRecipes= new ArrayList<Recipe>();
         Recipe tempRecipe;
        try 
         {
            // myStmt = con.createStatement();
             con = DBManager.getConnection();
             ps=con.prepareStatement("SELECT Product , Ingredient, Unit FROM productingredient WHERE Product LIKE '%"+productName+"%'");
             
             
             rs = ps.executeQuery();
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    { 
                        tempRecipe = new Recipe();
                        tempRecipe.setProduct(rs.getString("Product"));
                        tempRecipe.setIngredient(rs.getString("Ingredient"));
                        tempRecipe.setMeasurement(rs.getDouble("Unit"));
                     
                        
                        tempRecipes.add(tempRecipe);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll getUserAddresses: "+ex.getMessage());
        }
        finally{
            if(con!=null && ps!=null) {
                try{
                    ps.close();
                    con.close();
                }catch(SQLException e) {
                    e.printStackTrace();
                }
            } 
        }
        return tempRecipes;   
    }



    @Override
    public boolean addProduct(Product product) {
        try {
            ps = con.prepareStatement("INSERT INTO PRODUCT(Name,Price,Description,Warning,Information,Image,Quantity,"
              +"DiscountMargin,Deleted)Values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1,product.getName());
            ps.setDouble(2,product.getPrice());
            ps.setString(3,product.getDescription());
            ps.setString(4,product.getWarnings());
            ps.setString(5,product.getNutritionalInformation());
            ps.setString(6,product.getPictureOfCreation());
            ps.setInt(7,product.getQuantity());
            ps.setDouble(8,product.getDiscountMargin());
            ps.setBoolean(9,false);
            
            if(ps.executeUpdate()==1)
            {
                ps.close();
               if(addRecipes(product.getRecipes(),product.getName()))
               {
                  if(addProductCategories(product.getCategories(),product.getName()))
                  {
                      return true;
                  } 
               }
                
            }
                    
        } catch (SQLException ex) {
            System.out.println("in addProduct");
            System.out.println(ex.getMessage());
            try {
                ps.close();
            } catch (SQLException ex1) {
                System.out.println(ex1.getMessage());
            }
        }
        
        return false;
    }
   
    private boolean addProductCategories(ArrayList<ProductCategory>categories,String productName){
         for(ProductCategory cate:categories){
            try {
                ps = con.prepareStatement("INSERT INTO CATEGORYPRODUCT(Product,Category) values(?,?)");
                ps.setString(1,productName);
                ps.setInt(2,cate.getCategoryId());
                
               if(ps.executeUpdate()==1){
                   ps.close();
               }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("in addcate");
                return false;
            }
            
        }
        return true;
    }
    private boolean addRecipes(ArrayList<Recipe>recipes,String productName){
        for(Recipe recipe:recipes){
            try {
                ps = con.prepareStatement("INSERT INTO PRODUCTINGREDIENT(Product,Ingredient,Unit) values(?,?,?)");
                ps.setString(1,productName);
                ps.setString(2,recipe.getIngredient());
                ps.setDouble(3, recipe.getMeasurement());
               if(ps.executeUpdate()==1){
                   ps.close();
               }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println("in addrecipes");
                return false;
            }
            
        }
        return true;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList();
          try {
              ps = con.prepareStatement("SELECT Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin FROM PRODUCT");
              rs = ps.executeQuery();
              while(rs.next()){
                  products.add(new Product(rs.getString("Name"),rs.getString("Description"),rs.getString("Warning"),rs.getString("Information"),rs.getString("Image"),rs.getInt("Quantity"),
                          rs.getDouble("Price"),rs.getDouble("DiscountMargin")));
              }
          } catch (SQLException ex) 
          {
          }
          try {
              rs.close();
          } catch (SQLException ex) 
          {
          }
          for(Product p:products)
          {
             p.setCategories(getProductCategories(p.getName()));
          }
          for(Product p:products)
          {
              p.setRecipe(getProductIngredient(p.getName()));
          }
          
          return products;
    }
     private ArrayList<ProductCategory> getProductCategories(String productName){
         ArrayList<ProductCategory> categoriesOFProduct = new ArrayList();
          try {
              ps = con.prepareStatement("Select Product,Category from CATEGORYPRODUCT where product = ?");
              ps.setString(1,productName);
              rs = ps.executeQuery();
              while(rs.next())
              {
                  categoriesOFProduct.add(new ProductCategory(rs.getString("Product"),rs.getInt("Category")));
              }
              
          } catch (SQLException ex) 
          {
          }
          finally{
              if(rs!=null){
                  try {
                      rs.close();
                  } catch (SQLException ex) 
                  {
                  }
              }
          }
          return categoriesOFProduct;
    }
    private ArrayList<Recipe> getProductIngredient(String productName){
         ArrayList<Recipe> recipes = new ArrayList();
          try {
              ps = con.prepareStatement("Select Product,Ingredient,Unit from ProductIngredient where product = ?");
              ps.setString(1,productName);
              rs = ps.executeQuery();
              while(rs.next()){
                  recipes.add(new Recipe(rs.getString("Product"),rs.getString("Ingredient"),rs.getDouble("Unit")));
              }
              
          } catch (SQLException ex) 
          {
          }
          finally{
              if(rs!=null){
                  try {
                      rs.close();
                  } catch (SQLException ex) 
                  {
                  }
              }
          }
          return recipes;
    }

//    @Override
//    public boolean addProduct(Product product) 
//    {
//         try
//            {
//                 ps = con.prepareStatement("INSERT INTO product( Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin) VALUES (?, ?, ?, ?, ?, ?, ?, ? )");
//                 ps.setString(1, product.getName());
//                 ps.setDouble(2, product.getPrice());
//                 ps.setString(3, product.getDescription());
//                 ps.setString(4, product.getWarnings());
//                 ps.setString(5, product.getNutritionalInformation());
//                 ps.setString(6, product.getPictureOfCreation());
//                 ps.setInt(7, product.getQuantity());
//                 ps.setDouble(8, product.getDiscountMargin());
//                 
//                 ps.executeUpdate();
//            }catch(SQLException ex)
//                {
//                    System.out.println("Error at add product : "+ex.getMessage());
//                    return false;
//                }
//        
//        try
//            {
//                 ps = con.prepareStatement("INSERT INTO productingredient( Product, Ingredient , Unit) VALUES (?, ?, ?)");
//                 ps.setString(1, product.getName());
//                 ps.setString(2, product.getDescription());
//                 ps.setDouble(3, 0.0);
//                 
//                 ps.executeUpdate();
//            }catch(SQLException ex)
//                {
//                    System.out.println("Error at add product : "+ex.getMessage());
//                    return false;
//                }
//        try
//            {
//                if(ps.isClosed()!=true)
//                {
//                ps.close();
//                }
//            }catch(SQLException ex)
//                {
//                    System.out.println("Error : "+ex.getMessage());
//                }
//        return true;
//    }

//    @Override
//    public ArrayList<Product> getAllProducts() 
//    {
//        ArrayList<Product> tempProducts = new ArrayList<Product>();
//        Product tempProduct;
//        try 
//         {
//             ps = con.prepareStatement("SELECT Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin FROM product WHERE Deleted=0"); 
//             rs = ps.executeQuery();
//         } catch (SQLException ex) 
//         {
//             System.out.println("Error at : "+ex.getMessage());
//         }
//        try
//            {
//                while(rs.next())
//                    { 
//                        tempProduct= new Product();
//                        tempProduct.setName(rs.getString("Name"));
//                        tempProduct.setPrice(rs.getDouble("Price"));
//                        tempProduct.setDescription(rs.getString("Description"));
//                        tempProduct.setWarnings(rs.getString("Warning"));
//                        tempProduct.setNutritionalInformation(rs.getString("Information"));
//                        tempProduct.setPictureOfCreation(rs.getString("Image"));
//                        tempProduct.setQuantity(rs.getInt("Quantity"));
//                        tempProduct.setDiscountMargin(rs.getDouble("DiscountMargin"));
//                        
//                        tempProducts.add(tempProduct);
//                }
//        }catch(SQLException ex)
//        {
//            System.out.println("Error at GetAll products: "+ex.getMessage());
//        }
//        try
//            {
//                if(ps.isClosed()!=true)
//                {
//                ps.close();
//                }
//            }catch(SQLException ex)
//                {
//                    System.out.println("Error : "+ex.getMessage());
//                }
//        return tempProducts;    
//    }

//    @Override
//    public ArrayList<Product> getProductsByCategory() // public ArrayList<Product> getProductsByCategory(Category category)
//    {
//        
//    }

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
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
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
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

    @Override
    public ArrayList<Category> getCategories() 
    {
        ArrayList<Category> tempCats = new ArrayList<Category>();
        Category tempCat;
        try 
         {
             ps = con.prepareStatement("SELECT ID, Name FROM category"); 
             rs = ps.executeQuery();
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    { 
                        tempCat = new Category();
                        tempCat.setCateId(rs.getInt("ID"));
                        tempCat.setName(rs.getString("Name"));
                        
                        
                        tempCats.add(tempCat);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at Get Categories: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempCats; 
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
             System.out.println("Error at Edit Category: "+ex.getMessage());
             return false;
         }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
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
         try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

    @Override
    public boolean deleteProduct(String productName) 
    {
         try 
         {
             ps = con.prepareStatement("UPDATE product SET Deleted = 1 WHERE Name = '"+productName+"'");
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
         try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

    
    private boolean addProductRecipe(ArrayList<Recipe> recipes, String productID) 
    {
        Recipe r1 ;
        for(Recipe r : recipes)
            {
                try 
                {
                    ps = con.prepareStatement("INSERT INTO productingredient(Product, Ingredient, Unit) Values(?, ?, ? )");
                    ps.setString(1, productID);
                    ps.setString(2, r.getIngredient());
                    ps.setDouble(3, r.getMeasurement());

                      ps.executeUpdate();
                } catch (SQLException ex) 
                    {
                        System.out.println("");
                    } finally
                        {
                            return true;
                        }
            }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
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
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

    @Override
    public ArrayList<Ingredient> getIngredients() 
    {
        ArrayList<Ingredient> tempIngreds = new ArrayList<Ingredient>();
        Ingredient tempIngred=null;
        try 
         {
             ps = con.prepareStatement("SELECT Name, Quantity FROM ingredient"); 
           //  rs = myStmt.executeQuery("SELECT Name, Quantity FROM ingredient");
           rs = ps.executeQuery();
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    { 
                        tempIngred = new Ingredient(rs.getString("Name"),rs.getDouble("Quantity"));
                        tempIngreds.add(tempIngred);
                    }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        return tempIngreds;
    }

    @Override
    public boolean deleteIngredient(String ingredientName) 
    {
        try 
         {
             ps = con.prepareStatement("Update ingredient SET Deleted = 1 WHERE Name LIKE '%"+ingredientName+"%'");
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

    @Override
    public Product getProduct(String productName) 
    {
        Product product= null;
        
        try {
            ps=con.prepareStatement("SELECT Name, Price, Description, Warning, Information, Image, Quantity, DiscountMargin FROM product WHERE Name = '"+productName+"' AND Deleted = 0");
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
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return product;
    }

    @Override
    public Ingredient getIngredient(String ingredientName) 
    {
        Ingredient tempIngred = new Ingredient();
         try 
         {
             ps = con.prepareStatement("SELECT Name, Quantity, Deleted FROM ingredient WHERE Name ='"+ingredientName+"'");
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
                    if((rs.getInt(3))==0)
                        {
                            tempIngred.setDeleted(false);
                        }
                    if((rs.getInt(3))==1)
                        {
                            tempIngred.setDeleted(true);
                        }
                }
                
        }catch(SQLException ex)
        {
            System.out.println("Error at get Ingredient x: "+ex.getMessage());
        }
        
              
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempIngred;
    }

  
    private Recipe getRecipeItem(String productName, String ingredientName) 
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
        
              
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempRecipe;
        
    }

    @Override
    public ArrayList<Product> getProductsByCategory(Category category) 
    {
        
         ArrayList<Product> tempProducts = new ArrayList<Product>();
        Product tempProduct = null;
        category.getCateId();
        try 
         {
            // myStmt = con.createStatement(); 
            // rs = myStmt.executeQuery("SELECT Product FROM categoryproduct WHERE Category = "+category.getCateId()+"");
             ps = con.prepareStatement("SELECT Product FROM categoryproduct WHERE Category = "+category.getCateId()+"");
             rs = ps.executeQuery();
             
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        
        try 
        {
            while(rs.next())
                {
                    tempProducts.add(getProduct(rs.getString("Product")));
                }
        } catch (SQLException ex) 
        {
        }
                
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempProducts;    
    }
    private boolean addProductRecipe(Recipe recipe) 
    {
        Recipe r1 ;
        
       
            try {
                ps = con.prepareStatement("INSERT INTO productingredient(Product, Ingredient, Unit) Values(?, ?, ? )");
                ps.setString(1, recipe.getProduct());
                ps.setString(2, recipe.getIngredient());
                ps.setDouble(3, recipe.getMeasurement());
                  
                  ps.executeUpdate();
            } catch (SQLException ex) 
            {
                System.out.println("");
            }
                
           try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                } 
        return true;
    }

    @Override
    public Category getCategory(Integer category) 
    {
        Category tempCat = new Category();
        try 
         {
             ps = con.prepareStatement("SELECT ID, Name FROM category WHERE ID = "+category+"");
            rs= ps.executeQuery();
//             myStmt = con.createStatement(); 
//             rs = myStmt.executeQuery("SELECT ID, Name FROM category WHERE ID = "+category+"");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    { 
                        tempCat.setCateId(rs.getInt("ID"));
                        tempCat.setName(rs.getString("Name"));
                    }
        }catch(SQLException ex)
        {
            System.out.println("Error at get Categories: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempCat;
    }

   

    @Override
    public Category getCategory(Category category) 
    {
        Category tempCat = new Category();
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT ID, Name FROM category WHERE ID = "+category.getCateId()+"");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    { 
                        tempCat.setCateId(rs.getInt("ID"));
                        tempCat.setName(rs.getString("Name"));
                    }
        }catch(SQLException ex)
        {
            System.out.println("Error at get Categories: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempCat;
    }

   

    
    private boolean addProductCategory(Category category, Product prod) 
    {
        
        try
            {
                 ps = con.prepareStatement("INSERT INTO categoryproduct(Category, Product) VALUES (?, ?)");
                 ps.setString(1, prod.getName());
                 ps.setString(2, prod.getDescription());
                 ps.setDouble(3, 0.0);
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at  add product Category : "+ex.getMessage());
                    return false;
                }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
        
    }

    
    private ArrayList<Category> getAllCategoryOfProduct(Product prod) 
    {
        Category cat= null;
        ArrayList<Category>categories=null;
        try
            {
                ps = con.prepareStatement("SELECT Category FROM categoryproduct WHERE Product = '"+prod.getName()+"'");
                rs = ps.executeQuery();
                
                ps = con.prepareStatement("SELECT Name FROM category WHERE ID = "+rs.getInt("Category")+"");
                rs = ps.executeQuery();
                while(rs.next())
                    {
                        cat.setCateId(rs.getInt(1));
                        cat.setName(rs.getString(2));
                        
                        categories.add(cat);
                    }
            }catch(SQLException ex)
                {
                    System.out.print("Error : "+ex.getMessage());
                }
        finally
            {
                try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
                return categories;
            }
        
    }

    @Override
    public ArrayList<Product> getProductsByName(String name) 
    {
        ArrayList<Product>products = null;
        Product product = null;
        try
            {
                ps = con.prepareStatement("SELECT * FROM product WHERE name LIKE '%"+name+"%' AND Deleted = 0");
                rs = ps.executeQuery();
                while(rs.next())
                    {
                        product.setName(rs.getString("Name"));
                        product.setPrice(rs.getDouble("Price"));
                        product.setDescription(rs.getString("Description"));
                        product.setWarnings(rs.getString("Warning"));
                        product.setNutritionalInformation(rs.getString("Information"));
                        product.setPictureOfCreation(rs.getString("Image"));
                        product.setQuantity(rs.getInt("Quantity"));
                        product.setDiscountMargin(rs.getDouble("DiscountMargin"));
                           
                        products.add(product);
                    }
            }catch(SQLException ex)
                {
                    System.out.println(" Error :"+ex.getMessage());
                }
        finally
            {
                try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
                return products;
            }
    }

    
    
}
