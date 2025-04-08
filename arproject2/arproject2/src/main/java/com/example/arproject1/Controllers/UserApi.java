package com.example.arproject1.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Entities.Orders;
import com.example.arproject1.Entities.Products;
import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.Model.OrderDetails;
import com.example.arproject1.Services.OrdersServices;
import com.example.arproject1.Services.ProductsServices;
import com.example.arproject1.Services.UsersServices;
import com.example.arproject1.utils.Response;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/users/api")
public class UserApi {

    private final ProductsServices productsServices;
    private final OrdersServices ordersServices;
    private final UsersServices usersServices;

    public UserApi(ProductsServices productsServices, OrdersServices ordersServices, UsersServices usersServices) {
        this.productsServices = productsServices;
        this.ordersServices = ordersServices;
        this.usersServices = usersServices;
    }

    // Url: http://localhost:8080/users/api/product/get-all
    @GetMapping("/product/get-all")
    public List<Products> getAllProducts() {

        List<Products> productsList = productsServices.getAllProduct();
        return productsList;

    }

    // Url: http://localhost:8080/users/api/order/add
    @PostMapping("/order/add")
    public Response addOrder(@RequestBody Orders orderModel) {

        // // get user id from orderModel
        // long user_id = orderModel.getUser_id();
        // System.out.println("User id: " + user_id);
        // double totalPriceList = 0;
        // for (int i = 0; i < orderModel.getOrderItems().size(); i++) {
        // // int product_id = orderModel.getCart().get(i).getProduct_id();
        // // int quantity = orderModel.getCart().get(i).getQuantity();
        // // double price = orderModel.getCart().get(i).getPrice();
        // int product_id = (int) orderModel.getOrderItems().get(i).getItem_id();
        // int quantity = (int) orderModel.getOrderItems().get(i).getQuantity();
        // double price = (double) orderModel.getOrderItems().get(i).getPrice();

        // double totalPrice = quantity * price;
        // totalPriceList += totalPrice;

        // System.out.println("Product id: " + product_id);
        // System.out.println("Quantity: " + quantity);
        // System.out.println("Price: " + price);

        // }
        // System.out.println("Total price: " + totalPriceList);

        // orderModel.getOrderItems().forEach((orderItem) -> {
        // orderItem.setOrderTable(orderModel);
        // });

        // // boolean result = ordersServices.addOrder(user_id, timestamp.toString(),
        // // "pending", totalPriceList);
        // boolean result = ordersServices.addOrder(orderModel.getUser_id(),
        // timestamp.toString(), "pending",
        // totalPriceList, orderModel.getOrderItems());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orderModel.setOrder_date(timestamp.toString());
        orderModel.setStatus("pending");
        orderModel.getOrderItems().forEach((orderItem) -> {
            orderItem.setOrderTable(orderModel);
        });

        Double total = 0.0;
        for (int i = 0; i < orderModel.getOrderItems().size(); i++) {
            int quantity = orderModel.getOrderItems().get(i).getQuantity();
            double price = orderModel.getOrderItems().get(i).getPrice();
            total += quantity * price;
        }
        orderModel.setTotal(total);

        boolean result = ordersServices.addOrder(orderModel);

        if (result) {
            return new Response("Order added successfully", true);
        } else {
            return new Response("Order not added", false);
        }

    }

    // Url: http://localhost:8080/users/api/order/get-all
    @GetMapping("/order/get-all")
    public List<Orders> getAllOrders() {

        List<Orders> ordersList = ordersServices.getAllOrders();
        return ordersList;

    }

    // Url: http://localhost:8080/users/api/order/get-by-user-id
    @PostMapping("/order/get-by-user-id")
    public List<Orders> getOrderByUserId(@RequestBody List<Orders> orderModel) {

        Orders orders = new Orders();
        for (int i = 0; i < orderModel.size(); i++) {
            System.out.println("User id: " + orderModel.get(i).getUser_id());
            orders.setUser_id(orderModel.get(i).getUser_id());
        }

        // orders.setUser_id(orderModel.getUser_id());

        System.out.println("User id: " + orders.getUser_id());

        List<Orders> ordersList = ordersServices.getOrderByUserId(orders.getUser_id());
        return ordersList;
        // return null;

    }

    // Url: http://localhost:8080/users/api/order/get-order-details-by-id
    // Url: http://localhost:8080/users/api/order/get-order-details-by-id?id=1
    @GetMapping("/order/get-order-details-by-id")
    public List<OrderDetails> getOrderDetailsById(@Param("id") long id) {

        List<OrderDetails> orderDetailsList = ordersServices.getOrderDetailsById(id);
        return orderDetailsList;
    }

    // User Profile
    // Url: http://localhost:8080/users/api/user/profile
    // Url: http://localhost:8080/users/api/user/profile?id=1
    @GetMapping("/user/profile")
    public UsersEntity getUserById(@Param("id") long id) {

        UsersEntity user = usersServices.getUserById(id);
        return user;
    }

    // User Profile update
    // Url: http://localhost:8080/users/api/user/profile/update
    @PostMapping("/user/profile/update")
    public Response updateUserProfile(@RequestBody UsersEntity user) {

        boolean result = usersServices.updateUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getAddress());

        if (result) {
            return new Response("User profile updated successfully", true);
        } else {
            return new Response("User profile not updated", false);
        }

    }

}
