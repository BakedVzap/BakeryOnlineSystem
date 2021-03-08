
package com.baked.accounts.models;



/**
 *
 * @author UnicornBrendan
 */
public class Payment {
    private String cardNumber;
    private String expiryDate;
    private String cvvCode;
    private String cardType;
    private String userID;
    
    public Payment(String cardNumber,String expiryDate,String cvvCode,String cardType,String userID ){
        setCardNumber(cardNumber);
        setExpiryDate(expiryDate);
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
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.setExpiryDate(expiryDate);
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
