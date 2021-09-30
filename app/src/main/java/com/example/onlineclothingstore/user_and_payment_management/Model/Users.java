package com.example.onlineclothingstore.user_and_payment_management.Model;

public class Users {
    public String fullname,email,phone,address;
    public int isUser;

    public Users(){

    }

    public Users(String fullname,String email,String phone,String address,int isUser){
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

        return "09001168601438";
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
