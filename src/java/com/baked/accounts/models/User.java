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
public class User {
    private String name;
    private String surname;
    private String title;
    private Integer role;
    private String idNumber;
    private String password;
    private String homeTelephoneNumber;
    private String mobileTelephoneNumber;
    private String emailAddress;
    
    public User(String name,String surname,String password,String title,int role,String idNumber, String homeTelephoneNumber,String mobileTelephoneNumber,String emailAddress){
        setName(name);
        setSurname(surname);
        setPassword(password);
        setTitle(title);
        setRole(role);
        setIdNumber(idNumber);
        setHomeTelephoneNumber(homeTelephoneNumber);
        setMobileTelephoneNumber(homeTelephoneNumber);
        setEmailAddress(emailAddress);
    }
    public User(){}
    

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

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the role
     */
    public Integer getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the homeTelephoneNumber
     */
    public String getHomeTelephoneNumber() {
        return homeTelephoneNumber;
    }

    /**
     * @param homeTelephoneNumber the homeTelephoneNumber to set
     */
    public void setHomeTelephoneNumber(String homeTelephoneNumber) {
        this.homeTelephoneNumber = homeTelephoneNumber;
    }

    /**
     * @return the mobileTelephoneNumber
     */
    public String getMobileTelephoneNumber() {
        return mobileTelephoneNumber;
    }

    /**
     * @param mobileTelephoneNumber the mobileTelephoneNumber to set
     */
    public void setMobileTelephoneNumber(String mobileTelephoneNumber) {
        this.mobileTelephoneNumber = mobileTelephoneNumber;
    }

    /**
     * @return the emailAdress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAdress the emailAdress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
