package com.example.onlineclothingstore.cart_and_order_management.Model;

public class AdminOrders {

    private String address,city,date,email,name,phone,time,totalAmount;

    public AdminOrders(){

    }

    public AdminOrders(String address, String city, String date, String email, String name, String phone, String time, String totalAmount) {
        this.address = address;
        this.city = city;
        this.date = date;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.time = time;
        this.totalAmount = totalAmount;
       // this.uID= uID;

    }

//    public String getuID() {
//        return uID;
//    }
//
//    public void setuID(String uID) {
//        this.uID = uID;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
