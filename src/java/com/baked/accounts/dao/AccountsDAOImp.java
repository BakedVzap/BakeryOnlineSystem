
package com.baked.accounts.dao;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
//import com.mysql.jdbc.PreparedStatement; // Please Note I changed this     #1
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // I replaced it with this one            #1
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author UnicornBrendan
 */
public class AccountsDAOImp implements AccountsDAOInterface {
     private Connection con;
     private PreparedStatement ps;
     private ResultSet rs;
     private Statement myStmt;
    
    public AccountsDAOImp()
    {
         try 
         {
            Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) 
         {
            System.out.println("Driver not found");
            System.exit(0);
         }
        String url = "jdbc:mysql://192.168.20.241:3306/TheDoughKnot";
        try 
        {
            con = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException ex) 
        {
            System.out.println("Connection not established");
        }
    }
    
      /////**********************THIS WORKS, D!******************************/////
    @Override
    public User login(String email, String password) 
    {
         User tempUser = new User();
         try 
         {
             ps = con.prepareStatement("SELECT * FROM user WHERE email LIKE ? AND password LIKE ?");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
         try 
         {
             ps.setString(1, email);
             ps.setString(2, password);
             rs = ps.executeQuery();
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                {
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
                
        }catch(SQLException ex)
        {
            System.out.println("Error at Log In x: "+ex.getMessage());
        }
        
              
        
        return tempUser;
    }

     /////************************THIS WORKS, D!****************************///// 
    @Override
    public boolean signUp(User user) 
    {
         try {
             ps = con.prepareStatement("INSERT INTO User( Name, Surname, Password, Title, Role, ID, HomeNo, MobileNo, Email, ProfilePicture ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
             ps.setString(1, user.getName());
             ps.setString(2, user.getSurname());
             ps.setString(3, user.getPassword());
             ps.setString(4, user.getTitle());
             ps.setInt(5, user.getRole());
             ps.setString(6, user.getIdNumber());
             ps.setString(7, user.getHomeTelephoneNumber());
             ps.setString(8, user.getMobileTelephoneNumber());
             ps.setString(9, user.getEmailAddress());
             ps.setString(10, user.getProfilePicture());
             ps.execute();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Sign Up: "+ex.getMessage());
             return false;
         }
        return true;
    }

      /////**********************************************************/////
    @Override
    public boolean editUserProfile(User user) 
    {
        String tempID = user.getIdNumber();
        String tempEmail = user.getEmailAddress();
        String tempHomeNo = user.getHomeTelephoneNumber() ;
        String tempMobileNo = user.getMobileTelephoneNumber() ;
        String tempSurname = user.getSurname() ;
        String tempName = user.getName();
        int tempRole = user.getRole();
        String tempTitle = user.getTitle();
        
        try
            {
                ps = con.prepareStatement("UPDATE user SET Name = '"+tempName+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE user SET Surname = '"+tempSurname+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE user SET Title = '"+tempTitle+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE user SET MobileNo = '"+tempMobileNo+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE user SET Role = '"+tempRole+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE user SET HomeNo = '"+tempHomeNo+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
                ps = con.prepareStatement("UPDATE user SET Email = '"+tempEmail+"' WHERE ID LIKE '"+tempID+"';");
                ps.executeUpdate();
            }
        catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
        return true;
    }

     /////**********************************************************///// 
    @Override
    public boolean deleteUser(String userId)
    {
         try 
         {
             ps = con.prepareStatement("DELETE * FROM user WHERE ID = '"+userId+"'");
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
        return true;
        
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<User> getAllUsers() 
    {
        ArrayList<User> tempUsers = new ArrayList<User>();
        User tempUser;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT ID, Name, Surname, Title, MobileNo, Role, HomeNo, Email FROM user");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    {
                        tempUser = new User();
                        
                        tempUser.setName(rs.getString("Name"));
                        tempUser.setSurname(rs.getString("Surname"));
                        tempUser.setTitle(rs.getString("Title"));
                        tempUser.setRole(rs.getInt("Role"));
                        tempUser.setIdNumber(rs.getString("ID"));
                        tempUser.setHomeTelephoneNumber(rs.getString("HomeNo"));
                        tempUser.setMobileTelephoneNumber(rs.getString("MobileNo"));
                        tempUser.setEmailAddress(rs.getString("Email"));
                    // Please note Profile Picture has not yet been included here
                        tempUsers.add(tempUser);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        return tempUsers; 
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<User> getUserByName(String name) 
    {

        ArrayList<User> tempUsers = new ArrayList<User>();
        User tempUser;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT ID, Name, Surname, Title, MobileNo, Role, HomeNo, Email FROM user WHERE name = '"+name+"' ");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                while(rs.next())
                    {
                        tempUser = new User();
                        
                        tempUser.setName(rs.getString("Name"));
                        tempUser.setSurname(rs.getString("Surname"));
                        tempUser.setTitle(rs.getString("Title"));
                        tempUser.setRole(rs.getInt("Role"));
                        tempUser.setIdNumber(rs.getString("ID"));
                        tempUser.setHomeTelephoneNumber(rs.getString("HomeNo"));
                        tempUser.setMobileTelephoneNumber(rs.getString("MobileNo"));
                        tempUser.setEmailAddress(rs.getString("Email"));
                    // Please note Profile Picture has not yet been included here
                        tempUsers.add(tempUser);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        return tempUsers;     
    }

     /////**********************************************************///// 
    @Override
    public boolean addUserPayment(Payment payment) 
    {
        try
            {
                 ps = con.prepareStatement("INSERT INTO payment( CardNo , ExpiryDate, CVV, Type, User) VALUES (?, ?, ?, ?, ? )");
                 ps.setString(1, payment.getCardNumber());
                 ps.setString(2, payment.getCardNumber());
                 ps.setString(3, payment.getCvvCode());
                 ps.setString(4, payment.getCardType());
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users: "+ex.getMessage());
                }
        return true;
    }

     /////**********************************************************///// 
    @Override
    public boolean deleteUserPayment(String cardNo, String userID) 
    {
        try
            {
                 ps = con.prepareStatement("DELETE * FROM paymentusertable WHERE cardNo LIKE '"+cardNo+"' AND ID LIKE '"+userID+"' ");
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users: "+ex.getMessage());
                }
        return true;
    }
    
    
/////*****************************************************************/////
   
    
    
    @Override
    public ArrayList<Payment> getAllUserPayments(String userID) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /////**********************************************************///// 
    @Override
    public boolean addAddress(Address address, String userID) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<Address> getUserAddress(String userID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /////**********************************************************///// 
    @Override
    public boolean deleteUserAddress(int userAddressID, String userID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<Address> getAllUserAddress(String userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     /////**********************************************************///// 
    @Override
    public boolean passwordRest(String userID, String newPassword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
