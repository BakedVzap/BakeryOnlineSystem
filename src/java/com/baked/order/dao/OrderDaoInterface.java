
package com.baked.order.dao;

import com.baked.accounts.models.User;
import com.baked.ordering.models.Order;
//import java.sql.Date;  // remember not to use sql date
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author UnicornBrendan
 */
public interface OrderDaoInterface 
{
    public ArrayList<Order> getAllPlacedOrdersBetween(String dateFrom, String dateTo);
    boolean addOrder(Order order);
   // boolean setDeliveryDate(Integer orderId, Date deliveryDate);    REPLACED
    ArrayList<Order> getAllOrders();
    ArrayList<Order> getPendingOrders();
    ArrayList<Order> getOrdersBetween(Date deliveryDate , Date orderDate);
    public boolean setDeliveryDate(Integer orderId);
    Order getOrder(Integer OrderId);
    
    
    
}
