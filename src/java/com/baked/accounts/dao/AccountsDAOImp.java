
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
         // connection test successful
        String url = "jdbc:mysql://localhost:3306/TheDoughKnot";
        try 
        {
            con = DriverManager.getConnection(url, "root", "root");
        } catch (SQLException ex) 
        {
            System.out.println("Connection not established");
        }
    }
    
      /////**********************THIS WORKS,!******************************/////
    @Override
    public User login(String email, String password) 
    {
         User tempUser = new User();
         try 
         {
             ps = con.prepareStatement("SELECT * FROM user WHERE Email LIKE ? AND Password LIKE ?");
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
        
              
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempUser;
    }

     /////************************THIS WORKS,!****************************///// 
    @Override
    public boolean signUp(User user) 
    {
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
             ps.execute();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Sign Up: "+ex.getMessage());
             return false;
         }
         try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

      /////*************************this works*********************************/////
    @Override
    public boolean editUserProfile(User user) 
    {
       try
            {
                ps = con.prepareStatement("UPDATE user SET Name = '"+user.getName()+"'"
                        + ", Surname = '"+user.getSurname()+"'"
                        + ", Password = '"+user.getPassword()+"'"
                        + ", Title = '"+user.getTitle()+"'"
                        + ", Role = "+user.getRole()+""
                        + ", ID = '"+user.getIdNumber()+"'"
                        + ", HomeNo = '"+user.getHomeTelephoneNumber()+"'"
                        + ", MobileNo = '"+user.getMobileTelephoneNumber()+"'"
                        + ", Email = '"+user.getEmailAddress()+"'"
                        + ", ProfilePicture = '"+user.getProfilePicture()+"'"
                                + " WHERE ID LIKE '"+user.getIdNumber()+"'");
                ps.executeUpdate();
                
            }
        catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
       try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

     /////**********************************************************///// 
    @Override
    public boolean deleteUser(String userId)
    {
         try 
         {
             //must pass no whitespaces due to equals sign
             ps = con.prepareStatement("DELETE * FROM user WHERE ID = '"+userId+"'");
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
         try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
        
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<User> getAllUsers() 
    {
        //Creating a new List of Users
        ArrayList<User> tempUsers = new ArrayList<User>();
        // Creating a User reference here
        User tempUser;
        try 
         {
             // creating a statement to retrieve a result set of users
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT Name, Surname, Password, Title, Role, ID, HomeNo, MobileNo, Email, ProfilePicture FROM user");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
                        tempUser = new User();
                        //Assigning all fields from the result set to new template
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
                        // returning now that new user object to the List created at start of method
                        tempUsers.add(tempUser);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempUsers; // giving back the ArrayList here
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
             rs = myStmt.executeQuery("SELECT Name, Surname, Password, Title, Role, ID, HomeNo, MobileNo, Email, ProfilePicture FROM user WHERE Name = '"+name+"'");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
                        tempUser = new User();
                        //Assigning all fields from the result set to new template
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
                        // returning now that new user object to the List created at start of method
                        tempUsers.add(tempUser);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll Users: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempUsers; // giving back the ArrayList here    
    }

     /////*************************this works*********************************///// 
    @Override
    public boolean addUserPayment(Payment payment) 
    {
        try
            {
                // Populating both the payment and the paymentusertable tables
                 ps = con.prepareStatement("INSERT INTO payment( CardNo , ExpiryDate, CVV, Type, User) VALUES (?, ?, ?, ?, ? )");
                 ps.setString(1, payment.getCardNumber());
                 ps.setDate(2, payment.getExpiryDate());
                 ps.setString(3, payment.getCvvCode());
                 ps.setString(4, payment.getCardType());
                 ps.setString(5, payment.getUserID());
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users payment: "+ex.getMessage());
                    return false;
                }
        
        try
            {
                // Populating both the payment and the paymentusertable tables
                 ps = con.prepareStatement("INSERT INTO paymentusertable( CardNo, User) VALUES (?, ?)");
                 ps.setString(1, payment.getCardNumber());
                 ps.setString(2, payment.getUserID());
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users userPaymenttable : "+ex.getMessage());
                    return false;
                }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

     /////********************this works**************************************///// 
    @Override
    public boolean deleteUserPayment(String cardNo, String userID) 
    {
        try
            {
                 ps = con.prepareStatement("DELETE * FROM paymentusertable WHERE cardNo LIKE '"+cardNo+"' AND User LIKE '"+userID+"' ");
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users: "+ex.getMessage());
                    return false;
                }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }
    
    
/////*****************************************************************/////
   
    
    
    @Override
    public ArrayList<Payment> getAllUserPayments(String userID) 
    {
        ArrayList<Payment> tempPayments = new ArrayList<Payment>();
        Payment tempPayment;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT CardNo, ExpiryDate, CVV, Type, User FROM payment WHERE User = '"+userID+"'");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
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
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll payments: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempPayments; // giving back the ArrayList here   
        
    }

     /////**********************************************************///// 
    @Override
    public boolean addAddress(Address address, String userID) 
    {
        try
            {
                // Populating both the payment and the paymentusertable tables
                 ps = con.prepareStatement("INSERT INTO address( ID , StreetNo, Street, Suberb, City) VALUES (?, ?, ?, ?, ? )");
                 ps.setString(1, userID);
                 ps.setString(2, address.getStreetNumber());
                 ps.setString(3, address.getStreetName());
                 ps.setString(4, address.getSuberb());
                 ps.setString(5, address.getCity());
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at GetAll Users payment: "+ex.getMessage());
                    return false;
                }
        
        try
            {
                // Populating both the payment and the paymentusertable tables
                 ps = con.prepareStatement("INSERT INTO useraddresstable(User, Address) VALUES (?, ?)");
                 ps.setString(1, userID);
                 ps.setString(2, address.getStreetName());
                 
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at addAddress : "+ex.getMessage());
                    return false;
                }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<Address> getUserAddress(String userID)
    {
        ArrayList<Address> tempAddresses= new ArrayList<Address>();
        Address tempAddress;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT ID, StreetNo, Street, Suberb, City FROM address WHERE ID = '"+userID+"'");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
                        tempAddress = new Address();
                        //Assigning all fields from the result set to new template
                        tempAddress.setId(rs.getString("ID"));
                        tempAddress.setStreetNumber(rs.getString("StreetNo"));
                        tempAddress.setStreetName(rs.getString("Street"));
                        tempAddress.setSuberb(rs.getString("Suberb"));
                        tempAddress.setCity(rs.getString("City"));
                        
                        // returning now that new user object to the List created at start of method
                        tempAddresses.add(tempAddress);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll getUserAddresses: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempAddresses; // giving back the ArrayList here   
    }

     /////**********************************************************///// 
    @Override
    public boolean deleteUserAddress(int userAddressID, String userID)
    {
        try
            {
                 ps = con.prepareStatement("DELETE * FROM useraddresstable WHERE User LIKE '"+userID+"'");
                 ps.executeUpdate();
            }catch(SQLException ex)
                {
                    System.out.println("Error at DeleteUserAddress: "+ex.getMessage());
                    return false;
                }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

     /////**********************************************************///// 
    @Override
    public ArrayList<Address> getAllUserAddress(String userID) 
    {
        ArrayList<Address> tempAddresses= new ArrayList<Address>();
        Address tempAddress;
        try 
         {
             myStmt = con.createStatement(); 
             rs = myStmt.executeQuery("SELECT ID, StreetNo, Street, Suberb, City FROM address ");
         } catch (SQLException ex) 
         {
             System.out.println("Error at : "+ex.getMessage());
         }
        try
            {
                // the cursor will start at the first field and work through the whole database assigning each line to a new user template
                while(rs.next())
                    { 
                        //Clearing new User aka template
                        tempAddress = new Address();
                        //Assigning all fields from the result set to new template
                        tempAddress.setId(rs.getString("ID"));
                        tempAddress.setStreetNumber(rs.getString("StreetNo"));
                        tempAddress.setStreetName(rs.getString("Street"));
                        tempAddress.setSuberb(rs.getString("Suberb"));
                        tempAddress.setCity(rs.getString("City"));
                        
                        // returning now that new user object to the List created at start of method
                        tempAddresses.add(tempAddress);
                }
        }catch(SQLException ex)
        {
            System.out.println("Error at GetAll getUserAddresses: "+ex.getMessage());
        }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return tempAddresses; // giving back the ArrayList here  
    }

     /////**********************************************************///// 
    @Override
    public boolean passwordRest(String userID, String newPassword) 
    {
        try
            {
                ps = con.prepareStatement("UPDATE user SET Password = '"+newPassword+"'"+" WHERE ID = '"+userID+"'");
                ps.executeUpdate();
                
            }
        catch (SQLException ex) 
         {
             System.out.println("Error at Edit User Profile: "+ex.getMessage());
             return false;
         }
        try
            {
                if(ps.isClosed()!=true)
                {
                ps.close();
                }
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }
    
}
