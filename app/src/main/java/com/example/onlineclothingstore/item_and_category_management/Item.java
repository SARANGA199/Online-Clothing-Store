package com.example.onlineclothingstore.item_and_category_management;

import android.net.Uri;

public class Item {


    private String ItemName, Idescription, Iprice, Iproduct, IdiscountPrice, image;
    //private Uri image;


    public Item() {

    }

    public Item(String itemName, String idescription, String iprice, String iproduct, String idiscountPrice, String image) {
        ItemName = itemName;
        Idescription = idescription;
        Iprice = iprice;
        Iproduct = iproduct;
        IdiscountPrice = idiscountPrice;
        this.image = image;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getIdescription() {
        return Idescription;
    }

    public void setIdescription(String idescription) {
        Idescription = idescription;
    }

    public String getIprice() {
        return Iprice;
    }

    public void setIprice(String iprice) {
        Iprice = iprice;
    }

    public String getIproduct() {
        return Iproduct;
    }

    public void setIproduct(String iproduct) {
        Iproduct = iproduct;
    }

    public String getIdiscountPrice() {
        return IdiscountPrice;
    }

    public void setIdiscountPrice(String idiscountPrice) {
        IdiscountPrice = idiscountPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
