
package com.baked.products.models;


public class ProductCategory {

    private String productId;
    private String categoryId;
    //**************************************************************************
    public ProductCategory(Product prod, Category cat ) {
        this.productId = prod.getName();
        this.categoryId=cat.getName();
    }   
    public ProductCategory(Product prod){
        this.productId = prod.getName();
    }
    public ProductCategory(Category cat){
        this.categoryId = cat.getName();
    }
    public ProductCategory(){}
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId.getName();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId.getName();
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "productId=" + productId + ", categoryId=" + categoryId + '}';
    }
    
    

}
