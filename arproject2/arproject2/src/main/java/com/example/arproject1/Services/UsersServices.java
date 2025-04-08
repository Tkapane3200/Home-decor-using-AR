package com.example.arproject1.Services;

import java.util.List;


import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.utils.Response;

public interface UsersServices {

    /* 
    private long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
     */

    //Create User
    boolean createUser(String name, String email, String password, String address, String phone);
    boolean updateUser(long id, String name, String email, String password, String address, String phone);
    boolean deleteUser(long id);
    List<UsersEntity> getAllUser();
    UsersEntity getUserById(long id);
    UsersEntity getUserByEmail(String email);
    Response userLogin(String email, String password);
    // boolean userLogin(String email, String password);


    
    
}
