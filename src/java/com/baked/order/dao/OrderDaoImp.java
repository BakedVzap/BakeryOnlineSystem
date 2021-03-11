
package com.baked.order.dao;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import com.baked.ordering.models.Order;
import com.baked.ordering.models.OrderLineItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class OrderDaoImp implements OrderDaoInterface
{
     private Connection con;
     private PreparedStatement ps;
     private ResultSet rs;
     private Statement myStmt;

    public OrderDaoImp() 
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

    @Override
    public boolean addOrder(Order order) 
    {
        try
            {
                ps = con.prepareStatement("INSERT INTO order ID, TotalPrice, OrderDate, DeliveryDate, User, Address, Payment, Delivered, Deleted VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? )");
                ps.setInt(1, order.getId());
                ps.setDouble(2, order.getTotalPrice());
                ps.setDate(3, order.getOrderDate());
                ps.setDate(4, order.getDeliveryDate());
                ps.setString(5, order.getUser().getIdNumber());
                ps.setString(6, order.getAddress().getId());
                ps.setString(7, order.getPayment().getCardNumber());
                if((order.getDelivered())==true)
                        {
                            ps.setInt(8,1);
                        }
                if((order.getDelivered())==false)
                        {
                            ps.setInt(8,0);
                        }
                if((order.getDeleted())==true)
                        {
                            ps.setInt(9,1);
                        }
                if((order.getDeleted())==false)
                        {
                            ps.setInt(9,0);
                        }
                
                ps.executeUpdate();
                
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        finally
            {
               // ArrayList<OrderLineItem> list = new ArrayList<OrderLineItem>();
                for(OrderLineItem o : order.getItems())
                    {
                         try
                            {
                                ps = con.prepareStatement("INSERT INTO orderlineitem ID, Quantity, Order, Product VALUES(?, ?, ?, ?)");
                                ps.setInt(1, o.getId());
                                ps.setInt(2, o.getQuantity());
                                ps.setInt(3, o.getOrder());
                                ps.setString(4, o.getProduct());
                                ps.executeUpdate();
                            }
                        catch(SQLException ex)
                            {
                                System.out.println("Error : "+ex.getMessage());
                            }
                    }
               
            }
        
        try
            {
                ps.close();
            }catch(SQLException ex)
                {
                    System.out.println("Error : "+ex.getMessage());
                }
        return true;
    }

    @Override
    public boolean setDeliveryDate(Integer orderId, Date deliveryDate) 
    {
         try {
             ps = con.prepareStatement("UPDATE order SET Delivered = 1 WHERE ID = "+orderId+"" );
             ps.executeUpdate();
         } catch (SQLException ex) 
         {
             System.out.println("Error : "+ex.getMessage());
         }
            try
            {
                ps = con.prepareStatement("UPDATE order SET DeliveryDate = "+deliveryDate+" WHERE ID = "+orderId+"");
                ps.executeUpdate();
            }catch(SQLException ex)
            {
                System.out.println("Error : "+ex.getMessage());
            }
            try
              {
                  ps.close();
              }
            catch(SQLException ex)
                  {
                      System.out.println("Error : "+ex.getMessage());
                  }
        return true;
    }

    @Override
    public ArrayList<Order> getAllOrders() 
    {
        ArrayList<Order> list = null;
        Order order = null;
        Address address = null;
        Payment payment = null ;
        User user = null;
        try
            {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, DeliveredDate, User, Address, Payment, Delivered, Deleted FROM order ");
                rs = ps.executeQuery();
                while(rs.next())
                    {
                        order = new Order();
                  //order has 9 Columns
                  /*#1*/order.setId(rs.getInt("ID"));
                  /*#2*/order.setTotalPrice(rs.getDouble("TotalPrice"));
                  /*#3*/order.setOrderDate(rs.getDate("OrderDate"));
                  /*#4*/order.setDeliveryDate(rs.getDate("DeliveryDate"));
                  // Now Fetching Data from the user table and Creating the User Pojo
                  PreparedStatement ps2 = con.prepareStatement("SELECT * FROM user WHERE ID = '"+rs.getString("User")+"' ");
                  ResultSet rs2 = ps2.executeQuery();
                  while(rs2.next())
                    {
                        // User has 10 Columns
                        user = new User();
            /*#1.1*/      user.setName(rs.getString("Name"));
            /*#1.2*/      user.setSurname(rs.getString("Surname"));
            /*#1.3*/      user.setPassword(rs.getString("Password"));
            /*#1.4*/      user.setTitle(rs.getString("Title"));
            /*#1.5*/      user.setRole(rs.getInt("Role"));
            /*#1.6*/      user.setIdNumber(rs.getString("ID"));
            /*#1.7*/      user.setHomeTelephoneNumber(rs.getString("HomeNo"));
            /*#1.8*/      user.setMobileTelephoneNumber(rs.getString("MobileNo"));
            /*#1.9*/      user.setEmailAddress(rs.getString("Email"));
            /*#1.10*/     user.setProfilePicture(rs.getString("Profilepicture"));
                    }
                  /*#5*/order.setUser(user);
                  
                  // Now Fetching Data from the address table and Creating the Address Pojo
                  PreparedStatement ps3 = con.prepareStatement("SELECT * FROM address WHERE ID = '"+rs.getString("Address")+"' ");
                  ResultSet rs3 = ps3.executeQuery();
                  while(rs3.next())
                    {
                        
                        // Address has 5 Columns
                        address = new Address();
            /*#2.1*/      address.setId(rs3.getString("ID"));      
            /*#2.2*/      address.setStreetNumber(rs3.getString("StreetNo"));
            /*#2.3*/      address.setStreetName(rs3.getString("Street"));
            /*#2.4*/      address.setSuberb(rs3.getString("Suberb"));
            /*#2.5*/      address.setCity(rs3.getString("City"));
                    }
                  /*#6*/order.setAddress(address);
                  
                  // Now Fetching Data from the payment table and Creating the Payment pojo
                  PreparedStatement ps4 = con.prepareStatement("SELECT * FROM payment WHERE CardNo = '"+rs.getString("Payment")+"' ");
                  ResultSet rs4 = ps4.executeQuery();
                  while(rs4.next())
                    {
                        // Payment has 5 Columns
                        payment = new Payment();
            /*#3.1*/      payment.setCardNumber(rs4.getString("CardNo"));
            /*#3.2*/      payment.setExpiryDate(rs4.getDate("ExpiryDate"));
            /*#3.3*/      payment.setCvvCode(rs4.getString("CVV"));
            /*#3.4*/      payment.setCardType(rs4.getString("Type"));
            /*#3.5*/      payment.setUserID(rs4.getString("User"));
                    }
                  /*#7*/order.setPayment(payment);
                  try
                  {
                  if((rs.getInt("Delivered"))==1)
                  {
                    /*#8*/     order.setDelivered(true);
                  }
                  if((rs.getInt("Delivered"))==0)
                  {
                    /*#8*/     order.setDelivered(false);
                  }
                  if((rs.getInt("Deleted"))==1)
                  {
                      /*#9*/     order.setDeleted(true);
                  }
                  if((rs.getInt("Deleted"))==0)
                  {
                    /*#9*/     order.setDeleted(false);
                  } 
                  }
                  catch(SQLException ex)
                    {
                            System.out.println("Error at : "+ex.getMessage());
                    }
                  
                  
                  list.add(order);
                    
                    }// end of while loop
            }catch(SQLException ex)
            {
                
            }
        
        return list;
    }

    @Override
    public ArrayList<Order> getPendingOrders() 
    {
    }

    @Override
    public ArrayList<Order> getOrdersBetween(Date deliveryDate, Date orderDate) 
    {
    }

    @Override
    public ArrayList<Order> getUserOrders(User user) 
    {
    }

    @Override
    public Order getUserOrder(Integer OrderId, String userId) 
    {
    }

    @Override
    public Order getOrder(Integer OrderId) 
    {
    }
    
}
