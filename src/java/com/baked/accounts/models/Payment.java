/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.accounts.models;

import java.util.Date;

/**
 *
 * @author khwezi
 */
public class Payment {
    private String cardNumber;
    private Date expireryDate;
    private String cvvCode;
    private String cardType;

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
    public Date getExpireryDate() {
        return expireryDate;
    }

    /**
     * @param expireryDate the expireryDate to set
     */
    public void setExpireryDate(Date expireryDate) {
        this.expireryDate = expireryDate;
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
   
    
    
}
