package com.example.onlineclothingstore.item_and_category_management;

public class ModelCategory {
    //same spelling
    String id,category,Description;
   // long timestamp;


    //empty constructor
    public ModelCategory(){

        //empty
    }

    //parameterized constructor

    public ModelCategory(String id, String category,String Description) {
        this.id = id;
        this.category = category;
        this.Description= Description;
       // this.timestamp = timestamp;
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
