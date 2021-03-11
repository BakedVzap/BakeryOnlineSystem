
package com.baked.ordering.models;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public class Order 
{
        private Integer id;                             //  #1
        private ArrayList<OrderLineItem>items;          //  #2 remember this
        private Double totalPrice;                      //  #3
        private Date DeliveryDate;                      //  #4
        private Date OrderDate;                         //  #5
        private User user;                              //  #6
        private Address address;                        //  #7
        private Payment Payment;                        //  #8
        private Boolean Deleted;                        //  #9
        private Boolean Delivered;                      //  #10
      

    public Order(Integer id, ArrayList<OrderLineItem> items, Double totalPrice, Date DeliveryDate, Date OrderDate, User user, Address address, Payment Payment, Boolean Deleted) 
    {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.DeliveryDate = DeliveryDate;
        this.OrderDate = OrderDate;
        this.user = user;
        this.address = address;
        this.Payment = Payment;
        this.Deleted = Deleted;
    }

    public Order(Integer id, ArrayList<OrderLineItem> items, Double totalPrice, Date DeliveryDate, Date OrderDate, User user, Address address, Payment Payment, Boolean Deleted, Boolean Delivered) {
        this.id = id;
        this.items = items;
        this.totalPrice = totalPrice;
        this.DeliveryDate = DeliveryDate;
        this.OrderDate = OrderDate;
        this.user = user;
        this.address = address;
        this.Payment = Payment;
        this.Deleted = Deleted;
        this.Delivered = Delivered;
    }

    
    
    public void setTotalPrice(Double totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

   
    public Date getDeliveryDate() 
    {
        return DeliveryDate;
    }

   
    public void setDeliveryDate(Date DeliveryDate) 
    {
        this.DeliveryDate = DeliveryDate;
    }

   
    public Date getOrderDate() 
    {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) 
    {
        this.OrderDate = OrderDate;
    }

  
    public User getUser() 
    {
        return user;
    }

   
    public void setUser(User user) 
    {
        this.user = user;
    }

   
    public Address getAddress() 
    {
        return address;
    }

   
    public void setAddress(Address address) 
    {
        this.address = address;
    }

    
    public Payment getPayment() 
    {
        return Payment;
    }

   
    public void setPayment(Payment Payment) 
    {
        this.Payment = Payment;
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
    
    public Order()
    {
        
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

    public Boolean getDelivered() {
        return Delivered;
    }

    public void setDelivered(Boolean Delivered) {
        this.Delivered = Delivered;
    }
    
    
    
    
}
