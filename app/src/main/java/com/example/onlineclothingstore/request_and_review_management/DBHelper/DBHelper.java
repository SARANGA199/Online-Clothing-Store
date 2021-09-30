package com.example.onlineclothingstore.request_and_review_management.DBHelper;



public class DBHelper {
    private String type ="";
    private String size;
    private String quantity;
    private String description;

    public DBHelper(String type, String size, String quantity, String description) {
        this.type = type;
        this.size = size;
        this.quantity = quantity;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getQuantity() {
        return quantity;
    }
    public String getDescription() {
        return description;
    }
}