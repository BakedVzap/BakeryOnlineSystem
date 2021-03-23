package com.baked.order.dao;

import com.baked.databaseManager.DBManager;
import com.baked.ordering.models.Order;
import com.baked.ordering.models.OrderLineItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author UnicornBrendan
 */
public class OrderDaoImp implements OrderDaoInterface {

    private Connection con;

    private PreparedStatement ps;
    private PreparedStatement ps1;
    private ResultSet rs;
    private ResultSet rs1;

    public OrderDaoImp() {
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

    //////////////////***********METHOD ONE*************/////////////////////////////
    @Override
    public boolean addOrder(Order order) { 
        boolean isOk = true; 
        try {
            if (con != null) { 
                con.setAutoCommit(false); 
                ps = con.prepareStatement("INSERT INTO orders ( TotalPrice, User, Address, Payment, Deleted) VALUES(?,?,?,?,?)"); 
                ps.setDouble(1, order.getTotalPrice()); 
                ps.setString(2, order.getUser()); 
                ps.setString(3, order.getAddress()); 
                ps.setString(4, order.getPayment()); 
                ps.setBoolean(5, false);

                int i = ps.executeUpdate(); 
                if (i < 1) {
                    return !isOk; 
                }
               
                ps1 = con.prepareStatement("INSERT INTO orderlineitem ( Quantity, product, OrderID) VALUES"
                        + "(?, ?, (SELECT MAX(ID)) FROM orders)");
                for (OrderLineItem o : order.getItems()) {
                    try { 
                        ps1.setInt(1, o.getQuantity()); 
                        
                        ps1.setString(3, o.getProduct());  
                        if (ps1.executeUpdate() < 1) { 
                            isOk = false;
                            break;
                        }
                    } catch (SQLException ex) {
                        System.out.println("Error : " + ex.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {

            try {
                if (con != null) { 
                    if (isOk) {
                        con.commit(); 
                    } else {
                        con.rollback(); 
                    }
                    con.setAutoCommit(true); 
                }
                if (ps != null) { 
                    ps.close();
                }
                if (ps1 != null) { 
                    ps1.close();
                }
            } catch (SQLException ex) { // don't print stack trace
                System.out.println("Error : " + ex.getMessage());

            }
        }

        return isOk;
    }
//////////////////***********METHOD SIX*************/////////////////////////////

    @Override
    public Order getOrder(Integer OrderId) {

        Order order = null;
        ArrayList<OrderLineItem> list = new ArrayList<OrderLineItem>();
        OrderLineItem item = null;
        try {
            if (con != null) {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, DeliveryDate, user, address, Payment  FROM orders WHERE ID=? AND Deleted=0");
                ps.setInt(1, OrderId);
                rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        try {

                            order = new Order();
                            //order has 9 Columns
                            /*#1*/
                            order.setId(OrderId); // primary key Int
                            /*#2*/
                            order.setTotalPrice(rs.getDouble("TotalPrice")); 
                            /*#3*/
                            order.setOrderDate(rs.getDate("OrderDate"));
                            /*#4*/
                            order.setDeliveryDate(rs.getDate("DeliveryDate")); 
                            /*#5*/
                            order.setUser(rs.getString("User")); 
                            /*#6*/
                            order.setAddress(rs.getString("Address")); 
                            /*#7*/
                            order.setPayment(rs.getString("Payment")); 

                        } catch (SQLException ex) {
                            System.out.println("Error at creating Order from database data : " + ex.getMessage());
                        }

                    }// end of while loop
                }
                if (con != null) {
                    ps1 = con.prepareStatement("SELECT Quantity, OrderID, Product FROM orderlineitem WHERE OrderID = ?");
                    ps1.setInt(1, order.getId());
                    rs1 = ps1.executeQuery(); 
                    if (rs1 != null) 
                    {
                        while (rs1.next()) {
                            try { 
                                item = new OrderLineItem();
                               
                                item.setQuantity(rs1.getInt("Quantity"));
                                item.setOrder(rs1.getInt("OrderID")); 
                                item.setProduct(rs1.getString("Product"));

                                list.add(item); 
                            } catch (SQLException ex) {
                                System.out.println("Error at : " + ex.getMessage());
                            }
                        }
                    }
                }
                order.setItems(list); 
            }
        } catch (SQLException ex) {
            System.out.println("Error at adding OrderLineItem : " + ex.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {

                }
            }
            try {

                if (ps1 != null) {
                    ps1.close();
                }
                if (rs1 != null) {
                    rs1.close();
                }

                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error : " + ex.getMessage());
            }
        }
        return order;

    }

    //////////////////***********METHOD TWO*************/////////////////////////////
    @Override
    public boolean setDeliveryDate(Integer orderId) {
        if (con != null) {
            int rows = 0;
            try {
                ps = con.prepareStatement("UPDATE orders set DeliveryDate = CURRENT_TIMESTAMP where ID = ?");
                ps.setInt(1, orderId);
                rows = ps.executeUpdate();
            } catch (SQLException ex) {
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) { System.out.println("Error at : "+ex.getMessage());
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) { System.out.println("Error at : "+ex.getMessage());
                    }
                }
            }
            return rows == 1; // only one update
        }
        return false;
    }

//    @Override
//    public boolean setDeliveryDate(Integer orderId, Date deliveryDate) {}            ///////// REPLACED
//        try {
//            if (con != null) {
//                ps = con.prepareStatement("UPDATE orders SET DeliveryDate = CURRENT_TIMESTAMP WHERE ID = ?");
//                ps.setInt(1, orderId);
//                ps.executeUpdate();
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error : " + ex.getMessage());
//        }
//        try {
//            ps = con.prepareStatement("UPDATE orders SET DeliveryDate = ? WHERE ID = ?");
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); // this is the same as NOW() in sql
//            String dateString = formatter.format(deliveryDate);
//
//            ps.setString(1, dateString); // is it possible to save as String ? SQL Date Error , make sure
//            ps.setInt(2, orderId);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            System.out.println("Error : " + ex.getMessage());
//        }
//        try {
//            if (ps != null) {
//                ps.close();
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error : " + ex.getMessage());
//        }
//        try {
//            if (con!= null) {
//                con.close();
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error : " + ex.getMessage());
//        }
//
//        return true;
//    }
    private ArrayList<OrderLineItem> getOrderLineItems(Integer orderId) {
        ArrayList<OrderLineItem> items = new ArrayList();
        if (con != null) {
            PreparedStatement ps1 = null;
            ResultSet rs1 = null;

            try {
                ps1 = con.prepareStatement("Select Product,Quantity from orderlineitem where ID = ?");
                ps1.setInt(1, orderId);
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    items.add(new OrderLineItem(rs1.getString("Product"), rs1.getInt("Quantity")));
                }
            } catch (SQLException ex) {

            } finally {
                if (ps1 != null) {
                    try {
                        ps1.close();
                    } catch (SQLException ex) {
                    }
                }
                if (rs1 != null) {
                    try {
                        rs1.close();
                    } catch (SQLException ex) {
                    }
                }

            }
            return items;
        }
        return items;
    }

    //////////////////***********METHOD THREE*************/////////////////////////////
    @Override
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> list = new ArrayList<>();
        Order order = null;

        try {
            if (con != null) {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, DeliveryDate, User, Address, Payment FROM orders ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs != null) {
                        try {

                            order = new Order();

                            ArrayList<OrderLineItem> items = getOrderLineItems(rs.getInt("ID"));
                            list.add(new Order(rs.getInt("ID"), items, rs.getDouble("TotalPrice"), rs.getString("DeliveryDate"),
                                    rs.getString("OrderDate"), rs.getString("User"),
                                    rs.getString("Address"), rs.getInt("Payment")));

                        } catch (SQLException ex) {
                            System.out.println("Error at : " + ex.getMessage());
                        }

                        list.add(order);

                    }
                }// end of while loop
            }
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        return list;
    }

    //////////////////***********METHOD FOUR*************/////////////////////////////
    @Override
    public ArrayList<Order> getPendingOrders() {
        ArrayList<Order> list = new ArrayList<Order>();
        Order order = null;
        try {
            if (con != null) {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, User, Address, Payment FROM orders WHERE DeliveryDate = null ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs != null) {
                        try {

                            order = new Order();

                            order.setId(rs.getInt("ID"));
                            order.setTotalPrice(rs.getDouble("TotalPrice"));
                            order.setOrderDate(rs.getDate("OrderDate"));
                            order.setDeliveryDate(null);
                            order.setUser(rs.getString("User"));
                            order.setAddress(rs.getString("Address"));
                            order.setPayment(rs.getString("Payment"));
                            if ((rs.getInt("Deleted")) != 1) {
                                order.setDeleted(Boolean.FALSE);
                            } else {
                                order.setDeleted(Boolean.TRUE);
                            }

                        } catch (SQLException ex) {
                            System.out.println("Error at : " + ex.getMessage());
                        }

                    }
                    if (order != null) {
                        list.add(order);
                    } else {
                        System.out.println("Order is null");
                    }
                }

            }// end of while loop

        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        }
        try {
            if (ps.isClosed() != true) {
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
            }
        }
        return list;
    }

    @Override
    public ArrayList<Order> getAllPlacedOrdersBetween(String dateFrom, String dateTo) {
        ArrayList<Order> orders = new ArrayList();
        if (con != null) {
            if (dateTo != null) {
                try {
                    ps = con.prepareStatement("Select ID,TotalPrice,DeliveryDate,OrderDate,User,Address,Payment from orders WHERE cast(OrderDate as DATE) BETWEEN ? AND ? ");
                    ps.setString(1, dateFrom);
                    ps.setString(2, dateTo);

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        ArrayList<OrderLineItem> items = getOrderLineItems(rs.getInt("ID"));
                        orders.add(new Order(rs.getInt("ID"), items, rs.getDouble("TotalPrice"), rs.getString("DeliveryDate"),
                                rs.getString("OrderDate"), rs.getString("User"),
                                rs.getString("Address"), rs.getInt("Payment")));
                    }

                } catch (SQLException ex) {
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                        }
                    }

                }
            } else {
                try {
                    ps = con.prepareStatement("Select ID,TotalPrice,DeliveryDate,OrderDate,User,Address,Payment from orders WHERE cast(OrderDate as DATE) = ?");
                    ps.setString(1, dateFrom);

                    rs = ps.executeQuery();
                    while (rs.next()) {
                        ArrayList<OrderLineItem> items = getOrderLineItems(rs.getInt("ID"));
                        orders.add(new Order(rs.getInt("ID"), items, rs.getDouble("TotalPrice"), rs.getString("DeliveryDate"),
                                rs.getString("OrderDate"), rs.getString("User"),
                                rs.getString("Address"), rs.getInt("Payment")));
                    }

                } catch (SQLException ex) {
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                        }
                    }
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException ex) {
                        }
                    }

                }

            }
        }
        return orders;
    }

    //////////////////***********METHOD FIVE*************/////////////////////////////
    @Override
    public ArrayList<Order> getOrdersBetween(Date deliveryDate, Date orderDate) {
        ArrayList<Order> list = new ArrayList<Order>();
        Order order = null;

        try {
            if (con != null) {
                ps = con.prepareStatement("SELECT ID, TotalPrice, OrderDate, DeliveryDate, User, Address, Payment, Deleted FROM orders WHERE NOT (OrderDate>? OR DeliveryDate<?)  "); // this was an online tip I read, check again (the where not between dates)

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String Ordered = formatter.format(orderDate);
                String Delivered = formatter.format(deliveryDate);
                ps.setString(1, Ordered);
                ps.setString(2, Delivered);
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs != null) {
                        try {
                            order = new Order();
                            //order has 9 Columns
                            /*#1*/
                            order.setId(rs.getInt("ID"));
                            /*#2*/
                            order.setTotalPrice(rs.getDouble("TotalPrice"));
                            /*#3*/
                            order.setOrderDate(rs.getDate("OrderDate"));
                            /*#4*/
                            order.setDeliveryDate(rs.getDate("DeliveryDate"));

                            /*#5*/
                            order.setUser(rs.getString("user"));

                            /*#6*/
                            order.setAddress(rs.getString("address"));

                            /*#7*/
                            order.setPayment(rs.getString("Payment"));

                        } catch (SQLException ex) {
                            System.out.println("Error at : " + ex.getMessage());
                        }

                        list.add(order);
                    }

                }// end of while loop
            }
        } catch (SQLException ex) {
            System.out.println("Error at : " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (!ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error  closing: " + ex.getMessage());
            }
        }
        return list;
    }

}
