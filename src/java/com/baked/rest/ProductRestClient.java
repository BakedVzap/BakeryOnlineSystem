
package com.baked.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.baked.products.models.*;
public class ProductRestClient {

     public Product getProduct(String productNameID){
        String url = "http://localhost:8080/BakeryRest/baked/accounts/login/{productNameID}";
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target(url).resolveTemplate("productNameID", productNameID);
        Product prod = null;
        try {
            prod = new ObjectMapper().readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),Product.class);
        } catch (IOException ex) {
            Logger.getLogger(ProductRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prod;
    }
    //**************************************************************************
    public String addProduct(Product product){

        String url = "http://localhost:8080/BakeryRest/baked/products/addProduct";
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target(url);
       Response response =  webTarget.request().post(Entity.json(stringing(product)));
       //InvocationBuilder ib = webTarget.request();
       //response=ib.post(Entity.json(String(User)));
       return response.readEntity(String.class);
    }
     
   public String editProduct(Product product) {
       String url = "http://localhost:8080/BakeryRest/baked/accounts/editProduct";
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target(url);
       Response response =  webTarget.request(MediaType.APPLICATION_JSON).put(Entity.json(stringing(product)));
       return response.readEntity(String.class);
   }
   
   public String deleteProduct(String productNameID){
       String url = "http://localhost:8080/BakeryRest/baked/accounts/deleteProduct/{productNameID}";
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target(url).resolveTemplate("productNameID", productNameID);
        Response response =  webTarget.request(MediaType.APPLICATION_JSON).delete();
        return response.readEntity(String.class);
   }
   public ArrayList<Product> getAllProduct(){
       String url = "http://localhost:8080/BakeryRest/baked/products/getAllProducts";
       ArrayList<Product> products = new ArrayList();
        Client restClient = ClientBuilder.newClient();
        WebTarget webTarget = restClient.target(url);
        try {
            products =  new ObjectMapper().readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class),ArrayList.class);
        } catch (IOException ex) {
            Logger.getLogger(ProductRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
       return products;
   }
   private String stringing(Object type){
        try {
            return new ObjectMapper().writeValueAsString(type); //Writes any object as a Json String
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
