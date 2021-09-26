package com.example.onlineclothingstore.item_and_category_management;

public class ItemModel {

    String ItemBrand;
    String ItemName;
    String ItemPrice;
    String image;

    public ItemModel() {
    }

    public ItemModel(String itemName, String itemPrice, String itemBrand, String image) {
        this.ItemBrand = itemBrand;
        this.ItemName = itemName;
        this.ItemPrice = itemPrice;
        this.image = image;
    }


    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemBrand() {
        return ItemBrand;
    }

    public void setItemBrand(String itemBrand) {
        ItemBrand = itemBrand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
