package com.baked.accounts.dao;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import com.baked.databaseManager.DBManager;
import com.baked.ordering.models.Order;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public class AccountsDAOImp implements AccountsDAOInterface {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public AccountsDAOImp() {
        try {
            con = DBManager.getConnection();

            if (con != null) {
                System.out.println("Your connection to The Dough Knot Database is not null and your program is connected");
            }
            if (con == null) {
                System.out.println("Your connection to The Dough Knot Database is null and your program is not conected");
            }
        } catch (SQLException ex) {
            System.out.println("Connection not established");
        }
    }

    /////**********************THIS WORKS,!******************************/////
    @Override
    public User login(String email, String password) {
        User tempUser = new User();
        try {
            ps = con.prepareStatement("SELECT * FROM user WHERE Email LIKE ? AND Password LIKE ?");
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            while (rs.next()) {
                if (rs != null) {
                    tempUser.setName(rs.getString("Name"));
                    tempUser.setSurname(rs.getString("Surname"));
                    tempUser.setPassword(rs.getString("Password"));
                    tempUser.setTitle(rs.getString("Title"));
                    tempUser.setRole(rs.getInt("Role"));
                    tempUser.setIdNumber(rs.getString("ID"));
                    tempUser.setHomeTelephoneNumber(rs.getString("HomeNo"));
                    tempUser.setMobileTelephoneNumber(rs.getString("MobileNo"));
                    tempUser.setEmailAddress(rs.getString("Email"));
                    tempUser.setProfilePicture(rs.getString(10));

                }

            }
        } catch (SQLException ex) {
            System.out.println("Error at Log In x: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return tempUser;
    }

    /////************************THIS WORKS,!****************************///// 
    @Override
    public boolean signUp(User user) {
        try {
            ps = con.prepareStatement("INSERT INTO User( Name, Surname, Password, Title, Role, ID, HomeNo, MobileNo, Email, ProfilePicture ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getTitle());
            ps.setInt(5, user.getRole()); // remember datatype difference
            ps.setString(6, user.getIdNumber());
            ps.setString(7, user.getHomeTelephoneNumber());
            ps.setString(8, user.getMobileTelephoneNumber());
            ps.setString(9, user.getEmailAddress());
            ps.setString(10, user.getProfilePicture());
            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }
        } catch (SQLException ex) {
            System.out.println("Error at Sign Up: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

    /////*************************this works*********************************/////
    @Override
    public boolean editUserProfile(User user) {
        try {
            ps = con.prepareStatement("UPDATE user SET Name = '" + user.getName() + "'"
                    + ", Surname = '" + user.getSurname() + "'"
                    + ", Password = '" + user.getPassword() + "'"
                    + ", Title = '" + user.getTitle() + "'"
                    + ", Role = " + user.getRole() + ""
                    + ", ID = '" + user.getIdNumber() + "'"
                    + ", HomeNo = '" + user.getHomeTelephoneNumber() + "'"
                    + ", MobileNo = '" + user.getMobileTelephoneNumber() + "'"
                    + ", Email = '" + user.getEmailAddress() + "'"
                    + ", ProfilePicture = '" + user.getProfilePicture() + "'"
                    + " WHERE ID LIKE '" + user.getIdNumber() + "'");
            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }

        } catch (SQLException ex) {
            System.out.println("Error at Edit User Profile: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

    /////**********************************************************///// 
    @Override
    public boolean deleteUser(String userId) {
        try {
           
            ps = con.prepareStatement("DELETE * FROM user WHERE ID = '" + userId + "'");
            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }
        } catch (SQLException ex) {
            System.out.println("Error at Edit User Profile: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;

    }

    /////**********************************************************///// 
    @Override
    public ArrayList<User> getAllUsers() {
        
        ArrayList<User> tempUsers = new ArrayList<User>();
       
        User tempUser;
        try {
           
            ps = con.prepareStatement("SELECT Name, Surname, Password, Title, Role, ID, HomeNo, MobileNo, Email, ProfilePicture FROM user");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {

            while (rs.next()) {
                if (rs != null) {
                    
                    tempUser = new User();
                   
                    tempUser.setName(rs.getString("Name"));
                    tempUser.setSurname(rs.getString("Surname"));
                    tempUser.setPassword(rs.getString("Password"));
                    tempUser.setTitle(rs.getString("Title"));
                    tempUser.setRole(rs.getInt("Role"));
                    tempUser.setIdNumber(rs.getString("ID"));
                    tempUser.setHomeTelephoneNumber(rs.getString("HomeNo"));
                    tempUser.setMobileTelephoneNumber(rs.getString("MobileNo"));
                    tempUser.setEmailAddress(rs.getString("Email"));
                    tempUser.setProfilePicture(rs.getString("ProfilePicture"));
                    
                    tempUsers.add(tempUser);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll Users: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return tempUsers; // giving back the ArrayList here
    }

    /////**********************************************************///// 
    @Override
    public ArrayList<User> getUserByName(String name) {

        ArrayList<User> tempUsers = new ArrayList<User>();
        User tempUser;
        try {
            ps = con.prepareStatement("SELECT Name, Surname, Password, Title, Role, ID, HomeNo, MobileNo, Email, ProfilePicture FROM user WHERE Name = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            
            while (rs.next()) {
                if (rs != null) {
                    
                    tempUser = new User();
                    
                    tempUser.setName(rs.getString("Name"));
                    tempUser.setSurname(rs.getString("Surname"));
                    tempUser.setPassword(rs.getString("Password"));
                    tempUser.setTitle(rs.getString("Title"));
                    tempUser.setRole(rs.getInt("Role"));
                    tempUser.setIdNumber(rs.getString("ID"));
                    tempUser.setHomeTelephoneNumber(rs.getString("HomeNo"));
                    tempUser.setMobileTelephoneNumber(rs.getString("MobileNo"));
                    tempUser.setEmailAddress(rs.getString("Email"));
                    tempUser.setProfilePicture(rs.getString("ProfilePicture"));
                    
                    tempUsers.add(tempUser);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll Users: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return tempUsers;    
    }

    /////*************************this works*********************************///// 
    @Override
    public boolean addUserPayment(Payment payment) {
        try {
            
            ps = con.prepareStatement("INSERT INTO payment( CardNo , ExpiryDate, CVV, Type, User) VALUES (?, ?, ?, ?, ? )");
            ps.setString(1, payment.getCardNumber());
            ps.setDate(2, payment.getExpiryDate());
            ps.setString(3, payment.getCvvCode());
            ps.setString(4, payment.getCardType());
            ps.setString(5, payment.getUserID());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error at GetAll Users payment: " + ex.getMessage());
            return false;
        }

        try {
            
            ps = con.prepareStatement("INSERT INTO paymentusertable( CardNo, User) VALUES (?, ?)");
            ps.setString(1, payment.getCardNumber());
            ps.setString(2, payment.getUserID());

            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll Users userPaymenttable : " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

    /////********************this works**************************************///// 
    @Override
    public boolean deleteUserPayment(String cardNo, String userID) {
        try {
            ps = con.prepareStatement("DELETE * FROM paymentusertable WHERE cardNo LIKE ? AND User LIKE ? ");
            ps.setString(1, cardNo);
            ps.setString(2, userID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error at GetAll Users: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

    @Override
    public Order getUserOrder(Integer OrderId, String userId) {

        Order order = null;

        try {
            if (con != null) {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, DeliveryDate, User, Address, Payment, Deleted FROM orders WHERE User = ? AND ID = ?");
                ps.setInt(1, OrderId);
                ps.setString(2, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs != null) {
                        order = new Order();
                        
                        order.setId(rs.getInt("ID"));
                        order.setTotalPrice(rs.getDouble("TotalPrice"));
                        order.setOrderDate(rs.getDate("OrderDate"));
                        order.setDeliveryDate(rs.getDate("DeliveryDate"));
                        order.setUser(rs.getString("User"));
                        order.setAddress(rs.getString("Address"));
                        order.setPayment(rs.getString("PaymentID"));

                    }
                }// end of while loop
            }
        } catch (SQLException ex) {
            System.out.println("Hello Error at : " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return order;
    }

    @Override
    public ArrayList<Order> getUserOrders(User user) {
        ArrayList<Order> list = new ArrayList<Order>();
        Order order = null;

        try {
            if (con != null) {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, DeliveryDate, User, Address, Payment, Deleted FROM orders WHERE User = ?");
                ps.setString(1, user.getIdNumber());
                rs = ps.executeQuery();
                while (rs.next()) {
                    try {

                        if (rs != null) {
                            order = new Order();
                            //order has 9 Columns
                            /*#1*/
                            order.setId(rs.getInt("ID")); // primary key Int 
                            /*#2*/
                            order.setTotalPrice(rs.getDouble("TotalPrice")); // total price of the order
                            /*#3*/
                            order.setOrderDate(rs.getDate("OrderDate")); // NB check date formatting with team
                            /*#4*/
                            order.setDeliveryDate(rs.getDate("DeliveryDate")); // ''
                            /*#5*/
                            order.setUser(rs.getString("User")); // The User ID is a key and unique , See you have a user not an Order 
                            /*#6*/
                            order.setAddress(rs.getString("Address")); // this is an Id , confirm change , need to add collumn in address table
                            /*#7*/
                            order.setPayment(rs.getString("Payment")); // this has it's own Id too
                            order.setDeleted(Boolean.FALSE);

                            list.add(order);
                        }

                    } catch (SQLException ex) {
                        System.out.println("Error at : " + ex.getMessage());
                    }
                }
            }

            // end of while loop
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return list;
    }

/////*****************************************************************/////
    @Override
    public ArrayList<Payment> getAllUserPayments(String userID) {
        ArrayList<Payment> tempPayments = new ArrayList<Payment>();
        Payment tempPayment;
        try {
            ps = con.prepareStatement("SELECT CardNo, ExpiryDate, CVV, Type, User FROM payment WHERE User = ?");
            ps.setString(1, userID);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {

            while (rs.next()) {

                if (rs != null) {
                    tempPayment = new Payment();

                    //Assigning all fields from the result set to new template
                    tempPayment.setCardNumber(rs.getString("CardNo"));
                    tempPayment.setExpiryDate(rs.getDate("ExpiryDate"));
                    tempPayment.setCvvCode(rs.getString("CVV"));
                    tempPayment.setCardType(rs.getString("Type"));
                    tempPayment.setUserID(rs.getString("User"));
                    // returning now that new user object to the List created at start of method
                    tempPayments.add(tempPayment);
                }

            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll payments: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return tempPayments; // giving back the ArrayList here   

    }

    /////**********************************************************///// 
    @Override
    public boolean addAddress(Address address, String userID) {
        try {
            // Populating both the payment and the paymentusertable tables
            ps = con.prepareStatement("INSERT INTO address( ID , StreetNo, Street, Suberb, City) VALUES (?, ?, ?, ?, ? )");
            ps.setString(1, userID);
            ps.setString(2, address.getStreetNumber());
            ps.setString(3, address.getStreetName());
            ps.setString(4, address.getSuberb());
            ps.setString(5, address.getCity());

            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll Users payment: " + ex.getMessage());
            return false;
        }

        try {
            // Populating both the payment and the paymentusertable tables
            ps = con.prepareStatement("INSERT INTO useraddresstable(User, Address) VALUES (?, ?)");
            ps.setString(1, userID);
            ps.setString(2, address.getStreetName());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error at addAddress : " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

    /////**********************************************************///// 
    @Override
    public ArrayList<Address> getUserAddress(String userID) {
        ArrayList<Address> tempAddresses = new ArrayList<Address>();
        Address tempAddress;
        try {
            ps = con.prepareStatement("SELECT ID, StreetNo, Street, Suberb, City FROM address WHERE ID = ?");
            ps.setString(1, userID);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            // the cursor will start at the first field and work through the whole database assigning each line to a new user template
            while (rs.next()) {
                if (rs != null) {

                    //Clearing new User aka template
                    tempAddress = new Address();
                    //Assigning all fields from the result set to new template

                    tempAddress.setStreetNumber(rs.getString("StreetNo"));
                    tempAddress.setStreetName(rs.getString("Street"));
                    tempAddress.setSuberb(rs.getString("Suberb"));
                    tempAddress.setCity(rs.getString("City"));

                    tempAddress.setId(); //no parameters needed because it uses the exixting values to build up ID from full ADDRESS String 

                    // returning now that new user object to the List created at start of method
                    tempAddresses.add(tempAddress);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll getUserAddresses: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return tempAddresses; // giving back the ArrayList here   
    }

    /////**********************************************************///// 
    @Override
    public boolean deleteUserAddress(int userAddressID, String userID) {
        try {
            ps = con.prepareStatement("DELETE * FROM useraddresstable WHERE User LIKE ?");
            ps.setString(1, userID);
            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }
        } catch (SQLException ex) {
            System.out.println("Error at DeleteUserAddress: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

    /////**********************************************************///// 
    @Override
    public ArrayList<Address> getAllUserAddress(String userID) {
        ArrayList<Address> tempAddresses = new ArrayList<Address>();
        Address tempAddress;
        try {
            ps = con.prepareStatement("SELECT ID, StreetNo, Street, Suberb, City FROM address");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            // the cursor will start at the first field and work through the whole database assigning each line
            while (rs.next()) {
                if (rs != null) // can't stress enough on how important data checking is , before each use please.
                {

                    tempAddress = new Address();
                    //Assigning all fields from the result set to new template

                    tempAddress.setStreetNumber(rs.getString("StreetNo"));
                    tempAddress.setStreetName(rs.getString("Street"));
                    tempAddress.setSuberb(rs.getString("Suberb"));
                    tempAddress.setCity(rs.getString("City"));

                    tempAddress.setId();
                    // returning now that new user object to the List created at start of method
                    tempAddresses.add(tempAddress);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error at GetAll getUserAddresses: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return tempAddresses; // giving back the ArrayList here  
    }

    /////**********************************************************///// 
    @Override
    public boolean passwordRest(String userID, String newPassword) {
        try {

            ps = con.prepareStatement("UPDATE user SET Password = ? WHERE ID = ?");
            ps.setString(1, newPassword);
            ps.setString(2, userID);
            int i = ps.executeUpdate();
            if (i < 1) {
                System.out.println("Your update did not execute ");

            }

        } catch (SQLException ex) {
            System.out.println("Error at Edit User Profile: " + ex.getMessage());
            return false;
        } finally {
            try {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        System.out.println("Error Closing Connection at" + e.getMessage());
                    }
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return true;
    }

}
