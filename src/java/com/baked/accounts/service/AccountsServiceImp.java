/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.accounts.service;

import com.baked.accounts.dao.AccountsDAOImp;
import com.baked.accounts.dao.AccountsDAOInterface;
import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khwezi
 */
public class AccountsServiceImp {
    private AccountsDAOInterface accDao;
    
    public AccountsServiceImp(){
        accDao = new AccountsDAOImp();
    }
    
    public User login(String email, String password){
        return accDao.login(email, getSecurePassword(password));
    }
    public boolean signUp(User user){
        user.setPassword(getSecurePassword(user.getPassword()));
        return accDao.signUp(user);
    }
    
    public boolean editUserProfile(User user){
        return accDao.editUserProfile(user);
    }
    public boolean deleteUser(String userId){
        return accDao.deleteUser(userId);
    }
    public ArrayList<User>getAllUsers(){
        return accDao.getAllUsers();
    }
    public ArrayList<User>getUserByName(String name){
        return accDao.getUserByName(name);
    }
    public boolean addUserPayment(Payment payment){
        return accDao.addUserPayment(payment);
    }
    public boolean deleteUserPayment(String cardNo,String userID){
        return accDao.deleteUserPayment(cardNo, userID);
    }
    public ArrayList<Payment>getAllUserPayments(String userID){
        return accDao.getAllUserPayments(userID);
    }
    public boolean addAddress(Address address,String userID){
        return accDao.addAddress(address, userID);
    }
    public ArrayList<Address> getAllUserAddress(String userID){
        return accDao.getAllUserAddress(userID);
    }
    public boolean deleteUserAddress(int userAddressID,String userID){
        return accDao.deleteUserAddress(userAddressID, userID);
    }
    public boolean passwordRecovery(String email){
        return true;
    }
    public boolean passwordRest(String userID, String newPassword){
        return accDao.passwordRest(userID,newPassword);
    }
    
     private static String getSecurePassword(String passwordToHash){
        byte[]salt= null ;
        try {
            salt = getSalt();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountsServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
