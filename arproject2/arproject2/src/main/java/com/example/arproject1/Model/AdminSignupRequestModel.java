package com.example.arproject1.Model;

public class AdminSignupRequestModel {

    private String name;
    private String email;
    private String password;

    public AdminSignupRequestModel() {
    }

    public AdminSignupRequestModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
