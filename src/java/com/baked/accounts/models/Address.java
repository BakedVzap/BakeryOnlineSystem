
package com.baked.accounts.models;


/**
 *
 * 
 */
public class Address {
    private String id;
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String city;
    
    public Address(String streetNumber,String streetName, String suberb,String city){
        setStreetNumber(streetNumber);
        setStreetName(streetName);
        setSuberb(suberb);
        setCity(city);
        setId();
    }

    public Address() 
    {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId() {
        this.id =  getStreetNumber()+
        getStreetName()+
        getSuberb()+
        getCity();
    }

    /**
     * @return the streetNumber
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the suburb
     */
    public String getSuberb() {
        return suburb;
    }

    /**
     * @param suberb the suburb to set
     */
    public void setSuberb(String suberb) {
        this.suburb = suberb;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    
}
