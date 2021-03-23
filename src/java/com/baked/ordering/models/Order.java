
package com.baked.ordering.models;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
//import java.sql.Date; Do NOT use this SQL date Format, util is better
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Brendan
 */
public class Order 
{
        private Integer id;                             //  #1
        private ArrayList<OrderLineItem>items;          //  #2 remember this
        private Double totalPrice;                      //  #3
        private  String DeliveryDate;                      //  #4
        private  String OrderDate;                         //  #5
        private String userId;                          //  #6
        private String addressId;                         //  #7
        private String PaymentId;                         //  #8
        private Boolean Deleted;                        //  #9
        
        
      

        
   

    public Order(Integer id, ArrayList<OrderLineItem> items, Double totalPrice, Date DeliveryDate, Date OrderDate, String user, String address, String Payment, Boolean Deleted) 
    {
        this.id = id;
        this.items = items;
        setTotalPrice(totalPrice);
        setDeliveryDate(DeliveryDate);
        setOrderDate(OrderDate);
        this.userId = user;
        this.addressId = address;
        this.PaymentId = Payment;
        this.Deleted = Deleted;
       
    }

    public Order(int aInt, ArrayList<OrderLineItem> items, double aDouble, String string, String string0, String string1, String string2, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    public void setTotalPrice(Double totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

   
    public String getDeliveryDate() 
    {
        return DeliveryDate;
    }

   
    public void setDeliveryDate(Date DeliveryDate) 
    {
        if(DeliveryDate!=null)
        {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
         String dateString = formatter.format(DeliveryDate);
        this.DeliveryDate = dateString;
        }else 
        {
            System.out.println("Your Delivery Date is null");
        }
    }

    public Order (){}
   
    public String getOrderDate() 
    {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) 
    {
        if(OrderDate!=null)
        {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
         String dateString = formatter.format(OrderDate);
         this.OrderDate = dateString;
        }else 
        {
            System.out.println("Your Delivery Date is null");
        }
    }

  
    public String getUser() 
    {
        return userId;
    }

   
    public void setUser(String user) 
    {
        this.userId = user;
    }

   
    public String getAddress() 
    {
        return addressId;
    }

   
    public void setAddress(String address) 
    {
        this.addressId = address;
    }

    
    public String getPayment() 
    {
        return PaymentId;
    }

   
    public void setPayment(String Payment) 
    {
        this.PaymentId = Payment;
    }

    public Boolean getDeleted() 
    {
        return Deleted;
    }

    public void setDeleted(Boolean Deleted) 
    {
        this.Deleted = Deleted;
    }
 
    
    public Order(int id,ArrayList<OrderLineItem>items,double totalPrice)
    {
        setId(id);
        setItems(items);
        setTotalPrice(totalPrice);
    }
   
    public Integer getId() 
    {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<OrderLineItem> getItems() 
    {
        return items;
    }

    
    public void setItems(ArrayList<OrderLineItem> items) 
    {
        this.items = items;
    }
   

    public double getTotalPrice() 
    {
        return totalPrice;
    }

   
    public void setTotalPrice(double totalPrice) 
    {
        this.setTotalPrice((Double) totalPrice);
    }

   
    
    
    
    
}
    

