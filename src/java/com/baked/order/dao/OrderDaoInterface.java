
package com.baked.order.dao;

import com.baked.accounts.models.User;
import com.baked.ordering.models.Order;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author UnicornBrendan
 */
public interface OrderDaoInterface 
{
    boolean addOrder(Order order);
    boolean setDeliveryDate(Integer orderId, Date deliveryDate);
    ArrayList<Order> getAllOrders();
    ArrayList<Order> getPendingOrders();
    ArrayList<Order> getOrdersBetween(Date deliveryDate , Date orderDate);
    ArrayList<Order> getUserOrders(User user);
    Order getUserOrder(Integer OrderId, String userId);
    Order getOrder(Integer OrderId);
    
    
    
}
