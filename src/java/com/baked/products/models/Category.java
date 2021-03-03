/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.products.models;

/**
 *
 * @author khwezi
 */
public class Category {
    private Integer cateId;
    private String name;
    
    public Category(String name){
        setName(name);
    }
    public Category(String name,int cateId){
        
    }
    public Category(){}

    /**
     * @return the cateId
     */
    public Integer getCateId() {
        return cateId;
    }

    /**
     * @param cateId the cateId to set
     */
    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
