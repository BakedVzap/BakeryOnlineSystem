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
import javax.mail.MessagingException;

/**
 *
 * @author khwezi
 */
public class AccountsServiceImp implements AccountsServiceInterface {
    private final AccountsDAOInterface accDao;
    
    public AccountsServiceImp(){
        accDao = new AccountsDAOImp();
    }
    
    @Override
    public User login(String email, String password){
        return accDao.login(email, getSecurePassword(password));
    }
    @Override
    public boolean signUp(User user){
        user.setPassword(getSecurePassword(user.getPassword()));
        return accDao.signUp(user);
    }
    
    @Override
    public boolean editUserProfile(User user){
        return accDao.editUserProfile(user);
    }
    @Override
    public boolean deleteUser(String userId){
        return accDao.deleteUser(userId);
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<User>getAllUsers(){
        return accDao.getAllUsers();
    }
    @Override
    public ArrayList<User>getUserByName(String name){
        return accDao.getUserByName(name);
    }
    @Override
    public boolean addUserPayment(Payment payment){
        return accDao.addUserPayment(payment);
    }
    @Override
    public boolean deleteUserPayment(String cardNo,String userID){
        return accDao.deleteUserPayment(cardNo, userID);
    }
    @Override
    public ArrayList<Payment>getAllUserPayments(String userID){
        return accDao.getAllUserPayments(userID);
    }

    @Override
    public boolean addAddress(Address address,String userID){

        return accDao.addAddress(address, userID);
    }
    @Override
    public ArrayList<Address> getAllUserAddress(String userID){
        return accDao.getAllUserAddress(userID);
    }
    @Override
    public boolean deleteUserAddress(int userAddressID,String userID){
        return accDao.deleteUserAddress(userAddressID, userID);
    }
    @Override
    public boolean passwordRecovery(String email){
        try {
            new EmailSender(email,"Password Recovery","To reset your password please click on this link: ");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
     @Override
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
