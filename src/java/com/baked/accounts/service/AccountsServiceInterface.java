/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.accounts.service;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import java.util.ArrayList;

/**
 *
 * @author khwezi
 */
public interface AccountsServiceInterface {
    User login(String email,String password);
    boolean signUp(User user);
    boolean editUserProfile(User user);
    boolean deleteUser(String userId);
    ArrayList<User>getAllUsers();
    ArrayList<User>getUserByName(String name);
    boolean addUserPayment(Payment payment);
    boolean deleteUserPayment(String cardNo,String userID);
    ArrayList<Payment>getAllUserPayments(String userID);
    boolean addAddress(Address address,String userID);
    ArrayList<Address> getAllUserAddress(String userID);
    boolean passwordRecovery(String email);
    boolean passwordRest(String userID, String newPassword);
    boolean deleteUserAddress(int userAddressID,String userID);
}
