
package com.baked.rest;

import com.baked.products.models.Category;
import com.baked.products.models.Ingredient;
import com.baked.products.models.Product;
import com.baked.products.models.Recipe;
import com.baked.products.service.ProductsServiceImp;
import com.baked.products.service.ProductsServiceInterface;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductsRest {
private ProductsServiceInterface prodImp;
    
    public ProductsRest(){
        prodImp = new ProductsServiceImp();
    }
    
    @GET
    @Path("/product/{productName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("productName")String prodName){
        return prodImp.getProduct(prodName);
    }
    //**************************************************************************
    @POST
    @Path("/addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Product prod){
        if((prodImp.getProduct(prod.getName())!=null)){
            System.out.println("Product already exists!!!");
            return Response.status(Response.Status.OK).entity("Product adding was unsuccessful.").build();
        }else {
            ArrayList<Category> allProdCat = prod.getCategories();
            ArrayList<Recipe> recipes= prod.getRecipes();
            for(Category cat: allProdCat){
                prodImp.addProuctCategory(cat, prod);           
            } for(Recipe recipe: recipes) { 
                prodImp.addProductRecipe(recipe);   //The recipe constructor has a productName that can be used as a key that links the prod&recipe on the database.
            }
            prodImp.addProduct(prod);
            return Response.status(Response.Status.OK).entity("Adding product was successful.").build();
        }
    }
    //**************************************************************************
    @PUT
    @Path("/editProduct/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editProduct(Product product){
        if((prodImp.editProduct(product))){
         return Response.status(Response.Status.OK).entity("Editing Product was successful.").build();   
        }    
        return Response.status(Response.Status.OK).entity("Editing product was unsuccessful!!!").build();
    }
    //**************************************************************************
    @DELETE
    @Path("/deleteProduct/{productNameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteProduct(@PathParam("productNameId")String productName){
        return prodImp.deleteProduct(productName);
    }
    //**************************************************************************
    @GET
    @Path("/getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Product> getAllProducts(){
        return prodImp.getAllProducts();
    }
     @GET
    @Path("/getProductByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Product> getProductByName(@PathParam("name")String name){
        return prodImp.getProductsByName(name);
    }
    //**************************************************************************
    @POST
    @Path("/addCategory/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCategory(Category category){
       if((prodImp.addCategory(category))) {
            return Response.status(Response.Status.OK).entity("Adding a new Category was successful.").build();
       }    
       return Response.status(Response.Status.OK).entity("Adding a new Category was unsuccessful.").build();
    }
    //**************************************************************************
//    @PUT
//    @Path("/deleteCategory/{categoryName}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Boolean deleteCategory(@PathParam("categoryName")String catName){
//        return prodImp.deleteCategory(catName);
//    }
    //**************************************************************************
    @GET
    @Path("/getAllCategories/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Category> getAllCategories(){
        return prodImp.getCategories();
    }
    //**************************************************************************
    @POST
    @Path("/addIngredient/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIngredient(Ingredient ingred) {
        if((prodImp.addIngredients(ingred))){
            return Response.status(Response.Status.OK).entity("Product was successfully added").build();
        }
        return Response.status(Response.Status.OK).entity("The operation was unsuccessful!!!").build();
    }
    //**************************************************************************
    @GET
    @Path("/getIngredient/{ingredName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingredient getIngrdient(@PathParam("ingredName")String name){
        return prodImp.getIngredient(name);
    }
    //**************************************************************************
    @PUT
    @Path("/editIngredient/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editIngredient(Ingredient ingred){
        if((prodImp.editIngredient(ingred))) {
            return Response.status(Response.Status.OK).entity("Ingredient edit was successful.").build();
        }
        return Response.status(Response.Status.OK).entity("Ingredient edit was unsuccessful!!!").build();
    }
    //**************************************************************************
    @GET
    @Path("/getRecipe/{productName}/{ingredName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Recipe getRecipe(@PathParam("productName")String prodName, @PathParam("ingredName")String ingred){
       return prodImp.getRecipeItem(prodName, ingred);
    }
    //**************************************************************************
    @GET
    @Path("/getAllRecipesWithName/{productName}")
    public ArrayList<Recipe> getRecipes(@PathParam("productName")String prodName){
        return prodImp.getRecipes(prodName);
        
    }
   
}
