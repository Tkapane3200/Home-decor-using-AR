package com.example.arproject1.Services;

import java.util.List;

import com.example.arproject1.Entities.OrderItems;

public interface OrderItemsServices {

    //OrderItems-Services-start
    //OrderItems-Services-addOrderItems
    //OrderItems-Services-getAllOrderItems
    //OrderItems-Services-getOrderItemsById
    //OrderItems-Services-updateOrderItems
    //OrderItems-Services-deleteOrderItems
    //OrderItems-Services-end

    boolean addOrderItems(long order_id, long product_id, int quantity, double price);
    List<OrderItems> getAllOrderItems();
    OrderItems getOrderItemsById(long id);
    boolean updateOrderItems(long id, long order_id, long product_id, int quantity, double price);
    boolean deleteOrderItems(long id);
    
}
