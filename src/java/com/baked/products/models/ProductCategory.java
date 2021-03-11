
package com.baked.products.models;


public class ProductCategory {

    private String productId;
    private Integer categoryId;
    //**************************************************************************
    public ProductCategory(String prod, int cat ) {
      setProductId(prod);
      setCategoryId(cat);
    }   
    public ProductCategory(int cat){
         setCategoryId(cat);
    }
    public ProductCategory(){}
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "productId=" + productId + ", categoryId=" + categoryId + '}';
    }
    
    

}
