
package com.baked.accounts.models;

/**
 *
 * @author UnicornBrendan
 */
public class Address {
    private String id;
    private String streetNumber;
    private String streetName;
    private String suberb;
    private String city;
    
    public Address(){}
    public Address(String id,String streetNumber,String streetName, String suberb,String city){
        setId(id);
        setStreetNumber(streetNumber);
        setStreetName(streetName);
        setSuberb(suberb);
        setCity(city);
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
    public void setId(String id) {
        this.id=id;
        
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
     * @return the suberb
     */
    public String getSuberb() {
        return suberb;
    }

    /**
     * @param suberb the suberb to set
     */
    public void setSuberb(String suberb) {
        this.suberb = suberb;
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
