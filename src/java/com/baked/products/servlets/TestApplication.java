
package com.baked.products.servlets;

import com.baked.products.models.Product;
import com.baked.products.service.ProductsServiceImp;
import com.baked.rest.ProductRestClient;


public class TestApplication {
    public static void main(String [] args) {
        //ProductRestClient restEasy=new ProductRestClient();
        
        Product prodct=new ProductsServiceImp().getProduct("BlackForest");
       // Product prodct=restEasy.getProduct("BlackForest");
        System.out.println(prodct.toString());
    }
}
