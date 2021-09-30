package com.example.onlineclothingstore.item_and_category_management;

public class ItemModel {

    String idescription,idiscountPrice,image,iprice,iproduct,itemName;

    public ItemModel() {
    }

    public ItemModel(String idescription, String idiscountPrice, String image, String iprice, String iproduct, String itemName) {
        this.idescription = idescription;
        this.idiscountPrice = idiscountPrice;
        this.image = image;
        this.iprice = iprice;
        this.iproduct = iproduct;
        this.itemName = itemName;
    }

    public String getIdescription() {
        return idescription;
    }

    public void setIdescription(String idescription) {
        this.idescription = idescription;
    }

    public String getIdiscountPrice() {
        return idiscountPrice;
    }

    public void setIdiscountPrice(String idiscountPrice) {
        this.idiscountPrice = idiscountPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIprice() {
        return iprice;
    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }

    public String getIproduct() {
        return iproduct;
    }

    public void setIproduct(String iproduct) {
        this.iproduct = iproduct;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
