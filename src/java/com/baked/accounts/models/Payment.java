/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.accounts.models;



/**
 *
 * @author khwezi
 */
public class Payment {
    private String cardNumber;
    private String expireryDate;
    private String cvvCode;
    private String cardType;
    private String userID;
    
    public Payment(String cardNumber,String expireryDate,String cvvCode,String cardType,String userID ){
        setCardNumber(cardNumber);
        setExpireryDate(expireryDate);
        setCvvCode(cvvCode);
        setCardType(cardType);
        setUserID(userID);
        
    }
    public Payment(){}

    /**
     * @return the cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * @param cardNumber the cardNumber to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * @return the expireryDate
     */
    public String getExpireryDate() {
        return expireryDate;
    }

    /**
     * @param expireryDate the expireryDate to set
     */
    public void setExpireryDate(String expireryDate) {
        this.setExpireryDate(expireryDate);
    }

    /**
     * @return the cvvCode
     */
    public String getCvvCode() {
        return cvvCode;
    }

    /**
     * @param cvvCode the cvvCode to set
     */
    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    
    

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
   
    
    
}
