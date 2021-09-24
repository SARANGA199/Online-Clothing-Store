package com.example.onlineclothingstore.item_and_category_management;

public class CategoryModule {
    String category,Description,id;

    public CategoryModule(){}

    public CategoryModule(String category, String description, String id) {
        this.category = category;
        Description = description;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
