package com.example.onlineclothingstore.user_and_payment_management;

public class User {


    public String fullname,email,phone,address;
    public int isUser;


    public User(){

    }

    public User(String fullname,String email,String phone,String address,int isUser){
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isUser = isUser;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }
}
