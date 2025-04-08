package com.example.arproject1.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.Services.UsersServices;
import com.example.arproject1.utils.Response;

@RestController
@RequestMapping("/users/login")
public class UsersLoginControllers {

    private final UsersServices usersServices;

    public UsersLoginControllers(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PostMapping()
    public Response loginUsers(@RequestBody UsersEntity usersEntity) {

        String email = usersEntity.getEmail();
        String password = usersEntity.getPassword();

        Response getUsersLoginInfo = usersServices.userLogin(email, password);
        boolean isUsersLogin = getUsersLoginInfo.isStatus();
        // Response isUsersLogin = usersServices.userLogin(email, password);

        // if (isUsersLogin) {
        // return List.of(new Response("Users Login Successfully", true));
        // } else {
        // return List.of(new Response("Failed to Login Users", false));
        // }

        if (isUsersLogin) {
            // return new Response("Users Login Successfully", true);
            return getUsersLoginInfo;
        } else {
            // return new Response("Failed to Login Users", false);
            return getUsersLoginInfo;
        }

    }

}
