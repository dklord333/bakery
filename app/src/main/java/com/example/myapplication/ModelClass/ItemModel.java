package com.example.myapplication.ModelClass;

import java.io.Serializable;

public class ItemModel implements Serializable {

    public String itemName;
    public String price;
    public String stock;
    public String category;
    public String description;
    public String image;

    public ItemModel(String itemName, String price, String stock, String category, String description, String image) {
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
