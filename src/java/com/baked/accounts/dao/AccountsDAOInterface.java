
package com.baked.accounts.dao;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public interface AccountsDAOInterface {
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
    boolean deleteUserAddress(int userAddressID,String userID);
    boolean passwordRest(String userID,String newPassword);
    public ArrayList<Address> getUserAddress(String userID);
}
