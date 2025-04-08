package com.example.arproject1.Services;

import java.util.List;



import com.example.arproject1.Entities.Orders;
import com.example.arproject1.Model.OrderDetails;
import com.example.arproject1.Model.OrdersAdminDetails;

public interface OrdersServices {

    // Orders-Services-start
    // Orders-Services-addOrder
    // Orders-Services-getAllOrders
    // Orders-Services-getOrderById
    // Orders-Services-updateOrder
    // Orders-Services-deleteOrder
    // Orders-Services-end

    // boolean addOrder(long user_id, String order_date, String status, double
    // total, List<OrderItems> orderItems);
    boolean addOrder(Orders orderModel);

    List<Orders> getAllOrders();

    Orders getOrderById(long id);

    boolean updateOrder(long id, long user_id, String order_date, String status, double total);

    boolean deleteOrder(long id);

    List<Orders> getOrderByUserId(long user_id);

    List<OrderDetails> getOrderDetailsById(long id);

    // Admin
    List<OrdersAdminDetails> getAllOrdersAdmin();

    OrdersAdminDetails getOrderAdminById(long id);

    boolean updateOrderStatus(long id, String status);

}
