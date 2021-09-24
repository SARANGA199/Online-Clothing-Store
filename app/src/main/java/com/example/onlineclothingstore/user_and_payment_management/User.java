package com.example.onlineclothingstore.user_and_payment_management;

public class User {

    public String fullname,email,phone,address,isUser;

    public User(){

    }

    public User(String fullname,String email,String phone,String address,String isUser){
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isUser = isUser;
    }
}
